package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DMagicGems {
    class MatrixExpo {
        int n;

        public MatrixExpo(int n) {
            this.n = n;
        }

        long[][] matrixPower(long[][] base, long pow, long mod) {
            long[][] ans = new long[n][n];

            for (int i = 0; i < n; i++)
                ans[i][i] = 1;

            while (pow != 0) {
                if ((pow & 1) != 0) ans = multiplyMatrix(ans, base, mod);

                base = multiplyMatrix(base, base, mod);

                pow >>= 1;
            }

            return ans;
        }

        long[][] multiplyMatrix(long[][] m, long[][] m2, long mod) {
            long[][] ans = new long[n][n];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    ans[i][j] = 0;
                    for (int k = 0; k < n; k++) {
                        ans[i][j] += (m[i][k] * m2[k][j]) % mod;
                        ans[i][j] %= mod;
                    }
                }

            return ans;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        int m = in.nextInt();
        long[] dp = new long[m];

        dp[0] = 1;

        for (int i = 1; i < m; i++) {
            dp[i] += dp[i - 1];
            dp[i] %= mod;

            if (i >= m) {
                dp[i] += dp[i - m];
                dp[i] %= mod;
            }

            if (i == m - 1) {
                dp[i]++;
                dp[i] %= mod;
            }
        }

        if (n <= m) {
            out.println(dp[(int) n - 1]);
            return;
        }

        MatrixExpo me = new MatrixExpo(m);

        long[][] base = new long[m][m];

        base[0][0] = 1;
        base[0][m - 1] = 1;

        for (int i = 1; i < m; i++) {
            base[i][i - 1] = 1;
        }

        base = me.matrixPower(base, n - m, mod);

        long ans = 0;

        for (int i = 0; i < m; i++) {
            ans += (base[0][i] * dp[m - 1 - i]) % mod;
            ans %= mod;
        }

        out.println(ans);
    }

    int mod = 1000000007;
}
