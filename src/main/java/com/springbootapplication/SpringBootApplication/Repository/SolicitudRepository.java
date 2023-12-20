package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootapplication.SpringBootApplication.Entity.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

	@Query("FROM Solicitud s WHERE s.servicio=false")
	List<Solicitud> getSolicitudSinServicio();

	@Query("FROM Solicitud s WHERE s.servicio=true")
	List<Solicitud> getSolicitudConServicio();

	@Query(value = "SELECT s.nombPers,s.direccion, s.telefono, s.ci, s.cultDanado,s.tipoAfect, s.fecha, s.zonaAfect, s.pueblo.municipio.provincia.nombProv, s.pueblo.municipio.nomb_mun, s.pueblo.nomb_pueb  FROM Solicitud s WHERE s.id=?1 ")
	List<String> getDatosSolicitud(int id);

	Solicitud findById(int id);

}
