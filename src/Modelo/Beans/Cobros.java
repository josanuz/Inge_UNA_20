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
public class Cobros {
    
    
    public Cobros(Integer recibo,Integer cuenta,String fecha,Double monto){
        this.recibo = new SimpleIntegerProperty(recibo);
        this.cuenta = new SimpleIntegerProperty(cuenta);
        this.fecha = new SimpleStringProperty(fecha);
        this.monto = new SimpleDoubleProperty(monto);
    
    }
    
    
    public IntegerProperty reciboProperty(){
        return this.recibo;
    }
    
    public IntegerProperty cuentaProperty(){
        return this.cuenta;
    }
    
    public StringProperty fechaProperty(){
        return this.fecha;
    }
    
    public DoubleProperty montoProperty(){
        return this.monto;
    }
    

    public Integer getRecibo() {
        return recibo.get();
    }

    public void setRecibo(Integer recibo) {
        this.recibo.set(recibo);
    }

    public Integer getCuenta() {
        return cuenta.get();
    }

    public void setCuenta(IntegerProperty cuenta) {
        this.cuenta = cuenta;
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public Double getMonto() {
        return monto.get();
    }

    public void setMonto(Double monto) {
        this.monto.set(monto);
    }
    
    private IntegerProperty recibo;
    private IntegerProperty cuenta;
    private StringProperty fecha;
    private DoubleProperty monto;
    
    
}
