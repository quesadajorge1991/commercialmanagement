package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootapplication.SpringBootApplication.Entity.Provincia;


public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

	@Query("FROM Provincia p where p.id!=?1")
	List<Provincia> getProvinciaExcepto(long id);

	Provincia findByNombProv(String nombProv);
	
	
	

}
