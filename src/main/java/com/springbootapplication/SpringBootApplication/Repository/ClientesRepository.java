package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;


import com.springbootapplication.SpringBootApplication.Entity.Cliente;

public interface ClientesRepository extends CrudRepository<Cliente, Integer> {

	List<Cliente> findByNroContrato(String nroContrato);

	List<Cliente> findByCi(String ci);

}
