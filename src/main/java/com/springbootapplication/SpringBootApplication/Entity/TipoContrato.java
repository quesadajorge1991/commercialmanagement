/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author jenice
 */
@Entity
//@Table(name = "tipo_contrato")
public class TipoContrato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nro;

    private String tipoContrato;

    public TipoContrato() {
    }

    public TipoContrato(int nro) {
        this.nro = nro;
    }

    public TipoContrato(int nro, String tipoContrato) {
        this.nro = nro;
        this.tipoContrato = tipoContrato;
    }

    public TipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

 
}
