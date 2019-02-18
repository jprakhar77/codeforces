package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class OMatching {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }

        long[][] dp = new long[n][(1 << n)];

        for (int i = 0; i < n; i++) {
            if (a[0][i] == 1) {
                dp[0][1 << i]++;
            }
        }

        int[] pow = new int[n + 1];

        for (int i = 0; i <= n; i++)
            pow[i] = 1 << i;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int fp = 0;
                int sp = 0;
                for (int k = 0; k < j; k++) {
                    fp |= pow[k];
                }
                for (int k = j; k < n; k++) {
                    sp |= pow[k];
                }
                if (a[i][j] == 1) {
                    for (int set = 0; set < pow[n - 1]; set++) {
                        int aset = ((set & sp) << 1) + (set & fp);
                        int sset = aset | pow[j];
                        dp[i][sset] += dp[i - 1][aset];
                        if (dp[i][sset] >= mod)
                            dp[i][sset] -= mod;
                    }
                }
            }
        }

        out.println(dp[n - 1][(1 << n) - 1]);
    }

    int mod = 1000000007;
}
