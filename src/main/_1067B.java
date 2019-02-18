package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.List;

public class _1067B {
    class DiameterTree {
        List[] g;

        int node1;
        int dis = -1;

        int[] h;

        public DiameterTree(List[] g) {
            this.g = g;
            this.h = new int[g.length];
        }

        void dfs(int u, int p, int cmax) {
            if (cmax > dis) {
                node1 = u;
                dis = cmax;
            }

            h[u] = cmax;

            for (int edge : (List<Integer>) g[u]) {
                int v = edge;
                if (v != p)
                    dfs(v, u, cmax + 1);
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int k = in.nextInt();


        List[] g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        DiameterTree dt = new DiameterTree(g);

        for (int i = 0; i < n; i++) {
            if (g[i].size() == 1) {
                dt.dfs(i, -1, 0);
                break;
            }
        }

        int[] h1 = dt.h.clone();

        dt.dfs(dt.node1, -1, 0);

        int[] h2 = dt.h.clone();

        int c = 0;

        int node = -1;

        for (int i = 0; i < n; i++) {
            if (h1[i] == k && h2[i] == k) {
                c++;
                node = i;
            }
        }

        if (c != 1) {
            out.println("No");
            return;
        }

        dt.dis = -1;

        dt.dfs(node, -1, 0);

        dis = dt.dis;

        if (dis != k) {
            out.println("No");
            return;
        }

        int[] h = dt.h;

        dfs(node, -1, g, h);

        if (poss) {
            out.println("Yes");
        } else {
            out.println("No");
        }
    }

    boolean poss = true;
    int dis;

    void dfs(int u, int p, List[] g, int[] h) {
        if (g[u].size() == 1) {
            if (h[u] != dis) {
                poss = false;
            }
            return;
        }

        int c = 0;
        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                c++;
                dfs(v, u, g, h);
            }
        }

        if (c < 3) {
            poss = false;
        }
    }
}
