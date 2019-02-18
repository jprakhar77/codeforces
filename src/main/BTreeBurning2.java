package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BTreeBurning2 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int l = in.nextInt();

        int n = in.nextInt();

        long[] a = new long[n + 1];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        a[n] = l;

        for (int i = n; i > 0; i--) {
            a[i] -= a[i - 1];
        }

        solve(a, n, in);
        a = in.reverse(a);
        solve(a, n, in);


        out.println(ans);
    }

    long ans = 0;

    void solve(long[] a, int n, InputReader in) {
        long[] pre = in.calculatePrefixSum(a);
        long[] suf = in.calculateSuffixSum(a);

        int remp = n + 1 - 1;

        long remsum = 0;

        if (remp % 2 == 1) {
            int hp = remp / 2;
            int si = 2 * hp;
            for (int i = n; i > n - hp; i--) {
                int coeff = si - (n - i) * 2;
                remsum += coeff * a[i];
            }

            si = 1;
            for (int i = n - hp - 1; i > 0; i--) {
                int coeff = si + (n - hp - 1 - i) * 2;
                remsum += coeff * a[i];
            }
        } else {
            int hp = remp / 2;
            int si = 2 * hp - 1;
            for (int i = n; i > n - hp; i--) {
                int coeff = si - (n - i) * 2;
                remsum += coeff * a[i];
            }

            si = 2;
            for (int i = n - hp - 1; i > 0; i--) {
                int coeff = si + (n - hp - 1 - i) * 2;
                remsum += coeff * a[i];
            }
        }

        remsum += remp * a[0];
        ans = Math.max(ans, remsum);

        for (int i = 1; i < n; i++) {
            remp = n - i;

            remsum += (remp + 1) * a[i];
            remsum -= pre[i];

            if (remp % 2 == 1) {
                int hp = remp / 2;
                int i1 = n - hp;

                remsum -= suf[i1];

                long sufp = suf[i + 1];

                sufp -= suf[i1];

                remsum += sufp;

                int li = i;

                int coef = 2 * hp;

                remsum -= coef * a[i];

                ans = Math.max(ans, remsum);
            } else {
                int hp = remp / 2;
                int i1 = n - hp;

                remsum -= suf[i1 + 1];

                long sufp = suf[i + 1];

                sufp -= suf[i1];

                remsum += sufp;

                int li = i;

                int coef = 2 * hp - 1;

                remsum -= coef * a[i];

                ans = Math.max(ans, remsum);
            }
        }
    }
}
