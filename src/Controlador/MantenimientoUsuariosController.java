/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Beans.Usuario;
import Modelo.DataBase;
import Modelo.Mensajes;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class MantenimientoUsuariosController implements Initializable {
    
    @FXML
    TextField tf_nombre;
    @FXML
    TextField tf_cedula;
    @FXML
    TextField tf_dir;
    @FXML
    TextField tf_telefono;
    @FXML
    PasswordField pf_pass;
    @FXML
    PasswordField pf_passConf;
    @FXML
    TextField tf_rol;
    @FXML
    Button btn_ok;

    private boolean update;
    private Stage stage;
    private Stage owner;
    private Usuario user;
    
    @FXML
    private void guardar(ActionEvent event){
        if(tf_nombre.getText().equals("")||tf_cedula.getText().equals("")||tf_dir.getText().equals("")||tf_telefono.getText().equals("")||pf_pass.getText().equals("")||pf_passConf.getText().equals("")||tf_rol.getText().equals(""))
            Mensajes.ErrorDialog("Campos", "Todos los campos deben estar rellenos", stage);
        else if(!this.pf_pass.getText().equals(this.pf_passConf.getText()))
            Mensajes.ErrorDialog("Contraseña", "Las contaseñas deben ser iguales", stage);
        else
            try {
                if(!update) 
                    DataBase.getInstance().insertUpdateDelete("insert into gsisinve.usuario values('"+this.tf_nombre.getText()+"','"+this.tf_cedula.getText()+"','"+this.tf_telefono.getText()+"','"+this.tf_dir.getText()+"','"+this.tf_rol.getText()+"','"+this.pf_pass.getText()+"')");
                else{
                    DataBase.getInstance().insertUpdateDelete("update gsisinve.usuario set nombre ='"+this.tf_nombre.getText()+"', telefono = '"+this.tf_cedula.getText()+"',direccion ='"+this.tf_dir.getText()+"', rol = '"+this.tf_rol.getText()+"', password = '"+this.pf_pass.getText()+"' where cedula = '"+user.getCedula()+"'");
                    UsuariosController.masterData.remove(user);
                }
                UsuariosController.masterData.add(new Usuario(this.tf_nombre.getText(),this.tf_cedula.getText(),this.tf_telefono.getText(),this.tf_dir.getText(),this.tf_rol.getText(),this.pf_pass.getText()));
                stage.close();
            } catch (ClassNotFoundException |SQLException  ex) {
                Mensajes.ExceptionDialog(ex, stage);
                Logger.getLogger(VentanaGestorProveedoresController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void cerrarVentana(ActionEvent event) {
        this.owner.show();
        this.stage.close();
    }

    public void initData(Stage stage, Stage owner, Usuario u) {
        this.owner = owner;
        this.stage = stage;
        if(u!=null){
            update = true;
            this.tf_nombre.setText(u.getNombre());
            this.tf_cedula.setText(u.getCedula()); this.tf_cedula.setEditable(false);
            this.tf_telefono.setText(u.getTelefono());
            this.tf_dir.setText(u.getDireccion());
            this.tf_rol.setText(u.getRol());
            this.user = u;
        }
        else update = false;
    }
 
    
}
