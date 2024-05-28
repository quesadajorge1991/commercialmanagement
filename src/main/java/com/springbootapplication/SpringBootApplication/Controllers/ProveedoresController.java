package com.springbootapplication.SpringBootApplication.Controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
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
import com.springbootapplication.SpringBootApplication.Services.MunicipioService;
import com.springbootapplication.SpringBootApplication.Services.ProveedorService;
import com.springbootapplication.SpringBootApplication.Services.ProvinciaService;
import com.springbootapplication.SpringBootApplication.Services.PuebloService;
import com.springbootapplication.SpringBootApplication.Services.TipoContratoService;

@Controller
public class ProveedoresController {

	@Autowired
	ProveedorService proovedorService;

	@Autowired
	MunicipioService municipioService;

	@Autowired
	ProvinciaService provinciaService;

	@Autowired
	TipoContratoService tipoContratoService;

	@Autowired
	PuebloService puebloService;

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ECONOMIA')")
	@GetMapping("/proveedores")
	public String listproveedores(Model model) {
		model.addAttribute("proveedores", proovedorService.findByOrderByNroRegistro());
		model.addAttribute("standardDate", new Date());
		return "Proveedores/listproveedores";
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/addproveedores")
	public String Addproveedores(Model model) {
		model.addAttribute("proveedor", new Proveedor());
		model.addAttribute("provincias", provinciaService.findAll());
		model.addAttribute("tipocontratos", tipoContratoService.findAll());
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
		Proveedor proveedor = proovedorService.findById(nro);
		Municipio tempmunicipio = proveedor.getMunicipio();
		Provincia tempprov = tempmunicipio.getProvincia();
		List<Provincia> listaprovin = (List<Provincia>) provinciaService.findAll();
		listaprovin.remove(tempprov);
		listaprovin.add(0, tempprov);

		List<Municipio> listMunicipio = (List<Municipio>) municipioService
				.findMunicipiosByProvincia(listaprovin.get(0).getId());
		listMunicipio.remove(tempmunicipio);
		listMunicipio.add(0, tempmunicipio);

		TipoContrato tipo_Contrato = proveedor.getTipoContrato();
		List<TipoContrato> listTipo_Contrato = (List<TipoContrato>) tipoContratoService.findAll();
		listTipo_Contrato.remove(tipo_Contrato);
		listTipo_Contrato.add(0, tipo_Contrato);

		model.addAttribute("proveedor", proovedorService.findById(nro));
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
			model.addAttribute("provincias", provinciaService.findAll());
			model.addAttribute("tipocontratos", tipoContratoService.findAll());

			return "Proveedores/addproveedor";
		} else {
			try {
				proovedorService.save(new Proveedor(proveedor.getNroContrato(), proveedor.getNroRegistro(),
						proveedor.getNombreProveedor(), proveedor.getAliasProveedor(), proveedor.getFechaSuscripcion(),
						proveedor.getVigencia(), proveedor.getFichaCliente(), proveedor.getCodigoREUP(),
						proveedor.getCuentaBancaria(), proveedor.getDictaminado(), proveedor.getTelefono(),
						proveedor.getEmail(), proveedor.getDireccion(), proveedor.getObservaciones(),
						proveedor.getVencido(), proveedor.getNotificar(),
						new Municipio(proveedor.getMunicipio().getId()),
						new TipoContrato(proveedor.getTipoContrato().getNro())));

				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
				redirectAttributes.addFlashAttribute("msgbody", "Se ha agregado satisfactoriamente el proveedor ");

			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("msgtipo", "error");
				redirectAttributes.addFlashAttribute("msgtitu", "Error");
				redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el proveedor ");
			}
		}

		return "redirect:/proveedores";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping("/updateProveedor")
	public String updateProveedor(@ModelAttribute("proveedor") Proveedor proveedor, Model model,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("provincias", provinciaService.findAll());
			model.addAttribute("tipocontratos", tipoContratoService.findAll());

			return "Proveedores/updateproveedor";
		} else {
			try {

				proovedorService.save(new Proveedor(proveedor.getNro(), proveedor.getNroContrato(),
						proveedor.getNroRegistro(), proveedor.getNombreProveedor(), proveedor.getAliasProveedor(),
						proveedor.getFechaSuscripcion(), proveedor.getVigencia(), proveedor.getFichaCliente(),
						proveedor.getCodigoREUP(), proveedor.getCuentaBancaria(), proveedor.getDictaminado(),
						proveedor.getTelefono(), proveedor.getEmail(), proveedor.getDireccion(),
						proveedor.getObservaciones(), proveedor.getVencido(), proveedor.getNotificar(),
						new Municipio(proveedor.getMunicipio().getId()),
						new TipoContrato(proveedor.getTipoContrato().getNro())));

				redirectAttributes.addFlashAttribute("msgtipo", "success");
				redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
				redirectAttributes.addFlashAttribute("msgbody", "Se ha agregado satisfactoriamente el proveedor ");

			} catch (Exception e) {

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
		List<Municipio> municipios = municipioService.findMunicipiosByProvincia(Long.valueOf(idprovincia));
		model.addAttribute("municipios", municipios);

		return "templateBase/ComponentFragment :: municicipiosByProvincia";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@GetMapping(value = "/getPuebloByMunicipio")
	public String getPuebloByMunicipio(@RequestParam("municipio") long idmunicipio, Model model) {
		List<Pueblo> pueblos = puebloService.getPueblosByMunicipios(idmunicipio);
		model.addAttribute("pueblos", pueblos);
		return "templateBase/ComponentFragment :: pueblosByMunicipio";

	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping("/deleteProveedor/{nro}")
	public String deleteProveedor(@PathVariable int nro, RedirectAttributes redirectAttributes) {

		try {
			proovedorService.deleteById(nro);

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
		List<Proveedor> listproveedores = proovedorService.findByOrderByVigencia();
		try {

			List<Proveedor> contr_vencidos = new ArrayList<Proveedor>();
			ContratosxVencerse cxv = new ContratosxVencerse();
			List<ContratosxVencerse> contrxVencerse = new ArrayList<ContratosxVencerse>();
			Date actual = new Date();
			for (Proveedor proveedor : listproveedores) {
				if (actual.after(proveedor.getVigencia())) {
					contr_vencidos.add(proveedor);
					proveedor.setVencido("si");
					proovedorService.save(proveedor);
				} else {
					
					cxv = new ContratosxVencerse(proveedor,proovedorService.diasRestantes(proveedor.getVigencia()));
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



}
