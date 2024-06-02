package com.springbootapplication.SpringBootApplication.Services;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Municipio;
import com.springbootapplication.SpringBootApplication.Repository.MunicipioRepository;

@Service
public class MunicipioService {

	@Autowired
	MunicipioRepository municipioRepository;

	public void save(Municipio municipio) {
		municipioRepository.save(municipio);
	}

	public List<Municipio> findAll() {
		return municipioRepository.findAll();
	}

	public Municipio findById(long id) {
		return municipioRepository.findById(id).get();
	}

	public List<Municipio> findMunicipiosByProvincia(long id) {
		return municipioRepository.findMunicipiosByProvincia(id);
	}

	public List<String> listMunicipios() {
		return municipioRepository.listMunicipios();
	}

	/*
	 * public boolean existMunicipio(String municipio) { // boolean flag=false;
	 * 
	 * final boolean flag = true; findAll().forEach( Item -> { if
	 * (Item.nomb_mun.contains("Fomento")) { flag = true; } else {
	 * 
	 * } });
	 * 
	 * }
	 */

}
