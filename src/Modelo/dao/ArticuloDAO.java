package Modelo.dao;

import Modelo.Beans.Articulo;
import Modelo.Beans.Bodega;
import Modelo.Beans.Inventario;
import Modelo.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Casa on 14/02/2015.
 */
public class ArticuloDAO {
    private static ArticuloDAO ourInstance = new ArticuloDAO();
    public static ResultSetProcessor<Articulo> processor = (rs, rw) -> {
        Articulo art = new Articulo(rs.getNString("id"), rs.getNString("desc"), rs.getDouble("cost"), rs.getDouble("util"), true);
        art.setEsGrabado(rs.getByte("grav") == 1);
        return art;
    };

    public static ArticuloDAO getInstance() {
        return ourInstance;
    }

    private ArticuloDAO() {
    }

    public IntermediateArticuloSelect select() {
        return new IntermediateArticuloSelect();
    }

    public class IntermediateArticuloSelect implements IntermediateSelect<Articulo, String> {
        Bodega bodegaOrigen = null;
        boolean buscarEnTodasLasBodegas = true;

        /**
         * Getter for Bodega
         *
         * @return the Selected Bodega, null if Bodegas hasn't being set
         */
        public Bodega getBodegaOrigen() {
            return bodegaOrigen;
        }

        /**
         * Sets the Bodega to restrict the search
         *
         * @param origen The Bodega
         * @return the modified object
         */
        public IntermediateArticuloSelect setBodegaOrigen(Bodega origen) {
            this.bodegaOrigen = origen;
            this.buscarEnTodasLasBodegas=false;
            return this;
        }

        /**
         * Set if search must include all Bodegas
         * @param s True to enable
         * @return Modified Object
         */
        public IntermediateSelect setBuscarTodaslasBodegas(boolean s) {
            this.buscarEnTodasLasBodegas = s;
            return this;
        }

        @Override
        final public List<Articulo> all() {
            ArrayList<Articulo> articulos = new ArrayList<>();
            try (Connection c = DataBase.getInstance().getConnection()) {
                Statement stm = c.createStatement();
                if (buscarEnTodasLasBodegas)
                    retrieveAll(stm, articulos);
                else {
                    //TODO Mejorar Estos llamados
                    retrieveAll(stm, articulos);
                    if (bodegaOrigen == null) throw new NullPointerException("Bodega de Origen No ha sido establecida");
                    List<String> codigos = InventarioDAO.getInstance().select().where(b -> b.getCodigo_bodega().equals(bodegaOrigen.getCodigo())).stream().map(Inventario::getCodigo_articulo).collect(Collectors.toList());
                    return articulos.stream().filter(a -> codigos.contains(a.getCodigo())).collect(Collectors.toList());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return articulos;
        }

        private void retrieveAll(Statement stm, List<Articulo> ls) {
            try {
                ResultSet rs = stm.executeQuery("SELECT * FROM TB_Articulo");
                int i = 0;
                while (rs.next()) {
                    ls.add(processor.process(rs, i++));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        final public Articulo byID(String pk) {
            Articulo a = null;
            try (Connection c = DataBase.getInstance().getConnection()) {
                Statement stm = c.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM TB_Articulo WHERE TB_Articulo.id = '" + pk+"'");
                while (rs.next())
                    a = processor.process(rs, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return a;
        }

        @SafeVarargs
        @Override
        final public List<Articulo> where(Predicate<Articulo>... pl) {
            if (pl.length == 0) return all();
            Predicate<Articulo> ini = pl[0];
            for (int i = 1; i < pl.length; i++) ini = ini.and(pl[i]);
            return all().stream().filter(ini).collect(Collectors.toList());
        }

        /**
         * Retrieves all the Articulos that match the idList
         *
         * @param idList A list containing the desired Articulos
         * @return A new List with the Articulos found
         */
        final public List<Articulo> fromIdList(List<String> idList) {
            Articulo a = null;
            List<Articulo> articulos = new ArrayList<>();
            try (Connection c = DataBase.getInstance().getConnection()) {
                Statement stm = c.createStatement();
                StringBuilder sb = new StringBuilder();
                idList.stream().forEach(s -> sb.append("'").append(s).append("',"));
                sb.deleteCharAt(sb.lastIndexOf(","));
                String sql = "";
                if (buscarEnTodasLasBodegas)
                    sql = "SELECT * FROM TB_Articulo art where art.id IN (" + sb.toString() + ")";
                else
                    sql = "SELECT * FROM TB_Articulo art where art.id IN (SELECT inv.code_art FROM TB_Inventario inv WHERE inv.code_art IN (" + sb.toString() + ") AND inv.code_bod = '" + bodegaOrigen.getCodigo() + "')";
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    a = processor.process(rs, 0);
                    if (a != null) articulos.add(a);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return articulos;
        }

        /**
         * Retrieves all Articulos for a Bodega Set
         *
         * @param bodegas the list of Bodegas to Search for
         * @return a List containing all the Objects for that Bodega
         */
        final public List<Articulo> AllInBodegaList(List<Bodega> bodegas) {
            List<Articulo> articulos = new ArrayList<>();
            Articulo a;
            try (Connection c = DataBase.getInstance().getConnection()) {
                Statement stm = c.createStatement();
                StringBuilder sb = new StringBuilder();
                bodegas.stream().forEach(s -> sb.append("'").append(s.getCodigo()).append("',"));
                sb.deleteCharAt(sb.lastIndexOf(","));
                String sql = "";
                sql = "SELECT * FROM TB_Articulo art where art.id IN (SELECT DISTINCT(inv.code_art) FROM TB_Inventario inv WHERE inv.code_bod IN (" + sb.toString() + "))";
                ResultSet rs = stm.executeQuery(sql);
                while (rs.next()) {
                    a = processor.process(rs, 0);
                    if (a != null) articulos.add(a);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return articulos;
        }
    }
}
