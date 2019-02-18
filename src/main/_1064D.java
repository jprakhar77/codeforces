package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;

public class _1064D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int r = in.nextInt();
        int c = in.nextInt();

        int x = in.nextInt();
        int y = in.nextInt();

        String[] mat = new String[n];

        for (int i = 0; i < n; i++) {
            mat[i] = in.next();
        }

        r--;
        c--;

        dis = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dis[i][j] = inf;
            }
        }

        bfs(r, c, mat, n, m);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i].charAt(j) == dot) {
                    int mindis = Math.abs(j - c);
                    if (dis[i][j] == inf)
                        continue;
                    int adis = dis[i][j];

                    if (j <= c) {
                        int minx = c - j;
                        int miny = 0;
                        if (adis > mindis) {
                            minx += (adis - mindis) / 2;
                            miny += (adis - mindis) / 2;
                        }

                        if (minx <= x && miny <= y) {
                            //out.println(i + " " + j);
                            ans++;
                        }
                    } else {
                        int miny = j - c;
                        int minx = 0;
                        if (adis > mindis) {
                            minx += (adis - mindis) / 2;
                            miny += (adis - mindis) / 2;
                        }

                        if (minx <= x && miny <= y) {
                            //out.println(i + " " + j);
                            ans++;
                        }
                    }
                }
            }
        }

        out.println(ans);
    }

    int inf = 400000000;

    class coord {
        int x;
        int y;

        public coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[][] dis;
    char dot = '.';


    void bfs(int sr, int sc, String[] mat, int n, int m) {
        ArrayDeque<coord> dq = new ArrayDeque<>();
        dq.addLast(new coord(sr, sc));
        int[][] vis = new int[n][m];
        vis[sr][sc] = 2;
        dis[sr][sc] = 0;

        while (dq.size() > 0) {
            coord pc = dq.removeFirst();

            int r = pc.x;
            int c = pc.y;

            if (vis[r][c] > 2)
                continue;

            if (r > 0 && mat[r - 1].charAt(c) == dot && vis[r - 1][c] < 2) {
                vis[r - 1][c] = 2;
                dis[r - 1][c] = dis[r][c];
                dq.addFirst(new coord(r - 1, c));
            }

            if (r < n - 1 && mat[r + 1].charAt(c) == dot && vis[r + 1][c] < 2) {
                vis[r + 1][c] = 2;
                dis[r + 1][c] = dis[r][c];
                dq.addFirst(new coord(r + 1, c));
            }

            if (c > 0 && mat[r].charAt(c - 1) == dot && vis[r][c - 1] == 0) {
                vis[r][c - 1] = 1;
                dis[r][c - 1] = dis[r][c] + 1;
                dq.addLast(new coord(r, c - 1));
            }

            if (c < m - 1 && mat[r].charAt(c + 1) == dot && vis[r][c + 1] == 0) {
                vis[r][c + 1] = 1;
                dis[r][c + 1] = dis[r][c] + 1;
                dq.addLast(new coord(r, c + 1));
            }

            vis[r][c] = 3;
        }


    }
}
