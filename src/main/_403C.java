package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class _403C {
    class KosarajuScc {
        int n;
        List[] g;

        public KosarajuScc(int n, List[] g) {
            this.n = n;
            this.g = g;
            this.comp = new int[n];
            this.rg = new List[n];
            this.val = new int[n];
        }

        ArrayDeque<Integer> sccOrder = new ArrayDeque<>();

        int[] comp;

        List[] rg;

        int[] val;

        void generateSccOrder() {
            boolean[] vis = new boolean[n];
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

        List[] g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }

        int nze = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = in.nextInt();
                if (num > 0) {
                    nze++;
                    g[i].add(j);
                }
            }
        }

        if (nze > Math.min(n * n - 1, (n - 1) * (n - 1) + n)) {
            out.println("YES");
            return;
        }

        KosarajuScc scc = new KosarajuScc(n, g);

        scc.generateSccOrder();
        scc.reverseEdge();
        scc.scc();

        for (int i = 0; i < scc.comp.length; i++) {
            if (scc.comp[i] > 0) {
                out.println("NO");
                return;
            }
        }

        out.println("YES");

    }
}
