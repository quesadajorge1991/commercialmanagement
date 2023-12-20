/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springbootapplication.SpringBootApplication.Entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


/**
 *
 * @author wason
 */
@Entity
@Table(name = "proveedores")
public class Proveedor implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nro;
    
    private String nroRegistro;
    
    
    @Size(min = 2, message = "Número de contrato, obligatorio con mas de 2 caracteres")
    private String nroContrato;
    
    
   
    
    @Size(min = 2, message = "Nombre del Proveedor, obligatorio con mas de 2 caracteres")
    private String nombreProveedor;
    
    
    @Size(min = 2, message = "Alias del Proveedor, obligatorio con mas de 2 caracteres")
    private String aliasProveedor;


    @NotNull(message = "La fecha de suscripción no puede estar vacía")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaSuscripcion;

    @NotNull(message = "La fecha de vigencia no puede estar vacía")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date vigencia;
    
    
    @Size(min = 2, message = "Ficha del cliente, obligatorio con mas de 2 caracteres")
    private String fichaCliente;
    
    
    @Size(min = 2, message = "Código reup, obligatorio con mas de 2 caracteres")
    private String codigoREUP;
    
    
    @NotNull(message = "La cuenta bancaria, no puede estar vacía")
    private String cuentaBancaria;
    
    
    
    private String dictaminado;
    
    
  /*  @Size(min = 2, message = "Teléfono obligatorio con mas de 2 caracteres")
    @NotBlank(message = "El telefono, no puede estar vacio")*/
    private String telefono;
    
    
  /*  @NotBlank(message = "Email, no puede estar vacía")
    @Email(message = "Debe ser un correo valido")*/
    private String email;
    
    
    @Size(min = 2, message = "Campo obligatorio con mas de 2 caracteres")
    private String direccion;
    
    
  /*  @Size(min = 2, message = "Campo obligatorio con mas de 2 caracteres")*/
    private String observaciones;
    
    
    private String vencido;
    
    
    private String notificar;

    @ManyToOne(fetch = FetchType.EAGER)
    private Municipio municipio;

    @ManyToOne(fetch = FetchType.EAGER)
    private TipoContrato tipoContrato;

    public Proveedor() {
    }

    public Proveedor(int nro) {
        this.nro = nro;
    }

    public Proveedor(String nroContrato, String nroRegistro, 
            String nombreProveedor, 
            String aliasProveedor, Date fechaSuscripcion, 
            Date vigencia, String fichaCliente, String codigoREUP, 
            String cuentaBancaria, String dictaminado, String telefono, 
            String email, String direccion, String observaciones, 
            String vencido, String notificar, Municipio municipio, 
            TipoContrato tipoContrato) {
        this.nroContrato = nroContrato;
        this.nroRegistro = nroRegistro;
        this.nombreProveedor = nombreProveedor;
        this.aliasProveedor = aliasProveedor;
        this.fechaSuscripcion = fechaSuscripcion;
        this.vigencia = vigencia;
        this.fichaCliente = fichaCliente;
        this.codigoREUP = codigoREUP;
        this.cuentaBancaria = cuentaBancaria;
        this.dictaminado = dictaminado;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.observaciones = observaciones;
        this.vencido = vencido;
        this.notificar = notificar;
        this.municipio = municipio;
        this.tipoContrato = tipoContrato;
    }

    public Proveedor(int nro, String nro_contrato, String nroRegistro, 
            String nombreProveedor, String aliasProveedor, 
            Date fechaSuscripcion, Date vigencia, String fichaCliente, 
            String codigoREUP, String cuentaBancaria, String dictaminado, 
            String telefono, String email, String direccion, 
            String observaciones, String vencido, String notificar, 
            Municipio municipio, TipoContrato tipoContrato) {
        this.nro = nro;
        this.nroContrato = nro_contrato;
        this.nroRegistro = nroRegistro;
        this.nombreProveedor = nombreProveedor;
        this.aliasProveedor = aliasProveedor;
        this.fechaSuscripcion = fechaSuscripcion;
        this.vigencia = vigencia;
        this.fichaCliente = fichaCliente;
        this.codigoREUP = codigoREUP;
        this.cuentaBancaria = cuentaBancaria;
        this.dictaminado = dictaminado;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.observaciones = observaciones;
        this.vencido = vencido;
        this.notificar = notificar;
        this.municipio = municipio;
        this.tipoContrato = tipoContrato;
    }

    

 


	

    public String getNroRegistro() {
		return nroRegistro;
	}

	public void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

  

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public String getVencido() {
        return vencido;
    }

    public void setVencido(String vencido) {
        this.vencido = vencido;
    }

    public String getNotificar() {
        return notificar;
    }

    public void setNotificar(String notificar) {
        this.notificar = notificar;
    }

   

    public String getDictaminado() {
        return dictaminado;
    }

    public void setDictaminado(String dictaminado) {
        this.dictaminado = dictaminado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

	public String getNroContrato() {
		return nroContrato;
	}

	public void setNroContrato(String nroContrato) {
		this.nroContrato = nroContrato;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getAliasProveedor() {
		return aliasProveedor;
	}

	public void setAliasProveedor(String aliasProveedor) {
		this.aliasProveedor = aliasProveedor;
	}

	public Date getFechaSuscripcion() {
		return fechaSuscripcion;
	}

	public void setFechaSuscripcion(Date fechaSuscripcion) {
		this.fechaSuscripcion = fechaSuscripcion;
	}

	public String getFichaCliente() {
		return fichaCliente;
	}

	public void setFichaCliente(String fichaCliente) {
		this.fichaCliente = fichaCliente;
	}

	public String getCodigoREUP() {
		return codigoREUP;
	}

	public void setCodigoREUP(String codigoREUP) {
		this.codigoREUP = codigoREUP;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

   
    
}
