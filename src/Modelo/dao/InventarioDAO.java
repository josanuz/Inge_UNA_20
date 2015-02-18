package Modelo.dao;

import Modelo.Beans.Inventario;
import Modelo.DataBase;
import util.TwoTuple;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Casa on 14/02/2015.
 */
public class InventarioDAO {
    private static InventarioDAO instance = null;
    public static ResultSetProcessor<Inventario> processor = (rs, rw) -> {
        Inventario inventario = new Inventario(rs.getNString("code_art"), rs.getNString("code_bod"), rs.getInt("quant"), rs.getInt("max_quant"), rs.getInt("min_quant"));
        return inventario;
    };

    public static InventarioDAO getInstance() {
        if (instance == null) instance = new InventarioDAO();
        return instance;
    }

    public IntermediateInventorySelect select() {
        return new IntermediateInventorySelect();
    }

    //TODO Terminar de Definir metodos
    public class IntermediateInventorySelect implements IntermediateSelect<Inventario, TwoTuple<String, String>> {
        @Override
        final public List<Inventario> all() {
            ArrayList<Inventario> items = new ArrayList<>();
            try (Connection c = DataBase.getInstance().getConnection()) {
                Statement stm = c.createStatement();
                retrieveAll(stm, items);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return items;
        }

        @Override
        public Inventario byID(TwoTuple<String, String> pk) {
            Inventario inv = null;
            try (Connection c = DataBase.getInstance().getConnection()) {
                String sql = "SELECT * FROM TB_Inventario inv WHERE inv.code_art = ? AND inv.code_bod = ?";
                PreparedStatement pstm = c.prepareStatement(sql);
                pstm.setString(1, pk.getFirst());
                pstm.setString(2, pk.getSecond());
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    inv = processor.process(rs, 0);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return inv;
        }

        private void retrieveAll(Statement statement, List<Inventario> repo) throws SQLException {
            ResultSet rs = statement.executeQuery("SELECT * FROM TB_Inventario");
            Inventario inv;
            while (rs.next()) {
                inv = processor.process(rs, 0);
                if (inv != null) repo.add(inv);
            }
        }



        @Override
        @SafeVarargs
        final public List<Inventario> where(Predicate<Inventario>... pl) {
            throw new UnsupportedOperationException();
        }
    }
}
