package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _1042F {
    class DiameterTree {
        List[] g;

        int node1;
        int dis = -1;

        public DiameterTree(List[] g) {
            this.g = g;
        }

        void dfs(int u, int p, int cmax) {
            if (cmax > dis) {
                node1 = u;
                dis = cmax;
            }

            for (int v : (List<Integer>) g[u]) {
                if (v != p)
                    dfs(v, u, cmax + 1);
            }
        }
    }

    List[] g;
    int n;
    int k;
    int diaEnd;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        k = in.nextInt();

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            g[u].add(v);
            g[v].add(u);
        }

        DiameterTree dt = new DiameterTree(g);
        dt.dfs(0, -1, 0);

        diaEnd = dt.node1;

        dfs(diaEnd, -1, 0);

        out.println(ans);
    }

    int ans = 0;
    int minf = -10000000;

    int dfs(int u, int p, int kr) {
//        if (kr == 0) {
//            ans++;
//            kr = k;
//        }

        if (u == diaEnd) {
            ans++;
            kr = k;
        }

        if (u != diaEnd && g[u].size() == 1) {
            if (kr < 0) {
                ans++;
                return k - 1;
            } else {
                return minf;
            }
        }

        int ck = kr;
        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                ck = Math.max(ck, dfs(v, u, ck - 1));
            }
        }

        if (ck > kr) {
            return ck - 1;
        } else {
            return minf;
        }
    }
}
