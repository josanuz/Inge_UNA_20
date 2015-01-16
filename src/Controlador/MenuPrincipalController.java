package Controlador;

import gsis.GSIS;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuPrincipalController implements Initializable {
    
    private Stage stage;
    private Stage owner;
    @FXML
    private void ReparaciondeEquipo(ActionEvent event) throws IOException {
        FXMLLoader loader  = new FXMLLoader(GSIS.class.getResource("VentanaReparaciondeEquipo.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene((Pane)loader.load()));
        VentanaReparaciondeEquipoController controller =loader.<VentanaReparaciondeEquipoController>getController();
        controller.initData(stage2,stage);
        stage2.show();
    }
    
    @FXML
    private void Proveedores(ActionEvent event) throws IOException{
        FXMLLoader loader  = new FXMLLoader(GSIS.class.getResource("Proveedor.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene((Pane)loader.load()));
        ProveedorController controller =loader.<ProveedorController>getController();
        controller.initData(stage2,stage);
        stage2.show();
    
    }
    
    @FXML
    private void ConsultarFacturas(ActionEvent event) throws IOException{
        FXMLLoader loader  = new FXMLLoader(GSIS.class.getResource("VentanaConsultarFactura.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene((Pane)loader.load()));
        VentanaConsultarFacturaController controller =loader.<VentanaConsultarFacturaController>getController();
        controller.initData(stage2,stage);
        stage2.show();
    
    }
    
    @FXML
    private void Usuarios(ActionEvent event) throws IOException{
        FXMLLoader loader  = new FXMLLoader(GSIS.class.getResource("Usuarios.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene((Pane)loader.load()));
        UsuariosController controller =loader.<UsuariosController>getController();
        controller.initData(stage2,stage);
        stage2.show();
    
    }
    
    @FXML
    private void Venta(ActionEvent event) throws IOException{
        FXMLLoader loader  = new FXMLLoader(GSIS.class.getResource("VentaArticulo.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene((Pane)loader.load()));
        VentaArticuloController controller =loader.<VentaArticuloController>getController();
        controller.initData(stage2,stage);
        stage2.show();
    
    }
    
    public void initData(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void initData(Stage stage2, Stage stage) {
        this.stage=stage;
        this.owner=stage2;
            }

}
