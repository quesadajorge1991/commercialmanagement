/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "grupos")
public class Grupo implements Serializable {

    @Id
    private String nombre_grupo;

    public Grupo() {
    }

    public Grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

}
