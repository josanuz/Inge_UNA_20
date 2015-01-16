/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Beans;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Juan
 */
public class Reparacion {
    
    public Reparacion(Integer codigo, Integer codigo_cliente,Double monto,String fecha,Boolean estado){
        this.codigo = new SimpleIntegerProperty(codigo);
        this.codigo_cliente = new SimpleIntegerProperty(codigo_cliente);
        this.monto = new SimpleDoubleProperty(monto);
        this.fecha = new SimpleStringProperty(fecha);
        this.estado  = new SimpleBooleanProperty(estado);
    }
    
    
    public StringProperty fechaProperty(){
        return this.fecha;
    }
    public IntegerProperty codigoProperty(){
        return this.codigo;
    }
    public IntegerProperty codigo_clienteProperty(){
        return this.codigo_cliente;
    }
    public DoubleProperty montoProperty(){
        return this.monto;
    }
    public BooleanProperty estadoProperty(){
        return this.estado;
    }
    

    public Boolean getEstado() {
        return estado.get();
    }

    public void setEstado(Boolean estado) {
        this.estado.set(estado);
    }

    public Integer getCodigo() {
        return codigo.get();
    }

    public void setCodigo(Integer codigo) {
        this.codigo.set(codigo);
    }

    public Integer getCodigo_cliente() {
        return codigo_cliente.get();
    }

    public void setCodigo_cliente(Integer codigo_cliente) {
        this.codigo_cliente.set(codigo_cliente);
    }

    public Double getMonto() {
        return monto.get();
    }

    public void setMonto(Double monto) {
        this.monto.set(monto);
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }
    
    
    private BooleanProperty estado;
    private IntegerProperty codigo;
    private IntegerProperty codigo_cliente;
    private DoubleProperty monto;
    private StringProperty fecha;
}
