package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _743E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        int[] cnt = new int[9];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            cnt[a[i]]++;
        }

        int minc = n;
        for (int i = 1; i <= 8; i++) {
            if (cnt[i] == 0) {
                int ans = 0;
                for (int j = 1; j <= 8; j++) {
                    if (cnt[j] > 0)
                        ans++;
                }
                out.println(ans);
                return;
            }
            minc = Math.min(minc, cnt[i]);
        }

        int[][] next = new int[n][n];
        int[][] nextO = new int[n][9];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                next[i][j] = n;
            }

            for (int j = 0; j <= 8; j++) {
                nextO[i][j] = n;
            }
        }

        for (int i = 0; i < n; i++) {
            next[i][0] = i;
            nextO[i][a[i]] = i;
            int c = 1;
            for (int j = i + 1; j < n; j++) {
                if (a[j] == a[i]) {
                    next[i][c] = j;
                    c++;
                } else {
                    if (nextO[i][a[j]] == n) {
                        nextO[i][a[j]] = j;
                    }
                }
            }
        }

        int lo = 1;
        int hi = minc;

        long ans = 8;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            int[][] dp = new int[n + 1][1 << 8];

            dp[n][0] = 0;

            for (int j = 1; j < (1 << 8); j++) {
                dp[n][j] = minf;
            }

            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < (1 << 8); j++) {
                    dp[i][j] = dp[i + 1][j];
                    for (int k = 0; k < 8; k++) {
                        if ((j & (1 << k)) != 0) {
                            int si = nextO[i][k + 1];
                            if (si == n)
                                continue;
                            int li = next[si][mid - 1];

                            if (li != n) {
                                dp[i][j] = Math.max(dp[i][j], mid + dp[li + 1][j & (~(1 << k))]);
                            }

                            li = next[si][mid];

                            if (li != n) {
                                dp[i][j] = Math.max(dp[i][j], mid + 1 + dp[li + 1][j & (~(1 << k))]);
                            }
                        }
                    }
                }
            }

            if (dp[0][(1 << 8) - 1] >= 0) {
                ans = Math.max(ans, dp[0][(1 << 8) - 1]);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        out.println(ans);

    }

    int minf = -1000000000;
}
