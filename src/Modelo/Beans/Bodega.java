/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Juan
 */
public class Bodega {
    
    public Bodega(String ubicacion,Integer codigo){
        this.ubicacion = new SimpleStringProperty(ubicacion);
        this.codigo = new SimpleIntegerProperty(codigo);
    }
    
    
    public StringProperty ubicacionProperty(){
        return this.ubicacion;
    }
    public IntegerProperty codigoProperty(){
        return this.codigo;
    }
    

    public Integer getCodigo() {
        return codigo.get();
    }

    public void setCodigo(Integer codigo) {
        this.codigo.set(codigo);
    }

    public String getUbicacion() {
        return ubicacion.get();
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion.set(ubicacion);
    }
    
    
    private IntegerProperty codigo;
    private StringProperty ubicacion;
    
}
