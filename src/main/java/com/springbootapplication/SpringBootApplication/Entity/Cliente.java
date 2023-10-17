/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author wason
 */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nro;
    private String nroContrato;
    private String nombreCliente;
    private String tipoContrato;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaSuscripcion;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date vigencia;
    
    private String nroSuplemento;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaSuplemento;

    private String entidad;
    
    private String ci;
    
    private String telefono;
    
    private String direccion;
    
    private String vencido;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pueblo pueblo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cliente")
    private List<Servicio> servicios = new ArrayList<Servicio>();

    public Cliente() {
    }
    
    

    public Cliente(String nroContrato) {
		super();
		this.nroContrato = nroContrato;
	}



	public Cliente(String nombreCliente, String ci, String telefono, String direccion) {
		super();
		this.nombreCliente = nombreCliente;
		this.ci = ci;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	

	public Cliente(String nombreCliente, String ci, String telefono, String direccion,Pueblo pueblo) {
		super();
		this.nombreCliente = nombreCliente;
		this.ci = ci;
		this.telefono = telefono;
		this.direccion = direccion;
		this.pueblo = pueblo;
	}
	



	public Cliente(int nro) {
        this.nro = nro;
    }

    public Cliente(int nro, String nroContrato, String nombreCliente, String tipoContrato,
            Date fechaSuscripcion, Date vigencia, String nroSuplemento, Date fechaSuplemento,
            String entidad, String ci, String telefono, String direccion, String vencido, Pueblo pueblo) {
        this.nro = nro;
        this.nroContrato = nroContrato;
        this.nombreCliente = nombreCliente;
        this.tipoContrato = tipoContrato;
        this.fechaSuscripcion = fechaSuscripcion;
        this.vigencia = vigencia;
        this.nroSuplemento = nroSuplemento;
        this.fechaSuplemento = fechaSuplemento;
        this.entidad = entidad;
        this.ci = ci;
        this.telefono = telefono;
        this.direccion = direccion;
        this.vencido = vencido;
        this.pueblo = pueblo;
    }

    public Cliente(String nro_contrato, String nombre_cliente, String tipo_contrato,
            Date fecha_suscripcion, Date vigencia, String nro_suplemento,
            Date fecha_suplemento, String entidad, String ci, String telefono,
            String direccion, String vencido, Pueblo pueblo) {
        this.nroContrato = nro_contrato;
        this.nombreCliente = nombre_cliente;
        this.tipoContrato = tipo_contrato;
        this.fechaSuscripcion = fecha_suscripcion;
        this.vigencia = vigencia;
        this.nroSuplemento = nro_suplemento;
        this.fechaSuplemento = fecha_suplemento;
        this.entidad = entidad;
        this.ci = ci;
        this.telefono = telefono;
        this.direccion = direccion;
        this.vencido = vencido;
        this.pueblo = pueblo;
    }

    public Cliente(String nro_contrato, String nombre_cliente, String tipo_contrato,
            Date fecha_suscripcion, Date vigencia, String nro_suplemento,
            String entidad, String ci, String telefono,
            String direccion, String vencido, Pueblo pueblo) {
        this.nroContrato = nro_contrato;
        this.nombreCliente = nombre_cliente;
        this.tipoContrato = tipo_contrato;
        this.fechaSuscripcion = fecha_suscripcion;
        this.vigencia = vigencia;
        this.nroSuplemento = nro_suplemento;
      
        this.entidad = entidad;
        this.ci = ci;
        this.telefono = telefono;
        this.direccion = direccion;
        this.vencido = vencido;
        this.pueblo = pueblo;
    }
    public String getNroContrato() {
        return nroContrato;
    }

    public void setNroContrato(String nro_contrato) {
        this.nroContrato = nro_contrato;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombre_cliente) {
        this.nombreCliente = nombre_cliente;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipo_contrato) {
        this.tipoContrato = tipo_contrato;
    }

    public String getNroSuplemento() {
        return nroSuplemento;
    }

    public void setNroSuplemento(String nro_suplemento) {
        this.nroSuplemento = nro_suplemento;
    }

    public Date getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(Date fecha_suscripcion) {
        this.fechaSuscripcion = fecha_suscripcion;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public Date getFechaSuplemento() {
        return fechaSuplemento;
    }

    public void setFechaSuplemento(Date fecha_suplemento) {
        this.fechaSuplemento = fecha_suplemento;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getVencido() {
        return vencido;
    }

    public void setVencido(String vencido) {
        this.vencido = vencido;
    }

    public Pueblo getPueblo() {
        return pueblo;
    }

    public void setPueblo(Pueblo pueblo) {
        this.pueblo = pueblo;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public void addServicio(Servicio servicio) {
        this.servicios.add(servicio);
    }

}
