package util;

/**
 * Created by Casa on 15/02/2015.
 */
public class TwoTuple<K, N> {
    public TwoTuple(K first, N second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public N getSecond() {
        return second;
    }

    public void setSecond(N second) {
        this.second = second;
    }

    private K first;
    private N second;
}
