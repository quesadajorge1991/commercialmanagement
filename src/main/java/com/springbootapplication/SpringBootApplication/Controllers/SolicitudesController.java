package com.springbootapplication.SpringBootApplication.Controllers;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootapplication.SpringBootApplication.Entity.*;
import com.springbootapplication.SpringBootApplication.Services.MunicipioService;
import com.springbootapplication.SpringBootApplication.Services.ProvinciaService;
import com.springbootapplication.SpringBootApplication.Services.PuebloService;
import com.springbootapplication.SpringBootApplication.Services.SolicitudService;

@Controller
public class SolicitudesController {

	@Autowired
	SolicitudService solicitudService;

	@Autowired
	ProvinciaService provinciaService;

	@Autowired
	MunicipioService municipioService;

	@Autowired
	PuebloService puebloService;

	public List<String> getYears() {
		List<String> years = new ArrayList<String>();
		for (int i = 2000; i <= 3000; i++) {
			years.add(String.valueOf(i));
		}
		return years;
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','METEOROLOGIA','PRONOSTICO','ESTACIONES')")
	@GetMapping({ "/listsolicitudes", "/home" })
	public String listsolicitudes(Model model) {

		model.addAttribute("years", getYears());
		model.addAttribute("listsolicitudes", solicitudService.findAll());
		model.addAttribute("s_sin_servicio", solicitudService.getSolicitudSinServicio());
		model.addAttribute("s_con_servicio", solicitudService.getSolicitudConServicio());

		return "Solicitudes/listsolicitudes";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@GetMapping("/addsolicitud")
	public String addsolicitud(Model model) {
		model.addAttribute("solicitud", new Solicitud());
		model.addAttribute("provincias", provinciaService.findAll());
		model.addAttribute("municipios", provinciaService.findAll());
		model.addAttribute("pueblos", puebloService.findAll());
		return "Solicitudes/addsolicitud";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@PostMapping("/addSolicitud")
	public String addProveedor(@ModelAttribute("solicitud") @Valid Solicitud solicitud, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("provincias", provinciaService.findAll());

			System.out.println("NOMBRE " + solicitud.getNombPers());
			System.out.println("CI " + solicitud.getCi());
			System.out.println("DIRECCCION " + solicitud.getDireccion());
			System.out.println("TELEFONO " + solicitud.getTelefono());
			System.out.println("CULTIVO DAÑADO " + solicitud.getCultDanado());
			System.out.println("TIPO DE AFECTACION " + solicitud.getTipoAfect());
			System.out.println("FECHA " + solicitud.getFecha());
			System.out.println("ZONA AFECTADA " + solicitud.getZonaAfect());
			System.out.println("PUEBLO " + solicitud.getPueblo().getId());
			return "Solicitudes/addsolicitud";

		} else {
			try {
				/*
				 * String tempfesus = String.valueOf(solicitud.getFecha().getYear()) + "-" +
				 * String.valueOf(solicitud.getFecha().getMonth()) + "-" +
				 * String.valueOf(solicitud.getFecha().getDate()); Date fechasuscripcion = new
				 * SimpleDateFormat("yy-MM-dd").parse(tempfesus);
				 */

				solicitudService.save(new Solicitud(solicitud.getNombPers(), solicitud.getCi(),
						solicitud.getDireccion(), solicitud.getTelefono(), solicitud.getCultDanado(),
						solicitud.getTipoAfect(), solicitud.getFecha(), solicitud.getFechafin(),
						solicitud.getZonaAfect(), new Pueblo(solicitud.getPueblo().getId())));

				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
				redirectAttributes.addFlashAttribute("msgbody", "Se ha agregado satisfactoriamente el proveedor ");
				return "redirect:/listsolicitudes";

			} catch (Exception e) {
				System.out.println(e.getMessage());
				redirectAttributes.addFlashAttribute("msgtipo", "error");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");
				redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el proveedor ");
			}
		}

		return "Solicitudes/addsolicitud";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@GetMapping("/updateSolicitud/{id}")
	public String updatesolicitudes(@PathVariable int id, Model model) {
		/* Eliminar el objeto provincia y añadirlo en la primera posicion de la lista */
		/*
		 * se selecciona un objeto de provincia municipio y pueblo y se elminina de la
		 * lista y luego se añade en la primera posicion
		 */
		Pueblo temppueblo = solicitudService.findById(id).getPueblo();
		Municipio tempmun = temppueblo.getMunicipio();
		Provincia tempprov = tempmun.getProvincia();

		List<Provincia> listaprovin = (List<Provincia>) provinciaService.findAll();
		listaprovin.remove(tempprov);
		listaprovin.add(0, tempprov);

		List<Municipio> listMunicipio = (List<Municipio>) municipioService
				.findMunicipiosByProvincia(listaprovin.get(0).getId());
		listMunicipio.remove(tempmun);
		listMunicipio.add(0, tempmun);

		List<Pueblo> listpueblo = (List<Pueblo>) puebloService.getPueblosByMunicipios(listMunicipio.get(0).getId());
		listpueblo.remove(temppueblo);
		listpueblo.add(0, temppueblo);

		model.addAttribute("solicitud", solicitudService.findById(id));
		model.addAttribute("provincias", listaprovin);
		model.addAttribute("municipios", listMunicipio);
		model.addAttribute("pueblos", listpueblo);

		return "Solicitudes/updatesolicitud";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/deletesolicitud/{id}")
	public String deletesolicitud(@PathVariable int id, RedirectAttributes redirectAttributes) {
		try {
			solicitudService.deleteById(id);
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
			redirectAttributes.addFlashAttribute("msgbody", "Se ha eliminado satisfactoriamente la solicitud ");
		} catch (Exception e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eliminar la solicitud ");
		}
		return "redirect:/listsolicitudes";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@PostMapping("/updateSolicitudd")
	public String updateSolicitudd(@ModelAttribute("solicitud") @Valid Solicitud solicitud, Model model,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("provincias", provinciaService.findAll());
			return "Solicitudes/updatesolicitud";
		} else {
			try {
				/*
				 * String tempfesus = String.valueOf(solicitud.getFecha().getDate()) + "-" +
				 * String.valueOf(solicitud.getFecha().getMonth()) + "-" +
				 * String.valueOf(solicitud.getFecha().getYear() + 1900); Date fechasuscripcion
				 * = new SimpleDateFormat("dd-MM-yyyy").parse(tempfesus);
				 * System.out.println("sfsdfsdfsdf " + fechasuscripcion);
				 */

				solicitudService.save(new Solicitud(solicitud.getId(), solicitud.getNombPers(), solicitud.getCi(),
						solicitud.getDireccion(), solicitud.getTelefono(), solicitud.getCultDanado(),
						solicitud.getTipoAfect(), solicitud.getFecha(), solicitud.getFechafin(),
						solicitud.getZonaAfect(), new Pueblo(solicitud.getPueblo().getId())));

				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
				redirectAttributes.addFlashAttribute("msgbody", "Se ha modificado satisfactoriamente la solicitud ");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				redirectAttributes.addFlashAttribute("msgtipo", "error");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");
				redirectAttributes.addFlashAttribute("msgbody", "Error al modificar la solicitud " + e.getMessage());
			}
		}

		return "redirect:/listsolicitudes";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/deleteSolicitud/{id}")
	public String deleteProveedor(@PathVariable int id, RedirectAttributes redirectAttributes) {
		try {
			solicitudService.deleteById(id);

			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
			redirectAttributes.addFlashAttribute("msgbody", "Se ha eliminado satisfactoriamente la solicitud ");

		} catch (Exception e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eliminar la solicitud ");
		}

		return "redirect:/listsolicitudes";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@GetMapping(value = "/datosSolicitud")
	public @ResponseBody Solicitud getSolicitud(@RequestParam("ids") int ids, Model model) throws Exception {
		Solicitud resultsolicitud = new Solicitud();
		Solicitud solicitud = solicitudService.findById(ids);

		Municipio municipio = municipioService.findById(solicitud.getPueblo().getMunicipio().getId());

		Pueblo pueblo = new Pueblo(solicitud.getPueblo().getId(), solicitud.getPueblo().getNomb_pueb(), municipio);

		resultsolicitud = new Solicitud(solicitud.getId(), solicitud.getNombPers(), solicitud.getCi(),
				solicitud.getDireccion(), solicitud.getTelefono(), solicitud.getCultDanado(), solicitud.getTipoAfect(),
				solicitud.getFecha(), solicitud.getFechafin(), solicitud.getZonaAfect(), solicitud.isServicio(),
				pueblo);

		return resultsolicitud;
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@GetMapping("/getprovincia")
	public @ResponseBody List<Provincia> getprovincia(@RequestParam("ids") int ids, Model model) {
		List<Provincia> listaprovin = new ArrayList<>();
		try {
			Solicitud solicitud = solicitudService.findById(ids);
			Municipio municipio = puebloService.findById(solicitud.getPueblo().getMunicipio().getId()).getMunicipio();

			Pueblo pueblo = new Pueblo(solicitud.getPueblo().getId(), solicitud.getPueblo().getNomb_pueb(), municipio);
			/*
			 * Solicitud resultsolicitud = new Solicitud(solicitud.getId(),
			 * solicitud.getNomb_pers(), solicitud.getCi(), solicitud.getDireccion(),
			 * solicitud.getTelefono(), solicitud.getCult_danado(),
			 * solicitud.getTipo_afect(), solicitud.getFecha(), solicitud.getFechafin(),
			 * solicitud.getZona_afect(), solicitud.isServicio(), pueblo);
			 */

			Municipio tempmun = pueblo.getMunicipio();
			Provincia tempprov = tempmun.getProvincia();

			listaprovin = (List<Provincia>) provinciaService.findAll();
			listaprovin.remove(tempprov);
			listaprovin.add(0, tempprov);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return listaprovin;

	}

	// @PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@GetMapping("/filterByYear")
	public String filterByYear(@RequestParam("year") int year, Model model) {

		model.addAttribute("solicitud", new Solicitud());
		model.addAttribute("provincias", provinciaService.findAll());
		model.addAttribute("municipios", municipioService.findAll());
		model.addAttribute("pueblos", puebloService.findAll());
		return "Solicitudes/addsolicitud";
	}

}
