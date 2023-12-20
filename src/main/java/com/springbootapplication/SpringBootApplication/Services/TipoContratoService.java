package com.springbootapplication.SpringBootApplication.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.SpringBootApplication.Entity.TipoContrato;
import com.springbootapplication.SpringBootApplication.Repository.TipoContratoRepository;

@Service
public class TipoContratoService {

	@Autowired
	TipoContratoRepository tipoContratoRepository;

	public List<TipoContrato> findAll() {
		return tipoContratoRepository.findAll();
	}

	public List<String> findByTipoContrato(String tipoContrato) {
		return tipoContratoRepository.findByTipoContrato(tipoContrato);
	}

	public TipoContrato save(TipoContrato tipoContrato) {
		return tipoContratoRepository.save(tipoContrato);
	}

}
