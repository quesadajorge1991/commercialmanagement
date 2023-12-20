package com.springbootapplication.SpringBootApplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Provincia;
import com.springbootapplication.SpringBootApplication.Repository.ProvinciaRepository;

@Service
public class ProvinciaService {

	@Autowired
	ProvinciaRepository provinciaRepository;

	public List<Provincia> findAll() {
		return provinciaRepository.findAll();
	}

	public Provincia findByNombProv(String nombprov) {
		return provinciaRepository.findByNombProv(nombprov);
	}
	
	public void deleteById(long id) {
		provinciaRepository.deleteById(id);
	}

}
