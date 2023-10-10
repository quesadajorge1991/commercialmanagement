/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Dane
 */
@Entity
@Table
public class Provincia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String nomb_prov;

    public Provincia() {
    }

    public Provincia(long id) {
        this.id = id;
    }

    public Provincia(long id, String nomb_prov) {
        this.id = id;
        this.nomb_prov = nomb_prov;
    }

    
    
    public Provincia(String nomb_prov) {
		super();
		this.nomb_prov = nomb_prov;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomb_prov() {
        return nomb_prov;
    }

    public void setNomb_prov(String nomb_prov) {
        this.nomb_prov = nomb_prov;
    }

}
