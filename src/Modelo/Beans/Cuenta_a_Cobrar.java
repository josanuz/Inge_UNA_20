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

/**
 *
 * @author Juan
 */
public class Cuenta_a_Cobrar {
    
    public Cuenta_a_Cobrar(Integer cuenta,Integer codigo_cliente,Double total,Double saldo,Boolean estado){
        this.cuenta = new SimpleIntegerProperty(cuenta);
        this.codigo_cliente = new SimpleIntegerProperty(codigo_cliente);
        this.total = new SimpleDoubleProperty(total);
        this.saldo = new SimpleDoubleProperty(saldo);
        this.estado = new SimpleBooleanProperty(estado);
        
    }
    
    
    public IntegerProperty cuentaProperty(){
        return this.cuenta;
    }
    
    public IntegerProperty codigo_clienteProperty(){
        return this.codigo_cliente;
    }
    
    public DoubleProperty totalProperty(){
        return this.total;
    }
    
    public DoubleProperty saldoProperty(){
        return this.saldo;
    }
    
    public BooleanProperty estadoProperty(){
        return this.estado;
    }
    
    

    public Integer getCuenta() {
        return cuenta.get();
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta.set(cuenta);
    }

    public Integer getCodigo_cliente() {
        return codigo_cliente.get();
    }

    public void setCodigo_cliente(Integer codigo_cliente) {
        this.codigo_cliente.set(codigo_cliente);
    }

    public Double getTotal() {
        return total.get();
    }

    public void setTotal(Double total) {
        this.total.set(total);
    }

    public Double getSaldo() {
        return saldo.get();
    }

    public void setSaldo(Double saldo) {
        this.saldo.set(saldo);
    }

    public Boolean getEstado() {
        return estado.get();
    }

    public void setEstado(Boolean estado) {
        this.estado.set(estado);
    }
    
    private IntegerProperty cuenta;
    private IntegerProperty codigo_cliente;
    private DoubleProperty total;
    private DoubleProperty saldo;
    private BooleanProperty estado;
    
}
