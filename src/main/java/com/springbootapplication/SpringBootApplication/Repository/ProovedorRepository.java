package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springbootapplication.SpringBootApplication.Entity.Proveedor;

public interface ProovedorRepository extends CrudRepository<Proveedor, Integer> {

	/*
	 * @Query(value = "From Proveedor p order by date(p.vigencia)") List<Proveedor>
	 * getVigencia();
	 */

	List<Proveedor> findByOrderByVigencia();

	List<Proveedor> findByOrderByNroRegistro();

}
