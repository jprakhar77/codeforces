package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;

public class agc014c {
    boolean[][] vis;

    int inf = 100000;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        r = in.nextInt();
        c = in.nextInt();
        int k = in.nextInt();

        String[] a = new String[r];

        int si = -1;
        int sj = -1;
        for (int i = 0; i < r; i++) {
            a[i] = in.next();
            for (int j = 0; j < c; j++) {
                if (a[i].charAt(j) == 'S') {
                    si = i;
                    sj = j;
                }
            }
        }

        vis = new boolean[r][c];

        bfs(si, sj, a, k);

        int ans = inf;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (vis[i][j]) {
                    int mind = inf;

                    mind = Math.min(mind, i);
                    mind = Math.min(mind, j);
                    mind = Math.min(mind, c - 1 - j);
                    mind = Math.min(mind, r - 1 - i);

                    int ca = (mind + k - 1) / k;

                    ans = Math.min(ans, 1 + ca);
                }
            }
        }

        out.println(ans);
    }

    int r;
    int c;

    void dfs(int ri, int ci, String[] a, int cd) {
        if (vis[ri][ci])
            return;

        if (a[ri].charAt(ci) == '#')
            return;

        vis[ri][ci] = true;

        if (cd == 0)
            return;

        if (ri > 0)
            dfs(ri - 1, ci, a, cd - 1);
        if (ci > 0)
            dfs(ri, ci - 1, a, cd - 1);
        if (ci < c - 1)
            dfs(ri, ci + 1, a, cd - 1);
        if (ri < r - 1)
            dfs(ri + 1, ci, a, cd - 1);
    }

    class cell {
        int i;
        int j;

        public cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    void bfs(int si, int sj, String[] a, int cd) {

        ArrayDeque<cell> dq = new ArrayDeque<>();

        dq.addLast(new cell(si, sj));
        vis[si][sj] = true;

        int[][] l = new int[r][c];
        l[si][sj] = cd;

        while (dq.size() > 0) {
            cell pc = dq.removeFirst();

            int ri = pc.i;
            int ci = pc.j;

            if (l[ri][ci] > 0) {
                if (ri > 0 && !vis[ri - 1][ci] && a[ri - 1].charAt(ci) != '#') {
                    dq.addLast(new cell(ri - 1, ci));
                    l[ri - 1][ci] = l[ri][ci] - 1;
                    vis[ri - 1][ci] = true;
                }
                if (ci > 0 && !vis[ri][ci - 1] && a[ri].charAt(ci - 1) != '#') {
                    dq.addLast(new cell(ri, ci - 1));
                    l[ri][ci - 1] = l[ri][ci] - 1;
                    vis[ri][ci - 1] = true;
                }
                if (ci < c - 1 && !vis[ri][ci + 1] && a[ri].charAt(ci + 1) != '#') {
                    dq.addLast(new cell(ri, ci + 1));
                    l[ri][ci + 1] = l[ri][ci] - 1;
                    vis[ri][ci + 1] = true;
                }
                if (ri < r - 1 && !vis[ri + 1][ci] && a[ri + 1].charAt(ci) != '#') {
                    dq.addLast(new cell(ri + 1, ci));
                    l[ri + 1][ci] = l[ri][ci] - 1;
                    vis[ri + 1][ci] = true;
                }
            }
        }

    }
}
