package Modelo.Beans;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaron
 */
public class Factura_Venta {
    public Factura_Venta(int numero, int codigo_cliente, String fecha, double impuestos, double subtotal, double total, double saldo) {
        this.numero = new SimpleIntegerProperty(numero);
        this.fecha = new SimpleStringProperty(fecha);
        this.impuestos = new SimpleDoubleProperty(impuestos);
        this.subtotal = new SimpleDoubleProperty(subtotal);
        this.total = new SimpleDoubleProperty(total);
        this.lineas = new ArrayList<>();
    }

    public Factura_Venta() {
        this.numero = new SimpleIntegerProperty(0);
        this.fecha = new SimpleStringProperty(null);
        this.impuestos = new SimpleDoubleProperty(0);
        this.subtotal = new SimpleDoubleProperty(0);
        this.total = new SimpleDoubleProperty(0);
        this.lineas = new ArrayList<>();
    }

    public int getNumero() {
        return numero.get();
    }

    public IntegerProperty numeroProperty() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public double getImpuestos() {
        return impuestos.get();
    }

    public DoubleProperty impuestosProperty() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos.set(impuestos);
    }

    public double getSubtotal() {
        return subtotal.get();
    }

    public DoubleProperty subtotalProperty() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal.set(subtotal);
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public List<Linea_Factura> getLineas() {
        return lineas;
    }

    /**
     * public void agregarLinea(Linea_Factura lf){
     * lf.setNumero_factura(-1);
     * }*
     */
    public void setLineas(List<Linea_Factura> lineas) {
        this.lineas = lineas;
    }

    private IntegerProperty numero;
    private StringProperty fecha;
    private DoubleProperty impuestos;
    private DoubleProperty subtotal;
    private DoubleProperty total;
    private List<Linea_Factura> lineas;
}
