package library;

import java.util.List;

public class DfsSubtree {

    int[] dfsOrder;
    int[] loc;
    int[] subStart;
    int[] subEnd;

    int n;
    List[] g;

    public DfsSubtree(int n, List[] g) {
        this.n = n;
        this.g = g;
    }

    int dfs(int u, int p, int st) {
        dfsOrder[st] = u;
        loc[u] = st;
        subStart[u] = st;
        st++;

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v == p)
                continue;

            st = dfs(v, u, st);
        }

        subEnd[u] = st - 1;

        return st;
    }

    boolean isInSubtree(int u, int v) {
        if (loc[v] >= subStart[u] && loc[v] <= subEnd[u]) {
            return true;
        }

        return false;
    }
}
