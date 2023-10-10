package com.springbootapplication.SpringBootApplication.Controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbootapplication.SpringBootApplication.Entity.Tipo_Contrato;
import com.springbootapplication.SpringBootApplication.Repository.*;



@Controller
public class TIpoContratoController {
	
	@Autowired
	Tipo_ContratoRepository tipo_ContratoRepository;
	
	
	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping(value = "/getTipoContrato")
	public String getTipoContrato(Model model) {
		List<Tipo_Contrato> tipocontrato = (List<Tipo_Contrato>) tipo_ContratoRepository.findAll();
		model.addAttribute("tipocontratos", tipocontrato);
		return "templateBase/ComponentFragment :: tipocontrato";

	}
	
	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@PostMapping("/addTipoContrato")
	public @ResponseBody String addTipoContrato(@RequestParam("tipo_contrato") String tipo_contrato) {
		
		JSONObject jsono = new JSONObject();

		if (!tipo_ContratoRepository.getTipoContrato(tipo_contrato).contains(tipo_contrato)) {/* Pregunta si la lista esta vacia, de estarlo inserta el pueblo */

			try {
				tipo_ContratoRepository.save(new Tipo_Contrato(tipo_contrato));
				jsono.put("msgtipo", "success");
				jsono.put("msgtitu", "Información");
				jsono.put("msgbody", "Se inserto correctamente el tipo de contrato ");

			} catch (Exception e) {
				jsono.put("msgtipo", "error");
				jsono.put("msgtitu", "Error");
				jsono.put("msgbody", "Error al insertar el tipo de contrato ");

			}

		}else {
			jsono.put("msgtipo", "warning");
			jsono.put("msgtitu", "Error");
			jsono.put("msgbody", "El Tipo de contrato que intenta añadir ya existe ");
		}


		return jsono.toString();
		
	}

}
