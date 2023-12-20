package com.springbootapplication.SpringBootApplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Grupo;
import com.springbootapplication.SpringBootApplication.Repository.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	GrupoRepository grupoRepository;

	public List<Grupo> findAll() {
		return grupoRepository.findAll();
	}

	public boolean existsById(String grupo) {
		return grupoRepository.existsById(grupo);
	}

	public Grupo save(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	

}
