/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Modelo.Beans.Articulo;
import Modelo.Beans.Cliente;
import Modelo.Beans.Proveedor;
import Modelo.Beans.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;

/**
 * @author geykel
 */
public class CargarDatos {
    public static void cargarProveedores(ObservableList<Proveedor> list) throws SQLException, ClassNotFoundException {
        ResultSet rs = DataBase.getInstance().ExecuteQuery("select * from gsisinve.proveedor");
        Proveedor p;
        if (rs != null) {
            while (rs.next()) {
                p = new Proveedor(rs.getString("nombre"), rs.getString("email"), rs.getString("telefono"), rs.getInt("codigo"), rs.getString("contacto"));
                list.add(p);
            }
            rs.close();
        }
    }

    public static void cargarUsuarios(ObservableList<Usuario> list) throws ClassNotFoundException, SQLException {
        ResultSet rs = DataBase.getInstance().ExecuteQuery("select * from gsisinve.usuario");
        Usuario u;
        if (rs != null) {
            while (rs.next()) {
                u = new Usuario(rs.getString("nombre"), rs.getString("cedula"), rs.getString("telefono"), rs.getString("direccion"), rs.getString("rol"), rs.getString("password"));
                list.add(u);
            }
            rs.close();
        }
    }

    public static void cargarArticulos(ObservableList<Articulo> list) throws ClassNotFoundException, SQLException {
        ResultSet rs = DataBase.getInstance().ExecuteQuery("select * from gsisinve.articulo");
        Articulo ar;
        if (rs != null) {
            while (rs.next()) {
                ar = new Articulo(rs.getString("codigo"), rs.getString("descripcion"), rs.getDouble("costo"), rs.getDouble("utilidad"), rs.getBoolean("esgrabado"));
                list.add(ar);
            }
            rs.close();
        }
    }

    public static void cargarClientes(ObservableList<Cliente> list) throws ClassNotFoundException, SQLException {
        ResultSet rs = DataBase.getInstance().ExecuteQuery("select * from gsisinve.cliente");
        Cliente cl;
        if (rs != null) {
            while (rs.next()) {
                cl = new Cliente(rs.getString("nombre"), rs.getString("email"), rs.getString("telefono"), rs.getInt("codigo"), rs.getString("direccion_entrega"));
                list.add(cl);
            }
            rs.close();
        }
    }

    public static Cliente cargarCliente(String cedula) throws ClassNotFoundException, SQLException {
        ResultSet rs = DataBase.getInstance().ExecuteQuery("select * from gsisinve.cliente where cedula='" + cedula + "'");
        Cliente cl = null;
        if (rs != null) {
            while (rs.next()) {
                cl = new Cliente(rs.getString("nombre"), rs.getString("email"), rs.getString("telefono"), rs.getInt("codigo"), rs.getString("direccion_entrega"));
            }
            rs.close();
        }
        return cl;
    }

    public static Articulo cargarArticulo(String codigo) throws ClassNotFoundException, SQLException {
        String query = "select * from gsisinve.articulo where codigo='" + codigo + "'";
        Articulo ar = null;
        ResultSet rs = DataBase.getInstance().ExecuteQuery(query);
        //password = null;
        if (rs != null) {
            rs.next();
            ar = new Articulo(rs.getString("codigo"), rs.getString("descripcion"), rs.getDouble("costo"), rs.getDouble("utilidad"), rs.getBoolean("esgrabado"));
        }
        rs.close();
        return ar;
    }

    public static String comprobarUsuario(String cedula) throws ClassNotFoundException, SQLException {
        String query = "select password from gsisinve.usuario where cedula='" + cedula + "'";
        String password = "admin";
        ResultSet rs = DataBase.getInstance().ExecuteQuery(query);
        //password = null;
        if (rs != null) {
            rs.next();
            password = rs.getString("password");
        }
        rs.close();
        System.out.println(password);
        return password;
    }

    public static int countRows(String tableName) throws ClassNotFoundException, SQLException {
        ResultSet rs = DataBase.getInstance().ExecuteQuery("SELECT COUNT(*) FROM gsisinve." + tableName);
        int rowcount = 0;
        if (rs != null) {
            rs.next();
            rowcount = rs.getInt(1);
        }
        rs.close();
        return rowcount;
    }
}
