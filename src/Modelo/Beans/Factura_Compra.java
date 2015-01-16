/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Beans;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Juan
 */
public class Factura_Compra {
    
     public Factura_Compra(int  numero, int codigo_proveedor, String fecha, double impuestos, double subtotal, double total, double saldo) {
        this.numero = new SimpleIntegerProperty (numero);
        this.codigo_proveedor =new SimpleIntegerProperty (codigo_proveedor);
        this.fecha = new SimpleStringProperty(fecha);
        this.impuestos = new SimpleDoubleProperty (impuestos);
        this.subtotal = new SimpleDoubleProperty (subtotal);
        this.total = new SimpleDoubleProperty (total);
        this.saldo = new SimpleDoubleProperty (saldo);
    }
    
    public Factura_Compra() {
        this.numero = new SimpleIntegerProperty (0);
        this.codigo_proveedor =new SimpleIntegerProperty (0);
        this.fecha = new SimpleStringProperty(null);
        this.impuestos = new SimpleDoubleProperty (0);
        this.subtotal = new SimpleDoubleProperty (0);
        this.total = new SimpleDoubleProperty (0);
        this.saldo = new SimpleDoubleProperty (0);
    }

    public int getNumero() {
        return numero.get();
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public int getCodigo_proveedor() {
        return codigo_proveedor.get();
    }

    public void setCodigo_proveedor(int codigo_proveedor) {
        this.codigo_proveedor.set(codigo_proveedor);
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public double getImpuestos() {
        return impuestos.get();
    }

    public void setImpuestos(double impuestos) {
        this.impuestos.set(impuestos);
    }

    public double getSubtotal() {
        return subtotal.get();
    }

    public void setSubtotal(double subtotal) {
        this.subtotal.set(subtotal);
    }

    public double getTotal() {
        return total.get();
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public double getSaldo() {
        return saldo.get();
    }

    public void setSaldo(double saldo) {
        this.saldo.set(saldo);
    }
    
      
    
    private IntegerProperty numero;
    private IntegerProperty codigo_proveedor;
    private StringProperty fecha;
    private DoubleProperty impuestos;
    private DoubleProperty subtotal;
    private DoubleProperty total;
    private DoubleProperty saldo;
    
}
