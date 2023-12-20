package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springbootapplication.SpringBootApplication.Entity.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

	@Query(value = "FROM Servicio s WHERE s.cliente.nro=?1")
	List<Servicio> getServicioxCliente(int nro);

	@Modifying
	@Query(value = "DELETE FROM Servicio where nro=?1")
	void deleteById(int nro);

}
