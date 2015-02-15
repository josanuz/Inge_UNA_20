package Modelo.dao;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Casa on 14/02/2015.
 */
public interface IntermediateSelect<T, K> {
    /**
     * Use to select all elements
     *
     * @return all elements en the data source
     */
    public List<T> all();

    /**
     * Use to select the element with the key
     *
     * @param pk The unique key identifying the desired object
     * @return a new reference to the element, null otherwise
     */
    public T byID(K pk);

    /**
     * Use to select a set of elements that fully satisfy the predicates
     *
     * @param pl list of predicates
     * @return a subset of the elements that meet the requirements
     */
    @SuppressWarnings("unchecked")
    public List<T> where(Predicate<T>... pl);
}
