/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.io.Serializable;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Dane
 */
@Entity
@Table
public class Municipio implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    
    public String nomb_mun;

    @ManyToOne(fetch = FetchType.EAGER)
    public Provincia provincia;

    public Municipio() {
    }
    
    

    public Municipio(String nomb_mun, Provincia provincia) {
		super();
		this.nomb_mun = nomb_mun;
		this.provincia = provincia;
	}



	public Municipio(Provincia provincia) {
		super();
		this.provincia = provincia;
	}



	public Municipio(long id, String nomb_mun, Provincia provincia) {
        this.id = id;
        this.nomb_mun = nomb_mun;
        this.provincia = provincia;
    }

    public Municipio(long id) {
        this.id = id;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomb_mun() {
        return nomb_mun;
    }

    public void setNomb_mun(String nomb_mun) {
        this.nomb_mun = nomb_mun;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

}
