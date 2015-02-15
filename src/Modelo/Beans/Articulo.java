
package Modelo.Beans;

import javafx.beans.property.*;

public class Articulo {
    public Articulo(String codigo, String descripcion, double costo, double utilidad, boolean esGrabado) {
        this.codigo = new SimpleStringProperty(codigo);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.costo = new SimpleDoubleProperty(costo);
        this.utilidad = new SimpleDoubleProperty(utilidad);
        this.esGrabado = new SimpleBooleanProperty(esGrabado);
    }

    public Articulo() {
        this.codigo = new SimpleStringProperty("");
        this.descripcion = new SimpleStringProperty(null);
        this.costo = new SimpleDoubleProperty(0.0);
        this.utilidad = new SimpleDoubleProperty(0.0);
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

    public double getUtilidad() {
        return utilidad.get();
    }

    public void setUtilidad(double utilidad) {
        this.utilidad.set(utilidad);
    }

    public boolean isEsGrabado() {
        return esGrabado.get();
    }

    public void setEsGrabado(boolean grabado) {
        this.esGrabado.set(grabado);
    }

    private StringProperty codigo;
    private StringProperty descripcion;
    private DoubleProperty costo;
    private DoubleProperty utilidad;
    private BooleanProperty esGrabado;
}
