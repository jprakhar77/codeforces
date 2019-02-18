package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class _949C {
    class KosarajuScc {
        int n;
        List[] g;

        public KosarajuScc(int n, List[] g) {
            this.n = n;
            this.g = g;
            this.comp = new int[n];
            this.rg = new List[n];
        }

        ArrayDeque<Integer> sccOrder = new ArrayDeque<>();

        int[] comp;

        List[] rg;

        void generateSccOrder() {
            boolean[] vis = new boolean[2 * n];
            for (int i = 0; i < n; i++) {
                if (!vis[i])
                    dfs0(i, vis);
            }
        }

        void dfs0(int u, boolean[] vis) {
            vis[u] = true;
            for (Integer v : (List<Integer>) g[u]) {
                if (!vis[v]) {
                    dfs0(v, vis);
                }
            }

            sccOrder.addFirst(u);
        }

        void reverseEdge() {
            for (int i = 0; i < n; i++) {
                rg[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                for (Integer v : (List<Integer>) g[i]) {
                    rg[v].add(i);
                }
            }
        }

        void scc() {
            boolean[] vis = new boolean[n];
            int cn = 0;
            for (Integer v : sccOrder) {

                if (!vis[v]) {
                    dfs1(v, rg, cn, vis);
                    cn++;
                }
            }
        }

        void dfs1(int u, List[] g, int cn, boolean[] vis) {
            comp[u] = cn;
            vis[u] = true;

            for (int j = 0; j < g[u].size(); j++) {
                int v = (int) g[u].get(j);

                if (!vis[v]) {
                    dfs1(v, g, cn, vis);
                }
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int h = in.nextInt();

        int[] u = new int[n];
        for (int i = 0; i < n; i++) {
            u[i] = in.nextInt();
        }

        List[] g = new List[n];
        List[] ug = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
            ug[i] = new ArrayList();
        }

        for (int i = 0; i < m; i++) {
            int c1 = in.nextInt() - 1;
            int c2 = in.nextInt() - 1;

            int c1n = (u[c1] + 1) % h;
            int c2n = (u[c2] + 1) % h;

            if (c1n == u[c2]) {
                g[c1].add(c2);
                ug[c2].add(c1);
                ug[c1].add(c2);
            }
            if (c2n == u[c1]) {
                g[c2].add(c1);
                ug[c2].add(c1);
                ug[c1].add(c2);
            }
        }

        scc = new KosarajuScc(n, g);
        scc.generateSccOrder();
        scc.reverseEdge();
        scc.scc();

        int ans = n + 1;
        int ansc = -1;
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                maxc = -1;
                maxcs = -1;
                dfs(i, vis, ug);
                if (ans > maxcs) {
                    ans = maxcs;
                    ansc = maxc;
                }
            }
        }

        out.println(ans);

        for (int i = 0; i < n; i++) {
            if (scc.comp[i] == ansc) {
                out.print((i + 1) + " ");
            }
        }

    }

    KosarajuScc scc;
    int maxc = -1;
    int maxcs = -1;

    void dfs(int u, boolean[] vis, List[] g) {
        if (scc.comp[u] == maxc) {
            maxcs++;
        }

        if (scc.comp[u] > maxc) {
            maxc = scc.comp[u];
            maxcs = 1;
        }

        vis[u] = true;

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);
            if (!vis[v])
                dfs(v, vis, g);
        }
    }
}
