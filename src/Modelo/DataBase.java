/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Geykel
 * @version 0.1
 * @see java.util.Observable
 * @see java.util.Observer
 */
public class DataBase extends Observable implements Observer{
    private DataBase() {
        Observable s;
        Properties props = new Properties();
        FileInputStream fis = null;
        basicDataSource = new BasicDataSource();
        try {
            fis = new FileInputStream("db.properties");
            props.load(fis);
            this.host = props.getProperty("DBUrl");
            this.pass = props.getProperty("DBPass");
            this.user = props.getProperty("DBUser");
            this.port = Integer.parseInt(props.getProperty("DBPort"));
            this.databaseName = props.getProperty("DBName");
            basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            basicDataSource.setUsername(this.user);
            basicDataSource.setPassword(this.pass);
            basicDataSource.setUrl(this.host);
        } catch (FileNotFoundException e) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
            this.conectado = false;
            e.printStackTrace();
        } catch (IOException e) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, e);
            this.conectado = false;
            e.printStackTrace();
        }
    }

    public static DataBase getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DataBase();
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return this.basicDataSource.getConnection();
    }

    public boolean conectar() throws SQLException{
            if(conectado == false){
                basicDataSource = new BasicDataSource();
                basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
                basicDataSource.setUsername(this.user);
                basicDataSource.setPassword(this.pass);
                basicDataSource.setUrl(this.host);

                conectado = true;
                this.setChanged();
                this.notifyObservers();
            }
        return conectado;
    }
    
    public boolean isConnected(){
        return !basicDataSource.isClosed();
    }
    
    public void close() throws SQLException{
        if (conectado = true && basicDataSource != null) {
                conectado = false;
                this.setChanged();
                this.notifyObservers();
            basicDataSource.close();
            }
    }
    
    public ResultSet ExecuteQuery(String sql) throws SQLException{
        ResultSet resp = null;
            if(this.conectado == true){
                Connection tmp = getConnection();
                Statement stm = tmp.createStatement();
                resp = stm.executeQuery(sql);
                tmp.close();
            }
        return resp;
    }
    
    public void insertUpdateDelete(String sql) throws SQLException{
        Connection tmp = getConnection();
        Statement stm = tmp.createStatement();
        stm.execute(sql);
        tmp.close();
    }
    
    public PreparedStatement getStament(String sql) throws SQLException{
        return basicDataSource.getConnection().prepareStatement(sql);
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
        /** if(conectado == true){
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
         }**/
    }
    
    private static DataBase INSTANCE = null;
    private boolean conectado;
    private String host;
    private String user;
    private String pass;
    private Integer port;
    private String databaseName;
    private BasicDataSource basicDataSource = null;
}
