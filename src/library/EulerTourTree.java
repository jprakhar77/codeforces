package library;

import java.util.ArrayList;
import java.util.List;

public class EulerTourTree {
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