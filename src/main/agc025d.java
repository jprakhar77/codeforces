package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class agc025d {
    class point {
        int x;
        int y;

        public point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            point point = (point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    class HopcroftKarpBipartiteMatching {
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
            this.mapV = new int[n + 1];
            this.mapU = new int[m + 1];
            this.revMapU = new int[m + 1];
            this.revMapV = new int[n + 1];
            Arrays.fill(mapU, -1);
            Arrays.fill(mapV, -1);
            graph();
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

        int[] mapU;
        int[] mapV;
        int[] revMapU;
        int[] revMapV;

        List[] graph() {

            List[] newG = new List[m + 1];

            int vertexU = 0;
            int vertexV = 0;

            for (int i = 0; i < g.length; i++) {
                if (g[i].size() == 0) {
                    continue;
                }

                vertexU++;
                mapU[i] = vertexU;
                revMapU[vertexU] = i;

                newG[vertexU] = new ArrayList();

                for (int j = 0; j < g[i].size(); j++) {
                    int v = (int) g[i].get(j);

                    if (mapV[v] == -1) {
                        vertexV++;
                        mapV[v] = vertexV;
                        revMapV[vertexV] = v;

                        newG[vertexU].add(vertexV);
                    } else {
                        newG[vertexU].add(mapV[v]);
                    }
                }
            }

            this.m = vertexU;
            this.n = vertexV;

            return newG;
        }

        void dfsAlternatingPath(int u, boolean isU, boolean[] visU, boolean[] visV, HashSet<Integer> z) {
            if (isU) {
                if (visU[u])
                    return;

                visU[u] = true;
                z.add(u);
                for (int v : (List<Integer>) g[u]) {
                    if (pairU[u] != v) {
                        dfsAlternatingPath(v, !isU, visU, visV, z);
                    }
                }
            } else {
                if (visV[u])
                    return;

                visV[u] = true;
                z.add(m + u);
                dfsAlternatingPath(pairV[u], !isU, visU, visV, z);
            }
        }

        Set<Integer> maxIndependentSet() {
            HashSet<Integer> z = new HashSet<>();
            boolean[] visU = new boolean[m + 1];
            boolean[] visV = new boolean[n + 1];
            for (int i = 1; i <= m; i++) {
                if (pairU[i] == NIL)
                    dfsAlternatingPath(i, true, visU, visV, z);
            }

            Set<Integer> independentSet = new HashSet<>();

            for (int i = 1; i <= m; i++) {
                if (!z.contains(i)) {
                    int actualVertex = revMapU[i];
                    independentSet.add(actualVertex);
                }
            }

            for (int i = 1; i <= n; i++) {
                if (z.contains(m + i)) {
                    int actualVertex = revMapV[i];
                    independentSet.add(actualVertex);
                }
            }

            return independentSet;
        }

    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int d1 = in.nextInt();

        int d2 = in.nextInt();

        Set<point> points = new HashSet<>();

        for (int i = 0; i * i <= d1; i++) {
            int sq = i * i;

            int rem = d1 - sq;

            int root = in.squareRoot(rem);

            if (root * root == rem) {
                points.add(new point(i, root));
            }
        }

        int[][] isp = new int[2 * n][2 * n];

        boolean[][] vis = new boolean[2 * n][2 * n];
        int[][] l = new int[2 * n][2 * n];

        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                if (!vis[i][j]) {
                    bfs(isp, i, j, vis, points, 1, l, n);
                }
            }
        }

        points = new HashSet<>();

        for (int i = 0; i * i <= d2; i++) {
            int sq = i * i;

            int rem = d2 - sq;

            int root = in.squareRoot(rem);

            if (root * root == rem) {
                points.add(new point(i, root));
            }
        }

        vis = new boolean[2 * n][2 * n];
        l = new int[2 * n][2 * n];

        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                if (!vis[i][j]) {
                    bfs(isp, i, j, vis, points, 2, l, n);
                }
            }
        }

        int[] cnt = new int[4];
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                cnt[isp[i][j]]++;
            }
        }

        int di = -1;

        int n2 = n * n;

        for (int i = 0; i < 4; i++) {
            if (cnt[i] >= n2) {
                di = i;
                break;
            }
        }

        int cc = 0;
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < 2 * n; j++) {
                if (isp[i][j] == di) {
                    out.println(i + " " + j);
                    cc++;

                    if (cc == n2) {
                        return;
                    }
                }
            }
        }
    }

    void bfs(int[][] isp, int sr, int sc, boolean[][] vis, Set<point> points, int add, int[][] l, int n) {
        vis[sr][sc] = true;

        ArrayDeque<point> q = new ArrayDeque<>();

        q.addLast(new point(sr, sc));

        while (!q.isEmpty()) {
            point p = q.removeFirst();

            int i = p.x;
            int j = p.y;

            for (point point : points) {
                for (int a = -1; a <= 1; a += 2) {
                    for (int b = -1; b <= 1; b += 2) {
                        int x = i + a * point.x;
                        int y = j + b * point.y;

                        if (x >= 0 && x < 2 * n && y >= 0 && y < 2 * n) {
                            if (!vis[x][y]) {
                                vis[x][y] = true;
                                if (l[i][j] % 2 == 0) {
                                    isp[x][y] |= add;
                                }

                                l[x][y] = l[i][j] + 1;
                                q.addLast(new point(x, y));
                            }
                        }
                    }
                }
            }
        }
    }
}
