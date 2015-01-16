/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author geykel
 */
public class Proveedor {
    
    public Proveedor(String nombre, String email, String telefono, Integer codigo, String contacto){
        this.nombre = new SimpleStringProperty(nombre);
        this.email = new SimpleStringProperty(email);
        this.telefono = new SimpleStringProperty(telefono);
        this.codigo = new SimpleIntegerProperty(codigo);
        this.contacto = new SimpleStringProperty(contacto);
    }
    
    public StringProperty nombreProperty(){
        return this.nombre;
    }
    public StringProperty emailProperty(){
        return this.email;
    }
    public IntegerProperty codigoProperty(){
        return this.codigo;
    }
    public StringProperty telefonoProperty(){
        return this.telefono;
    }
    public StringProperty contactoProperty(){
        return this.contacto;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public Integer getCodigo() {
        return codigo.get();
    }

    public void setCodigo(Integer codigo) {
        this.codigo.set(codigo);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getContacto() {
        return contacto.get();
    }

    public void setContacto(String contacto) {
        this.contacto.set(contacto);
    }
    
    private StringProperty nombre;
    private StringProperty email;
    private IntegerProperty codigo;
    private StringProperty telefono;
    private StringProperty contacto;
}
