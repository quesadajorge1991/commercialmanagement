package com.springbootapplication.SpringBootApplication.Controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootapplication.SpringBootApplication.Entity.*;
import com.springbootapplication.SpringBootApplication.Repository.*;

@Controller
public class ProveedoresController {

	@Autowired
	ProovedorRepository proovedorRepository;

	@Autowired
	MunicipioRepository municipioRepository;

	@Autowired
	ProvinciaRepository provinciaRepository;

	@Autowired
	Tipo_ContratoRepository tipo_ContratoRepository;

	@Autowired
	PuebloRepository puebloRepository;

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ECONOMIA')")
	@GetMapping("/proveedores")
	public String listproveedores(Model model) {
		model.addAttribute("proveedores", proovedorRepository.findAll());
		model.addAttribute("standardDate", new Date());
		return "Proveedores/listproveedores";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/addproveedores")
	public String Addproveedores(Model model) {
		model.addAttribute("proveedor", new Proveedor());
		model.addAttribute("provincias", provinciaRepository.findAll());
		model.addAttribute("tipocontratos", tipo_ContratoRepository.findAll());
		return "Proveedores/addproveedor";
	}

	/*
	 * @RequestMapping("/agregarproveedor") public String agregarproveedor() {
	 * return "redirect:/Proveedores/addproveedor"; }
	 */

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/updateProveedor/{nro}")
	public String updateProveedor(@PathVariable int nro, Model model) {
		// Proveedor provee= proovedorRepository.findById(Integer.parseInt(nro)).get();
		Proveedor proveedor = proovedorRepository.findById(nro).get();
		Municipio tempmunicipio = proveedor.getMunicipio();
		Provincia tempprov = tempmunicipio.getProvincia();
		List<Provincia> listaprovin = (List<Provincia>) provinciaRepository.findAll();
		listaprovin.remove(tempprov);
		listaprovin.add(0, tempprov);

		List<Municipio> listMunicipio = (List<Municipio>) municipioRepository
				.getMunicipiosByProvincia(listaprovin.get(0).getId());
		listMunicipio.remove(tempmunicipio);
		listMunicipio.add(0, tempmunicipio);

		Tipo_Contrato tipo_Contrato = proveedor.getTipo_contrato();
		List<Tipo_Contrato> listTipo_Contrato = (List<Tipo_Contrato>) tipo_ContratoRepository.findAll();
		listTipo_Contrato.remove(tipo_Contrato);
		listTipo_Contrato.add(0, tipo_Contrato);

		model.addAttribute("proveedor", proovedorRepository.findById(nro).get());
		model.addAttribute("provincias", listaprovin);
		model.addAttribute("municipios", listMunicipio);
		model.addAttribute("tipocontratos", listTipo_Contrato);

		return "Proveedores/updateproveedor";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping("/addProveedor")
	public String addProveedor(@ModelAttribute("proveedor") @Valid Proveedor proveedor, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("provincias", provinciaRepository.findAll());
			model.addAttribute("tipocontratos", tipo_ContratoRepository.findAll());

			return "Proveedores/addproveedor";
		} else {
			try {
				proovedorRepository.save(new Proveedor(proveedor.getNro_contrato(), proveedor.getNro_registro(),
						proveedor.getNombre_proveedor(), proveedor.getAlias_proveedor(),
						proveedor.getFecha_suscripcion(), proveedor.getVigencia(), proveedor.getFicha_cliente(),
						proveedor.getCodigo_REUP(), proveedor.getCuenta_bancaria(), proveedor.getDictaminado(),
						proveedor.getTelefono(), proveedor.getEmail(), proveedor.getDireccion(),
						proveedor.getObservaciones(), proveedor.getVencido(), proveedor.getNotificar(),
						new Municipio(proveedor.getMunicipio().getId()),
						new Tipo_Contrato(proveedor.getTipo_contrato().getNro())));

				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
				redirectAttributes.addFlashAttribute("msgbody", "Se ha agregado satisfactoriamente el proveedor ");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				redirectAttributes.addFlashAttribute("msgtipo", "error");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");
				redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el proveedor ");
			}
		}

		return "redirect:/addproveedores";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping("/updateProveedor")
	public String updateProveedor(@ModelAttribute("proveedor") Proveedor proveedor, Model model,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("provincias", provinciaRepository.findAll());
			model.addAttribute("tipocontratos", tipo_ContratoRepository.findAll());

			return "Proveedores/updateproveedor";
		} else {
			try {

				proovedorRepository.save(new Proveedor(proveedor.getNro(), proveedor.getNro_contrato(),
						proveedor.getNro_registro(), proveedor.getNombre_proveedor(), proveedor.getAlias_proveedor(),
						proveedor.getFecha_suscripcion(), proveedor.getVigencia(), proveedor.getFicha_cliente(),
						proveedor.getCodigo_REUP(), proveedor.getCuenta_bancaria(), proveedor.getDictaminado(),
						proveedor.getTelefono(), proveedor.getEmail(), proveedor.getDireccion(),
						proveedor.getObservaciones(), proveedor.getVencido(), proveedor.getNotificar(),
						new Municipio(proveedor.getMunicipio().getId()),
						new Tipo_Contrato(proveedor.getTipo_contrato().getNro())));

				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
				redirectAttributes.addFlashAttribute("msgbody", "Se ha agregado satisfactoriamente el proveedor ");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				redirectAttributes.addFlashAttribute("msgtipo", "error");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");
				redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el proveedor ");
			}
		}

		return "redirect:/proveedores";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@GetMapping(value = "/getMunicipioByProvincia")
	public String MunicipioByProvincia(@RequestParam("provincia") String idprovincia, Model model) {
		List<Municipio> municipios = municipioRepository.getMunicipiosByProvincia(Long.valueOf(idprovincia));
		model.addAttribute("municipios", municipios);
		System.out.println(municipios.size());

		return "templateBase/ComponentFragment :: municicipiosByProvincia";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@GetMapping(value = "/getPuebloByMunicipio")
	public String getPuebloByMunicipio(@RequestParam("municipio") long idmunicipio, Model model) {
		List<Pueblo> pueblos = puebloRepository.getPueblosByMunicipios(idmunicipio);
		model.addAttribute("pueblos", pueblos);
		return "templateBase/ComponentFragment :: pueblosByMunicipio";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/deleteProveedor/{nro}")
	public String deleteProveedor(@PathVariable int nro, RedirectAttributes redirectAttributes) {
		System.out.println(nro);
		try {
			proovedorRepository.deleteById(nro);

			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
			redirectAttributes.addFlashAttribute("msgbody", "Se ha eliminado satisfactoriamente el proveedor ");

		} catch (Exception e) {
			// TODO: handle exception
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eliminar el proveedor ");
		}

		return "redirect:/proveedores";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping({ "/index", "/" })
	public String index(Model model) {

		try {
			List<Proveedor> listproveedores = proovedorRepository.getVigencia();
			List<Proveedor> contr_vencidos = new ArrayList<Proveedor>();
			ContratosxVencerse cxv = new ContratosxVencerse();
			List<ContratosxVencerse> contrxVencerse = new ArrayList<ContratosxVencerse>();
			Date actual = new Date();
			for (Proveedor proveedor : listproveedores) {
				if (actual.after(proveedor.getVigencia())) {
					contr_vencidos.add(proveedor);
					proveedor.setVencido("si");
					// pdao.updateProveedor(proveedor);
					proovedorRepository.save(proveedor);
				} else {
					cxv = new ContratosxVencerse(proveedor, diasRestantes(proveedor.getVigencia()));
					contrxVencerse.add(cxv);
				}
			}
			model.addAttribute("contr_vencidos", contr_vencidos);
			model.addAttribute("contrxVencerse", contrxVencerse);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return "index";
	}

	private static int diasRestantes(Date fecha) {
		DateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
		int dias = 0;
		boolean activo = false;
		Calendar calendar;
		Date aux;
		do {
			calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, dias);
			aux = calendar.getTime();
			if (dd.format(aux).equals(dd.format(fecha))) {
				System.out.println("Igual");
				activo = true;
			} else {
				dias++;
			}
		} while (activo != true);
		return dias;
	}

}
