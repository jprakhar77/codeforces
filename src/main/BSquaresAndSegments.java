package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class BSquaresAndSegments {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

//        Set<Long> fac = factors(n);
//
//        long ans = n + 1;
//        for (long f : fac) {
//            long t = n / f;
//
//            ans = Math.min(ans, t + f);
//        }
//
//        long sq = (long) Math.sqrt(n);
//
//        while (sq * sq < n)
//            sq++;
//
//        out.println(Math.min(2 * sq, ans));

        long ans = n + 1;
        for (int i = 1; i * i <= n; i++) {
            long ceil = (n + i - 1) / i;

            ans = Math.min(ans, i + ceil);
        }

        out.println(ans);
    }

    Set<Long> factors(long n) {
        Set<Long> factors = new HashSet<>();

        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }

        return factors;
    }
}
