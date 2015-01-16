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
public class Pago_Factura_Compra {
    
    public Pago_Factura_Compra(Integer numero_factura_compra,Integer recibo,Double monto){
        this.numero_factura_compra = new SimpleIntegerProperty(numero_factura_compra);
        this.recibo = new SimpleIntegerProperty(recibo);
        this.monto = new SimpleDoubleProperty(monto);
   }
    
    
    public IntegerProperty numero_factura_compraProperty(){
        return this.numero_factura_compra;
    }
    
     public IntegerProperty reciboProperty(){
        return this.recibo;
    }
     
     public DoubleProperty montoProperty(){
         return this.monto;
     }
    

    public Integer getNumero_factura_compra() {
        return numero_factura_compra.get();
    }

    public void setNumero_factura_compra(Integer numero_factura_compra) {
        this.numero_factura_compra.set(numero_factura_compra);
    }

    public Integer getRecibo() {
        return recibo.get();
    }

    public void setRecibo(Integer recibo) {
        this.recibo.set(recibo);
    }

    public Double getMonto() {
        return monto.get();
    }

    public void setMonto(Double monto) {
        this.monto.set(monto);
    }
    
    
    private IntegerProperty numero_factura_compra;
    private IntegerProperty recibo;
    private DoubleProperty monto;
    
}
