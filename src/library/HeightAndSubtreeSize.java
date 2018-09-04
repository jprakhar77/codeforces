package library;

import java.util.List;

public class HeightAndSubtreeSize {
    void dfs(int u, int p, List[] g, int[] st, int[] ht) {
        st[u] = 1;
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);
            if (v != p) {
                dfs(v, u, g, st, ht);
                ht[u] = Math.max(ht[v] + 1, ht[u]);
                st[u] += st[v];
            }
        }
    }
}
