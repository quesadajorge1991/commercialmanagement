package com.springbootapplication.SpringBootApplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Pueblo;
import com.springbootapplication.SpringBootApplication.Repository.PuebloRepository;

@Service
public class PuebloService {

	@Autowired
	PuebloRepository puebloRepository;

	public List<Pueblo> findAll() {
		return puebloRepository.findAll();
	}

	public List<Pueblo> getPueblosByMunicipios(long id) {
		return puebloRepository.getPueblosByMunicipios(id);
	}

	public Pueblo findById(long id) {
		return puebloRepository.findById(id).get();

	}

	public Pueblo save(Pueblo pueblo) {
		return puebloRepository.save(pueblo);

	}

	public List<String> getNamePueblo(long id) {
		return puebloRepository.getNamePueblo(id);

	}

}
