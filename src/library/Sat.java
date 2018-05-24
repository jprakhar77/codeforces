package library;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Sat {

    int n;
    List[] g;

    public Sat(int n, List[] g) {
        this.n = n;
        this.g = g;
    }

    List<Integer> topoOrder = new ArrayList<>();

    void topoSort() {

        int[] in = new int[2 * n];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < g[i].size(); j++) {
                int v = (int) g[i].get(j);

                in[v]++;
            }
        }

        for (int i = 0; i < 2 * n; i++) {
            if (in[i] == 0) {
                q.addLast(i);
                topoOrder.add(i);
            }
        }

        while (!q.isEmpty()) {
            int ce = q.removeFirst();

            topoOrder.add(ce);
            for (int i = 0; i < g[ce].size(); i++) {
                int v = (int) g[ce].get(i);

                in[v]--;

                if (in[v] == 0) {
                    q.addLast(v);
                }
            }
        }
    }

    int[] comp = new int[2 * n];

    List[] rg = new List[n];

    void reverseEdge() {
        for (int i = 0; i < 2 * n; i++) {
            rg[i] = new ArrayList<>();
        }

        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < g[i].size(); j++) {
                int v = (int) g[i].get(j);

                rg[v].add(i);
            }
        }
    }

    void scc() {
        boolean[] vis = new boolean[2 * n];
        for (int i = 0; i < topoOrder.size(); i++) {
            int cn = 0;

            int v = (int) topoOrder.get(i);

            if (!vis[v]) {
                dfs(v, rg, cn, vis);
            }
        }
    }

    void dfs(int u, List[] g, int cn, boolean[] vis) {
        comp[u] = cn;
        vis[u] = true;

        for (int j = 0; j < g[u].size(); j++) {
            int v = (int) g[u].get(j);

            if (!vis[v]) {
                dfs(v, g, cn, vis);
            }
        }
    }

    int[] val = new int[n];

    boolean checkSatisfiability() {
        for (int i = 0; i < n; i++) {
            if (comp[i] == comp[n + i]) {
                return false;
            }
        }

        return true;
    }

    void assignVals() {
        boolean[] vis = new boolean[2 * n];
        for (int i = 0; i < topoOrder.size(); i++) {
            int cn = 0;

            int v = (int) topoOrder.get(i);

            if (!vis[v]) {
                dfs2(v, rg, cn, vis);
            }
        }
    }

    void dfs2(int u, List[] g, int cn, boolean[] vis) {
        if (u >= n) {
            if (val[u - n] == 0) {
                val[u - n] = 1;
            }
        } else {
            if (val[u] == 0) {
                val[u] = -1;
            }
        }

        vis[u] = true;

        for (int j = 0; j < g[u].size(); j++) {
            int v = (int) g[u].get(j);

            if (!vis[v]) {
                dfs(v, g, cn, vis);
            }
        }
    }
}
