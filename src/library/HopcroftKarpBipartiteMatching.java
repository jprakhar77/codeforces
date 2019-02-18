package library;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class HopcroftKarpBipartiteMatching {
    int m;
    int n;
    List[] g;

    int[] pairU;
    int[] pairV;

    int[] dis;

    int inf = 10000000;

    int NIL = 0;

    // g should have edges from u to v only.
    // All the u's and v's should be numbered from 1 to m and 1 to n respectively.
    // Be careful. 1-based index in g. From 1 to m.
    public HopcroftKarpBipartiteMatching(int m, int n, List[] g) {
        this.m = m;
        this.n = n;
        this.g = g;
        this.pairU = new int[m + 1];
        this.pairV = new int[n + 1];
        this.dis = new int[m + 1];
    }

    int maximumMatching() {

        Arrays.fill(pairU, NIL);
        Arrays.fill(pairV, NIL);

        int maxMatching = 0;

        while (bfs()) {
            for (int i = 1; i <= m; i++) {
                if (pairU[i] == NIL && dfs(i))
                    maxMatching++;
            }
        }

        return maxMatching;
    }

    boolean bfs() {

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= m; i++) {
            if (pairU[i] == NIL) {
                queue.addLast(i);
                dis[i] = 0;
            } else {
                dis[i] = inf;
            }
        }

        dis[NIL] = inf;

        while (!queue.isEmpty()) {
            int u = queue.removeFirst();

            if (dis[u] < dis[NIL]) {
                for (int v : (List<Integer>) g[u]) {
                    if (dis[pairV[v]] == inf) {
                        queue.addLast(pairV[v]);
                        dis[pairV[v]] = dis[u] + 2;
                    }
                }
            }
        }

        return dis[NIL] < inf;
    }

    boolean dfs(int u) {
        if (u != NIL) {
            for (int v : (List<Integer>) g[u]) {
                if (dis[pairV[v]] == dis[u] + 2) {
                    if (dfs(pairV[v])) {
                        pairV[v] = u;
                        pairU[u] = v;
                        return true;
                    }
                }
            }
            dis[u] = inf;
            return false;
        } else
            return true;
    }

}