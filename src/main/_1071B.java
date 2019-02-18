package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;

public class _1071B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();


        String[] mat = new String[n];

        for (int i = 0; i < n; i++) {
            mat[i] = in.next();
        }

        int[][] adp = new int[n][n];

        if (mat[0].charAt(0) == 'a')
            adp[0][0] = 1;

        for (int j = 1; j < n; j++) {
            if (mat[0].charAt(j) == 'a') {
                adp[0][j] = adp[0][j - 1] + 1;
            } else {
                adp[0][j] = adp[0][j - 1];
            }
        }

        for (int i = 1; i < n; i++) {
            if (mat[i].charAt(0) == 'a') {
                adp[i][0] = adp[i - 1][0] + 1;
            } else {
                adp[i][0] = adp[i - 1][0];
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (mat[i].charAt(j) == 'a') {
                    adp[i][j] = Math.max(adp[i - 1][j], adp[i][j - 1]) + 1;
                } else {
                    adp[i][j] = Math.max(adp[i - 1][j], adp[i][j - 1]);
                }
            }
        }

        if (k + adp[n - 1][n - 1] >= 2 * n - 1) {
            for (int i = 0; i < 2 * n - 1; i++) {
                out.print('a');
            }
            return;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i].charAt(j) != 'a') {
                    int dis = i + j;
                    int as = adp[i][j];

                    if (dis - as == k) {
                        max = Math.max(max, as);
                    }
                }
            }
        }

        char minch = (int) 'z' + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i].charAt(j) != 'a') {
                    int dis = i + j;
                    int as = adp[i][j];

                    if (dis - as == k && as == max) {
                        minch = (char) Math.min(minch, mat[i].charAt(j));
                    }
                }
            }
        }

        ArrayList<cell> q = new ArrayList<>();

        for (int i = 0; i < k + max; i++) {
            out.print('a');
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i].charAt(j) == minch) {
                    int dis = i + j;
                    int as = adp[i][j];

                    if (dis - as == k && as == max) {
                        q.add(new cell(i, j));
                    }
                }
            }
        }

        boolean[][] vis = new boolean[n][n];

        ArrayList<cell> q2 = new ArrayList<>();

        char mch = minch;

        while (q.size() > 0) {
            out.print(mch);
            for (int i = 0; i < q.size(); i++) {
                cell dqe = q.get(i);

                int ci = dqe.x;
                int cj = dqe.y;

                if (ci < n - 1) {
                    if (!vis[ci + 1][cj]) {
                        q2.add(new cell(ci + 1, cj));
                        vis[ci + 1][cj] = true;
                    }

                }

                if (cj < n - 1) {
                    if (!vis[ci][cj + 1]) {
                        q2.add(new cell(ci, cj + 1));
                        vis[ci][cj + 1] = true;
                    }
                }
            }

            mch = 'z' + 1;

            ArrayList<cell> q3 = new ArrayList<>();
            for (cell cell : q2) {
                mch = (char) Math.min(mch, mat[cell.x].charAt(cell.y));
            }

            for (cell cell : q2) {
                if (mat[cell.x].charAt(cell.y) == mch) {
                    q3.add(cell);
                }
            }

            q = q3;
            q2.clear();
        }
    }

    class cell {
        int x;
        int y;

        public cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
