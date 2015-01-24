package Controlador;

import static Controlador.ProveedorController.masterData;

import Modelo.Beans.Cliente;
import Modelo.Beans.Proveedor;
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
public class ClienteController implements Initializable, Observer {
    @FXML
    private TextField buscarCliente;
    @FXML
    private TableView<Cliente> tv_clientes;
    @FXML
    private TableColumn<Cliente, Integer> tc_codigo;
    @FXML
    private TableColumn<Cliente, String> tc_nombre;
    @FXML
    private TableColumn<Cliente, String> tc_dirent;
    @FXML
    private TableColumn<Cliente, String> tc_email;
    @FXML
    private TableColumn<Cliente, String> tc_telefono;
    public static final ObservableList<Cliente> masterData = observableArrayList();
    private Stage stage;
    private Stage owner;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataBase.getInstance().addObserver(this);
        tc_codigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        tc_email.setCellValueFactory(new PropertyValueFactory("email"));
        tc_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tc_dirent.setCellValueFactory(new PropertyValueFactory("direccion"));
        tc_telefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        FilteredList<Cliente> filteredData = new FilteredList<>(masterData, p -> true);
        SortedList<Cliente> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tv_clientes.comparatorProperty());
        tv_clientes.setItems(sortedData);
        buscarCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(r -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (((Cliente) r).getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            if (DataBase.getInstance().isConnected()) {
                masterData.clear();
                CargarDatos.cargarClientes(masterData);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Mensajes.ExceptionDialog(ex, stage);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initData(Stage stage, Stage owner) {
        this.owner = owner;
        this.stage = stage;
        try {
            masterData.clear();
            if (DataBase.getInstance().isConnected()) CargarDatos.cargarClientes(masterData);
        } catch (ClassNotFoundException | SQLException ex) {
            Mensajes.ExceptionDialog(ex, stage);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
