package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class _955C {
    class interv {
        long s;
        long e;

        public interv(long s, long e) {
            this.s = s;
            this.e = e;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int q = in.nextInt();

        StringBuilder sb = new StringBuilder();

        Set<Integer> powers = new HashSet<>();

        int[] pb = new int[61];

        for (int i = 2; i <= 60; i++) {

            int ci = i;
            int[] f = new int[61];
            for (int j = 2; j * j <= ci; j++) {
                while (ci % j == 0) {
                    ci /= j;
                    f[j]++;
                }
            }

            if (ci > 1)
                f[ci]++;

            int c = 0;
            boolean sf = false;

            for (int j = 2; j <= 60; j++) {
                if (f[j] > 1)
                    sf = true;
                if (f[j] > 0)
                    c++;
            }

            if (!sf) {
                powers.add(i);
                if (c % 2 == 0) {
                    pb[i] = -1;
                } else {
                    pb[i] = 1;
                }
            }
        }

        long[] pth = new long[61];

        for (int i = 1; i <= 60; i++) {
            pth[i] = (long) Math.pow(1e18, 1.0 / i);
        }

        while (q-- > 0) {
            long l = in.nextLong();
            long r = in.nextLong();

            long ans = 0;

            if (l == 1) {
                ans = 1;
                l++;
            }

            for (Integer p : powers) {
                long lo = 2;
                long chi = pth[p];

                long hi = chi;

                long min = (long) Math.pow(l, 1.0 / p);

                while (pow(min, p) >= l)
                    min--;

                while (pow(min, p) < l)
                    min++;

                if (pow(min, p) > r)
                    continue;

                if (min == 1)
                    continue;

                long max = (long) Math.pow(r, 1.0 / p);

                while (pow(max, p) <= r)
                    max++;

                while (pow(max, p) > r)
                    max--;

                if (max < min)
                    continue;
                //if (max == -1)
                //  continue;

                //il.add(new interv(min, max));

                ans += pb[p] * (max - min + 1);
            }

//            il.sort((i1, i2) -> (int) Math.signum(i1.s - i2.s));
//
//            long ln = 1;
//
//            for (int i = 0; i < il.size(); i++) {
//                interv ci = il.get(i);
//
//                if (ln >= ci.e)
//                    continue;
//
//                if (ln < ci.s) {
//                    ans += (ci.e - ci.s + 1);
//                } else {
//                    ans += (ci.e - ln);
//                }
//
//                ln = ci.e;
//            }

            sb.append(ans);
            sb.append(System.lineSeparator());
        }

        out.println(sb.toString());
    }

    long inf = (long) 1e18 + 1;

    long pow(long a, long p) {
        if (p == 0)
            return 1;

        long t = pow(a, p / 2);

        assert t >= 0;

        if (t == inf)
            return inf;

        if (inf / t < t) {
            return inf;
        }

        if (p % 2 == 1) {
            long t2 = t * t;
            if (inf / t2 < a)
                return inf;
            return Math.min(t2 * a, inf);
        } else {
            return Math.min(t * t, inf);
        }
    }

}
