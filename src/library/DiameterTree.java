package library;

import java.util.HashSet;
import java.util.Set;

public class DiameterTree {
    Set[] g;

    int node1;
    int dis = -1;

    int center;

    int[] parent;

    public DiameterTree(Set[] g) {
        this.g = g;
        this.parent = new int[g.length];
    }

    void dfs(int u, int p, int cmax) {
        if (cmax > dis) {
            node1 = u;
            dis = cmax;
        }

        parent[u] = p;

        for (edge edge : (HashSet<edge>) g[u]) {
            int v = edge.v;
            if (v != p)
                dfs(v, u, cmax + 1);
        }
    }

    int findCenter(int u) {
        int current = node1;

        for (int i = 0; i < dis / 2; i++) {
            current = parent[current];
        }

        return current;
    }
}