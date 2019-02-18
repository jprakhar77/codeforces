package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class DevelopmentCost {
    class query {
        int xs;
        int ys;
        int xd;
        int yd;

        public query(int xs, int ys, int xd, int yd) {
            this.xs = xs;
            this.ys = ys;
            this.xd = xd;
            this.yd = yd;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        int q = in.nextInt();
        int k = in.nextInt();

        a = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }

        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                s.add(a[i][j]);
            }
        }

        List<Integer> l = new ArrayList<>();

        for (int val : s) {
            l.add(val);
        }

        l.sort((x, y) -> x - y);

        List<query> queries = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            queries.add(new query(in.nextInt() - 1, in.nextInt() - 1, in.nextInt() - 1, in.nextInt() - 1));
        }

        int lo = 0;
        int hi = l.size() - 1;

        int ans = l.get(l.size() - 1);
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            max = l.get(mid);

            vis = new int[n][m];

            val = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (vis[i][j] == 0 && a[i][j] <= max) {
                        dfs(i, j);
                        val++;
                    }
                }
            }

            int ck = 0;

            for (int i = 0; i < q; i++) {
                query cq = queries.get(i);

                if (vis[cq.xs][cq.ys] != 0 && vis[cq.xd][cq.yd] != 0 && vis[cq.xs][cq.ys] == vis[cq.xd][cq.yd]) {
                    ck++;
                }
            }

            if (ck >= k) {
                ans = Math.min(ans, max);
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        out.println(ans);
    }

    int[] mx = {-1, 0, 0, 1};
    int[] my = {0, -1, 1, 0};

    int[][] a;
    int[][] vis;
    int n;
    int m;
    int max;
    int val;

    class cell {
        int x;
        int y;

        public cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    void dfs(int x, int y) {
//        vis[x][y] = val;
//        for (int i = 0; i < 4; i++) {
//            int nx = x + mx[i];
//            int ny = y + my[i];
//
//            if (nx < n && ny < m && nx >= 0 && ny >= 0 && vis[nx][ny] == 0 && a[nx][ny] <= max) {
//                dfs(nx, ny);
//            }
//        }

        ArrayDeque<cell> q = new ArrayDeque<>();

        q.addLast(new cell(x, y));
        vis[x][y] = val;

        while (!q.isEmpty()) {
            cell pc = q.removeFirst();

            int cx = pc.x;
            int cy = pc.y;

            for (int i = 0; i < 4; i++) {
                int nx = cx + mx[i];
                int ny = cy + my[i];

                if (nx < n && ny < m && nx >= 0 && ny >= 0 && vis[nx][ny] == 0 && a[nx][ny] <= max) {
                    //dfs(nx, ny);
                    vis[nx][ny] = val;
                    q.addLast(new cell(nx, ny));
                }
            }
        }
    }
}
