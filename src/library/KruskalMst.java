package library;

import java.util.ArrayList;
import java.util.List;

public class KruskalMst {
    class DsuInteger {

        public DsuInteger(int n) {
            this.n = n;
            this.parent = new int[n];
            this.rank = new int[n];
            this.size = new int[n];
        }

        int[] parent;
        int[] rank;
        int[] size;
        int n;


        int createSet(int x) {
            parent[x] = x;
            rank[x] = 0;
            size[x] = 1;
            return x;
        }

        int findSet(int x) {
            int par = parent[x];
            if (x != par) {
                parent[x] = findSet(par);
                return parent[x];
            }
            return par;
        }

        int mergeSets(int x, int y) {
            int rx = findSet(x);
            int ry = findSet(y);

            if (rx == ry) {
                return rx;
            }

            int fp = -1;

            if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
                fp = rx;
            } else {
                parent[rx] = ry;
                fp = ry;
            }

            size[fp] = size[rx] + size[ry];

            if (rank[rx] == rank[ry]) {
                rank[ry] = rank[ry] + 1;
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

        DsuInteger dsu = new DsuInteger(n);

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