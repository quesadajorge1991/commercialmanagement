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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Dane
 */
@Entity
@Table
public class Solicitud implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 2, message = "Nombre de persona, obligatorio con mas de 2 caracteres")
	@NotBlank(message = "El telefono, no puede estar vacio")
	private String nombPers;

	@Size(min = 2, message = "Carnet de Identidad o Código NIT, obligatorio con mas de 2 caracteres")
	@NotBlank(message = "Carnet de Identidad o Código NIT , no puede estar vacio")
	private String ci;

	@Size(min = 2, message = "La dirección es obligatoria con mas de 2 caracteres")
	@NotBlank(message = "La dirección, no puede estar vacio")
	private String direccion;

	@Size(min = 2, message = "Teléfono, obligatorio con mas de 2 caracteres")
	@NotBlank(message = "El telefono, no puede estar vacio")
	private String telefono;

	@Size(min = 2, message = "Cultivo dañado, obligatorio con mas de 2 caracteres")
	@NotBlank(message = "Cultivo dañado, no puede estar vacio")
	private String cultDanado;

	@Size(min = 2, message = "Tipo de afectación, obligatorio con mas de 2 caracteres")
	@NotBlank(message = "Tipo de afectación, no puede estar vacio")
	private String tipoAfect;

	@NotNull(message = "La fecha no puede estar vacía")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fecha;
	
	@NotNull(message = "La fecha no puede estar vacía")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechafin;

	private String zonaAfect;

	private boolean servicio;

	@ManyToOne(fetch = FetchType.EAGER)
	private Pueblo pueblo;

	public Solicitud() {
	}

	public Solicitud(int id) {
		this.id = id;
	}

	public Solicitud(String nomb_pers, String ci, String direccion, String telefono, String cult_danado,
			String tipo_afect, Date fecha,Date fechafin, String zona_afect, Pueblo pueblo) {
		this.nombPers = nomb_pers;
		this.ci = ci;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cultDanado = cult_danado;
		this.tipoAfect = tipo_afect;
		this.fecha = fecha;
		this.fechafin = fechafin;
		this.zonaAfect = zona_afect;
		this.pueblo = pueblo;
	}
	
	public Solicitud(int id,String nomb_pers, String ci, String direccion, String telefono, String cult_danado,
			String tipo_afect, Date fecha,Date fechafin, String zona_afect, Pueblo pueblo) {
		this.id = id;
		this.nombPers = nomb_pers;
		this.ci = ci;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cultDanado = cult_danado;
		this.tipoAfect = tipo_afect;
		this.fecha = fecha;
		this.fechafin = fechafin;
		this.zonaAfect = zona_afect;
		this.pueblo = pueblo;
	}

	public Solicitud(int id, String nomb_pers, String ci, String direccion, String telefono, String cult_danado,
			String tipo_afect, Date fecha,Date fechafin, String zona_afect, boolean servicio, Pueblo pueblo) {
		this.id = id;
		this.nombPers = nomb_pers;
		this.ci = ci;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cultDanado = cult_danado;
		this.tipoAfect = tipo_afect;
		this.fecha = fecha;
		this.fechafin = fechafin;
		this.zonaAfect = zona_afect;
		this.servicio = servicio;
		this.pueblo = pueblo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombPers() {
		return nombPers;
	}

	public void setNombPers(String nomb_pers) {
		this.nombPers = nomb_pers;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCultDanado() {
		return cultDanado;
	}

	public void setCultDanado(String cult_danado) {
		this.cultDanado = cult_danado;
	}

	public String getTipoAfect() {
		return tipoAfect;
	}

	public void setTipoAfect(String tipo_afect) {
		this.tipoAfect = tipo_afect;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getZonaAfect() {
		return zonaAfect;
	}

	public void setZonaAfect(String zona_afect) {
		this.zonaAfect = zona_afect;
	}

	public boolean isServicio() {
		return servicio;
	}

	public void setServicio(boolean servicio) {
		this.servicio = servicio;
	}

	public Pueblo getPueblo() {
		return pueblo;
	}

	public void setPueblo(Pueblo pueblo) {
		this.pueblo = pueblo;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	
	

}
