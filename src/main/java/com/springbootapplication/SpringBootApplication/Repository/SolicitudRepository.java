package com.springbootapplication.SpringBootApplication.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springbootapplication.SpringBootApplication.Entity.Solicitud;


public interface SolicitudRepository extends CrudRepository<Solicitud, Integer> {

	@Query("FROM Solicitud s WHERE s.servicio=false")
	List<Solicitud> getSolicitudSinServicio();

	@Query("FROM Solicitud s WHERE s.servicio=true")
	List<Solicitud> getSolicitudConServicio();

	@Query(value = "SELECT s.nomb_pers,s.direccion, s.telefono, s.ci, s.cult_danado,s.tipo_afect, s.fecha, s.zona_afect, s.pueblo.municipio.provincia.nomb_prov, s.pueblo.municipio.nomb_mun, s.pueblo.nomb_pueb  FROM Solicitud s WHERE s.id=?1 ")
	List<String> getDatosSolicitud(int id);
	
	@Query(value = "FROM Solicitud s WHERE s.id=?1 ")
	List<Solicitud> getDatosSolicitudd(int id);
	
	
	

}
