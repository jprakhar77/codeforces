package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _609E {
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

    public static class SparseTableLca {
        List[] g;
        int[] p;
        int[] pw;
        int[] h;
        int n;
        int log = 0;

        SparseTableLca.node r;

        int[][] stp;
        int[][] stpw;

        static class node {
            int v;
            int w;

            public node(int v, int w) {
                this.v = v;
                this.w = w;
            }
        }

        public SparseTableLca(List[] g, node r) {
            this.g = g;
            this.r = r;
            this.n = g.length;
            this.p = new int[n];
            this.pw = new int[n];
            this.h = new int[n];
            int cn = n;
            while (cn > 0) {
                cn /= 2;
                log++;
            }
            this.stp = new int[n][log + 1];
            this.stpw = new int[n][log + 1];
            dfs(r, r.v, 0);
            for (int i = 0; i < n; i++) {
                stp[i][0] = p[i];
                stpw[i][0] = pw[i];
            }
            init();
        }

        void dfs(node u, int cp, int ch) {
            p[u.v] = cp;
            pw[u.v] = u.w;
            h[u.v] = ch;

            for (int i = 0; i < g[u.v].size(); i++) {
                node v = (node) g[u.v].get(i);

                if (v.v != cp) {
                    dfs(v, u.v, ch + 1);
                }
            }
        }

        public SparseTableLca(int[] p, int[] pw, int[] h) {
            this.p = p;
            this.pw = pw;
            this.h = h;
            this.n = p.length;

            int cn = n;
            while (cn > 0) {
                cn /= 2;
                log++;
            }

            this.stp = new int[n][log + 1];
            this.stpw = new int[n][log + 1];

            for (int i = 0; i < n; i++) {
                stp[i][0] = p[i];
                stpw[i][0] = pw[i];
            }
        }

        void init() {
            for (int i = 1; i <= log; i++) {
                for (int j = 0; j < n; j++) {
                    stp[j][i] = stp[stp[j][i - 1]][i - 1];
                    stpw[j][i] = Math.max(stpw[j][i - 1], stpw[stp[j][i - 1]][i - 1]);
                }
            }
        }

        int lca(int u, int v) {
            if (h[u] < h[v]) {
                int t = u;
                u = v;
                v = t;
            }

            int h1 = h[u];
            int h2 = h[v];

            if (h1 > h2) {
                int d = h1 - h2;
                int cu = u;
                for (int i = log; i >= 0; i--) {
                    if (d >= 1 << i) {
                        cu = stp[cu][i];
                        d -= (1 << i);
                    }
                }
                u = cu;
            }

            if (u == v) {
                return u;
            }

            for (int i = log; i >= 0; i--) {
                if (stp[u][i] != stp[v][i]) {
                    u = stp[u][i];
                    v = stp[v][i];
                }
            }

            return p[u];
        }

        int maxdis(int u, int v) {
            int lca = lca(u, v);

            if (u == v) {
                return 0;
            }

            if (lca == u || lca == v) {
                return maxdisAncestor(u, v);
            }

            return Math.max(maxdisAncestor(u, lca), maxdisAncestor(v, lca));
        }

        int maxdisAncestor(int u, int v) {
            if (h[u] < h[v]) {
                int t = u;
                u = v;
                v = t;
            }

            int diff = h[u] - h[v];

            int cu = u;
            int max = 0;
            for (int i = log; i >= 0; i--) {
                if ((1 << i) <= diff) {
                    diff -= (1 << i);
                    max = Math.max(max, stpw[cu][i]);
                    cu = stp[cu][i];
                }
            }

            return max;
        }

    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        KruskalMst kruskalMst = new KruskalMst();

        List<KruskalMst.Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            edges.add(kruskalMst.new Edge(i, in.nextInt() - 1, in.nextInt() - 1, in.nextInt()));
        }

        List<KruskalMst.Edge> nedges = kruskalMst.kruskal(edges, n);

        List[] g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<SparseTableLca.node>();
        }

        long mst = 0;

        for (int i = 0; i < nedges.size(); i++) {
            KruskalMst.Edge edge = nedges.get(i);

            int u = edge.u;
            int v = edge.v;
            int w = edge.w;

            g[u].add(new SparseTableLca.node(v, w));
            g[v].add(new SparseTableLca.node(u, w));

            mst += w;
        }

        SparseTableLca stlca = new SparseTableLca(g, new SparseTableLca.node(0, 0));

        long[] ans = new long[m];
        for (int i = 0; i < edges.size(); i++) {
            KruskalMst.Edge edge = edges.get(i);

            int u = edge.u;
            int v = edge.v;
            int w = edge.w;

            long max = stlca.maxdis(u, v);

            ans[edge.i] = mst + w - max;
        }

        for (int i = 0; i < m; i++) {
            out.println(ans[i]);
        }
    }
}
