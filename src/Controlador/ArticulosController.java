/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Beans.Articulo;
import Modelo.CargarDatos;
import Modelo.DataBase;
import Modelo.Mensajes;

import java.net.URL;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.collections.FXCollections.observableArrayList;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class ArticulosController implements Initializable, Observer {
    private Stage stage;
    private Stage owner;
    @FXML
    TableView<Articulo> tabla;
    @FXML
    TableColumn<Articulo, Integer> codigo;
    @FXML
    TableColumn<Articulo, String> descripcion;
    @FXML
    TableColumn<Articulo, Double> costo;
    @FXML
    TableColumn<Articulo, String> utilidad;
    @FXML
    TableColumn<Articulo, Boolean> esGrabado;
    @FXML
    TextField buscar;
    public static final ObservableList<Articulo> masterData = observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataBase.getInstance().addObserver(this);
        codigo.setCellValueFactory(new PropertyValueFactory("Codigo"));
        descripcion.setCellValueFactory(new PropertyValueFactory("Descripcion"));
        costo.setCellValueFactory(new PropertyValueFactory("Costo"));
        utilidad.setCellValueFactory(new PropertyValueFactory("Utilidad"));
        esGrabado.setCellValueFactory(new PropertyValueFactory("EsGrabado"));
        FilteredList<Articulo> filteredData = new FilteredList<>(masterData, p -> true);
        SortedList<Articulo> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sortedData);
        this.buscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(r -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (((Articulo) r).getCodigo().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (((Articulo) r).getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        this.owner.show();
        this.stage.close();
    }

    public void initData(Stage stage, Stage owner) {
        this.owner = owner;
        this.stage = stage;
        try {
            masterData.clear();
            if (DataBase.getInstance().isConnected()) {
                CargarDatos.cargarArticulos(masterData);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Mensajes.ExceptionDialog(ex, stage);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            if (DataBase.getInstance().isConnected()) {
                masterData.clear();
                CargarDatos.cargarArticulos(masterData);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Mensajes.ExceptionDialog(ex, stage);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
