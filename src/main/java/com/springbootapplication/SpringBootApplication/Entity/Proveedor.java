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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nro;
    
    
    private String nro_registro;
    
    
    @Size(min = 2, message = "Número de contrato, obligatorio con mas de 2 caracteres")
    private String nro_contrato;
    
    
   
    
    @Size(min = 2, message = "Nombre del Proveedor, obligatorio con mas de 2 caracteres")
    private String nombre_proveedor;
    
    
    @Size(min = 2, message = "Alias del Proveedor, obligatorio con mas de 2 caracteres")
    private String alias_proveedor;


    @NotNull(message = "La fecha de suscripción no puede estar vacía")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fecha_suscripcion;

    @NotNull(message = "La fecha de vigencia no puede estar vacía")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date vigencia;
    
    
    @Size(min = 2, message = "Ficha del cliente, obligatorio con mas de 2 caracteres")
    private String ficha_cliente;
    
    
    @Size(min = 2, message = "Código reup, obligatorio con mas de 2 caracteres")
    private String codigo_REUP;
    
    
    @NotNull(message = "La cuenta bancaria, no puede estar vacía")
    private String cuenta_bancaria;
    
    
    
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
    private Tipo_Contrato tipo_contrato;

    public Proveedor() {
    }

    public Proveedor(int nro) {
        this.nro = nro;
    }

    public Proveedor(String nro_contrato, String nro_registro, 
            String nombre_proveedor, 
            String alias_proveedor, Date fecha_suscripcion, 
            Date vigencia, String ficha_cliente, String codigo_REUP, 
            String cuenta_bancaria, String dictaminado, String telefono, 
            String email, String direccion, String observaciones, 
            String vencido, String notificar, Municipio municipio, 
            Tipo_Contrato tipo_contrato) {
        this.nro_contrato = nro_contrato;
        this.nro_registro = nro_registro;
        this.nombre_proveedor = nombre_proveedor;
        this.alias_proveedor = alias_proveedor;
        this.fecha_suscripcion = fecha_suscripcion;
        this.vigencia = vigencia;
        this.ficha_cliente = ficha_cliente;
        this.codigo_REUP = codigo_REUP;
        this.cuenta_bancaria = cuenta_bancaria;
        this.dictaminado = dictaminado;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.observaciones = observaciones;
        this.vencido = vencido;
        this.notificar = notificar;
        this.municipio = municipio;
        this.tipo_contrato = tipo_contrato;
    }

    public Proveedor(int nro, String nro_contrato, String nro_registro, 
            String nombre_proveedor, String alias_proveedor, 
            Date fecha_suscripcion, Date vigencia, String ficha_cliente, 
            String codigo_REUP, String cuenta_bancaria, String dictaminado, 
            String telefono, String email, String direccion, 
            String observaciones, String vencido, String notificar, 
            Municipio municipio, Tipo_Contrato tipo_contrato) {
        this.nro = nro;
        this.nro_contrato = nro_contrato;
        this.nro_registro = nro_registro;
        this.nombre_proveedor = nombre_proveedor;
        this.alias_proveedor = alias_proveedor;
        this.fecha_suscripcion = fecha_suscripcion;
        this.vigencia = vigencia;
        this.ficha_cliente = ficha_cliente;
        this.codigo_REUP = codigo_REUP;
        this.cuenta_bancaria = cuenta_bancaria;
        this.dictaminado = dictaminado;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.observaciones = observaciones;
        this.vencido = vencido;
        this.notificar = notificar;
        this.municipio = municipio;
        this.tipo_contrato = tipo_contrato;
    }

    

    public String getNro_registro() {
        return nro_registro;
    }

    public void setNro_registro(String nro_registro) {
        this.nro_registro = nro_registro;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getNro_contrato() {
        return nro_contrato;
    }

    public void setNro_contrato(String nro_contrato) {
        this.nro_contrato = nro_contrato;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    public String getAlias() {
        return alias_proveedor;
    }

    public void setAlias(String alias) {
        this.alias_proveedor = alias;
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

    public String getFicha_cliente() {
        return ficha_cliente;
    }

    public void setFicha_cliente(String ficha_cliente) {
        this.ficha_cliente = ficha_cliente;
    }

    public String getCodigo_REUP() {
        return codigo_REUP;
    }

    public void setCodigo_REUP(String codigo_REUP) {
        this.codigo_REUP = codigo_REUP;
    }

    public String getCuenta_bancaria() {
        return cuenta_bancaria;
    }

    public void setCuenta_bancaria(String cuenta_bancaria) {
        this.cuenta_bancaria = cuenta_bancaria;
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

    public String getAlias_proveedor() {
        return alias_proveedor;
    }

    public void setAlias_proveedor(String alias_proveedor) {
        this.alias_proveedor = alias_proveedor;
    }

    public Tipo_Contrato getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(Tipo_Contrato tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }    
    
}
