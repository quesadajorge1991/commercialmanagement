package com.springbootapplication.SpringBootApplication.Controllers;

import java.text.ParseException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springbootapplication.SpringBootApplication.Entity.Municipio;
import com.springbootapplication.SpringBootApplication.Entity.Provincia;
import com.springbootapplication.SpringBootApplication.Entity.Pueblo;
import com.springbootapplication.SpringBootApplication.Services.MunicipioService;
import com.springbootapplication.SpringBootApplication.Services.ProvinciaService;

@Controller
public class TerritorioController {

	@Autowired
	ProvinciaService provinciaService;

	@Autowired
	MunicipioService municipioService;

	@GetMapping(value = "/Territorio/index")
	public String provincias(Model model) {
		model.addAttribute("provincias", provinciaService.findAll());
		return "/Territorio/index";
	}

	@GetMapping(value = "/Territorio/Provincia")
	public String addProvinciaa(Model model) {
		model.addAttribute("provincias", provinciaService.findAll());
		return "/Territorio/addProvincia";
	}

	@GetMapping(value = "/Territorio/updateProvincia/{id}")
	public String updateProvincia(@PathVariable("id") long id, Model model) {
		model.addAttribute("provincia", provinciaService.findByIdProvincia(id));
		return "/Territorio/updateProvincia";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping(value = "/Territorio/addMunicipio")
	public @ResponseBody String addMunicipio(@RequestParam(value = "provincia") long provincia,
			@RequestParam(value = "namemunicipio") String namemunicipio) throws JSONException, ParseException {

		JSONObject jsono = new JSONObject();

		if (provincia == -1 || namemunicipio.isBlank() || namemunicipio.isEmpty()) {
			jsono.put("msgtipo", "error");
			jsono.put("msgtitu", "Error");
			jsono.put("msgbody", "No deben quedar campos en blanco");
		} else {
			/* validar si el municipio ya existe */
			if (municipioService.listMunicipios().contains(namemunicipio)) {
				jsono.put("msgtipo", "warning");
				jsono.put("msgtitu", "Error");
				jsono.put("msgbody", "El municipio que intenta añadir ya existe " + namemunicipio);
			} else {
				try {
					municipioService.save(new Municipio(namemunicipio, new Provincia(provincia)));
					jsono.put("msgtipo", "success");
					jsono.put("msgtitu", "Información");
					jsono.put("msgbody", "Se inserto correctamente municipio " + namemunicipio);
				} catch (Exception e) {
					jsono.put("msgtipo", "error");
					jsono.put("msgtitu", "Error");
					jsono.put("msgbody", "Error al insertar el municipio " + namemunicipio);

				}

			}
		}

		return jsono.toString();
	}

	@PostMapping(value = "/Territorio/updateProvincia")
	public @ResponseBody String updateProvincia(@ModelAttribute(value = "provincia") Provincia provincia) {
		JSONObject jsono = new JSONObject();
		if (provincia == null) {
			/* mensaje de erorr */
			jsono.put("msgtipo", "error");
			jsono.put("msgtitu", "Error");
			jsono.put("msgbody", "El nombre de la provincia no debe estar vacío ");
		} else {
			provinciaService.save(new Provincia(provincia.getId(), provincia.getNombProv()));
			jsono.put("msgtipo", "success");
			jsono.put("msgtitu", "Información");
			jsono.put("msgbody", "Se modifico correctamente la provincia " + provincia.getNombProv());
		
		}

		return jsono.toString();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/Territorio/addProvincia")
	public @ResponseBody String addProvincia(@RequestParam(value = "provincia") String nombProv)
			throws JSONException, ParseException {

		JSONObject jsono = new JSONObject();

		if (nombProv.isEmpty() || nombProv.isBlank()) {
			jsono.put("msgtipo", "error");
			jsono.put("msgtitu", "Error");
			jsono.put("msgbody", "El nombre de la provincia no debe estar vacío ");
		} else {
			if (provinciaService.findByNombProv(nombProv) != null) {
				jsono.put("msgtipo", "warning");
				jsono.put("msgtitu", "Error");
				jsono.put("msgbody", "La provincia que intenta añadir ya existe " + nombProv);
			} else {
				try {
					provinciaService.save(new Provincia(nombProv));
					jsono.put("msgtipo", "success");
					jsono.put("msgtitu", "Información");
					jsono.put("msgbody", "Se inserto correctamente la provincia " + nombProv);
				} catch (Exception e) {
					jsono.put("msgtipo", "error");
					jsono.put("msgtitu", "Error");
					jsono.put("msgbody", "Error al insertar la provincia " + nombProv);

				}

			}

		}

		return jsono.toString();
	}

	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/deleteProvincia/{id}")
	public String deleteProvincia(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

		try {
			provinciaService.deleteById(id);
			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Información");
			redirectAttributes.addFlashAttribute("msgbody", "Provincia eleminada correctamente ");
			return "redirect:/listusuarios";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al eleminar la provincia ");
		}

		return "redirect:/Territorio/index";
	}
	
	
	
	
	@GetMapping(value = "/Territorio/getListProvincia")
	public String getListProvincia(Model model) {
		model.addAttribute("provincias", provinciaService.findAll());
		return "templateBase/ComponentFragment :: provincias";

	}

}
