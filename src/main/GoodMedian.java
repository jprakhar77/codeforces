package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class GoodMedian {
    boolean start = true;

    long[][] nCr(int n, int r, int mod) {
        long[][] ncr = new long[n + 1][r + 1];

        ncr[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            ncr[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r; j++) {
                ncr[i][j] = ncr[i - 1][j - 1] + ncr[i - 1][j];
                ncr[i][j] %= mod;
            }
        }

        return ncr;
    }

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

    long[][] ncr;

    int mod = 1000000007;

    long[][] dp;

    long[] inv;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (start) {
            ncr = nCr(1000, 1000, mod);

//            inv = new long[1001];
//
//            for (int i = 1; i <= 1000; i++) {
//                inv[i] = pow(i, mod - 2, mod);
//            }

            start = false;
        }

        int n = in.nextInt();

        dp = new long[2 * n + 1][2 * n + 1];

        for (int i = 0; i <= 2 * n; i++) {
            for (int j = 0; j <= 2 * n; j++) {
                dp[i][j] = -1;
            }
        }

        int[] a = new int[n];

        in.readArray(a, n, 0);

        int[] cnt = new int[2 * n + 1];

        int mxcnt = 0;
        for (int i = 0; i < n; i++) {
            cnt[a[i]]++;
            mxcnt = Math.max(mxcnt, cnt[a[i]]);
        }

        long ans = 0;

        long odd = 0;
        long even = 0;

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                even += ncr[n][i];
                even %= mod;
            } else {
                odd += ncr[n][i];
                odd %= mod;
            }
        }

        if (mxcnt == 1) {
            out.println(odd);
            return;
        }

        ans += odd;
        ans %= mod;
        ans += even;
        ans %= mod;

        int[] precnt = in.calculatePrefixSum(cnt);

        long remans = 0;

        long[][] dpf = new long[2 * n + 1][n + 1];
        long[][] dpb = new long[2 * n + 1][n + 1];

        for (int i = 1; i <= 2 * n; i++) {
            if (cnt[i] == 0)
                continue;
            int cc = precnt[i];

            for (int j = 1; j <= cc; j++) {
                for (int k = 1; k <= cnt[i]; k++) {
                    if (k > j)
                        break;

                    if (j - k > cc - cnt[i]) {
                        continue;
                    }

                    dpf[i][j] += ncr[cnt[i]][k] * ncr[cc - cnt[i]][j - k];
                    dpf[i][j] %= mod;
                }
            }
        }

        for (int i = 2 * n; i >= 1; i--) {
            if (cnt[i] == 0)
                continue;
            int cc = n - precnt[i - 1];

            for (int j = 1; j <= cc; j++) {
                for (int k = 1; k <= cnt[i]; k++) {
                    if (k > j)
                        break;

                    if (j - k > cc - cnt[i]) {
                        continue;
                    }

                    dpb[i][j] += ncr[cnt[i]][k] * ncr[cc - cnt[i]][j - k];
                    dpb[i][j] %= mod;
                }
            }
        }

        for (int i = 1; i <= 2 * n; i++) {
            if (cnt[i] == 0)
                continue;
            for (int j = i + 1; j <= 2 * n; j++) {
                if (cnt[j] == 0)
                    continue;

                long ca = 1;

                int left = i;

                int right = j;

                if (dp[left][right] == -1) {
                    int min = Math.min(precnt[left], n - precnt[right - 1]);

                    dp[left][right] = 0;
                    for (int k = 1; k <= min; k++) {
                        dp[left][right] += dpf[i][k] * dpb[j][k];
                        dp[left][right] %= mod;
                    }
                }

                ca *= dp[left][right];

                remans += ca;
                remans %= mod;
            }
        }

        ans -= remans;
        ans %= mod;

        if (ans < 0) {
            ans += mod;
        }

        out.println(ans);
    }
}
