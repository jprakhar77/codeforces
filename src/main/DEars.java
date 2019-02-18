package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DEars {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long[] l = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            l[i] = in.nextInt();
        }

        //add odd
        long[] es = new long[n + 1];
        long[] os = new long[n + 1];

        long[] ee = new long[n + 1];
        long[] oe = new long[n + 1];

        long[] prel = in.calculatePrefixSum(l);
        long[] sufl = in.calculateSuffixSum(l);

        for (int i = 1; i <= n; i++) {
            if (l[i] == 0) {
                es[i] = es[i - 1] + 2;
            } else if (l[i] % 2 == 1) {
                es[i] = es[i - 1] + 1;
            } else {
                es[i] = es[i - 1];
            }
            es[i] = Math.min(es[i], prel[i]);
        }


        for (int i = 1; i <= n; i++) {
            if (l[i] % 2 == 0) {
                os[i] = os[i - 1] + 1;
            } else {
                os[i] = os[i - 1];
            }
            os[i] = Math.min(os[i], prel[i]);
        }


        for (int i = n - 1; i >= 0; i--) {
            if (l[i + 1] == 0) {
                ee[i] = ee[i + 1] + 2;
            } else if (l[i + 1] % 2 == 1) {
                ee[i] = ee[i + 1] + 1;
            } else {
                ee[i] = ee[i + 1];
            }

            ee[i] = Math.min(sufl[i + 1], ee[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            if (l[i + 1] % 2 == 0) {
                oe[i] = oe[i + 1] + 1;
            } else {
                oe[i] = oe[i + 1];
            }

            oe[i] = Math.min(sufl[i + 1], oe[i]);
        }

        //w e
        long ans = Long.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, es[i] + ee[i]);
        }

        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, os[i] + oe[i]);
        }

        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, es[i] + oe[i]);
        }

        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, os[i] + ee[i]);
        }

        long[][] dp = new long[n + 1][2];

        for (int i = n - 1; i >= 0; i--) {
            int o = 0;
            int e = 0;

            if (l[i + 1] % 2 == 0)
                o++;
            else
                e++;

            dp[i][0] = ee[i];
            dp[i][1] = Math.min(dp[i + 1][1] + o, dp[i + 1][0] + o);
            dp[i][0] = Math.min(sufl[i + 1], dp[i][0]);
            dp[i][1] = Math.min(sufl[i + 1], dp[i][1]);
        }

//        for (int i = n - 1; i >= 0; i--) {
//            dp[i] = Math.min(dp[i + 1] + 1 - (l[i + 1] % 2), ee[i]);
//            dp[i] = Math.min(sufl[i + 1], dp[i]);
//        }

        for (int i = 1; i <= n; i++) {
            long ca = es[i];

//            int lo = i;
//            int hi = n - 1;
//
//            while (lo <= hi) {
//                int mid = (lo + hi) / 2;
//
//                long va1 = oe[]
//            }

            ans = Math.min(ans, es[i] + Math.min(dp[i][0], dp[i][1]));
        }

        out.println(ans);
    }
}
