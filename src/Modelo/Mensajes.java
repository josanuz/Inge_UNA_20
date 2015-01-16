/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

/**
 *
 * @author geykel
 */
public class Mensajes {
    public static boolean ComfirmDialog(String head,String ms,Stage stage){
        Action response = Dialogs.create()
        .owner(stage)
        .title("Confirm Dialog")
        .masthead(head)
        .message(ms)
        .showConfirm();
        return response == Dialog.Actions.YES;
    }
    
    public static void InformationDialog(String ms,Stage stage){
        Dialogs.create()
        .owner(stage)
        .title("Information Dialog")
        .masthead(null)
        .message(ms)
        .showInformation();
    }
    
    public static void ExceptionDialog(Exception e,Stage stage){
        Dialogs.create()
        .owner(stage)
        .title("Exception Dialog")
        .masthead("Exception")
        .message(e.getMessage())
        .showException(e);
    }
    
    public static void ErrorDialog(String msg1,String msg2,Stage stage) {
        Dialogs.create()
        .owner(stage)
        .title("Error Dialog")
        .masthead(msg1)
        .message(msg2)
        .showError();
    }
}
