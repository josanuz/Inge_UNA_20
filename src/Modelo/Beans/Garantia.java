
package Modelo.Beans;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Garantia {

    public Garantia(int consecutivo, String fecha, boolean estado, int numero, int codigo) {
        this.consecutivo = new SimpleIntegerProperty( consecutivo);
        this.fecha = new SimpleStringProperty(fecha);
        this.estado = new SimpleBooleanProperty(estado);
        this.numero = new SimpleIntegerProperty(numero);
        this.codigo = new SimpleIntegerProperty(codigo);
    }

    public int getConsecutivo() {
        return consecutivo.get();
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo.get();
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.get();
    }

    public boolean getEstado() {
        return estado.get();
    }

    public void setEstado() {
        this.estado.set(! this.getEstado());
    }

    public int getNumero() {
        return numero.get();
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public int getCodigo() {
        return codigo.get();
    }

    public void setCodigo(int codigo) {
        this.codigo.get();
    }
 
    
    
    
    private IntegerProperty consecutivo;
    private StringProperty fecha;
    private BooleanProperty estado;
    private IntegerProperty numero;
    private IntegerProperty codigo;
}
