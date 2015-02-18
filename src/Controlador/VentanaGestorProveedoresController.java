/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Beans.Proveedor;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Aaron
 */
public class VentanaGestorProveedoresController implements Initializable {
    @FXML
    TextField nombre;
    @FXML
    TextField contacto;
    @FXML
    TextField direccion;
    @FXML
    TextField email;
    @FXML
    TextField telOficina;
    @FXML
    ComboBox activo;
    @FXML
    ComboBox entrega;
    @FXML
    Button btn;
    private boolean update;
    private Proveedor p;
    private Stage stage;
    private Stage owner;

    @FXML
    private void guardar(ActionEvent event) {
        if (nombre.getText().equals("") || contacto.getText().equals("") || direccion.getText().equals("") || email.getText().equals("") || telOficina.getText().equals(""))
            Mensajes.ErrorDialog("Campos", "Todos los campos deben estar rellenos", stage);
        else
            try {
                if (!update)
                    DataBase.getInstance().insertUpdateDelete("insert into gsisinve.proveedor values(" + telOficina.getText() + ",'" + nombre.getText() + "','" + telOficina.getText() + "','" + email.getText() + "','" + contacto.getText() + "')");
                else {
                    DataBase.getInstance().insertUpdateDelete("update gsisinve.proveedor set nombre ='" + nombre.getText() + "', telefono = '" + telOficina.getText() + "',email ='" + email.getText() + "', contacto = '" + contacto.getText() + "' where codigo = " + p.getCodigo());
                    ProveedorController.masterData.remove(p);
                }
                if (!update)
                    ProveedorController.masterData.add(new Proveedor(nombre.getText(), email.getText(), telOficina.getText(), Integer.parseInt(telOficina.getText()), contacto.getText()));
                else
                    ProveedorController.masterData.add(new Proveedor(nombre.getText(), email.getText(), telOficina.getText(), p.getCodigo(), contacto.getText()));
                stage.close();
            } catch (SQLException ex) {
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

    public void initData(Stage stage, Stage owner, Proveedor p) {
        this.owner = owner;
        this.stage = stage;
        if (p != null) {
            update = true;
            this.telOficina.setText(p.getTelefono());
            this.contacto.setText(p.getContacto());
            this.email.setText(p.getEmail());
            this.nombre.setText(p.getNombre());
            this.nombre.setEditable(false);
            this.p = p;
        } else update = false;
    }
}
