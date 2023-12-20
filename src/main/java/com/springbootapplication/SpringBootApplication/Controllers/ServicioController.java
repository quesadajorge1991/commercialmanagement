package com.springbootapplication.SpringBootApplication.Controllers;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootapplication.SpringBootApplication.Entity.*;
import com.springbootapplication.SpringBootApplication.Services.ClientesService;
import com.springbootapplication.SpringBootApplication.Services.GrupoService;
import com.springbootapplication.SpringBootApplication.Services.MunicipioService;
import com.springbootapplication.SpringBootApplication.Services.ProvinciaService;
import com.springbootapplication.SpringBootApplication.Services.PuebloService;
import com.springbootapplication.SpringBootApplication.Services.ServicioService;
import com.springbootapplication.SpringBootApplication.Services.SolicitudService;

@Controller
public class ServicioController {

	@Autowired
	ProvinciaService provinciaService;

	@Autowired
	SolicitudService solicitudService;

	@Autowired
	GrupoService grupoService;

	@Autowired
	ClientesService clientesService;

	@Autowired
	ServicioService servicioService;

	@Autowired
	MunicipioService municipioService;

	@Autowired
	PuebloService puebloService;

	private int numero;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ECONOMIA')")
	@GetMapping("/listservicios")
	public String listservicios(Model model) {
		model.addAttribute("servicios", servicioService.findAll());
		return "Servicios/listservicios";

	}

	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/addServicioId/{id}")
	public String addServicioId(Model model, @PathVariable(value = "id") int id) {
		// ModelAndView mav = new ModelAndView();
		List<Provincia> listprovincias = (List<Provincia>) provinciaService.findAll();
		Solicitud solicitud = solicitudService.findById(id);
		Provincia provincia = solicitud.getPueblo().getMunicipio().getProvincia();
		setNumero(solicitud.getId());

		setId(id);

		List<Provincia> listprovtemp = listprovincias;
		listprovtemp.remove(provincia);
		listprovtemp.add(0, provincia);

		model.addAttribute("servicio", new Servicio(solicitud.getCultDanado(), solicitud.getTipoAfect(),
				solicitud.getFecha(), solicitud.getFecha(), solicitud.getZonaAfect()));
		model.addAttribute("provincias", listprovtemp);
		model.addAttribute("municipio", solicitud.getPueblo().getMunicipio());
		model.addAttribute("pueblo", solicitud.getPueblo());
		model.addAttribute("listclientes", clientesService.findAll());
		model.addAttribute("listgrupos", grupoService.findAll());

		model.addAttribute("nroContrato", solicitud.getPueblo());

		return "Servicios/addServicioId";
	}

