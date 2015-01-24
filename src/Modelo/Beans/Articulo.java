
package Modelo.Beans;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Articulo {
    public Articulo(String codigo, String descripcion, double costo, String utilidad, boolean esGrabado) {
        this.codigo = new SimpleStringProperty(codigo);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.costo = new SimpleDoubleProperty(costo);
        this.utilidad = new SimpleStringProperty(utilidad);
        this.esGrabado = new SimpleBooleanProperty(esGrabado);
    }

    public Articulo() {
        this.codigo = new SimpleStringProperty("");
        this.descripcion = new SimpleStringProperty(null);
        this.costo = new SimpleDoubleProperty(0.0);
        this.utilidad = new SimpleStringProperty(null);
        this.esGrabado = new SimpleBooleanProperty(false);
    }

    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public double getCosto() {
        return costo.get();
    }

    public String getCosto2() {
        return Double.toString(costo.get());
    }

    public void setCosto(double costo) {
        this.costo.set(costo);
    }

    public String getUtilidad() {
        return utilidad.get();
    }

    public void setUtilidad(String utilidad) {
        this.utilidad.set(utilidad);
    }

    public boolean isEsGrabado() {
        return esGrabado.get();
    }

    public void setEsGrabado() {
        boolean esGrabado1 = !this.isEsGrabado();
        this.esGrabado.set(esGrabado1);
    }

    private StringProperty codigo;
    private StringProperty descripcion;
    private DoubleProperty costo;
    private StringProperty utilidad;
    private BooleanProperty esGrabado;
}
