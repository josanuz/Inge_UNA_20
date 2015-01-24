package Modelo.Beans;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Juan
 */
public class Linea_Factura {
    public Linea_Factura(Integer numero_factura, Articulo art, Integer cantidad, Double precio, Double descuento) {
        this.numero_factura = new SimpleIntegerProperty(numero_factura);
        articulo = art;
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.precio = new SimpleDoubleProperty(precio);
        this.descuento = new SimpleDoubleProperty(descuento);
    }

    public int getNumero_factura() {
        return numero_factura.get();
    }

    public IntegerProperty numero_facturaProperty() {
        return numero_factura;
    }

    public void setNumero_factura(int numero_factura) {
        this.numero_factura.set(numero_factura);
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public double getPrecio() {
        return precio.get();
    }

    public DoubleProperty precioProperty() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public double getDescuento() {
        return descuento.get();
    }

    public DoubleProperty descuentoProperty() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento.set(descuento);
    }

    private IntegerProperty numero_factura;
    private Articulo articulo;
    private IntegerProperty cantidad;
    private DoubleProperty precio;
    private DoubleProperty descuento;
    
    
}
