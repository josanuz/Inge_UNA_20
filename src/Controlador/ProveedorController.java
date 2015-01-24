
package Controlador;

import Modelo.Beans.Proveedor;
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

public class ProveedorController implements Initializable, Observer {
    @FXML
    TextField bprov;
    @FXML
    Button btn;
    @FXML
    TableView<Proveedor> tabla;
    @FXML
    TableColumn<Proveedor, String> nombre;
    @FXML
    TableColumn<Proveedor, String> email;
    @FXML
    TableColumn<Proveedor, String> telefono;
    @FXML
    TableColumn<Proveedor, String> contacto;
    @FXML
    TableColumn<Proveedor, Integer> codigo;
    public static final ObservableList<Proveedor> masterData = observableArrayList();
    private Stage stage;
    private Stage owner;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataBase.getInstance().addObserver(this);
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        email.setCellValueFactory(new PropertyValueFactory("email"));
        telefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        contacto.setCellValueFactory(new PropertyValueFactory("contacto"));
        codigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        FilteredList<Proveedor> filteredData = new FilteredList<>(masterData, p -> true);
        SortedList<Proveedor> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(sortedData);
        bprov.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(r -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (((Proveedor) r).getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (((Proveedor) r).getContacto().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (((Proveedor) r).getTelefono().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        tabla.setRowFactory((TableView<Proveedor> param) -> {
            final TableRow<Proveedor> row = new TableRow<>();
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

    private void Eliminar(Proveedor p) {
        if (Mensajes.ComfirmDialog("Eliminar Proveedor", "¿Esta seguro que quiere eliminar el Proveedor?", stage)) {
            try {
                DataBase.getInstance().insertUpdateDelete("delete from gsisinve.proveedor where codigo ='" + p.getCodigo() + "'");
                /*PreparedStatement PSDelete = DataBase.getInstance().getStament("delete from gsisinve.proveedor where codigo = ?");
                PSDelete.setInt(1, p.getCodigo());
                DataBase.getInstance().insertUpdateDelete(PSDelete);*/
                masterData.remove(p);
                Mensajes.InformationDialog("El Proveedor ha sido eliminado!!!", stage);
            } catch (SQLException ex) {
                Mensajes.ExceptionDialog(ex, stage);
                Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void Modificar(Proveedor p) {
        FXMLLoader loader = new FXMLLoader(GSIS.class.getResource("VentanaGestorProveedores.fxml"));
        Stage stage2 = new Stage();
        try {
            stage2.setScene(new Scene((Pane) loader.load()));
        } catch (IOException ex) {
            Mensajes.ExceptionDialog(ex, stage);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        VentanaGestorProveedoresController controller = loader.<VentanaGestorProveedoresController>getController();
        controller.initData(stage2, stage, p);
        stage2.show();
    }

    @FXML
    private void Crear(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(GSIS.class.getResource("VentanaGestorProveedores.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene((Pane) loader.load()));
        VentanaGestorProveedoresController controller = loader.<VentanaGestorProveedoresController>getController();
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
            if (DataBase.getInstance().isConnected()) CargarDatos.cargarProveedores(masterData);
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
                CargarDatos.cargarProveedores(masterData);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Mensajes.ExceptionDialog(ex, stage);
            Logger.getLogger(ProveedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
