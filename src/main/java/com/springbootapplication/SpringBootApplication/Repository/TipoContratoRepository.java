package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springbootapplication.SpringBootApplication.Entity.TipoContrato;

public interface TipoContratoRepository extends JpaRepository<TipoContrato, Integer> {

	@Query("SELECT tipoContrato FROM TipoContrato tc WHERE tc.tipoContrato=?1")
	List<String> findByTipoContrato(String tipoContrato);

}
