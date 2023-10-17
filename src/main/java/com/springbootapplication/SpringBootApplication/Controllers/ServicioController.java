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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootapplication.SpringBootApplication.Entity.*;
import com.springbootapplication.SpringBootApplication.Repository.*;

@Controller
public class ServicioController {

	@Autowired
	ProvinciaRepository provinciaRepository;

	@Autowired
	SolicitudRepository solicitudRepository;

	@Autowired
	GrupoRepository grupoRepository;

	@Autowired
	ClientesRepository clientesRepository;

	@Autowired
	ServicioRepository servicioRepository;

	@Autowired
	MunicipioRepository municipioRepository;

	@Autowired
	PuebloRepository puebloRepository;

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
		model.addAttribute("servicios", servicioRepository.findAll());
		model.addAttribute("servicios", servicioRepository.findAll());
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
		List<Provincia> listprovincias = (List<Provincia>) provinciaRepository.findAll();
		Solicitud solicitud = solicitudRepository.findById(id).get();
		Provincia provincia = solicitud.getPueblo().getMunicipio().getProvincia();
		setNumero(solicitud.getId());

		setId(id);

		List<Provincia> listprovtemp = listprovincias;
		listprovtemp.remove(provincia);
		listprovtemp.add(0, provincia);

		model.addAttribute("servicio", new Servicio(solicitud.getCult_danado(), solicitud.getTipo_afect(),
				solicitud.getFecha(), solicitud.getFecha(), solicitud.getZona_afect()));
		model.addAttribute("provincias", listprovtemp);
		model.addAttribute("municipio", solicitud.getPueblo().getMunicipio());
		model.addAttribute("pueblo", solicitud.getPueblo());
		model.addAttribute("listclientes", clientesRepository.findAll());
		model.addAttribute("grupos", grupoRepository.findAll());

		model.addAttribute("nroContrato", solicitud.getPueblo());
		model.addAttribute("grupos", grupoRepository.findAll());
		model.addAttribute("grupos", grupoRepository.findAll());
		model.addAttribute("grupos", grupoRepository.findAll());

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
		Cliente clientetemp = clientesRepository.findById(servicio.getCliente().getNro()).get();
		Cliente c = new Cliente(clientetemp.getNroContrato());
		Solicitud sol = solicitudRepository.findById(getNumero())
				.get(); /* sdao.getSolicitudxNro(getNumero()).get(0); */
		sol.setServicio(true);

