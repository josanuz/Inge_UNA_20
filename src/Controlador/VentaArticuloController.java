/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ProveedorController.masterData;

import Modelo.Beans.Articulo;
import Modelo.Beans.Cliente;
import Modelo.Beans.Linea_Factura;
import Modelo.Beans.Proveedor;
import Modelo.CargarDatos;
import Modelo.DataBase;
import Modelo.Mensajes;
import gsis.GSIS;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;

import static javafx.collections.FXCollections.observableArrayList;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class VentaArticuloController implements Initializable, Observer {
    @FXML
    TextField cedCliente;
    @FXML
    TextField cantArticulo;
    @FXML
    TextField codArticulo;
    @FXML
    TableView<Linea_Factura> tb_LineaFactura;
    @FXML
    Label total;
    @FXML
    TableColumn<Linea_Factura, Integer> tc_cantidad;
    @FXML
    TableColumn<Linea_Factura, Integer> tc_articulo;
    @FXML
    TableColumn<Linea_Factura, Integer> tc_precio;
    private double preciototal;
    private Stage stage;
    private Stage owner;
    public static final ObservableList<Linea_Factura> masterData = observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tc_cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        tc_articulo.setCellValueFactory(new PropertyValueFactory("codigo_articulo"));
        tc_precio.setCellValueFactory(new PropertyValueFactory("precio"));
        FilteredList<Linea_Factura> filteredData = new FilteredList<>(masterData, p -> true);
        SortedList<Linea_Factura> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(this.tb_LineaFactura.comparatorProperty());
        this.tb_LineaFactura.setItems(sortedData);
        this.tb_LineaFactura.setRowFactory((TableView<Linea_Factura> param) -> {
            final TableRow<Linea_Factura> row = new TableRow<>();
            final ContextMenu rowMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Eliminar");
            removeItem.setOnAction((ActionEvent event) -> {
                Eliminar(row.getItem());
            });
            rowMenu.getItems().addAll(removeItem);
            row.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(row.itemProperty()))
                    .then(rowMenu)
                    .otherwise((ContextMenu) null));
            return row;
        });
    }

    @FXML
    public void Agregar(ActionEvent event) {
        String cantArticulo2 = cantArticulo.getText();
        String cedCliente2 = cedCliente.getText();
        String codArticulo2 = codArticulo.getText();
        if (cantArticulo2.equals("") || cedCliente2.equals("") || codArticulo2.equals("")) {
            Mensajes.ErrorDialog("Campos", "Todos los campos deben estar rellenos", stage);
        } else {
            try {
                Articulo ar = CargarDatos.cargarArticulo(codArticulo2);
                int lineafactura = CargarDatos.countRows("factura_venta");
                int cant = Integer.parseInt(cantArticulo2);
                double precio = ar.getCosto() * cant;
                masterData.add(new Linea_Factura(lineafactura + 1, ar, cant, precio, 0.0));
                this.preciototal += precio;
            } catch (ClassNotFoundException | SQLException e) {
                Mensajes.ErrorDialog("Campos", "Error el codigo del articulo o el cliente es incorrecto", stage);
            }
        }
        this.total.setText(Double.toString(this.preciototal));
    }

    @FXML
    public void consultarArticulo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(GSIS.class.getResource("Articulos.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene((Pane) loader.load()));
        ArticulosController controller = loader.<ArticulosController>getController();
        controller.initData(stage2, stage);
        stage2.show();
    }

    private void Eliminar(Linea_Factura p) {
        if (Mensajes.ComfirmDialog("Eliminar Linea", "¿Esta seguro que quiere eliminar este articulo?", stage)) {
            try {
                //DataBase.getInstance().insertUpdateDelete("delete from gsisinve.proveedor where codigo ='"+p.getCodigo()+"'");
                /*PreparedStatement PSDelete = DataBase.getInstance().getStament("delete from gsisinve.proveedor where codigo = ?");
                PSDelete.setInt(1, p.getCodigo());
                DataBase.getInstance().insertUpdateDelete(PSDelete);*/
                masterData.remove(p);
                this.preciototal -= p.getPrecio();
                Mensajes.InformationDialog("El Articulo ha sido eliminado!!!", stage);
                this.total.setText(Double.toString(preciototal));
            } catch (Exception e) {
                Mensajes.ExceptionDialog(e, stage);
                Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @FXML
    public void consultarCliente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(GSIS.class.getResource("Cliente.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene((Pane) loader.load()));
        ClienteController controller = loader.<ClienteController>getController();
        controller.initData(stage2, stage);
        stage2.show();
    }

    @FXML
    public void Facturar(ActionEvent event) {
        String cedCliente2 = cedCliente.getText();
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        this.owner.show();
        this.stage.close();
    }

    public void initData(Stage stage, Stage owner) {
        this.owner = owner;
        this.stage = stage;
        masterData.clear();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
    }
}
