package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DNearestCardGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int q = in.nextInt();

        long[] a = in.nextLongArray(n);

        int[] x = in.nextIntArray(q);

        long[][] dp = new long[n][2];

        dp[0][0] = a[0];

        for (int i = 1; i < n; i++) {
            //0
            dp[i][0] = a[i] + dp[i - 1][1];

            //1
            dp[i][1] = dp[i - 1][0];
        }

        long[] suf = in.calculateSuffixSum(a);

        for (int i = 0; i < q; i++) {
            int cx = x[i];

            if (cx >= a[n - 1]) {
                out.println(dp[n - 1][0]);
                continue;
            }

            int lo = fig(cx, a, n);
            int hi = n - 1;

            int ans = n - 1;

            int ftcnt = 1;
            int facnt = 0;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                int tcnt = n - mid;

                int acnt;

                if (mid > 0) {
                    long diff = a[mid] - cx;
                    long cxm = cx - diff;

                    int fig = fig(cxm - 1, a, n);

                    acnt = mid - fig;
                } else {
                    acnt = 0;
                }

                if (tcnt > acnt + 1) {
                    lo = mid + 1;
                } else if (tcnt < acnt) {
                    if (mid < ans) {
                        ftcnt = tcnt;
                        facnt = tcnt;
                        ans = Math.min(ans, mid);
                    }
                    hi = mid - 1;
                } else {
                    if (mid < ans) {
                        ftcnt = tcnt;
                        facnt = acnt;
                        ans = Math.min(ans, mid);
                    }
                    hi = mid - 1;
                }
            }

            if (ftcnt > facnt) {
                int li = n - 1 - ftcnt - facnt;

                if (li < 0)
                    out.println(suf[n - ftcnt]);
                else {
                    out.println(suf[n - ftcnt] + dp[li][1]);
                }
            } else {
                int li = n - 1 - ftcnt - facnt;

                if (li < 0)
                    out.println(suf[n - ftcnt]);
                else {
                    out.println(suf[n - ftcnt] + dp[li][0]);
                }
            }

        }
    }

    int fig(long num, long[] a, int n) {
        int lo = 0;
        int hi = n - 1;

        int ans = n;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (a[mid] > num) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }
}
