package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class _1055E {
    class seg {
        int l;
        int r;

        public seg(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int s = in.nextInt();

        int m = in.nextInt();

        int k = in.nextInt();

        int[] a = in.nextIntArray(n);

        seg[] segs = new seg[s];
        for (int i = 0; i < s; i++) {
            int l = in.nextInt();
            int r = in.nextInt();

            l--;
            r--;

            segs[i] = new seg(l, r);
        }

        Arrays.sort(segs, (s1, s2) ->
        {
            if (s1.l == s2.l) {
                return s1.r - s2.r;
            } else {
                return s1.l - s2.l;
            }
        });

        int[] b = a.clone();

        Arrays.sort(b);

        int lo = 0;
        int hi = n - 1;

        int ans = b[n - 1] + 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            int num = b[mid];

            int[][] cum = new int[n][n];

            for (int i = 0; i < n; i++) {
                if (a[i] <= num) {
                    cum[i][i] = 1;
                }
                for (int j = i + 1; j < n; j++) {
                    if (a[j] <= num) {
                        cum[i][j] = cum[i][j - 1] + 1;
                    } else {
                        cum[i][j] = cum[i][j - 1];
                    }
                }
            }

            int[][] dp = new int[n + 1][m + 1];

            for (int i = n - 1; i >= 0; i--) {
                dp[i][m] = dp[i + 1][m];
                for (int j = s - 1; j >= 0; j--) {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                    if (segs[j].l <= i && segs[j].r >= i) {
                        dp[i][j] = Math.max(dp[i][j], cum[i][segs[j].r] + dp[segs[j].r + 1][j + 1]);
                    }
                }
            }

            if (dp[0][0] >= k) {
                ans = Math.min(ans, num);
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        if (ans > b[n - 1]) {
            ans = -1;
        }
        out.println(ans);

    }
}
