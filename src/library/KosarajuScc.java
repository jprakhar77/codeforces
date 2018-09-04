package library;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KosarajuScc {
    int n;
    List[] g;

    public KosarajuScc(int n, List[] g) {
        this.n = n;
        this.g = g;
        this.comp = new int[2 * n];
        this.rg = new List[2 * n];
        this.val = new int[n];
    }

    ArrayDeque<Integer> sccOrder = new ArrayDeque<>();

    int[] comp;

    List[] rg;

    int[] val;

    void generateSccOrder() {
        boolean[] vis = new boolean[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            if (!vis[i])
                dfs0(i, vis);
        }
    }

    void dfs0(int u, boolean[] vis) {
        vis[u] = true;
        for (Integer v : (Set<Integer>) g[u]) {
            if (!vis[v]) {
                dfs0(v, vis);
            }
        }

        sccOrder.addFirst(u);
    }

    void reverseEdge() {
        for (int i = 0; i < 2 * n; i++) {
            rg[i] = new ArrayList<>();
        }

        for (int i = 0; i < 2 * n; i++) {
            for (Integer v : (Set<Integer>) g[i]) {

                rg[v].add(i);
            }
        }
    }

    void scc() {
        boolean[] vis = new boolean[2 * n];
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
