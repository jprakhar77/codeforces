package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;

public class DKilaniAndTheGame {
    char[][] grid;

    class cell {
        int x;
        int y;
        int itr;
        int dis;

        public cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public cell(int x, int y, int itr, int dis) {
            this.x = x;
            this.y = y;
            this.itr = itr;
            this.dis = dis;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int p = in.nextInt();

        int[] sp = new int[p];

        sp = in.nextIntArray(p);

        grid = new char[n][m];

        ArrayDeque[] qs = new ArrayDeque[p];

        for (int i = 0; i < p; i++) {
            qs[i] = new ArrayDeque();
        }

        for (int i = 0; i < n; i++) {
            String s = in.next();

            grid[i] = s.toCharArray();

            for (int j = 0; j < m; j++) {
                char ch = grid[i][j];

                if (Character.isDigit(ch)) {
                    int num = ch - '1';

                    qs[num].addLast(new cell(i, j, 0, 0));
                }
            }
        }

        for (int itr = 1; ; itr++) {
            int md = 0;
            for (int i = 0; i < p; i++) {
                char pc = (char) (i + '1');
                ArrayDeque<cell> q = qs[i];
                while (!q.isEmpty()) {
                    cell cc = q.getFirst();

                    if (cc.itr == itr && cc.dis == sp[i])
                        break;

                    q.removeFirst();

                    int cx = cc.x;
                    int cy = cc.y;
                    int cd = cc.dis;

                    int nd = cd + 1;

                    if (cc.itr < itr) {
                        nd = 1;
                    }

                    //top
                    if (cx > 0 && grid[cx - 1][cy] == '.') {
                        grid[cx - 1][cy] = pc;
                        q.addLast(new cell(cx - 1, cy, itr, nd));
                        md++;
                    }

                    //left
                    if (cy > 0 && grid[cx][cy - 1] == '.') {
                        grid[cx][cy - 1] = pc;
                        q.addLast(new cell(cx, cy - 1, itr, nd));
                        md++;
                    }

                    if (cy < m - 1 && grid[cx][cy + 1] == '.') {
                        grid[cx][cy + 1] = pc;
                        q.addLast(new cell(cx, cy + 1, itr, nd));
                        md++;
                    }

                    if (cx < n - 1 && grid[cx + 1][cy] == '.') {
                        grid[cx + 1][cy] = pc;
                        q.addLast(new cell(cx + 1, cy, itr, nd));
                        md++;
                    }
                }
            }

            if (md == 0)
                break;
        }

        int[] ans = new int[p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = grid[i][j];

                if (Character.isDigit(ch)) {
                    int num = ch - '1';
                    ans[num]++;
                }
            }
        }

        for (int i = 0; i < p; i++) {
            out.print(ans[i] + " ");
        }
    }
}
