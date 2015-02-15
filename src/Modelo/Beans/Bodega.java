/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Juan
 */
public class Bodega {
    public Bodega(String ubicacion, String codigo) {
        this.ubicacion = new SimpleStringProperty(ubicacion);
        this.codigo = new SimpleStringProperty(codigo);
    }

    public StringProperty ubicacionProperty() {
        return this.ubicacion;
    }

    public StringProperty codigoProperty() {
        return this.codigo;
    }

    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public String getUbicacion() {
        return ubicacion.get();
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion.set(ubicacion);
    }

    private StringProperty codigo;
    private StringProperty ubicacion;
}
