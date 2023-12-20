package com.springbootapplication.SpringBootApplication.Controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbootapplication.SpringBootApplication.Entity.Grupo;
import com.springbootapplication.SpringBootApplication.Entity.TipoContrato;
import com.springbootapplication.SpringBootApplication.Repository.GrupoRepository;
import com.springbootapplication.SpringBootApplication.Services.GrupoService;

@Controller
public class GrupoController {

	@Autowired
	GrupoService grupoService;

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping(value = "/getGrupo")
	public String getTipoContrato(Model model) {
		List<Grupo> grupos = (List<Grupo>) grupoService.findAll();
		model.addAttribute("grupos", grupos);
		return "templateBase/ComponentFragment :: grupo";

	}

	//@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping("/addGrupo")
	public @ResponseBody String addGrupo(@RequestParam("grupo") String grupo) {

		JSONObject jsono = new JSONObject();

		if (!grupoService.existsById(grupo)) {

			try {
				grupoService.save(new Grupo(grupo));
				jsono.put("msgtipo", "success");
				jsono.put("msgtitu", "Información");
				jsono.put("msgbody", "Se inserto correctamente el grupo ");
			} catch (Exception e) {
				jsono.put("msgtipo", "error");
				jsono.put("msgtitu", "Error");
				jsono.put("msgbody", "Error al insertar el grupo ");
			}

		} else {
			jsono.put("msgtipo", "warning");
			jsono.put("msgtitu", "Error");
			jsono.put("msgbody", "El grupo que intenta añadir ya existe ");
		}

		return jsono.toString();

	}
	
	
/*	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping(value = "/getNroContrato")
	public String getNroContrato(Model model) {
		List<Grupo> grupos = (List<Grupo>) grupoRepository.findAll();
		model.addAttribute("nroscontratos", nroscontratos);
		return "templateBase/ComponentFragment :: grupo";

	}  */
	
	

}
