package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springbootapplication.SpringBootApplication.Entity.Cliente;



@Repository
public interface ClientesRepository extends CrudRepository<Cliente, Integer> {
	
	
	@Query("FROM Cliente c WHERE c.nro_contrato=?1")
	List<Cliente> getClientexNro_contrato(String nro_contrato);
	
	@Query("FROM Cliente c WHERE c.ci=?1")
	List<Cliente> getClientexCI(String ci);

}
