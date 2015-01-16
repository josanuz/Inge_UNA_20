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
 * @author aaron
 */
public class Factura_Reparacion {

    public Factura_Reparacion(int numero, int codigo_Cliente, int codigo_reparacion, String fecha, double impuestos, double subTotal, double total, double saldo) {
        this.numero = new SimpleIntegerProperty(numero);
        this.codigo_Cliente = new SimpleIntegerProperty(codigo_Cliente);
        this.codigo_reparacion = new SimpleIntegerProperty(codigo_reparacion);
        this.fecha = new SimpleStringProperty(fecha);
        this.impuestos = new SimpleDoubleProperty(impuestos);
        this.subTotal = new SimpleDoubleProperty(subTotal);
        this.total = new SimpleDoubleProperty(total);
        this.saldo = new SimpleDoubleProperty(saldo);
    }

    public Factura_Reparacion() {
        this.numero = new SimpleIntegerProperty(0);
        this.codigo_Cliente = new SimpleIntegerProperty(0);
        this.codigo_reparacion = new SimpleIntegerProperty(0);
        this.fecha = new SimpleStringProperty(null);
        this.impuestos = new SimpleDoubleProperty(0);
        this.subTotal = new SimpleDoubleProperty(0);
        this.total = new SimpleDoubleProperty(0);
        this.saldo = new SimpleDoubleProperty(0);
    }

    public int getNumero() {
        return numero.get();
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public int  getCodigo_Cliente() {
        return codigo_Cliente.get();
    }

    public void setCodigo_Cliente(int codigo_Cliente) {
        this.codigo_Cliente.get();
    }

    public int getCodigo_reparacion() {
        return codigo_reparacion.get();
    }

    public void setCodigo_reparacion(int codigo_reparacion) {
        this.codigo_reparacion.set(codigo_reparacion);
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

    public double getSubTotal() {
        return subTotal.get();
    }

    public void setSubTotal(double subTotal) {
        this.subTotal.set(subTotal);
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
    private IntegerProperty codigo_Cliente;
    private IntegerProperty codigo_reparacion;
    private StringProperty fecha;
    private DoubleProperty impuestos;
    private DoubleProperty subTotal;
    private DoubleProperty total;
    private DoubleProperty saldo;

}
