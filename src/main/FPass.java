package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class FPass {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        String s = in.next();

        int n = s.length();

        int[] r = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                r[i] += 2;
            } else if (s.charAt(i) == '1') {
                r[i]++;
                b[i]++;
            } else {
                b[i] += 2;
            }
        }

        int[] pr = in.calculatePrefixSum(r);
        int[] pb = in.calculatePrefixSum(b);

        int[][] dp = new int[2 * n][2 * n + 1];

        if (r[0] > 0) {
            dp[0][1] = 1;
        }

        if (b[0] > 0) {
            dp[0][0] = 1;
        }

        for (int i = 1; i < 2 * n; i++) {
            for (int j = 0; j <= Math.min(i, pr[Math.min(i - 1, n - 1)]); j++) {
                int maxi = Math.min(i, n - 1);
                if (pr[maxi] > j) {
                    dp[i][j + 1] += dp[i - 1][j];
                    dp[i][j + 1] %= mod;
                }
                int bu = i - j;
                int br = pb[maxi] - bu;

                if (br > 0) {
                    dp[i][j] += dp[i - 1][j];
                    dp[i][j] %= mod;
                }
            }
        }

        long ans = 0;

        for (int i = 0; i <= 2 * n; i++) {
            ans += dp[2 * n - 1][i];
            ans %= mod;
        }

        out.println(ans);
    }

    int mod = 998244353;
}
