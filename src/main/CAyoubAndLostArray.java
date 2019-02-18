package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CAyoubAndLostArray {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int l = in.nextInt();
        int r = in.nextInt();

        long[][] dp = new long[n][3];

        int[] cal = cal(l, r);

        for (int j = 0; j < 3; j++) {
            dp[0][j] = cal[j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int rem = (j + k) % 3;

                    dp[i][rem] += (cal[k] * dp[i - 1][j]) % mod;
                    dp[i][rem] %= mod;
                }
            }
        }

        out.print(dp[n - 1][0]);

    }

    int mod = 1000000007;

    int[] cal(int l, int r) {
        int[] val = new int[3];

        while (l % 3 != 1 && l <= r) {
            val[l % 3]++;
            l++;
        }

        while (r % 3 != 0 && l <= r) {
            val[r % 3]++;
            r--;
        }

        if (l <= r) {
            int gap = r - l + 1;
            for (int i = 0; i < 3; i++) {
                val[i] += gap / 3;
            }
        }

        return val;
    }
}
