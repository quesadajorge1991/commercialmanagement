package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapplication.SpringBootApplication.Entity.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findByNroContrato(String nroContrato);

	List<Cliente> findByCi(String ci);

}