		if (bindingResult.hasErrors()) {
			List<Provincia> listprovincias = (List<Provincia>) provinciaRepository.findAll();
			Solicitud solicitud = solicitudRepository.findById(getId()).get();
			Provincia provincia = solicitud.getPueblo().getMunicipio().getProvincia();
			setNumero(solicitud.getId());

			setId(id);

			List<Provincia> listprovtemp = listprovincias;
			listprovtemp.remove(provincia);
			listprovtemp.add(0, provincia);

			model.addAttribute("servicio", new Servicio(solicitud.getCult_danado(), solicitud.getTipo_afect(),
					solicitud.getFecha(), solicitud.getFecha(), solicitud.getZona_afect()));
			model.addAttribute("provincias", listprovtemp);
			model.addAttribute("municipio", solicitud.getPueblo().getMunicipio());
			model.addAttribute("pueblo", solicitud.getPueblo());

			model.addAttribute("listclientes", clientesRepository.findAll());
			model.addAttribute("grupos", grupoRepository.findAll());
			model.addAttribute("nroContrato", solicitud.getPueblo());

			return "Servicios/addServicioId";

		} else {

			try {
				Servicio s = new Servicio(servicio.getFecha_servicio(), servicio.getCultivo(),
						servicio.getTipo_afectacion(), servicio.getFecha_afectacion(),
						servicio.getFecha_afectacionfin(), servicio.getNro_factura(), servicio.getZona_afectacion(),
						servicio.getEntregado(), p, c, g);

				System.out.println("ffff " + servicio.getFecha_servicio());
				System.out.println("ffff " + servicio.getCultivo());
				System.out.println("ffff " + servicio.getTipo_afectacion());
				System.out.println("ffff " + servicio.getFecha_afectacion());
				System.out.println("ffff " + servicio.getNro_factura());
				System.out.println("ffff " + servicio.getZona_afectacion());
				System.out.println("ffff " + servicio.getEntregado());

				// servicioRepository.save(new Servicio(servicio.getCultivo(),
				// servicio.getTipo_afectacion(), servicio.getFecha_afectacion(),
				// servicio.getZona_afectacion()));
				servicioRepository.save(new Servicio(servicio.getFecha_servicio(), servicio.getCultivo(),
						servicio.getTipo_afectacion(), servicio.getFecha_afectacion(),
						servicio.getFecha_afectacionfin(), servicio.getNro_factura(), servicio.getZona_afectacion(),
						servicio.getEntregado(), p, clientetemp, g));

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
		List<Provincia> provincias = (List<Provincia>) provinciaRepository.findAll();
		List<Solicitud> solicitudes = (List<Solicitud>) solicitudRepository.getSolicitudSinServicio();
		List<Cliente> clientes = (List<Cliente>) clientesRepository.findAll();
		List<Grupo> grupos = (List<Grupo>) grupoRepository.findAll();

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
			Solicitud sol = solicitudRepository.findById(solicitud).get();
			Solicitud soli = new Solicitud(sol.getId(), sol.getNomb_pers(), sol.getCi(), sol.getDireccion(),
					sol.getTelefono(), sol.getCult_danado(), sol.getTipo_afect(), sol.getFecha(), sol.getFechafin(),
					sol.getZona_afect(), true, sol.getPueblo());

			sol.setServicio(true);

			Servicio s = new Servicio(servicio.getFecha_servicio(), servicio.getCultivo(),
					servicio.getTipo_afectacion(), servicio.getFecha_afectacion(), servicio.getFecha_afectacionfin(),
					servicio.getNro_factura(), servicio.getZona_afectacion(), servicio.getEntregado(),
					new Pueblo(p.getId()), new Cliente(c.getNro()), new Grupo(g.getName()));

			System.out.println("solicitud " + solicitud);
			System.out.println("ffffff " + servicio.getFecha_servicio());
			System.out.println("ffffff " + servicio.getCultivo());
			System.out.println("ffffff " + servicio.getTipo_afectacion());
			System.out.println("ffffff afecatacion " + servicio.getFecha_afectacion());
			System.out.println("ffffffinnnnn " + servicio.getFecha_afectacionfin());

			System.out.println("ffffff " + servicio.getNro_factura());
			System.out.println("ffffff " + servicio.getZona_afectacion());
			System.out.println("ffffff " + servicio.getEntregado());
			System.out.println("ffffff " + p.getId());
			System.out.println("ffffff " + c.getNro());
			System.out.println("ffffff " + g.getName());

			servicioRepository.save(s);
			solicitudRepository.save(soli);
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
			Servicio servicio = servicioRepository.findById(nro).get();

			servicioRepository.deleteservicio(nro);
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

		Servicio servicios = servicioRepository.findById(nro).get();
		List<Provincia> provincias = (List<Provincia>) provinciaRepository.findAll();

		setNumero(servicios.getNro());
		List<Cliente> clientes = (List<Cliente>) clientesRepository.findAll();
		List<Grupo> grupos = (List<Grupo>) grupoRepository.findAll();

		List<Municipio> listMunicipio = (List<Municipio>) municipioRepository.findAll();
		listMunicipio.remove(servicios.getPueblo().getMunicipio());
		listMunicipio.add(0, servicios.getPueblo().getMunicipio());

		List<Pueblo> listpueblo = (List<Pueblo>) puebloRepository.findAll();
		listpueblo.remove(servicios.getPueblo());
		listpueblo.add(0, servicios.getPueblo());

		provincias.remove(servicios.getPueblo().getMunicipio().getProvincia());
		provincias.add(0, servicios.getPueblo().getMunicipio().getProvincia());

		model.addAttribute("servicios", new Servicio());
		model.addAttribute("clientes", clientes);
		model.addAttribute("fecha_servicio", servicios.getFecha_servicio());
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
			Servicio s = new Servicio(servicio.getNro(), servicio.getFecha_servicio(), servicio.getCultivo(),
					servicio.getTipo_afectacion(), servicio.getFecha_afectacion(), servicio.getFecha_afectacionfin(),
					servicio.getNro_factura(), servicio.getZona_afectacion(), servicio.getEntregado(),
					new Pueblo(servicio.getPueblo().getId()), new Cliente(servicio.getCliente().getNro()),
					new Grupo(servicio.getGrupo().getName()));

			servicioRepository.save(s);
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
