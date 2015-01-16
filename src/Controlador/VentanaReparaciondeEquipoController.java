/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class VentanaReparaciondeEquipoController implements Initializable {

    private Stage stage;
    private Stage owner;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void cerrarVentana(ActionEvent event){
        this.owner.show();
        this.stage.close();
    }
    
    public void initData(Stage stage,Stage owner){
        this.owner=owner;
        this.stage=stage;
    }
    
}
