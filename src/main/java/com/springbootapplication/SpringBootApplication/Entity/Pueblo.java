/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 *
 * @author Dane
 */
@Entity
@Table
public class Pueblo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nomb_pueb;

	@ManyToOne(fetch = FetchType.EAGER)
	private Municipio municipio;

	@OneToMany(mappedBy = "pueblo")
	private List<Cliente> clientes;

	@OneToMany(mappedBy = "pueblo")
	// @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	//@JsonManagedReference
	private List<Solicitud> solicituds;

	@OneToMany(mappedBy = "pueblo")
	private List<Servicio> servicios;

	public Pueblo() {
	}

	public Pueblo(long id) {
		this.id = id;
	}
	

	public Pueblo(long id, String nomb_pueb, Municipio municipio) {
		super();
		this.id = id;
		this.nomb_pueb = nomb_pueb;
		this.municipio = municipio;
	}

	public Pueblo(String nomb_pueb, Municipio municipio) {
		this.nomb_pueb = nomb_pueb;
		this.municipio = municipio;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomb_pueb() {
		return nomb_pueb;
	}

	public void setNomb_pueb(String nomb_pueb) {
		this.nomb_pueb = nomb_pueb;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Solicitud> getSolicituds() {
		return solicituds;
	}

	public void setSolicituds(List<Solicitud> solicituds) {
		this.solicituds = solicituds;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

}
