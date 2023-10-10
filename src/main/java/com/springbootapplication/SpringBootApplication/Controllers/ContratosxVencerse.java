/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package    com.springbootapplication.SpringBootApplication.Controllers;

import com.springbootapplication.SpringBootApplication.Entity.Proveedor;

/**
 *
 * @author jenice
 */
public class ContratosxVencerse {

    private Proveedor proveedor;
    private int dias;

    public ContratosxVencerse() {
    }

    public ContratosxVencerse(Proveedor proveedor, int dias) {
        this.proveedor = proveedor;
        this.dias = dias;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

}
