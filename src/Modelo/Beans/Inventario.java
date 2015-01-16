/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author aaron
 */
public class Inventario {
    
    public Inventario(int codigo_articulo, int codigo_bodega, int cantidad, int cantidad_max,int cantidad_min){
        this.codigo_articulo=new SimpleIntegerProperty(codigo_articulo);
        this.codigo_bodega=new SimpleIntegerProperty(codigo_bodega);
        this.camtidad=new SimpleIntegerProperty(cantidad);
        this.cantidad_max=new SimpleIntegerProperty(cantidad_max);
        this.cantidad_min= new SimpleIntegerProperty(cantidad_min);        
    }
    
      public Inventario(){
        this.codigo_articulo=new SimpleIntegerProperty(0);
        this.codigo_bodega=new SimpleIntegerProperty(0);
        this.camtidad=new SimpleIntegerProperty(0);
        this.cantidad_max=new SimpleIntegerProperty(0);
        this.cantidad_min= new SimpleIntegerProperty(0);        
    }

    public int getCodigo_articulo() {
        return codigo_articulo.get();
    }

    public void setCodigo_articulo(int codigo_articulo) {
        this.codigo_articulo.set(codigo_articulo); ;
    }

    public int getCodigo_bodega() {
        return codigo_bodega.get();
    }

    public void setCodigo_bodega(int codigo_bodega) {
        this.codigo_bodega.set(codigo_bodega); ;
    }

    public int getCamtidad() {
        return camtidad.get();
    }

    public void setCamtidad(int camtidad) {
        this.camtidad.set(camtidad);
    }

    public int getCantidad_max() {
        return cantidad_max.get();
    }

    public void setCantidad_max(int cantidad_max) {
        this.cantidad_max.set(cantidad_max);
    }

    public int getCantidad_min() {
        return cantidad_min.get();
    }

    public void setCantidad_min(int cantidad_min) {
        this.cantidad_min.set(cantidad_min);
    }
    
    private IntegerProperty codigo_articulo;
    private IntegerProperty codigo_bodega ;
    private IntegerProperty  camtidad;
    private IntegerProperty  cantidad_max;
    private IntegerProperty  cantidad_min;
}
