package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class UGrouping {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[][] a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }

        long[] sum = new long[1 << n];

        for (int set = 0; set < (1 << n); set++) {
            long val = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((set & (1 << i)) != 0 && (set & (1 << j)) != 0) {
                        val += a[i][j];
                    }
                }
            }

            sum[set] = val / 2;
        }

        long[] dp = new long[1 << n];

        for (int set = 0; set < (1 << n); set++) {
            dp[set] = Math.max(0, sum[set]);

            for (int sub = set; sub > 0; sub = ((sub - 1) & set)) {
                int sub2 = set ^ sub;

                dp[set] = Math.max(dp[sub] + dp[sub2], dp[set]);
            }
        }

        out.println(dp[(1 << n) - 1]);
    }
}
