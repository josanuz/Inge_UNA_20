/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Geykel
 * @version 0.1
 * @see java.util.Observable
 * @see java.util.Observer
 */
public class DataBase extends Observable implements Observer {
    private DataBase() {
        Observable s;
        Properties props = new Properties();
        FileInputStream fis;
        basicDataSource = new BasicDataSource();
        try {
            fis = new FileInputStream("C:\\Users\\Casa\\IdeaProjects\\Inge\\src\\db.properties");
            props.load(fis);
            this.host = props.getProperty("DBHost");
            this.host = props.getProperty("DBUrl");
            this.pass = props.getProperty("DBPass");
            this.user = props.getProperty("DBUser");
            this.port = Integer.parseInt(props.getProperty("DBPort"));
            this.databaseName = props.getProperty("DBName");
            basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            basicDataSource.setUsername(this.user);
            basicDataSource.setPassword(this.pass);
            basicDataSource.setUrl(this.host);
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

    //TODO eliminar cuando corriga las dependencias
    public boolean conectar() throws SQLException {
        if (!conectado) {
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

    public boolean isConnected() {
        return !basicDataSource.isClosed();
    }

    public void close() throws SQLException {
        if (conectado = basicDataSource != null) {
            conectado = false;
            this.setChanged();
            this.notifyObservers();
            basicDataSource.close();
        }
    }

    //TODO eliminar cuando corriga las dependencias
    public ResultSet ExecuteQuery(String sql) throws SQLException {
        ResultSet resp = null;
        if (this.conectado) {
            Connection tmp = getConnection();
            Statement stm = tmp.createStatement();
            resp = stm.executeQuery(sql);
            tmp.close();
        }
        return resp;
    }

    //TODO eliminar cuando corriga las dependencias
    public void insertUpdateDelete(String sql) throws SQLException {
        Connection tmp = getConnection();
        Statement stm = tmp.createStatement();
        stm.execute(sql);
        tmp.close();
    }

    //TODO eliminar cuando corriga las dependencias
    public PreparedStatement getStament(String sql) throws SQLException {
        return basicDataSource.getConnection().prepareStatement(sql);
    }

    //TODO eliminar cuando corriga las dependencias
    public boolean setConnection(String host, int port, String user, String password) throws SQLException {
        this.close();
        this.host = "jdbc:mysql://" + host;
        this.user = user;
        this.pass = password;
        this.port = port;
        return this.conectar();
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
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
