package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springbootapplication.SpringBootApplication.Entity.Servicio;




@Repository
public interface ServicioRepository extends CrudRepository<Servicio, Integer> {
	
	//@Modifying
	@Query(value = "delete from servicios s where s.nro=?1",nativeQuery = true)
	void deleteservicio(int nro);
	
	
	@Query(value = "FROM Servicio s WHERE s.cliente.nro=?1")
	List<Servicio> getServicioxCliente(int nro);

}
