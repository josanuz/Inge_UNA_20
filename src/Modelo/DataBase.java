/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Geykel
 */
public class DataBase extends Observable implements Observer{

    private DataBase() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        this.host = "jdbc:mysql://127.0.0.1";
        this.user = "root";
        this.pass = "root";
        this.port = 3306;
        conectado = false;
    }
    
    public static DataBase getInstance() throws ClassNotFoundException {
        if(INSTANCE == null){
            INSTANCE = new DataBase();
        }
        return INSTANCE;
    }
    
    public boolean conectar() throws SQLException{
            if(conectado == false){
                con = DriverManager.getConnection(this.host,this.user,this.pass);
                conectado = true;
                this.setChanged();
                this.notifyObservers();
            }
        return conectado;
    }
    
    public boolean isConnected(){
        return conectado;
    }
    
    public void close() throws SQLException{
            if(conectado = true && con != null){
                conectado = false;
                this.setChanged();
                this.notifyObservers();
                con.close();  
            }
    }
    
    public ResultSet ExecuteQuery(String sql) throws SQLException{
        ResultSet resp = null;
            if(this.conectado == true){
                Statement stm = con.createStatement();
                resp = stm.executeQuery(sql);
            }
        return resp;
    }
    
    public void insertUpdateDelete(String sql) throws SQLException{
        Statement s = con.createStatement();
        s.execute(sql);
    }
    
    public PreparedStatement getStament(String sql) throws SQLException{
        return con.prepareStatement(sql);
    }
    
    public boolean setConnection(String host, int port, String user, String password) throws SQLException{
        this.close();
        this.host = "jdbc:mysql://"+host;
        this.user = user;
        this.pass = password;
        this.port = port;
        return this.conectar();
    }

    @Override
    public void update(Observable o, Object arg) {
            if(conectado == true){
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
                conectado = false;
                this.setChanged();
                this.notifyObservers();
            }
        try {
            this.conectar();//al cambiar la informacion del servidor se desconecta
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    private static DataBase INSTANCE = null;
    private boolean conectado;
    private Connection con;
    private String host;
    private String user;
    private String pass;
    private Integer port;
}
