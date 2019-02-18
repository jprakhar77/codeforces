package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.TreeSet;

public class _1029F {
    TreeSet<Long> factors(long n) {
        TreeSet<Long> factors = new TreeSet<>();

        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }

        return factors;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.nextLong();
        long b = in.nextLong();

        long s = a + b;

        long sq = (long) Math.sqrt(s);

        TreeSet<Long> apm = factors(a);
        TreeSet<Long> bpm = factors(b);

        long ans = 2 * s + 2;

        for (long i = sq + 1; i >= 1; i--) {
            long s1 = i;
            long s2 = -1;
            if (s % s1 == 0) {
                s2 = s / s1;
            } else {
                continue;
            }

            if (s1 > s2) {
                long t = s1;
                s1 = s2;
                s2 = t;
            }

            Long lpa = apm.floor(s2);

            if (lpa != null && a / lpa <= s1) {
                ans = Math.min(ans, 2 * s1 + 2 * s2);
            }

            Long lpb = bpm.floor(s2);

            if (lpb != null && b / lpb <= s1) {
                ans = Math.min(ans, 2 * s1 + 2 * s2);
            }
        }

        out.println(ans);
    }
}
