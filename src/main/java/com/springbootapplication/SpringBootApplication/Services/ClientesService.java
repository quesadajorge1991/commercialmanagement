package com.springbootapplication.SpringBootApplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.Cliente;
import com.springbootapplication.SpringBootApplication.Repository.ClientesRepository;

@Service
public class ClientesService {

	@Autowired
	ClientesRepository clientesRepository;

	public List<Cliente> findByNroContrato(String nroContrato) {
		return clientesRepository.findByNroContrato(nroContrato);
	}

	public List<Cliente> findByCi(String ci) {
		return clientesRepository.findByCi(ci);
	}

	public List<Cliente> findAll() {
		return clientesRepository.findAll();
	}

	public Cliente findById(int nro) {
		return clientesRepository.findById(nro).get();
	}

	public Cliente save(Cliente cliente) {
		return clientesRepository.save(cliente);
	}

	public void deleteById(int id) {
		clientesRepository.deleteById(id);
	}

}
