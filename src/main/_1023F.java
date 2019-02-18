package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _1023F {
    class DSU {

        public DSU(int n) {
            this.n = n;
            this.parent = new int[n];
            this.rank = new int[n];
        }

        int[] parent;
        int[] rank;
        int n;


        int createSet(int x) {
            parent[x] = x;
            rank[x] = 0;
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

            if (rank[rx] == rank[ry]) {
                rank[ry] = rank[ry] + 1;
            }

            return fp;
        }
    }

    class KruskalMst {
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

    class edge {
        int u;
        int v;
        int w;

        public edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    class vedge {
        int v;
        int w;
        int i;

        public vedge(int v, int w, int i) {
            this.v = v;
            this.w = w;
            this.i = i;
        }
    }

    List[] g;
    vedge[] p;
    int[] d;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();

        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        p = new vedge[n];
        d = new int[n];

        DSU kruskal = new DSU(n);

        for (int i = 0; i < n; i++) {
            kruskal.createSet(i);
        }

        for (int i = 0; i < k; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            g[u].add(new vedge(v, 0, i));
            g[v].add(new vedge(u, 0, i));

            kruskal.mergeSets(u, v);
        }

        boolean[] sel = new boolean[m];
        List<edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();

            edges.add(new edge(u, v, w));

            int us = kruskal.findSet(u);
            int vs = kruskal.findSet(v);

            if (us != vs) {
                sel[i] = true;
                kruskal.mergeSets(us, vs);
                g[u].add(new vedge(v, w, -1));
                g[v].add(new vedge(u, w, -1));
            }
        }
        ans = new int[k];


        dfs(0, -1, -1, 0, -1);

        dsu = new DSU(n);
        for (int i = 0; i < n; i++) {
            dsu.createSet(i);
        }

        for (int i = 0; i < m; i++) {
            if (!sel[i]) {
                merge(edges.get(i));
            }
        }

        for (int i = 0; i < k; i++) {
            if (ans[i] == 0) {
                out.println(-1);
                return;
            }
        }

        long fans = 0;

        for (int i = 0; i < k; i++) {
            fans += ans[i];
        }

        out.println(fans);
    }

    DSU dsu;

    void dfs(int u, int cp, int w, int cd, int en) {
        p[u] = new vedge(cp, w, en);
        d[u] = cd;

        for (vedge v : (List<vedge>) g[u]) {
            if (v.v == cp) {
                continue;
            }
            dfs(v.v, u, v.w, cd + 1, v.i);
        }
    }

    int[] ans;

    void merge(edge e) {

        Set<Integer> set = new HashSet<>();

        int u = dsu.findSet(e.u);
        int v = dsu.findSet(e.v);
        set.add(u);
        set.add(v);

        while (u != v) {
            if (d[u] >= d[v]) {
                vedge ver = p[u];
                u = ver.v;
                if (ver.w == 0) {
                    ans[ver.i] = e.w;
                }
                u = dsu.findSet(u);
                set.add(u);
            } else {
                vedge ver = p[v];
                v = ver.v;
                if (ver.w == 0) {
                    ans[ver.i] = e.w;
                }
                v = dsu.findSet(v);
                set.add(v);
            }
        }

        for (int ver : set) {
            dsu.mergeSets(u, ver);
        }

        int fs = dsu.findSet(u);

        vedge np = p[u];
        int pd = np.v == -1 ? -1 : d[np.v];

        d[fs] = pd + 1;
        p[fs] = p[u];


//        g[fs].clear();

//        for (int ver : set) {
//            for (vedge ver2 : (List<vedge>) g[u].get(u)) {
//                int cv = ver2.v;
//
//                if (!set.contains(cv) && cv != np) {
//                    p[cv] = fs;
//                    d[cv] = pd + 2;
//                    g[fs].add(ver2);
//                }
//            }
//        }
    }

}
