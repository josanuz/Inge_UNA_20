package gsis;

import Controlador.LoginUserController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Beans.Articulo;
import Modelo.Beans.Bodega;
import Modelo.Mensajes;
import Modelo.dao.ArticuloDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GSIS extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
        stage.setScene(new Scene((Pane) loader.load()));
        stage.setResizable(false);
        loader.<LoginUserController>getController().initData(stage);
        ArrayList<Bodega> arrayList = new ArrayList<>();
        arrayList.add(new Bodega("", "1"));
        List<Articulo> articulos = ArticuloDAO.getInstance().select().AllInBodegaList(arrayList);
        for (Articulo ar : articulos) Mensajes.InformationDialog(ar.getCodigo() + " " + ar.getDescripcion(), stage);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
