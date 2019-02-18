package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class ECivilization {
    class DsuInteger {

        public DsuInteger(int n) {
            this.n = n;
            this.parent = new int[n];
            this.rank = new int[n];
            this.size = new int[n];
            //this.cent = new int[n];
            this.diam = new int[n];
        }

        int[] parent;
        int[] rank;
        int[] size;
        int[] diam;
        //int[] cent;
        int n;


        int createSet(int x) {
            parent[x] = x;
            rank[x] = 0;
            size[x] = 1;
            //cent[x] = x;
            diam[x] = 0;
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

            int c1 = (diam[rx] + 1) / 2;
            int c2 = (diam[ry] + 1) / 2;

            diam[fp] = Math.max(Math.max(diam[rx], diam[ry]), c1 + c2 + 1);


            if (rank[rx] == rank[ry]) {
                rank[ry] = rank[ry] + 1;
            }

            return fp;
        }
    }

    class DiameterTree {
        List[] g;

        int node1;
        int dis = -1;

        int center;

        int[] parent;

        boolean[] vis;


        int node;
        DsuInteger dsu;

        public DiameterTree(List[] g) {
            this.g = g;
            this.parent = new int[g.length];
            this.vis = new boolean[g.length];
        }

        void dfs(int u, int p, int cmax) {

            vis[u] = true;

            if (cmax > dis) {
                node1 = u;
                dis = cmax;
            }

            dsu.mergeSets(node, u);

            parent[u] = p;

            for (int edge : (List<Integer>) g[u]) {
                int v = edge;
                if (v != p)
                    dfs(v, u, cmax + 1);
            }
        }

        int findCenter(int u) {
            int current = node1;

            for (int i = 0; i < dis / 2; i++) {
                current = parent[current];
            }

            return current;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();

        List[] g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        //in.readUndirectedGraph(g, n, m, 1);

        DsuInteger dsu = new DsuInteger(n);

        for (int i = 0; i < n; i++) {
            dsu.createSet(i);
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            //dsu.mergeSets(a, b);

            g[a].add(b);
            g[b].add(a);
        }

        DiameterTree dt = new DiameterTree(g);
        dt.dsu = dsu;
        for (int i = 0; i < n; i++) {
            if (!dt.vis[i]) {
                dt.node = i;
                dt.dfs(i, -1, 0);
                dt.dfs(dt.node1, -1, 0);

                dsu.diam[dsu.findSet(i)] = dt.dis;

                dt.dis = -1;
            }
        }

        while (q-- > 0) {
            int t = in.nextInt();

            if (t == 1) {
                out.println(dsu.diam[dsu.findSet(in.nextInt() - 1)]);
            } else {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;

                dsu.mergeSets(a, b);
            }
        }
    }
}
