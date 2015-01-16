
package Modelo.Beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Usuario {
    public Usuario(String nombre,String cedula,String telefono,String direccion,String rol,String pass){
        this.nombre = new SimpleStringProperty(nombre);
        this.cedula = new SimpleStringProperty(cedula);
        this.telefono = new SimpleStringProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.rol = new SimpleStringProperty(rol);
        this.pass = pass;
    }
 
    /**
     * @return the cedula
     */
    public StringProperty cedulaProperty() {
        return cedula;
    }

    /**
     * @return the nombre
     */
    public StringProperty nombreProperty() {
        return nombre;
    }

    /**
     * @return the telefono
     */
    public StringProperty telefonoProperty() {
        return telefono;
    }

    /**
     * @return the direccion
     */
    public StringProperty direccionProperty() {
        return direccion;
    }

    /**
     * @return the rol
     */
    public StringProperty rolProperty() {
        return rol;
    }
    
    public String getNombre(){
        return this.nombre.get();
    }
    
    public String getCedula(){
        return this.cedula.get();
    }
    
    public String getTelefono(){
        return this.telefono.get();
    }
    
    public String getDireccion(){
        return this.direccion.get();
    }
    
    public String getRol(){
        return rol.get();
    }
    
    public String getPassword(){
        return pass;
    }
    
    public void setPassword(String pass){
        this.pass = pass;
    }
    
    private final StringProperty cedula;
    private final StringProperty nombre;
    private final StringProperty telefono;
    private final StringProperty direccion;
    private final StringProperty rol;
    private String pass;
}
