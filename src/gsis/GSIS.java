package gsis;

import Controlador.LoginUserController;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GSIS extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
        //LoginUserController controller = loader.<LoginUserController>getController();
        Stage stage2=new Stage();
        
        
        stage2.setScene(new Scene((Pane) loader.load()));
        
        stage2.setResizable(false);
        loader.<LoginUserController>getController().initData(stage2);
        //if(loader.<LoginUserController>getController() == null)System.out.println("lala");
        stage2.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
