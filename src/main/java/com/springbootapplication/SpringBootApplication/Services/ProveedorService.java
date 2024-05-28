package com.springbootapplication.SpringBootApplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Proveedor;
import com.springbootapplication.SpringBootApplication.Repository.ProovedorRepository;

@Service
public class ProovedorService {

	@Autowired
	ProovedorRepository proovedorRepository;

	public List<Proveedor> findByOrderByVigencia() {
		return proovedorRepository.findByOrderByVigencia();
	}

	public List<Proveedor> findByOrderByNroRegistro() {
		return proovedorRepository.findByOrderByNroRegistro();
	}

	public Proveedor findById(int id) {
		return proovedorRepository.findById(id).get();
	}

	public Proveedor save(Proveedor proveedor) {
		return proovedorRepository.save(proveedor);
	}

	// deleteById

	public void deleteById(int id) {
		proovedorRepository.deleteById(id);
	}

}
