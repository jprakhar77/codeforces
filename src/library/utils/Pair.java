package library.utils;

public class Pair<K, V> {
    K k;
    V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    K getKey() {
        return k;
    }

    V getValue() {
        return v;
    }

}