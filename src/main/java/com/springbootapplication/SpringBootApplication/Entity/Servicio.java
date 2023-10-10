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
    private Date fecha_servicio;
    private String cultivo;
    private String tipo_afectacion;

    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha_afectacion;
    
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha_afectacionfin;
    
    
    
    private String nro_factura;
    private String zona_afectacion;
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

    
    
    
    public Servicio(String cultivo, String tipo_afectacion, Date fecha_afectacion, Date fecha_afectacionfin,String zona_afectacion) {
		super();
		this.cultivo = cultivo;
		this.tipo_afectacion = tipo_afectacion;
		this.fecha_afectacion = fecha_afectacion;
		this.fecha_afectacionfin = fecha_afectacionfin;
		this.zona_afectacion = zona_afectacion;
	}
    
    
    

	public Servicio(Date fecha_servicio,
            String cultivo,
            String tipo_afectacion,
            Date fecha_afectacion,
            Date fecha_afectacionfin,
            String nro_factura,
            String zona_afectacion,
            String entregado,
            Pueblo pueblo,
            Cliente cliente,
            Grupo grupo) {
        this.fecha_servicio = fecha_servicio;
        this.cultivo = cultivo;
        this.tipo_afectacion = tipo_afectacion;
        this.fecha_afectacion = fecha_afectacion;
        this.fecha_afectacionfin = fecha_afectacionfin;
        this.nro_factura = nro_factura;
        this.zona_afectacion = zona_afectacion;
        this.entregado = entregado;
        this.pueblo = pueblo;
        this.cliente = cliente;
        this.grupo = grupo;
    }

    public Servicio(int nro, Date fecha_servicio, 
            String cultivo, String tipo_afectacion, 
            Date fecha_afectacion,
            Date fecha_afectacionfin,
            String nro_factura, 
            String zona_afectacion, String entregado, 
            Pueblo pueblo, Cliente cliente, Grupo grupo) {
        this.nro = nro;
        this.fecha_servicio = fecha_servicio;
        this.cultivo = cultivo;
        this.tipo_afectacion = tipo_afectacion;
        this.fecha_afectacion = fecha_afectacion;
        this.fecha_afectacionfin = fecha_afectacionfin;
        this.nro_factura = nro_factura;
        this.zona_afectacion = zona_afectacion;
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

    public Date getFecha_afectacionfin() {
		return fecha_afectacionfin;
	}

	public void setFecha_afectacionfin(Date fecha_afectacionfin) {
		this.fecha_afectacionfin = fecha_afectacionfin;
	}

	public Date getFecha_servicio() {
        return fecha_servicio;
    }

    public void setFecha_servicio(Date fecha_servicio) {
        this.fecha_servicio = fecha_servicio;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
    }

    public String getTipo_afectacion() {
        return tipo_afectacion;
    }

    public void setTipo_afectacion(String tipo_afectacion) {
        this.tipo_afectacion = tipo_afectacion;
    }

    public Date getFecha_afectacion() {
        return fecha_afectacion;
    }

    public void setFecha_afectacion(Date fecha_afectacion) {
        this.fecha_afectacion = fecha_afectacion;
    }

    public String getNro_factura() {
        return nro_factura;
    }

    public void setNro_factura(String nro_factura) {
        this.nro_factura = nro_factura;
    }

    public String getZona_afectacion() {
        return zona_afectacion;
    }

    public void setZona_afectacion(String zona_afectacion) {
        this.zona_afectacion = zona_afectacion;
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

	public Servicio(Date fecha_afectacionfin) {
		super();
		this.fecha_afectacionfin = fecha_afectacionfin;
	}
    
    
}
