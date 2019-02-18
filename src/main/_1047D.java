package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1047D {
    class HopcroftKarpBipartiteMatching {
        int m;
        int n;
        List[] g;

        int[] pairU;
        int[] pairV;

        int[] dis;

        int inf = 10000000;

        int NIL = 0;

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

    int n;
    int m;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        m = in.nextInt();

        long ans = 0;

        if (n > 100) {
            int nn = (n - 100) % 6;
            long nq = (n - 100) / 6;
            ans += nq * 6 * m;
            n = 100 + nn;
        }

        if (m > 100) {
            int nm = (m - 100) % 6;
            long mq = (m - 100) / 6;
            ans += mq * 6 * n;
            m = 100 + nm;
        }

        int[][] mat = new int[n + 1][m + 1];

        int u = 1;
        int v = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if ((i + j) % 2 == 0) {
                    mat[i][j] = u;
                    u++;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if ((i + j) % 2 != 0) {
                    mat[i][j] = v;
                    v++;
                }
            }
        }

        List[] g = new List[u];

        for (int i = 0; i < u; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if ((i + j) % 2 == 0) {
                    addEdge(mat, g, i, j, i - 3, j);
                    addEdge(mat, g, i, j, i - 2, j - 1);
                    addEdge(mat, g, i, j, i - 2, j + 1);
                    addEdge(mat, g, i, j, i, j - 3);
                    addEdge(mat, g, i, j, i - 1, j - 2);
                    addEdge(mat, g, i, j, i + 1, j - 2);
                    addEdge(mat, g, i, j, i, j + 3);
                    addEdge(mat, g, i, j, i - 1, j + 2);
                    addEdge(mat, g, i, j, i + 1, j + 2);
                    addEdge(mat, g, i, j, i + 3, j);
                    addEdge(mat, g, i, j, i + 2, j - 1);
                    addEdge(mat, g, i, j, i + 2, j + 1);
                }
            }
        }

        HopcroftKarpBipartiteMatching bpm = new HopcroftKarpBipartiteMatching(u - 1, v - 1, g);

        ans += 2 * bpm.maximumMatching();

        out.println(ans);
    }

    void addEdge(int[][] mat, List[] g, int i, int j, int s, int t) {
        if (s <= 0 || t <= 0 || s > n || t > m)
            return;

        g[mat[i][j]].add(mat[s][t]);
    }
}
