package library;

import java.util.HashMap;
import java.util.Map;

//T should implement equals AND hashcode
public class DSU<T> {

    Map<T, T> parent = new HashMap<>();
    Map<T, Integer> rank = new HashMap<>();

    T createSet(T x) {
        parent.put(x, x);
        rank.put(x, 0);
        return x;
    }

    T findSet(T x) {
        T par = parent.get(x);
        if (!x.equals(par)) {
            parent.put(x, findSet(par));
            return parent.get(x);
        }
        return par;
    }

    T mergeSets(T x, T y) {
        T rx = findSet(x);
        T ry = findSet(y);

        if (rx.equals(ry)) {
            return rx;
        }

        T fp = null;

        if (rank.get(rx) > rank.get(ry)) {
            parent.put(ry, rx);
            fp = rx;
        } else {
            parent.put(rx, ry);
            fp = ry;
        }

        if (rank.get(rx).equals(rank.get(ry))) {
            rank.put(ry, rank.get(ry) + 1);
        }

        return fp;
    }
}