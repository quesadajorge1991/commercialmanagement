/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author wason
 */
@Entity
@Table(name = "servicios")
public class Servicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nro;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaServicio;
    private String cultivo;
    private String tipoAfectacion;

    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaAfectacion;
    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaAfectacionfin;
    
    
    
    private String nroFactura;
    private String zonaAfectacion;
    private String entregado;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pueblo pueblo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    private Grupo grupo;

    public Servicio() {
    }

    public Servicio(int nro) {
        this.nro = nro;
    }

    
    
    
    public Servicio(String cultivo, String tipoAfectacion, Date fechaAfectacion, Date fechaAfectacionfin,String zonaAfectacion) {
		super();
		this.cultivo = cultivo;
		this.tipoAfectacion = tipoAfectacion;
		this.fechaAfectacion = fechaAfectacion;
		this.fechaAfectacionfin = fechaAfectacionfin;
		this.zonaAfectacion = zonaAfectacion;
	}
    
    
    

	public Servicio(Date fechaServicio,
            String cultivo,
            String tipoAfectacion,
            Date fechaAfectacion,
            Date fechaAfectacionfin,
            String nroFactura,
            String zonaAfectacion,
            String entregado,
            Pueblo pueblo,
            Cliente cliente,
            Grupo grupo) {
        this.fechaServicio = fechaServicio;
        this.cultivo = cultivo;
        this.tipoAfectacion = tipoAfectacion;
        this.fechaAfectacion = fechaAfectacion;
        this.fechaAfectacionfin = fechaAfectacionfin;
        this.nroFactura = nroFactura;
        this.zonaAfectacion = zonaAfectacion;
        this.entregado = entregado;
        this.pueblo = pueblo;
        this.cliente = cliente;
        this.grupo = grupo;
    }

    public Servicio(int nro, Date fechaServicio, 
            String cultivo, String tipoAfectacion, 
            Date fechaAfectacion,
            Date fechaAfectacionfin,
            String nroFactura, 
            String zona_afectacion, String entregado, 
            Pueblo pueblo, Cliente cliente, Grupo grupo) {
        this.nro = nro;
        this.fechaServicio = fechaServicio;
        this.cultivo = cultivo;
        this.tipoAfectacion = tipoAfectacion;
        this.fechaAfectacion = fechaAfectacion;
        this.fechaAfectacionfin = fechaAfectacionfin;
        this.nroFactura = nroFactura;
        this.zonaAfectacion = zona_afectacion;
        this.entregado = entregado;
        this.pueblo = pueblo;
        this.cliente = cliente;
        this.grupo = grupo;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public Date getFechaAfectacionfin() {
		return fechaAfectacionfin;
	}

	public void setFechaAfectacionfin(Date fechaAfectacionfin) {
		this.fechaAfectacionfin = fechaAfectacionfin;
	}

	public Date getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public String getTipoAfectacion() {
        return tipoAfectacion;
    }

    public void setTipoAfectacion(String tipoAfectacion) {
        this.tipoAfectacion = tipoAfectacion;
    }

    public Date getFechaAfectacion() {
        return fechaAfectacion;
    }

    public void setFechaAfectacion(Date fechaAfectacion) {
        this.fechaAfectacion = fechaAfectacion;
    }

    public String getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(String nroFactura) {
        this.nroFactura = nroFactura;
    }

    public String getZonaAfectacion() {
        return zonaAfectacion;
    }

    public void setZonaAfectacion(String zonaAfectacion) {
        this.zonaAfectacion = zonaAfectacion;
    }

    public String getEntregado() {
        return entregado;
    }

    public void setEntregado(String entregado) {
        this.entregado = entregado;
    }

    public Pueblo getPueblo() {
        return pueblo;
    }

    public void setPueblo(Pueblo pueblo) {
        this.pueblo = pueblo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

	public Servicio(Date fechaAfectacionfin) {
		super();
		this.fechaAfectacionfin = fechaAfectacionfin;
	}
    
    
}
