/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ProveedorController.masterData;

import Modelo.Beans.Proveedor;
import Modelo.Beans.Usuario;
import Modelo.CargarDatos;
import Modelo.DataBase;
import Modelo.Mensajes;
import gsis.GSIS;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Observable;
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
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
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
public class UsuariosController implements Initializable, Observer {
    @FXML
    TextField tf_buscUsuario;
    @FXML
    Button btn_crearUsuario;
    @FXML
    TableView<Usuario> tw_Usuarios;
    @FXML
    TableColumn<Usuario, String> tc_nombre;
    @FXML
    TableColumn<Usuario, String> tc_cedula;
    @FXML
    TableColumn<Usuario, String> tc_telefono;
    @FXML
    TableColumn<Usuario, String> tc_dir;
    @FXML
    TableColumn<Usuario, String> tc_rol;
    private Stage stage;
    private Stage owner;
    public static final ObservableList<Usuario> masterData = observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataBase.getInstance().addObserver(this);
        this.tc_cedula.setCellValueFactory(new PropertyValueFactory("cedula"));
        this.tc_dir.setCellValueFactory(new PropertyValueFactory("direccion"));
        this.tc_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.tc_rol.setCellValueFactory(new PropertyValueFactory("rol"));
        this.tc_telefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        FilteredList<Usuario> filteredData = new FilteredList<>(masterData, u -> true);
        SortedList<Usuario> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tw_Usuarios.comparatorProperty());
        this.tw_Usuarios.setItems(sortedData);
        this.tf_buscUsuario.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(u -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFilter = newValue.toLowerCase();
                if (((Usuario) u).getNombre().toLowerCase().contains(lowerCaseFilter)) return true;
                else if (((Usuario) u).getCedula().toLowerCase().contains(lowerCaseFilter)) return true;
                return false;
            });
        });
        this.tw_Usuarios.setRowFactory((TableView<Usuario> param) -> {
            final TableRow<Usuario> row = new TableRow<>();
            final ContextMenu rowMenu = new ContextMenu();
            MenuItem editItem = new MenuItem("Modificar");
            MenuItem removeItem = new MenuItem("Eliminar");
            removeItem.setOnAction((ActionEvent event) -> {
                Eliminar(row.getItem());
            });
            editItem.setOnAction((ActionEvent event) -> {
                Modificar(row.getItem());
            });
            rowMenu.getItems().addAll(editItem, removeItem);
            row.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(row.itemProperty()))
                    .then(rowMenu)
                    .otherwise((ContextMenu) null));
            return row;
        });
    }

    private void Eliminar(Usuario u) {
        if (Mensajes.ComfirmDialog("Eliminar Usuario", "¿Esta seguro que quiere eliminar el Usuario?", stage)) {
            try {
                DataBase.getInstance().insertUpdateDelete("delete from gsisinve.usuario where cedula ='" + u.getCedula() + "'");
                masterData.remove(u);
                Mensajes.InformationDialog("El Usuario ha sido eliminado!!!", stage);
            } catch (SQLException ex) {
                Mensajes.ExceptionDialog(ex, stage);
                Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void Modificar(Usuario u) {
        FXMLLoader loader = new FXMLLoader(GSIS.class.getResource("Mantenimiento Usuarios.fxml"));
        Stage stage2 = new Stage();
        try {
            stage2.setScene(new Scene((Pane) loader.load()));
        } catch (IOException ex) {
            Mensajes.ExceptionDialog(ex, stage);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        MantenimientoUsuariosController controller = loader.<MantenimientoUsuariosController>getController();
        controller.initData(stage2, stage, u);
        stage2.show();
    }

    @FXML
    private void Crear(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(GSIS.class.getResource("Mantenimiento Usuarios.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene((Pane) loader.load()));
        MantenimientoUsuariosController controller = loader.<MantenimientoUsuariosController>getController();
        controller.initData(stage2, stage, null);
        stage2.show();
    }

    private void cerrarVentana(ActionEvent event) {
        this.owner.show();
        this.stage.close();
    }

    public void initData(Stage stage, Stage owner) {
        this.owner = owner;
        this.stage = stage;
        try {
            masterData.clear();
            if (DataBase.getInstance().isConnected()) CargarDatos.cargarUsuarios(masterData);
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
                CargarDatos.cargarUsuarios(masterData);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Mensajes.ExceptionDialog(ex, stage);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
