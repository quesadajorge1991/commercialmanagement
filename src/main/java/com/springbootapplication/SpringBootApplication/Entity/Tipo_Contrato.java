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
@Table(name = "tipo_contrato")
public class Tipo_Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nro;

    private String tipo_contrato;

    public Tipo_Contrato() {
    }

    public Tipo_Contrato(int nro) {
        this.nro = nro;
    }

    public Tipo_Contrato(int nro, String tipo_contrato) {
        this.nro = nro;
        this.tipo_contrato = tipo_contrato;
    }

    public Tipo_Contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

}
