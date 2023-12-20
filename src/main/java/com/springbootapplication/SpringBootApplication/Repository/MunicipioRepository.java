package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootapplication.SpringBootApplication.Entity.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

	@Query("FROM Municipio m WHERE m.provincia.id=?1")
	List<Municipio> findMunicipiosByProvincia(long id);

}
