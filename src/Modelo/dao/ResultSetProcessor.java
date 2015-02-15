package Modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Casa on 14/02/2015.
 */
@FunctionalInterface
public interface ResultSetProcessor<T> {
    /**
     * @param rs       ResultSet in inspection
     * @param rowCount row count
     * @return A fullyConstructed object of Type T
     * @throws SQLException
     */
    public T process(ResultSet rs, int rowCount) throws SQLException;
}
