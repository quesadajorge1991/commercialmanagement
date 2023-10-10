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
    private String nro_contrato;
    private String nombre_cliente;
    private String tipo_contrato;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha_suscripcion;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date vigencia;
    
    private String nro_suplemento;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha_suplemento;

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
    
    

    public Cliente(String nro_contrato) {
		super();
		this.nro_contrato = nro_contrato;
	}



	public Cliente(String nombre_cliente, String ci, String telefono, String direccion) {
		super();
		this.nombre_cliente = nombre_cliente;
		this.ci = ci;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	

	public Cliente(String nombre_cliente, String ci, String telefono, String direccion,Pueblo pueblo) {
		super();
		this.nombre_cliente = nombre_cliente;
		this.ci = ci;
		this.telefono = telefono;
		this.direccion = direccion;
		this.pueblo = pueblo;
	}
	



	public Cliente(int nro) {
        this.nro = nro;
    }

    public Cliente(int nro, String nro_contrato, String nombre_cliente, String tipo_contrato,
            Date fecha_suscripcion, Date vigencia, String nro_suplemento, Date fecha_suplemento,
            String entidad, String ci, String telefono, String direccion, String vencido, Pueblo pueblo) {
        this.nro = nro;
        this.nro_contrato = nro_contrato;
        this.nombre_cliente = nombre_cliente;
        this.tipo_contrato = tipo_contrato;
        this.fecha_suscripcion = fecha_suscripcion;
        this.vigencia = vigencia;
        this.nro_suplemento = nro_suplemento;
        this.fecha_suplemento = fecha_suplemento;
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
        this.nro_contrato = nro_contrato;
        this.nombre_cliente = nombre_cliente;
        this.tipo_contrato = tipo_contrato;
        this.fecha_suscripcion = fecha_suscripcion;
        this.vigencia = vigencia;
        this.nro_suplemento = nro_suplemento;
        this.fecha_suplemento = fecha_suplemento;
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
        this.nro_contrato = nro_contrato;
        this.nombre_cliente = nombre_cliente;
        this.tipo_contrato = tipo_contrato;
        this.fecha_suscripcion = fecha_suscripcion;
        this.vigencia = vigencia;
        this.nro_suplemento = nro_suplemento;
      
        this.entidad = entidad;
        this.ci = ci;
        this.telefono = telefono;
        this.direccion = direccion;
        this.vencido = vencido;
        this.pueblo = pueblo;
    }
    public String getNro_contrato() {
        return nro_contrato;
    }

    public void setNro_contrato(String nro_contrato) {
        this.nro_contrato = nro_contrato;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public String getNro_suplemento() {
        return nro_suplemento;
    }

    public void setNro_suplemento(String nro_suplemento) {
        this.nro_suplemento = nro_suplemento;
    }

    public Date getFecha_suscripcion() {
        return fecha_suscripcion;
    }

    public void setFecha_suscripcion(Date fecha_suscripcion) {
        this.fecha_suscripcion = fecha_suscripcion;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public Date getFecha_suplemento() {
        return fecha_suplemento;
    }

    public void setFecha_suplemento(Date fecha_suplemento) {
        this.fecha_suplemento = fecha_suplemento;
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
