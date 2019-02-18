package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CNewYearAndTheSphereTransmission {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextInt();

        Set<Long> fac = factors(n);

        TreeSet<Long> ans = new TreeSet<>();
        for (long f : fac) {
            long t = n / f;

            long cnt = cnt(t);

            cnt *= f;

            cnt -= (f - 1) * t;

            ans.add(cnt);
        }

        for (long val : ans) {
            out.print(val + " ");
        }

    }

    long cnt(long n) {
        return (n * (n + 1)) / 2;
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
