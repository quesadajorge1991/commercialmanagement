package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springbootapplication.SpringBootApplication.Entity.Provincia;

public interface ProvinciaRepository extends CrudRepository<Provincia, Long> {

	@Query("FROM Provincia p where p.id!=?1")
	List<Provincia> getProvinciaExcepto(long id);



	Provincia findByNombProv(String nombProv);

	/*
	 * @Query("FROM Provincia p where p.nomb_prov LIKE '%Burton'") List<Provincia>
	 * getProvinciaActual(String );
	 */

}
