package com.springbootapplication.SpringBootApplication.Controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootapplication.SpringBootApplication.Entity.Cliente;
import com.springbootapplication.SpringBootApplication.Entity.Municipio;
import com.springbootapplication.SpringBootApplication.Entity.Provincia;
import com.springbootapplication.SpringBootApplication.Entity.Pueblo;
import com.springbootapplication.SpringBootApplication.Entity.Solicitud;
import com.springbootapplication.SpringBootApplication.Repository.ServicioRepository;
import com.springbootapplication.SpringBootApplication.Repository.SolicitudRepository;
import com.springbootapplication.SpringBootApplication.Services.ClientesService;
import com.springbootapplication.SpringBootApplication.Services.MunicipioService;
import com.springbootapplication.SpringBootApplication.Services.ProvinciaService;
import com.springbootapplication.SpringBootApplication.Services.PuebloService;

@Controller
public class ClientesController {

	@Autowired
	ClientesService clientesService;

	@Autowired
	ProvinciaService provinciaService;

	@Autowired
	SolicitudRepository solicitudRepository;

	@Autowired
	MunicipioService municipioService;

	@Autowired
	PuebloService puebloService;

	@Autowired
	ServicioRepository servicioRepository;

	private int numero;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ECONOMIA')")
	@GetMapping("/listclientes")
	public String listclientes(Model model) {
		model.addAttribute("clientes", clientesService.findAll());
		return "Clientes/listclientes";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/addCliente")
	public String addCliente(Model model) {
		model.addAttribute("listsolicitudes", solicitudRepository.findAll());
		model.addAttribute("provincias", provinciaService.findAll());
		model.addAttribute("cliente", new Cliente());
		return "Clientes/addCliente";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping(value = "/updateCliente/{nro}")
	public String updateCliente(Model model, @PathVariable(value = "nro") int nro) {
		Cliente clientes = clientesService.findById(nro);
		setNumero(clientes.getNro());
		Pueblo temppueblo = clientes.getPueblo();
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
		model.addAttribute("clientetoupdate", clientes);
		model.addAttribute("fecha_suscripcion", clientes.getFechaSuscripcion());
		model.addAttribute("vigencia", clientes.getVigencia());
		model.addAttribute("fecha_suplemento", clientes.getFechaSuplemento());

		model.addAttribute("provincias", listaprovin);
		model.addAttribute("municipios", listMunicipio);
		model.addAttribute("pueblos", listpueblo);
		return "Clientes/updateCliente";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping(value = "/update_Cliente")
	public String update_Cliente(@ModelAttribute("clientetoupdate") Cliente cliente, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		Pueblo p = new Pueblo(cliente.getPueblo().getId());
		Cliente c = new Cliente(getNumero(), cliente.getNroContrato(), cliente.getNombreCliente(),
				cliente.getTipoContrato(), cliente.getFechaSuscripcion(), cliente.getVigencia(),
				cliente.getNroSuplemento(), cliente.getFechaSuplemento(), cliente.getEntidad(), cliente.getCi(),
				cliente.getTelefono(), cliente.getDireccion(), cliente.getVencido(), p);

		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			Cliente clientes = clientesService.findById(cliente.getNro());
			Pueblo temppueblo = clientes.getPueblo();
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

			mav.addObject("clientetoupdate", clientes);
			mav.addObject("fecha_suscripcion", clientes.getFechaSuscripcion());
			mav.addObject("vigencia", clientes.getVigencia());
			mav.addObject("fecha_suplemento", clientes.getFechaSuplemento());
			mav.addObject("provincias", listaprovin);

			mav.addObject("municipios", listMunicipio);
			mav.addObject("pueblos", listpueblo);

			return "Clientes/updateCliente";

		} else {
			try {

				clientesService.save(c);
				redirectAttributes.addFlashAttribute("msgbody", "Se han guardado correctamente los datos del cliente");
				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
				return "redirect:/listclientes";

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("msgbody", "Error al guardar los datos");
				redirectAttributes.addFlashAttribute("msgtipo", "error");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");

			}

		}

		return "Clientes/updateCliente";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/addClienteId/{id}")
	public String addClienteId(@PathVariable(value = "id") int id, Model model) {
		List<Provincia> provincias = (List<Provincia>) provinciaService.findAll();

		Solicitud solicitud = solicitudRepository.findById(id);
		provincias.remove(solicitud.getPueblo().getMunicipio().getProvincia());
		provincias.add(0, solicitud.getPueblo().getMunicipio().getProvincia());
		/*
		 * model.addAttribute("cliente", new Cliente(solicitud.getNomb_pers(),
		 * solicitud.getCi(), solicitud.getTelefono(), solicitud.getDireccion()));
		 */
		model.addAttribute("cliente", new Cliente(solicitud.getNombPers(), solicitud.getCi(), solicitud.getTelefono(),
				solicitud.getDireccion(), solicitud.getPueblo()));
		model.addAttribute("solicitudtoadd", solicitud);
		model.addAttribute("listprovincias", provincias);

		return "Clientes/addClienteId";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping("/add_Cliente")
	public String agregarCliente(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes redirectAttributes)
			throws ParseException {

		try {
			if (clientesService.findByCi(cliente.getCi()).isEmpty()) {

				if (!clientesService.findByNroContrato(cliente.getNroContrato()).isEmpty()) {

					redirectAttributes.addFlashAttribute("msgtipo", "warning");
					redirectAttributes.addFlashAttribute("msgbody",
							"El nro. de contrato " + cliente.getNroContrato() + " ya existe");
					redirectAttributes.addFlashAttribute("msgtitulo", "Error");

				} else {

					if (!cliente.getFechaSuplemento()
							.equals(null)) { /* pregunta si tiene valor la fecha de suplemento */

						Cliente c = new Cliente(cliente.getNroContrato(), cliente.getNombreCliente(),
								cliente.getTipoContrato(), cliente.getFechaSuscripcion(), cliente.getVigencia(),
								cliente.getNroSuplemento(), cliente.getFechaSuplemento(), cliente.getEntidad(),
								cliente.getCi(), cliente.getTelefono(), cliente.getDireccion(), cliente.getVencido(),
								new Pueblo(cliente.getPueblo().getId()));

						try {
							clientesService.save(c);

							redirectAttributes.addFlashAttribute("msgtipo", "success");
							redirectAttributes.addFlashAttribute("msgbody",
									"Se ha insertado correctamente los datos del cliente "
											+ cliente.getNombreCliente());
							redirectAttributes.addFlashAttribute("msgtitu", "Información");

						} catch (Exception e) {

							redirectAttributes.addFlashAttribute("msgtipo", "success");
							redirectAttributes.addFlashAttribute("msgbody",
									"Error al insertar " + cliente.getNombreCliente());
							redirectAttributes.addFlashAttribute("msgtitu", "Información");

						}

					} else {

						Cliente c = new Cliente(cliente.getNroContrato(), cliente.getNombreCliente(),
								cliente.getTipoContrato(), cliente.getFechaSuscripcion(), cliente.getVigencia(),
								cliente.getNroSuplemento(), null, cliente.getEntidad(), cliente.getCi(),
								cliente.getTelefono(), cliente.getDireccion(), cliente.getVencido(),
								new Pueblo(cliente.getPueblo().getId()));

						try {
							clientesService.save(c);

							redirectAttributes.addFlashAttribute("msgtipo", "success");
							redirectAttributes.addFlashAttribute("msgbody",
									"Se ha insertado correctamente los datos del cliente "
											+ cliente.getNombreCliente());
							redirectAttributes.addFlashAttribute("msgtitu", "Información");

						} catch (Exception e) {

							redirectAttributes.addFlashAttribute("msgtipo", "success");
							redirectAttributes.addFlashAttribute("msgbody",
									"Error al insertar " + cliente.getNombreCliente());
							redirectAttributes.addFlashAttribute("msgtitu", "Información");

						}
					}
				}
			} else {
				redirectAttributes.addFlashAttribute("msgtipo", "warning");
				redirectAttributes.addFlashAttribute("msgbody", "Ya el cliente existe");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");

			}

		} catch (Exception e) {
			clientesService.save(new Cliente(cliente.getNroContrato(), cliente.getNombreCliente(),
					cliente.getTipoContrato(), cliente.getFechaSuscripcion(), cliente.getVigencia(),
					cliente.getNroSuplemento(), null, cliente.getEntidad(), cliente.getCi(), cliente.getTelefono(),
					cliente.getDireccion(), cliente.getVencido(), new Pueblo(cliente.getPueblo().getId())));
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgbody",
					"Se ha insertado correctamente los datos del cliente " + cliente.getNombreCliente());
			redirectAttributes.addFlashAttribute("msgtitu", "Información");

		}

		return "redirect:/listclientes";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping(value = "/add_Cliente_Id")
	public String add_Cliente_Id(@ModelAttribute("cliente") Cliente cliente, Model model, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) throws java.text.ParseException {

		if (bindingResult.hasErrors()) {
			model.addAttribute("listprovincias", provinciaService.findAll());
			return "Clientes/addClienteId";

		} else {

			Pueblo p = new Pueblo(cliente.getPueblo().getId());
			Cliente c = new Cliente();
			if (!(cliente.getFechaSuplemento() == null)) {

				try {
					c = new Cliente(cliente.getNroContrato(), cliente.getNombreCliente(), cliente.getTipoContrato(),
							cliente.getFechaSuscripcion(), cliente.getVigencia(), cliente.getNroSuplemento(),
							cliente.getFechaSuplemento(), cliente.getEntidad(), cliente.getCi(), cliente.getTelefono(),
							cliente.getDireccion(), cliente.getVencido(), p);

					clientesService.save(c);
					redirectAttributes.addFlashAttribute("msgbody",
							"Se ha insertado correctamente los datos del cliente " + cliente.getNombreCliente());
					redirectAttributes.addFlashAttribute("msgtipo", "success");
					redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
					return "redirect:/listclientes";
				} catch (Exception e) {
					redirectAttributes.addFlashAttribute("msgbody", "Error al insertar");
					redirectAttributes.addFlashAttribute("msgtipo", "error");
					redirectAttributes.addFlashAttribute("msgtitu", "Error");
				}

			} else {

				try {
					c = new Cliente(cliente.getNroContrato(), cliente.getNombreCliente(), cliente.getTipoContrato(),
							cliente.getFechaSuscripcion(), cliente.getVigencia(), cliente.getNroSuplemento(),
							cliente.getEntidad(), cliente.getCi(), cliente.getTelefono(), cliente.getDireccion(),
							cliente.getVencido(), p);

					clientesService.save(c);
					redirectAttributes.addFlashAttribute("msgbody",
							"Se ha insertado correctamente los datos del cliente " + cliente.getNombreCliente());
					redirectAttributes.addFlashAttribute("msgtipo", "success");
					redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
					return "redirect:/listclientes";

				} catch (Exception e) {
					redirectAttributes.addFlashAttribute("msgbody", "Error al insertar");
					redirectAttributes.addFlashAttribute("msgtipo", "error");
					redirectAttributes.addFlashAttribute("msgtitu", "Error");
				}

			}

		}

		return "Clientes/addClienteId";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping(value = "/delete_cliente/{nro}")
	public String delete(@PathVariable(value = "nro") int nro, RedirectAttributes redirectAttributes) {

		try {
			clientesService.deleteById(nro);
			redirectAttributes.addFlashAttribute("msgbody", "Se ha eliminado correctamente los datos del cliente ");
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");

		} catch (Exception e) {

			redirectAttributes.addFlashAttribute("msgbody",
					"Error al eliminar los datos del cliente " + e.getMessage());
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");

		}

		return "redirect:/listclientes";
	}

}
