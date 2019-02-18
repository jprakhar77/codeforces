package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;

public class AConnectThree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int xa = in.nextInt();
        int ya = in.nextInt();

        int xb = in.nextInt();
        int yb = in.nextInt();
        int xc = in.nextInt();
        int yc = in.nextInt();

        int[][] disa = new int[1001][1001];
        coord[][] para = new coord[1001][max];

        bfs(xa, ya, disa, para);


        int[][] disb = new int[1001][1001];
        coord[][] parb = new coord[1001][max];

        bfs(xb, yb, disb, parb);

        int[][] disc = new int[1001][1001];
        coord[][] parc = new coord[1001][max];

        bfs(xc, yc, disc, parc);

        int mind = Integer.MAX_VALUE;

        coord best = new coord(-1, -1);
        for (int i = 0; i <= 1000; i++) {
            for (int j = 0; j <= 1000; j++) {
                int dis = disa[i][j] + disb[i][j] + disc[i][j] + 1;

                if (dis < mind) {
                    mind = dis;
                    best.x = i;
                    best.y = j;
                }
            }
        }

        out.println(mind);

        out.println(best.x + " " + best.y);

        print(para, best, out);
        print(parb, best, out);
        print(parc, best, out);
    }

    void print(coord[][] par, coord st, OutputWriter out) {
        coord cur = par[st.x][st.y];

        while (cur != null) {
            out.println(cur.x + " " + cur.y);
            cur = par[cur.x][cur.y];
        }
    }

    int max = 1001;

    void bfs(int sx, int sy, int[][] dis, coord[][] par) {
        ArrayDeque<coord> q = new ArrayDeque<>();

        boolean[][] vis = new boolean[1001][1001];


        q.addLast(new coord(sx, sy));
        vis[sx][sy] = true;

        while (!q.isEmpty()) {
            coord pe = q.removeFirst();

            int x = pe.x;
            int y = pe.y;

            //l
            if (isv(x - 1, y, vis)) {
                dis[x - 1][y] = dis[x][y] + 1;
                vis[x - 1][y] = true;
                par[x - 1][y] = pe;
                q.addLast(new coord(x - 1, y));
            }

            if (isv(x, y - 1, vis)) {
                dis[x][y - 1] = dis[x][y] + 1;
                vis[x][y - 1] = true;
                par[x][y - 1] = pe;
                q.addLast(new coord(x, y - 1));
            }
            if (isv(x, y + 1, vis)) {
                dis[x][y + 1] = dis[x][y] + 1;
                vis[x][y + 1] = true;
                par[x][y + 1] = pe;
                q.addLast(new coord(x, y + 1));
            }
            if (isv(x + 1, y, vis)) {
                dis[x + 1][y] = dis[x][y] + 1;
                vis[x + 1][y] = true;
                par[x + 1][y] = pe;
                q.addLast(new coord(x + 1, y));
            }
        }
    }


    boolean isv(int x, int y, boolean[][] v) {
        if (x < 0 || x > 1000 || y < 0 || y > 1000) {
            return false;
        }

        if (v[x][y])
            return false;

        return true;
    }

    class coord {
        int x;
        int y;

        public coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
