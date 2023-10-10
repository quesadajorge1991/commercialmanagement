package com.springbootapplication.SpringBootApplication.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springbootapplication.SpringBootApplication.Entity.Grupo;




@Repository
public interface GrupoRepository extends CrudRepository<Grupo, String> {

}
