package com.springbootapplication.SpringBootApplication.Controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootapplication.SpringBootApplication.Entity.Solicitud;
import com.springbootapplication.SpringBootApplication.Repository.*;

@RestController
public class ControllerPrueba {

	@Autowired
	SolicitudRepository solicitudRepository;

	@Autowired
	PuebloRepository puebloRepository;

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping(value = "/getdatosSolicitud", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getdatosSolicitud(@RequestParam(value = "ids", required = true) int ids)
			throws Exception {
		JSONObject jsono = new JSONObject();

		List<String> bus_list = solicitudRepository.getDatosSolicitud(ids);
		jsono.put("listsolicitud", bus_list);

		return jsono.toString();
	}

	@PreAuthorize("hasAnyRole('ADMIN','COMERCIAL')")
	@GetMapping(value = "/gettdata", produces = "application/json;charset=UTF-8")
	public List<Solicitud> getSolicitud() throws Exception {
		// JSONObject jsono = new JSONObject();
		List<Solicitud> bus_list = (List<Solicitud>) solicitudRepository.findAll();
		// jsono.put("listsolicitud", bus_list);
		return bus_list;
	}

}
