package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class TaskC {
    char d = 'D';
    char m = 'M';
    char c = 'C';

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String s = in.next();

        long[] dpre = new long[n];

        long[] csuf = new long[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == d) {
                dpre[i] = 1;
            }
            if (s.charAt(i) == c) {
                csuf[i] = 1;
            }
        }

        dpre = in.calculatePrefixSum(dpre);

        for (int i = n - 2; i >= 0; i--) {
            csuf[i] = csuf[i] + csuf[i + 1];
        }

        long tot = 0;
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == m) {
                tot += dpre[i - 1] * csuf[i + 1];
            }
        }

        long[] mfa = new long[n];

        int mf = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == m) {
                mf++;
            }

            if (s.charAt(i) == c)
                mfa[i] = mf;
        }

        for (int i = n - 2; i >= 0; i--) {
            mfa[i] = mfa[i] + mfa[i + 1];
        }

        int q = in.nextInt();

        o:
        while (q-- > 0) {
            int k = in.nextInt();

            long minus = 0;

            mf = 0;
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == m) {
                    mf++;
                } else if (s.charAt(i) == d) {
                    int j = i + k;

                    if (j >= n)
                        continue;

                    minus += mfa[j] - mf * csuf[j];
                }
            }

            out.println(tot - minus);
        }

    }

    int mod = (int) (1e9 + 7);
}
