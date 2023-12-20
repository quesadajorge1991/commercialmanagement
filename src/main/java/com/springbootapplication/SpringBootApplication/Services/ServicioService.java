package com.springbootapplication.SpringBootApplication.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Servicio;
import com.springbootapplication.SpringBootApplication.Entity.Solicitud;
import com.springbootapplication.SpringBootApplication.Repository.ServicioRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ServicioService {

	@Autowired
	ServicioRepository servicioRepository;

	public List<Servicio> findAll() {
		return servicioRepository.findAll();
	}

	public void deleteById(int nro) {
		servicioRepository.deleteById(nro);
	}

	public Servicio save(Servicio servicio) {
		return servicioRepository.save(servicio);
	}

	public void deleteservicio(int nro) {
		servicioRepository.deleteById(nro);
	}

	public Servicio findById(int nro) {
		return servicioRepository.findById(nro).get();
	}
	
	
	


}
