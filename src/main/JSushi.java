package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class JSushi {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int[] c = new int[4];
        for (int val : a) {
            c[val]++;
        }

        double[][][] dp = new double[n + 1][n + 1][n + 1];


        for (int k = 0; k <= n; k++) {
            for (int j = 0; j <= n; j++) {
                for (int i = 0; i <= n; i++) {
                    double tot = i + j + k;

                    if (tot == 0)
                        continue;

                    if (i + j + k > n)
                        continue;

                    double ans = n / tot;
                    //1

                    if (i > 0) {
                        ans += (i / tot) * dp[i - 1][j][k];
                    }

                    if (j > 0) {
                        ans += (j / tot) * dp[i + 1][j - 1][k];
                    }

                    if (k > 0) {
                        ans += (k / tot) * dp[i][j + 1][k - 1];
                    }

                    dp[i][j][k] = ans;
                }
            }
        }

        out.println(dp[c[1]][c[2]][c[3]]);

    }
}