	/*
	 * @GetMapping("/getPueblo") public @ResponseBody Pueblo Pueblo(@){ List<Pueblo>
	 * pueblos =
	 * pdao.getPuebloxMunicipio(solicitud.getPueblo().getMunicipio().getId());
	 * 
	 * 
	 * }
	 */

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping(value = "/add_Servicio_Id")
	public String add_Id(@ModelAttribute("servicio") @Valid Servicio servicio, Model model, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		Pueblo p = new Pueblo(servicio.getPueblo().getId());
		Grupo g = new Grupo(servicio.getGrupo().getName());
		Cliente clientetemp = clientesService.findById(servicio.getCliente().getNro());
		Cliente c = new Cliente(clientetemp.getNroContrato());
		Solicitud sol = solicitudService.findById(getNumero()); /* sdao.getSolicitudxNro(getNumero()).get(0); */
		sol.setServicio(true);

		if (bindingResult.hasErrors()) {
			List<Provincia> listprovincias = (List<Provincia>) provinciaService.findAll();
			Solicitud solicitud = solicitudService.findById(getId());
			Provincia provincia = solicitud.getPueblo().getMunicipio().getProvincia();
			setNumero(solicitud.getId());

			setId(id);

			List<Provincia> listprovtemp = listprovincias;
			listprovtemp.remove(provincia);
			listprovtemp.add(0, provincia);

			model.addAttribute("servicio", new Servicio(solicitud.getCultDanado(), solicitud.getTipoAfect(),
					solicitud.getFecha(), solicitud.getFecha(), solicitud.getZonaAfect()));
			model.addAttribute("provincias", listprovtemp);
			model.addAttribute("municipio", solicitud.getPueblo().getMunicipio());
			model.addAttribute("pueblo", solicitud.getPueblo());

			model.addAttribute("listclientes", clientesService.findAll());
			model.addAttribute("grupos", grupoService.findAll());
			model.addAttribute("nroContrato", solicitud.getPueblo());

			return "Servicios/addServicioId";

		} else {

			try {
				Servicio s = new Servicio(servicio.getFechaServicio(), servicio.getCultivo(),
						servicio.getTipoAfectacion(), servicio.getFechaAfectacion(), servicio.getFechaAfectacionfin(),
						servicio.getNroFactura(), servicio.getZonaAfectacion(), servicio.getEntregado(), p, c, g);

				System.out.println("ffff " + servicio.getFechaServicio());
				System.out.println("ffff " + servicio.getCultivo());
				System.out.println("ffff " + servicio.getTipoAfectacion());
				System.out.println("ffff " + servicio.getFechaAfectacion());
				System.out.println("ffff " + servicio.getNroFactura());
				System.out.println("ffff " + servicio.getZonaAfectacion());
				System.out.println("ffff " + servicio.getEntregado());

				// servicioRepository.save(new Servicio(servicio.getCultivo(),
				// servicio.getTipo_afectacion(), servicio.getFecha_afectacion(),
				// servicio.getZona_afectacion()));
				servicioService.save(new Servicio(servicio.getFechaServicio(), servicio.getCultivo(),
						servicio.getTipoAfectacion(), servicio.getFechaAfectacion(), servicio.getFechaAfectacionfin(),
						servicio.getNroFactura(), servicio.getZonaAfectacion(), servicio.getEntregado(), p, clientetemp,
						g));

				redirectAttributes.addFlashAttribute("msgbody",
						"Se ha insertado correctamente los datos del servicio ");
				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");

				return "redirect:/listservicios";
			} catch (Exception e) {

				redirectAttributes.addFlashAttribute("msgbody", "Error al insertar " + e.getMessage());
				redirectAttributes.addFlashAttribute("msgtipo", "error");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");

			}

		}

		return "Servicios/addServicioId";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/addServicio")
	public String addServicio(Model model) {
		List<Provincia> provincias = (List<Provincia>) provinciaService.findAll();
		List<Solicitud> solicitudes = (List<Solicitud>) solicitudService.getSolicitudSinServicio();
		List<Cliente> clientes = (List<Cliente>) clientesService.findAll();
		List<Grupo> grupos = (List<Grupo>) grupoService.findAll();

		model.addAttribute("listsolicitudes", solicitudes);
		model.addAttribute("provincias", provincias);
		model.addAttribute("listclientes", clientes);
		model.addAttribute("listgrupos", grupos);

		model.addAttribute("servicio", new Servicio());
		model.addAttribute("servicio", new Servicio());

		return "Servicios/addServicio";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping(value = "/add_Servicio")
	public String add(@ModelAttribute("servicio") Servicio servicio, @RequestParam(value = "solicitud") int solicitud,
			RedirectAttributes redirectAttributes) {

		Pueblo p = new Pueblo(servicio.getPueblo().getId());
		Grupo g = new Grupo(servicio.getGrupo().getName());
		Cliente c = new Cliente(servicio.getCliente().getNro());

		try {
			Solicitud sol = solicitudService.findById(solicitud);
			Solicitud soli = new Solicitud(sol.getId(), sol.getNombPers(), sol.getCi(), sol.getDireccion(),
					sol.getTelefono(), sol.getCultDanado(), sol.getTipoAfect(), sol.getFecha(), sol.getFechafin(),
					sol.getZonaAfect(), true, sol.getPueblo());

			sol.setServicio(true);

			Servicio s = new Servicio(servicio.getFechaServicio(), servicio.getCultivo(), servicio.getTipoAfectacion(),
					servicio.getFechaAfectacion(), servicio.getFechaAfectacionfin(), servicio.getNroFactura(),
					servicio.getZonaAfectacion(), servicio.getEntregado(), new Pueblo(p.getId()),
					new Cliente(c.getNro()), new Grupo(g.getName()));

			System.out.println("solicitud " + solicitud);
			System.out.println("ffffff " + servicio.getFechaServicio());
			System.out.println("ffffff " + servicio.getCultivo());
			System.out.println("ffffff " + servicio.getTipoAfectacion());
			System.out.println("ffffff afecatacion " + servicio.getFechaAfectacion());
			System.out.println("ffffffinnnnn " + servicio.getFechaAfectacionfin());

			System.out.println("ffffff " + servicio.getNroFactura());
			System.out.println("ffffff " + servicio.getZonaAfectacion());
			System.out.println("ffffff " + servicio.getEntregado());
			System.out.println("ffffff " + p.getId());
			System.out.println("ffffff " + c.getNro());
			System.out.println("ffffff " + g.getName());

			servicioService.save(s);
			solicitudService.save(soli);
			redirectAttributes.addFlashAttribute("msgbody", "Se ha insertado correctamente los datos del servicio ");
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
			return "redirect:/listservicios";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgbody", "Error al insertar " + e.getMessage());
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
		}

		return "Servicios/addServicio";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping(value = "/delete_servicio/{nro}")
	public String delete(@PathVariable(value = "nro") int nro, RedirectAttributes redirectAttributes) {
		try {
			Servicio servicio = servicioService.findById(nro);

			servicioService.deleteservicio(nro);
			redirectAttributes.addFlashAttribute("msgbody", "Se ha eliminado correctamente los datos del servicio ");
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");

			return "redirect:/listservicios";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgbody", "Se ha eliminado correctamente los datos del servicio ");
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
		}
		return "redirect:/listservicios";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping(value = "/updateServicio/{nro}")
	public String update(Model model, @PathVariable(value = "nro") int nro) {

		Servicio servicios = servicioService.findById(nro);
		List<Provincia> provincias = (List<Provincia>) provinciaService.findAll();

		setNumero(servicios.getNro());
		List<Cliente> clientes = (List<Cliente>) clientesService.findAll();
		List<Grupo> grupos = (List<Grupo>) grupoService.findAll();

		List<Municipio> listMunicipio = (List<Municipio>) municipioService.findAll();
		listMunicipio.remove(servicios.getPueblo().getMunicipio());
		listMunicipio.add(0, servicios.getPueblo().getMunicipio());

		List<Pueblo> listpueblo = (List<Pueblo>) puebloService.findAll();
		listpueblo.remove(servicios.getPueblo());
		listpueblo.add(0, servicios.getPueblo());

		provincias.remove(servicios.getPueblo().getMunicipio().getProvincia());
		provincias.add(0, servicios.getPueblo().getMunicipio().getProvincia());

		model.addAttribute("servicios", new Servicio());
		model.addAttribute("clientes", clientes);
		model.addAttribute("fecha_servicio", servicios.getFechaServicio());
		model.addAttribute("grupos", grupos);
		model.addAttribute("serviciotoupdate", servicios);
		model.addAttribute("provincias", provincias);
		model.addAttribute("municipios", listMunicipio);
		model.addAttribute("pueblos", listpueblo);
		model.addAttribute(null, listpueblo);
		model.addAttribute(null, listpueblo);
		model.addAttribute(null, listpueblo);

		return "Servicios/updateServicio";
	}

	@PostMapping(value = "/update_Servicio")
	public String update_Servicio(@ModelAttribute("serviciotoupdate") Servicio servicio,
			RedirectAttributes redirectAttributes) {

		try {
			Servicio s = new Servicio(servicio.getNro(), servicio.getFechaServicio(), servicio.getCultivo(),
					servicio.getTipoAfectacion(), servicio.getFechaAfectacion(), servicio.getFechaAfectacionfin(),
					servicio.getNroFactura(), servicio.getZonaAfectacion(), servicio.getEntregado(),
					new Pueblo(servicio.getPueblo().getId()), new Cliente(servicio.getCliente().getNro()),
					new Grupo(servicio.getGrupo().getName()));

			servicioService.save(s);
			redirectAttributes.addFlashAttribute("msgbody", "Se modificado correctamente el servicio");
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
			return "redirect:/listservicios";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("mensaje", "Error al modificar el servicio");
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
		}

		return "redirect:/listservicios";
	}

}
