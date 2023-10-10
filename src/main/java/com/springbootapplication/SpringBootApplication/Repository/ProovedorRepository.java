package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springbootapplication.SpringBootApplication.Entity.Proveedor;


@Repository
public interface ProovedorRepository extends CrudRepository<Proveedor, Integer>{
	
	@Query(value = "From Proveedor p order by date(p.vigencia)")
	List<Proveedor> getVigencia();

}
