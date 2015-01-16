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
public class Pago {
    
    public Pago(Integer recibo,Integer cuenta_a_pagar,String fecha,Double monto){
        this.recibo = new SimpleIntegerProperty(recibo);
        this.cuenta_a_pagar = new SimpleIntegerProperty(cuenta_a_pagar);
        this.fecha = new SimpleStringProperty(fecha);
        this.monto = new SimpleDoubleProperty(monto);
    }
    
    public IntegerProperty reciboProperty(){
        return this.recibo;
    }
    
    public IntegerProperty cuenta_a_pagarProperty(){
        return this.cuenta_a_pagar;
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

    public Integer getCuenta_a_pagar() {
        return cuenta_a_pagar.get();
    }

    public void setCuenta_a_pagar(Integer cuenta_a_pagar) {
        this.cuenta_a_pagar.set(cuenta_a_pagar);
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
    private IntegerProperty cuenta_a_pagar;
    private StringProperty fecha;
    private DoubleProperty monto;
    
}
