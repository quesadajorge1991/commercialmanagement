package com.springbootapplication.SpringBootApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootapplication.SpringBootApplication.Entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, String> {

}
