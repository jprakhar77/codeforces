package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class DDoubleLandscape {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] ri = new int[n * m + 1];
        int[] ci = new int[n * m + 1];

        Arrays.fill(ri, -1);
        Arrays.fill(ci, -1);


        int[] r = in.nextIntArray(n);
        int[] c = in.nextIntArray(m);

        long c1 = Arrays.stream(r).distinct().count();
        long c2 = Arrays.stream(c).distinct().count();

        if (c1 < n || c2 < m) {
            out.println(0);
            return;
        }

        int[] maxr = new int[n];
        int[] maxc = new int[m];

        for (int i = 0; i < n; i++) {
            ri[r[i]] = i;
        }

        for (int i = 0; i < m; i++) {
            ci[c[i]] = i;
        }

        if (ri[n * m] == -1 || ci[n * m] == -1) {
            out.println(0);
            return;
        }

        maxr[ri[n * m]] = n * m;
        maxc[ci[n * m]] = n * m;


        long[] b = new long[n * m + 2];
        b[n * m] = 1;

        int rr = 1;
        int cr = 1;

        for (int i = n * m - 1; i > 0; i--) {
            if (ri[i] == -1 && ci[i] == -1) {

            } else if (ri[i] != -1 && ci[i] != -1) {
                b[i] = 1;
                if (maxr[ri[i]] > i || maxc[ci[i]] > i) {
                    out.println(0);
                    return;
                }

                maxr[ri[i]] = i;
                maxc[ci[i]] = i;
                rr++;
                cr++;
            } else if (ri[i] != -1) {
                if (maxr[ri[i]] > 0) {
                    out.println(0);
                    return;
                }

                maxr[ri[i]] = i;
                rr++;
            } else if (ci[i] != -1) {
                if (maxc[ci[i]] > i) {
                    out.println(0);
                    return;
                }

                maxc[ci[i]] = i;
                cr++;
            }

            if (n * m - i + 1 > rr * cr) {
                out.println(0);
                return;
            }
        }

        long[] bs = in.calculateSuffixSum(b);


        rr = n;
        cr = m;

        int cb = 0;

        long[] rra = new long[n * m + 1];
        long[] cra = new long[n * m + 1];

        for (int i = 1; i <= n * m; i++) {
            if (ri[i] == -1 && ci[i] == -1) {
                if (rr <= 0 || cr <= 0) {
                    out.println(0);
                    return;
                }
                long val = rr;
                val *= cr;
                val %= mod;

                val -= bs[i + 1];
                val -= cb;
                val %= mod;
                //ans *= (val);
                //ans %= mod;
                //rr--;
                cb++;
                //cr--;

            } else if (ri[i] != -1 && ci[i] != -1) {
                rr--;
                cr--;
            } else if (ri[i] != -1) {
                if (cr <= 0) {
                    out.println(0);
                    return;
                }
                //ans *= cr;
                //ans %= mod;
                rr--;
            } else if (ci[i] != -1) {
                if (rr <= 0) {
                    out.println(0);
                    return;
                }
                //ans *= rr;
                //ans %= mod;
                cr--;
            }

            if (i < (n - rr) * (m - cr)) {
                out.println(0);
                return;
            }

            rra[i] = rr;
            cra[i] = cr;
        }

        long ans = 1;

        rr = 1;
        cr = 1;

        for (int i = n * m - 1; i >= 1; i--) {
            if (ri[i] == -1 && ci[i] == -1) {
                int done = n * m - i;
                int sp = rr * cr;

                ans *= (sp - done);
                ans %= mod;
            } else if (ri[i] != -1 && ci[i] != -1) {
                rr++;
                cr++;
            } else if (ri[i] != -1) {
                ans *= (cra[i - 1]);
                ans %= mod;
                rr++;
            } else if (ci[i] != -1) {
                ans *= (rra[i - 1]);
                ans %= mod;
                cr++;
            }

//            if (rr + rra[i - 1] != 0 || cr + cra[i - 1] != 0) {
//                out.println(0);
//                return;
//            }
        }

        if (ans < 0) {
            ans += mod;
        }

        out.println(ans);
    }

    int mod = 1_000_000_007;

}
