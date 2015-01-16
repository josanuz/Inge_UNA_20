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

/**
 *
 * @author Juan
 */
public class Cobro_Factura {
    
    public Cobro_Factura(Double monto,Integer recibo,Integer numero){
        this.monto = new SimpleDoubleProperty(monto);
        this.recibo = new SimpleIntegerProperty(recibo);
        this.numero = new SimpleIntegerProperty(numero);
    }
    
    
    public DoubleProperty montoProperty(){
        return this.monto;
    }
    
    public IntegerProperty reciboProperty(){
        return this.recibo;
    }
    
    public IntegerProperty numeroProperty(){
        return this.numero;
    }
    

    public Double getMonto() {
        return monto.get();
    }

    public void setMonto(Double monto) {
        this.monto.set(monto);
    }

    public Integer getRecibo() {
        return recibo.get();
    }

    public void setRecibo(Integer recibo) {
        this.recibo.set(recibo);
    }

    public Integer getNumero() {
        return numero.get();
    }

    public void setNumero(Integer numero) {
        this.numero.set(numero);
    }
    
    private DoubleProperty monto;
    private IntegerProperty recibo;
    private IntegerProperty numero;
    
}
