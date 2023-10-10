package com.springbootapplication.SpringBootApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springbootapplication.SpringBootApplication.Entity.Pueblo;


public interface PuebloRepository extends CrudRepository<Pueblo, Long> {
	
	
	@Query("FROM Pueblo p  where  p.municipio.id=?1")
	List<Pueblo> getPueblosByMunicipios(long id);
	
	@Query("SELECT nomb_pueb FROM Pueblo p  where  p.municipio.id=?1")
	List<String> getNamePueblo(long id);

}
