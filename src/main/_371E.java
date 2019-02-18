package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class _371E {
    class sub {
        long x;
        int i;

        public sub(long x, int i) {
            this.x = x;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        sub[] x = new sub[n];

        for (int i = 0; i < n; i++) {
            x[i] = new sub(in.nextInt(), i);
        }

        int k = in.nextInt();

        Arrays.sort(x, (x1, x2) -> (int) Math.signum(x1.x - x2.x));

        long[] d = new long[n - 1];

        for (int i = 1; i < n; i++) {
            d[i - 1] = x[i].x - x[i - 1].x;
        }

        long[] dp = new long[n - 1];

        dp[0] = d[0];
        for (int i = 1; i < n - 1; i++) {
            dp[i] = d[i] + dp[i - 1];
        }

        long[] dc = new long[n - 1];

        for (int i = 0; i < n - 1; i++) {
            dc[i] = (i + 1) * d[i];
        }

        long[] dcp = new long[n - 1];

        dcp[0] = dc[0];
        for (int i = 1; i < n - 1; i++) {
            dcp[i] = dcp[i - 1] + dc[i];
        }

        int anss = 0;

        k--;

        long min = 0;

        long cans = 0;
        long pans = 0;
        for (int i = k; i < n - 1; i++) {
            long diff = k * d[i];

            long t1 = dp[i - 1];

            if (i - k - 1 >= 0) {
                t1 -= dp[i - k - 1];
            }

            long t3 = t1;

            t1 *= (k + 1);

            diff -= t1;

            long t2 = dcp[i - 1];

            if (i - k - 1 >= 0) {
                t2 -= dcp[i - k - 1];
            }

            if (i - k > 0) {
                t2 -= (i - k) * t3;
            }

            t2 *= 2;

            t2 -= t3;

            diff += t2;

            if (pans + diff < min) {
                min = pans + diff;
                anss = i - k + 1;
            }

            pans += diff;
        }

        anss++;


        for (int i = anss - 1; i < anss + k; i++) {
            out.print((x[i].i + 1) + " ");
        }
    }
}
