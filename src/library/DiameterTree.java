package library;

import java.util.HashSet;
import java.util.Set;

public class DiameterTree {
    Set[] g;

    int node1;
    int dis = -1;

    public DiameterTree(Set[] g) {
        this.g = g;
    }

    void dfs(int u, int p, int cmax) {
        if (cmax > dis) {
            node1 = u;
            dis = cmax;
        }

        for (edge edge : (HashSet<edge>) g[u]) {
            int v = edge.v;
            if (v != p)
                dfs(v, u, cmax + 1);
        }
    }
}