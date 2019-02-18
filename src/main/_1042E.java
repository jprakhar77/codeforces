package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class _1042E {
    class cell {
        int r;
        int c;
        int val;

        public cell(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }

        int r = in.nextInt();
        int c = in.nextInt();

        cell[] cells = new cell[n * m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cell cell = new cell(i + 1, j + 1, a[i][j]);
                cells[i * m + j] = cell;
            }
        }

        Arrays.sort(cells, (c1, c2) -> c1.val - c2.val);

        long[] dp = new long[n * m];

        dp[0] = 0;

        if (cells[0].r == r && cells[0].c == c) {
            out.println(0);
            return;
        }

        int sz = n * m;

        long[] x1 = new long[n * m];
        long[] y1 = new long[n * m];
        long[] x12 = new long[n * m];
        long[] y12 = new long[n * m];
        long[] cdp = new long[n * m];

        x1[0] = cells[0].r;
        x12[0] = (long) cells[0].r * cells[0].r;
        y1[0] = cells[0].c;
        y12[0] = (long) cells[0].c * cells[0].c;

        int li = -1;
        long ans = 0;
        for (int i = 1; i < sz; i++) {
            long x2 = cells[i].r;
            long y2 = cells[i].c;

            if (cells[i].val != cells[i - 1].val) {
                li = i - 1;
            }

            if (li > -1) {
                long cv = x12[li] + y12[li];
                cv -= 2 * x2 * x1[li];
                cv -= 2 * y2 * y1[li];
                cv += ((x2 * x2 + y2 * y2) % mod) * (li + 1);
                cv %= mod;

                cv += cdp[li];

                cv %= mod;

                cv *= pow(li + 1, mod - 2, mod);

                cv %= mod;

                dp[i] = cv;

                if (x2 == r && y2 == c) {
                    ans = (dp[i] + mod) % mod;
                    break;
                }
            }

            cdp[i] = dp[i] + cdp[i - 1];
            cdp[i] %= mod;
            x1[i] = x1[i - 1] + x2;
            x1[i] %= mod;
            y1[i] = y1[i - 1] + y2;
            y1[i] %= mod;
            x12[i] = x12[i - 1] + (x2 * x2);
            x12[i] %= mod;
            y12[i] = y12[i - 1] + (y2 * y2);
            y12[i] %= mod;
        }

        out.println(ans);
    }

    int mod = 998244353;

    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }
}
