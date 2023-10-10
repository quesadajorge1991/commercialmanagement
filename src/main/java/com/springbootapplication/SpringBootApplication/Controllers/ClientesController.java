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
import com.springbootapplication.SpringBootApplication.Repository.ClientesRepository;
import com.springbootapplication.SpringBootApplication.Repository.MunicipioRepository;
import com.springbootapplication.SpringBootApplication.Repository.ProvinciaRepository;
import com.springbootapplication.SpringBootApplication.Repository.PuebloRepository;
import com.springbootapplication.SpringBootApplication.Repository.ServicioRepository;
import com.springbootapplication.SpringBootApplication.Repository.SolicitudRepository;

@Controller
public class ClientesController {

	@Autowired
	ClientesRepository clientesRepository;

	@Autowired
	ProvinciaRepository provinciaRepository;

	@Autowired
	SolicitudRepository solicitudRepository;

	@Autowired
	MunicipioRepository municipioRepository;

	@Autowired
	PuebloRepository puebloRepository;

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
		model.addAttribute("clientes", clientesRepository.findAll());
		return "Clientes/listclientes";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/addCliente")
	public String addCliente(Model model) {
		model.addAttribute("listsolicitudes", solicitudRepository.findAll());
		model.addAttribute("provincias", provinciaRepository.findAll());
		model.addAttribute("cliente", new Cliente());
		return "Clientes/addCliente";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping(value = "/updateCliente/{nro}")
	public String updateCliente(Model model, @PathVariable(value = "nro") int nro) {
		Cliente clientes = clientesRepository.findById(nro).get();
		setNumero(clientes.getNro());
		Pueblo temppueblo = clientes.getPueblo();
		Municipio tempmun = temppueblo.getMunicipio();
		Provincia tempprov = tempmun.getProvincia();
		List<Provincia> listaprovin = (List<Provincia>) provinciaRepository.findAll();
		listaprovin.remove(tempprov);
		listaprovin.add(0, tempprov);
		List<Municipio> listMunicipio = (List<Municipio>) municipioRepository
				.getMunicipiosByProvincia(listaprovin.get(0).getId());
		listMunicipio.remove(tempmun);
		listMunicipio.add(0, tempmun);
		List<Pueblo> listpueblo = (List<Pueblo>) puebloRepository.getPueblosByMunicipios(listMunicipio.get(0).getId());
		listpueblo.remove(temppueblo);
		listpueblo.add(0, temppueblo);
		model.addAttribute("clientetoupdate", clientes);
		model.addAttribute("fecha_suscripcion", clientes.getFecha_suscripcion());
		model.addAttribute("vigencia", clientes.getVigencia());
		model.addAttribute("fecha_suplemento", clientes.getFecha_suplemento());
		model.addAttribute("municipios", listMunicipio);
		model.addAttribute("pueblos", listpueblo);
		return "Clientes/updateCliente";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping(value = "/update_Cliente")
	public String update_Cliente(@ModelAttribute("clientetoupdate") Cliente cliente, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		Pueblo p = new Pueblo(cliente.getPueblo().getId());
		Cliente c = new Cliente(getNumero(), cliente.getNro_contrato(), cliente.getNombre_cliente(),
				cliente.getTipo_contrato(), cliente.getFecha_suscripcion(), cliente.getVigencia(),
				cliente.getNro_suplemento(), cliente.getFecha_suplemento(), cliente.getEntidad(), cliente.getCi(),
				cliente.getTelefono(), cliente.getDireccion(), cliente.getVencido(), p);

		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			Cliente clientes = clientesRepository.findById(cliente.getNro()).get();
			Pueblo temppueblo = clientes.getPueblo();
			Municipio tempmun = temppueblo.getMunicipio();
			Provincia tempprov = tempmun.getProvincia();

			List<Provincia> listaprovin = (List<Provincia>) provinciaRepository.findAll();
			listaprovin.remove(tempprov);
			listaprovin.add(0, tempprov);

			List<Municipio> listMunicipio = (List<Municipio>) municipioRepository
					.getMunicipiosByProvincia(listaprovin.get(0).getId());
			listMunicipio.remove(tempmun);
			listMunicipio.add(0, tempmun);

			List<Pueblo> listpueblo = (List<Pueblo>) puebloRepository
					.getPueblosByMunicipios(listMunicipio.get(0).getId());
			listpueblo.remove(temppueblo);
			listpueblo.add(0, temppueblo);

			mav.addObject("clientetoupdate", clientes);
			mav.addObject("fecha_suscripcion", clientes.getFecha_suscripcion());
			mav.addObject("vigencia", clientes.getVigencia());
			mav.addObject("fecha_suplemento", clientes.getFecha_suplemento());
			mav.addObject("provincias", listaprovin);
			mav.addObject("municipios", listMunicipio);
			mav.addObject("pueblos", listpueblo);

			return "Clientes/updateCliente";

		} else {
			try {

				clientesRepository.save(c);
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
		List<Provincia> provincias = (List<Provincia>) provinciaRepository.findAll();

		Solicitud solicitud = solicitudRepository.findById(id).get();
		provincias.remove(solicitud.getPueblo().getMunicipio().getProvincia());
		provincias.add(0, solicitud.getPueblo().getMunicipio().getProvincia());
		/*
		 * model.addAttribute("cliente", new Cliente(solicitud.getNomb_pers(),
		 * solicitud.getCi(), solicitud.getTelefono(), solicitud.getDireccion()));
		 */
		model.addAttribute("cliente", new Cliente(solicitud.getNomb_pers(), solicitud.getCi(), solicitud.getTelefono(),
				solicitud.getDireccion(), solicitud.getPueblo()));
		model.addAttribute("solicitudtoadd", solicitud);
		model.addAttribute("listprovincias", provincias);

		return "Clientes/addClienteid";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping("/add_Cliente")
	public String agregarCliente(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes redirectAttributes)
			throws ParseException {

		try {
			if (clientesRepository.getClientexCI(cliente.getCi()).isEmpty()) {

				if (!clientesRepository.getClientexNro_contrato(cliente.getNro_contrato()).isEmpty()) {

					redirectAttributes.addFlashAttribute("msgtipo", "warning");
					redirectAttributes.addFlashAttribute("msgbody",
							"El nro. de contrato " + cliente.getNro_contrato() + " ya existe");
					redirectAttributes.addFlashAttribute("msgtitulo", "Error");

				} else {

					if (!cliente.getFecha_suplemento()
							.equals(null)) { /* pregunta si tiene valor la fecha de suplemento */

						System.out.println("ENTROOOOOOOOOOO " + cliente.getCi());

						Cliente c = new Cliente(cliente.getNro_contrato(), cliente.getNombre_cliente(),
								cliente.getTipo_contrato(), cliente.getFecha_suscripcion(), cliente.getVigencia(),
								cliente.getNro_suplemento(), cliente.getFecha_suplemento(), cliente.getEntidad(),
								cliente.getCi(), cliente.getTelefono(), cliente.getDireccion(), cliente.getVencido(),
								new Pueblo(cliente.getPueblo().getId()));

						try {
							clientesRepository.save(c);

							redirectAttributes.addFlashAttribute("msgtipo", "success");
							redirectAttributes.addFlashAttribute("msgbody",
									"Se ha insertado correctamente los datos del cliente "
											+ cliente.getNombre_cliente());
							redirectAttributes.addFlashAttribute("msgtitu", "Información");

						} catch (Exception e) {

							redirectAttributes.addFlashAttribute("msgtipo", "success");
							redirectAttributes.addFlashAttribute("msgbody",
									"Error al insertar " + cliente.getNombre_cliente());
							redirectAttributes.addFlashAttribute("msgtitu", "Información");

						}

					} else {

						Cliente c = new Cliente(cliente.getNro_contrato(), cliente.getNombre_cliente(),
								cliente.getTipo_contrato(), cliente.getFecha_suscripcion(), cliente.getVigencia(),
								cliente.getNro_suplemento(), null, cliente.getEntidad(), cliente.getCi(),
								cliente.getTelefono(), cliente.getDireccion(), cliente.getVencido(),
								new Pueblo(cliente.getPueblo().getId()));

						try {
							clientesRepository.save(c);

							redirectAttributes.addFlashAttribute("msgtipo", "success");
							redirectAttributes.addFlashAttribute("msgbody",
									"Se ha insertado correctamente los datos del cliente "
											+ cliente.getNombre_cliente());
							redirectAttributes.addFlashAttribute("msgtitu", "Información");

						} catch (Exception e) {

							redirectAttributes.addFlashAttribute("msgtipo", "success");
							redirectAttributes.addFlashAttribute("msgbody",
									"Error al insertar " + cliente.getNombre_cliente());
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
			clientesRepository.save(new Cliente(cliente.getNro_contrato(), cliente.getNombre_cliente(),
					cliente.getTipo_contrato(), cliente.getFecha_suscripcion(), cliente.getVigencia(),
					cliente.getNro_suplemento(), null, cliente.getEntidad(), cliente.getCi(), cliente.getTelefono(),
					cliente.getDireccion(), cliente.getVencido(), new Pueblo(cliente.getPueblo().getId())));
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgbody",
					"Se ha insertado correctamente los datos del cliente " + cliente.getNombre_cliente());
			redirectAttributes.addFlashAttribute("msgtitu", "Información");

		}

		return "redirect:/listclientes";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping(value = "/add_Cliente_Id")
	public String add_Cliente_Id(@ModelAttribute("cliente") Cliente cliente, Model model, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) throws java.text.ParseException {

		if (bindingResult.hasErrors()) {
			model.addAttribute("listprovincias", provinciaRepository.findAll());
			return "Clientes/addClienteId";

		} else {

			Pueblo p = new Pueblo(cliente.getPueblo().getId());
			Cliente c = new Cliente();
			if (!(cliente.getFecha_suplemento() == null)) {

				try {
					c = new Cliente(cliente.getNro_contrato(), cliente.getNombre_cliente(), cliente.getTipo_contrato(),
							cliente.getFecha_suscripcion(), cliente.getVigencia(), cliente.getNro_suplemento(),
							cliente.getFecha_suplemento(), cliente.getEntidad(), cliente.getCi(), cliente.getTelefono(),
							cliente.getDireccion(), cliente.getVencido(), p);

					clientesRepository.save(c);
					redirectAttributes.addFlashAttribute("msgbody",
							"Se ha insertado correctamente los datos del cliente " + cliente.getNombre_cliente());
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
					c = new Cliente(cliente.getNro_contrato(), cliente.getNombre_cliente(), cliente.getTipo_contrato(),
							cliente.getFecha_suscripcion(), cliente.getVigencia(), cliente.getNro_suplemento(),
							cliente.getEntidad(), cliente.getCi(), cliente.getTelefono(), cliente.getDireccion(),
							cliente.getVencido(), p);

					clientesRepository.save(c);
					redirectAttributes.addFlashAttribute("msgbody",
							"Se ha insertado correctamente los datos del cliente " + cliente.getNombre_cliente());
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
			clientesRepository.deleteById(nro);
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