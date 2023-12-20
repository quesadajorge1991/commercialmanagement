package com.springbootapplication.SpringBootApplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Municipio;
import com.springbootapplication.SpringBootApplication.Repository.MunicipioRepository;

@Service
public class MunicipioService {

	@Autowired
	MunicipioRepository municipioRepository;

	public List<Municipio> findAll() {
		return municipioRepository.findAll();
	}
	
	public Municipio findById(long id) {
		return municipioRepository.findById(id).get();
	}

	public List<Municipio> findMunicipiosByProvincia(long id) {
		return municipioRepository.findMunicipiosByProvincia(id);
	}

}
