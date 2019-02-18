package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DJongmah {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int m = in.nextInt();

        int[] a = in.nextIntArray(n);

        int[] c = new int[m + 1];

        for (int val : a) {
            c[val]++;
        }

        if (m == 1) {
            out.println(c[1] / 3);
            return;
        }

        long[][][] dp = new long[m + 1][3][5];


        for (int k = 0; k <= m; k++) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 4; j++) {
                    dp[k][i][j] = -1;
                }
            }
        }

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 4; j++) {
                if (c[1] < i || c[2] < j)
                    continue;
                int rem0 = (c[1] - i) / 3;
                int rem1 = (c[2] - j) / 3;
                dp[2][i][j] = Math.max(dp[2][i][j], rem0 + rem1);
            }
        }

        for (int k = 3; k <= m; k++) {
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 4; j++) {

                    if (dp[k - 1][i][j] == -1)
                        continue;

                    for (int l = 0; l <= 2; l++) {
                        for (int o = 0; o <= 4; o++) {

                            if (Math.min(i, j) < l)
                                continue;

                            if (c[k] < l + o)
                                continue;

                            int rem = c[k] - l - o;

                            int jrem = Math.min(j - l, 2);

                            dp[k][jrem][o] = Math.max(dp[k][jrem][o], dp[k - 1][i][j] + rem / 3 + l);
                        }
                    }
                }
            }
        }

        long ans = 0;

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 4; j++) {
                ans = Math.max(ans, dp[m][i][j]);
            }
        }

        out.println(ans);
    }
}
