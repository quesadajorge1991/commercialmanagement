package com.springbootapplication.SpringBootApplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Solicitud;
import com.springbootapplication.SpringBootApplication.Repository.SolicitudRepository;

@Service
public class SolicitudService {

	@Autowired
	SolicitudRepository solicitudRepository;

	public List<Solicitud> findAll() {
		return solicitudRepository.findAll();
	}

	public List<Solicitud> getSolicitudSinServicio() {
		return solicitudRepository.getSolicitudSinServicio();
	}

	public List<Solicitud> getSolicitudConServicio() {
		return solicitudRepository.getSolicitudConServicio();
	}

	public List<String> getDatosSolicitud(int id) {
		return solicitudRepository.getDatosSolicitud(id);
	}

	public Solicitud findById(int id) {
		return solicitudRepository.findById(id);
	}

	public Solicitud save(Solicitud solicitud) {
		return solicitudRepository.save(solicitud);
	}

	public void deleteById(int id) {
		solicitudRepository.deleteById(id);
	}

}
