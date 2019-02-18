package library;

import java.util.ArrayList;
import java.util.List;

// Variables named as in
// https://www.topcoder.com/community/competitive-programming/tutorials/range-minimum-query-and-lowest-common-ancestor/#Reduction%20from%20LCA%20to%20RMQ
public class EulerTourTree {
    List[] g;
    int n;

    List<Integer> eulerTour;
    boolean[] vis;

    int[] euler;
    int[] eulerD;
    int[] h;

    public EulerTourTree(List[] g, int n) {
        this.g = g;
        this.n = n;
        this.vis = new boolean[n];
        this.euler = new int[2 * n - 1];
        this.eulerD = new int[2 * n - 1];
        this.h = new int[n];
        this.eulerTour = new ArrayList<>();
    }

    List<Integer> eulerTour() {
        dfs(0, -1, 0);
        return eulerTour;
    }

    int ind = 0;

    void dfs(int u, int p, int cd) {
        vis[u] = true;
        eulerTour.add(u);
        euler[ind] = u;
        eulerD[ind] = cd;
        h[u] = ind;
        ind++;
        for (Integer v : (List<Integer>) g[u]) {
            if (v != p && !vis[v]) {
                dfs(v, u, cd + 1);
                euler[ind] = u;
                eulerD[ind] = cd;
                ind++;
                eulerTour.add(u);
            }
        }
    }
}