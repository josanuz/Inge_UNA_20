/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Beans;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Juan
 */
public class Articulo_Proveedor {
    
    public Articulo_Proveedor(Integer codigo_articulo,Integer codigo_proveedor,Double precio_compra){
        this.codigo_articulo = new SimpleIntegerProperty(codigo_articulo);
        this.codigo_proveedor = new SimpleIntegerProperty(codigo_proveedor);
        this.precio_compra = new SimpleDoubleProperty(precio_compra);
        
    
    }
    
    
    public IntegerProperty codigo_articuloProperty(){
        return this.codigo_articulo;
    }
    
    public IntegerProperty codigo_proveedorProperty(){
        return this.codigo_proveedor;
    }
    
    public DoubleProperty precioCompraProperty(){
        return this.precio_compra;
    }
    

    public Integer getCodigo_articulo() {
        return codigo_articulo.get();
    }

    public void setCodigo_articulo(Integer codigo_articulo) {
        this.codigo_articulo.set(codigo_articulo);
    }

    public Integer getCodigo_proveedor() {
        return codigo_proveedor.get();
    }

    public void setCodigo_proveedor(Integer codigo_proveedor) {
        this.codigo_proveedor.set(codigo_proveedor);
    }

    public Double getPrecio_compra() {
        return precio_compra.get();
    }

    public void setPrecio_compra(Double precio_compra) {
        this.precio_compra.set(precio_compra);
    }
    
    private IntegerProperty codigo_articulo;
    private IntegerProperty codigo_proveedor;
    private DoubleProperty precio_compra;
    
}
