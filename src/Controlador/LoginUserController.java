
package Controlador;

import Modelo.CargarDatos;
import Modelo.DataBase;
import Modelo.Mensajes;
import gsis.GSIS;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginUserController implements Initializable {

    
    Stage stage;
   
    
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @FXML
    private void Login(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {

        String username1 = username.getText();
        String password1 = password.getText();
        
        if (username1.equals("") || password1.equals("")) {
            Mensajes.ErrorDialog("Campos", "Todos los campos deben estar rellenos", stage);
        } else {

            System.out.print(username1 + password1);
            try {
                DataBase db = DataBase.getInstance();
                db.conectar();
                if (CargarDatos.comprobarUsuario(username1).equals(password1)) {
                    System.out.println("Usuario Correcto");
                    FXMLLoader loader = new FXMLLoader(GSIS.class.getResource("MenuPrincipal.fxml"));
                    Stage stage2 = new Stage();
                    stage2.setScene(new Scene((Pane) loader.load()));
                    MenuPrincipalController controller = loader.<MenuPrincipalController>getController();
                    controller.initData(stage2, stage);
                    stage2.show();
                    this.stage.close();
                    
                    
                } else {
                    Mensajes.ErrorDialog("Contaseña", "Contraseña incorrecta", stage);
                }
            } catch (ClassNotFoundException | SQLException | IOException e) {
                Mensajes.ErrorDialog("Contaseña o Usuario", "Ususario o contraseña es incorrecta", stage);
            }

        }
    }
   
    public void initData(Stage stage){
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
