package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KruskalMst {
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
            T rx = parent.get(x);
            T ry = parent.get(y);

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

    class Edge {
        int i;
        int u;
        int v;
        int w;

        public Edge(int i, int u, int v, int w) {
            this.i = i;
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    List<Edge> kruskal(List<Edge> edges, int n) {

        DSU<Integer> dsu = new DSU<>();

        for (int i = 0; i < n; i++) {
            dsu.createSet(i);
        }
        edges.sort((e1, e2) -> e1.w - e2.w);

        List<Edge> mst = new ArrayList<>();

        for (int i = 0; i < edges.size(); i++) {
            Edge ce = edges.get(i);

            int u = ce.u;
            int v = ce.v;

            if (dsu.findSet(u) != dsu.findSet(v)) {
                mst.add(ce);
                dsu.mergeSets(u, v);
            }
        }

        return mst;
    }
}