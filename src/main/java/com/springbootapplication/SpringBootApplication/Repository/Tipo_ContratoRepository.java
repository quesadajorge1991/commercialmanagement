package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springbootapplication.SpringBootApplication.Entity.Tipo_Contrato;



@Repository
public interface Tipo_ContratoRepository extends CrudRepository<Tipo_Contrato, Integer> {
	
	@Query("SELECT tipo_contrato FROM Tipo_Contrato tc WHERE tc.tipo_contrato=?1")
	List<String> getTipoContrato(String tipo_contrato);

}
