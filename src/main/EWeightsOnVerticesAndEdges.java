package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EWeightsOnVerticesAndEdges {
    class DsuInteger {

        public DsuInteger(int n) {
            this.n = n;
            this.parent = new int[n];
            this.rank = new int[n];
            this.size = new long[n];
        }

        int[] parent;
        int[] rank;
        long[] size;
        int n;


        long[] w;

        int createSet(int x) {
            parent[x] = x;
            rank[x] = 0;
            size[x] = w[x];
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

    class edge {
        int u;
        int v;
        long w;
        int i;

        public edge(int u, int v, long w, int i) {
            this.u = u;
            this.v = v;
            this.w = w;
            this.i = i;
        }
    }

    class vertex {
        int v;
        long w;

        public vertex(int v, long w) {
            this.v = v;
            this.w = w;
        }
    }

    List[] g;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        long[] x = in.nextLongArray(n);

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        List<edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            long w = in.nextInt();

            u--;
            v--;

            edge edge = new edge(u, v, w, i);

            edges.add(edge);

            g[u].add(new vertex(v, w));
            g[v].add(new vertex(u, w));
        }

        edges.sort((s, y) -> (int) Math.signum(s.w - y.w));

        Set<Integer> ep = new HashSet<>();

        DsuInteger dsu = new DsuInteger(n);

        dsu.w = x;

        for (int i = 0; i < n; i++)
            dsu.createSet(i);

        for (int i = 0; i < m; i++) {
            edge ce = edges.get(i);

            dsu.mergeSets(ce.u, ce.v);

            if (ce.w <= dsu.size[dsu.findSet(ce.u)]) {
                ep.add(ce.i);
            }
        }

        boolean[] vis = new boolean[n];

        int ans = 0;

        for (int i = m - 1; i >= 0; i--) {
            edge ce = edges.get(i);

            if (ep.contains(ce.i) && !vis[ce.u]) {
                dfs(ce.u, vis, ce.w);
            } else if (!vis[ce.u]) {
                ans++;
            }
        }

        out.println(ans);
    }

    void dfs(int u, boolean[] vis, long w) {
        vis[u] = true;

        for (int i = 0; i < g[u].size(); i++) {
            vertex v = (vertex) g[u].get(i);

            if (v.w <= w && !vis[v.v]) {
                dfs(v.v, vis, w);
            }
        }
    }
}
