package library;

import java.util.List;

public class CycleFinder {
    List[] g;
    int n;

    public CycleFinder(List[] g, int n) {
        this.g = g;
        this.n = n;
        this.recstack = new boolean[n];
        this.vis = new boolean[n];
    }

    boolean isCycleDirected(int u) {
        isCycle = false;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i);
            }
        }

        return isCycle;
    }

    boolean isCycle;
    boolean[] recstack;
    boolean[] vis;

    private void dfs(int u) {
        recstack[u] = true;

        vis[u] = true;

        for (int v : (List<Integer>) g[u]) {
            if (recstack[v]) {
                isCycle = true;
            }
            if (!vis[v]) {
                dfs(v);
            }
        }

        recstack[u] = false;
    }
}

