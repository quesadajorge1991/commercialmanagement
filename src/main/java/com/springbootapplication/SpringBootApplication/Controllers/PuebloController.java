package com.springbootapplication.SpringBootApplication.Controllers;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbootapplication.SpringBootApplication.Entity.Municipio;
import com.springbootapplication.SpringBootApplication.Entity.Pueblo;
import com.springbootapplication.SpringBootApplication.Services.PuebloService;



@Controller
public class PuebloController {

	@Autowired
	PuebloService puebloService;

	
	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL','ESTACIONES')")
	@GetMapping(value = "/addPueblo")
	public @ResponseBody String addPueblo(@RequestParam(value = "municipio") long municipio,
			@RequestParam(value = "namepueblo") String namepueblo) throws JSONException, ParseException {
		
		System.err.println("nombre puebloooo " + namepueblo);

		JSONObject jsono = new JSONObject();

		if (!puebloService.getNamePueblo(municipio).contains(namepueblo)) {/* Pregunta si la lista esta vacia, de estarlo inserta el pueblo */

			try {
				puebloService.save(new Pueblo(namepueblo, new Municipio(municipio)));
				jsono.put("msgtipo", "success");
				jsono.put("msgtitu", "Información");
				jsono.put("msgbody", "Se inserto correctamente el pueblo " + namepueblo);

			} catch (Exception e) {
				jsono.put("msgtipo", "error");
				jsono.put("msgtitu", "Error");
				jsono.put("msgbody", "Error al insertar el pueblo " + namepueblo);

			}

		}else {
			jsono.put("msgtipo", "warning");
			jsono.put("msgtitu", "Error");
			jsono.put("msgbody", "El pueblo que intenta añadir ya existe " + namepueblo);
		}


		return jsono.toString();
	}

/*	@GetMapping("/addpueblo")
	public @ResponseBody String addpueblo(@RequestParam("nombrepueblo") String nombrepueblo,
			@RequestParam("idmunicipio") long id, RedirectAttributes redirectAttributes) {

		try {

			// System.out.println("Municipio id +" +id);
			// puebloRepository.save(new Pueblo(nombrepueblo, new Municipio(id)));

			redirectAttributes.addFlashAttribute("msgtipo", "success");
			redirectAttributes.addFlashAttribute("msgtitu", "Confirmación");
			redirectAttributes.addFlashAttribute("msgbody", "Se ha agregado satisfactoriamente el proveedor ");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("msgtipo", "error");
			redirectAttributes.addFlashAttribute("msgtitu", "Error");
			redirectAttributes.addFlashAttribute("msgbody", "Error al agregar el proveedor ");

		}

		return "redirect:/addSolicitud";

	}*/

}
