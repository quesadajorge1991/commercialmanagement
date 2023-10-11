package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springbootapplication.SpringBootApplication.Entity.TipoContrato;




public interface TipoContratoRepository extends CrudRepository<TipoContrato, Integer> {
	
	@Query("SELECT tipoContrato FROM TipoContrato tc WHERE tc.tipoContrato=?1")
	List<String> getTipoContrato(String tipoContrato);

}
