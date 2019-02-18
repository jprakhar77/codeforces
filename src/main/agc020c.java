package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class agc020c {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        int ts = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            ts += a[i];
        }

        if (n == 1) {
            out.println(a[0]);
            return;
        }

        int clsz = (ts + 64) / 64;

        long[][] dp = new long[2][clsz];

        dp[0][0] = (1l << 63);
        dp[0][a[0] / 64] |= (1l << (63 - (a[0] % 64)));

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < clsz; j++) {
                dp[1][j] = dp[0][j];
            }

            long[] clone = dp[0];

            int words = (a[i] + 63) / 64;

            long[] newclone = new long[clsz + 1];

            for (int j = words; j <= clsz; j++) {
                newclone[j] = clone[j - words];
            }

            if (words * 64 > a[i]) {
                long diff = words * 64 - a[i];

                long mask = ((1l << diff) - 1) << (64 - diff);
                for (int j = words - 1; j < clsz; j++) {
                    newclone[j] <<= diff;
                    newclone[j] |= ((newclone[j + 1] & mask) >>> (64 - diff));
                }
            }

            for (int j = 0; j < clsz; j++) {
                dp[1][j] |= newclone[j];
            }

            int sum = (ts + 1) / 2;

            if (i == n - 1) {
                for (int j = sum; j <= ts; j++) {
                    if ((dp[1][j / 64] & ((1l << (63 - (j % 64))))) != 0) {
                        out.println(j);
                        return;
                    }
                }
            }

            for (int j = 0; j < clsz; j++) {
                dp[0][j] = dp[1][j];
            }
        }
    }
}
