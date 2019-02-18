package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _781C {
    class EulerTourTree {
        List[] g;
        int n;

        List<Integer> eulerTour;
        boolean[] vis;

        public EulerTourTree(List[] g, int n) {
            this.g = g;
            this.n = n;
            this.vis = new boolean[n];
            this.eulerTour = new ArrayList<>();
        }

        List<Integer> eulerTour() {
            dfs(0, -1);
            return eulerTour;
        }

        void dfs(int u, int p) {
            vis[u] = true;
            eulerTour.add(u);
            for (Integer v : (List<Integer>) g[u]) {
                if (v != p && !vis[v]) {
                    dfs(v, u);
                    eulerTour.add(u);
                }
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int k = in.nextInt();

        List[] g = new List[n];
        for (int i = 0; i < n; i++)
            g[i] = new ArrayList();

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            g[u].add(v);
            g[v].add(u);
        }

        int size = (2 * n + k - 1) / k;

        EulerTourTree eulerTour = new EulerTourTree(g, n);
        List<Integer> et = eulerTour.eulerTour();

        int ci = 0;
        for (int i = 0; i < k; i++) {
            if (ci + size <= et.size()) {
                out.print(size + " ");
                for (int j = ci; j < ci + size; j++) {
                    out.print((et.get(j) + 1) + " ");
                }
                ci = ci + size;
                out.println();
            } else {
                if (ci < et.size()) {
                    out.print((et.size() - ci) + " ");
                    for (int j = ci; j < et.size(); j++) {
                        out.print((et.get(j) + 1) + " ");
                    }
                    ci = et.size();
                    out.println();
                } else {
                    out.print(1 + " ");
                    out.print(1);
                    out.println();
                }
            }
        }
    }
}
