package Modelo.dao;

import Modelo.Beans.Inventario;
import javafx.util.Pair;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Casa on 14/02/2015.
 */
public class InventarioDAO {
    private static InventarioDAO instance = null;

    public static InventarioDAO getInstance() {
        if (instance == null) instance = new InventarioDAO();
        return instance;
    }

    public IntermediateInventorySelect select() {
        return new IntermediateInventorySelect();
    }

    //TODO Define Methods
    public class IntermediateInventorySelect implements IntermediateSelect<Inventario, Pair<String, String>> {
        @Override
        final public List<Inventario> all() {
            throw new UnsupportedOperationException();
        }

        @Override
        final public Inventario byID(Pair<String, String> pk) {
            throw new UnsupportedOperationException();
        }

        @Override
        @SafeVarargs
        final public List<Inventario> where(Predicate<Inventario>... pl) {
            throw new UnsupportedOperationException();
        }
    }
}
