
package Modelo.Beans;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Articulo_Repacion {

    public Articulo_Repacion(int codigo_reparacion, String nombre, String descripcion, String marca, String modelo, String dano, double cargo_por_reparacion) {
        this.codigo_reparacion = new SimpleIntegerProperty(codigo_reparacion);
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.marca = new SimpleStringProperty(marca);
        this.modelo = new SimpleStringProperty(modelo);
        this.dano = new SimpleStringProperty(dano);
        this.cargo_por_reparacion = new SimpleDoubleProperty(cargo_por_reparacion);
    }
    
    public Articulo_Repacion() {
        this.codigo_reparacion = new SimpleIntegerProperty(0);
        this.nombre = new SimpleStringProperty(null);
        this.descripcion = new SimpleStringProperty(null);
        this.marca = new SimpleStringProperty(null);
        this.modelo = new SimpleStringProperty(null);
        this.dano = new SimpleStringProperty(null);
        this.cargo_por_reparacion = new SimpleDoubleProperty(0);
    }

    public int getCodigo_reparacion() {
        return codigo_reparacion.get();
    }

    public void setCodigo_reparacion(int codigo_reparacion) {
        this.codigo_reparacion.set(codigo_reparacion);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.get();
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public String getMarca() {
        return marca.get();
    }

    public void setMarca(String marca) {
        this.marca.get();
    }

    public String getModelo() {
        return modelo.get();
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public String getDano() {
        return dano.get();
    }

    public void setDano(String dano) {
        this.dano.set(dano);
    }

    public double getCargo_por_reparacion() {
        return cargo_por_reparacion.get();
    }

    public void setCargo_por_reparacion(double cargo_por_reparacion) {
        this.cargo_por_reparacion.get();
    }
    
    
    
    
    private IntegerProperty codigo_reparacion;
    private StringProperty  nombre;
    private StringProperty descripcion;
    private StringProperty marca;
    private StringProperty modelo;
    private StringProperty dano;
    private DoubleProperty cargo_por_reparacion;
}
