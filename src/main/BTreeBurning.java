package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BTreeBurning {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        l = in.nextInt();

        n = in.nextInt();

        a = in.nextLongArray(n);

        dp = new long[n][n][2];
        sum = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = dp[i][j][1] = -1;
            }
        }

        long a1 = rec(0, n - 1, 0);
        long a2 = l - a[n - 1] + rec(0, n - 1, 1);

        out.println(Math.max(a1, a2));

    }

    long l;
    int n;
    long[] a;

    private long rec(int st, int en, int forb) {
        if (st > en) {
            return 0;
        }
        if (dp[st][en][forb] != -1) {
            return dp[st][en][forb];
        }

        if (st == en) {
            if (forb == 0) {
                dp[st][en][forb] = a[en];
                if (st > 0) {
                    dp[st][en][forb] -= a[st - 1];
                }
                return dp[st][en][forb];
            } else {
                return dp[st][en][forb] = 0;
            }
        }

        sum[st][en] = a[en];

        long low = 0;
        if (st > 0) {
            low = a[st - 1];
            sum[st][en] -= a[st - 1];
        }

        long rem = l - sum[st][en];

        if (forb == 0) {
            long ca = 0;
            long cv = 0;
            for (int i = st; i <= en; i++) {
                cv = a[i] - low;

                long val = 0;
                if (i < en) {
                    val = 2 * cv + rem + rec(i + 1, en, 1);
                } else {
                    val = cv;
                }

                ca = Math.max(ca, val);
            }

            dp[st][en][forb] = ca;
        } else {
            long ca = 0;
            long cv = 0;
            ca = rem + rec(st, en - 1, 0);
            for (int i = en; i > st; i--) {
                cv = a[en];

                if (i > 0) {
                    cv -= a[i - 1];
                }

                long val = 0;
                if (i > st + 1) {
                    val = 2 * cv + rem + rec(st, i - 2, 0);
                } else {
                    val = cv;
                }

                ca = Math.max(ca, val);
            }

            dp[st][en][forb] = ca;
        }

        return dp[st][en][forb];
    }

    long[][][] dp;
    long[][] sum;

}
