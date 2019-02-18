package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CTrailingLovesOrLoeufs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        long b = in.nextLong();


        //Set<Long> factors = factors(b);

        HashMap<Long, Integer> pf = primeFactors(b);

        Set<Long> factors = pf.keySet();

        long ans = Long.MAX_VALUE;
        for (long f : factors) {
            long ca = 0;

            long cn = n;
            while (cn > 0) {
                ca += cn / f;
                cn /= f;
            }

            ca /= pf.get(f);

            ans = Math.min(ca, ans);
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

    HashMap<Long, Integer> primeFactors(long n) {
        HashMap<Long, Integer> cm = new HashMap<>();

        long cn = n;
        for (long i = 2; i * i <= cn; i++) {
            if (cn % i == 0) {
                while (cn % i == 0) {
                    cn /= i;
                    cm.merge(i, 1, (x, y) -> x + y);
                }
            }
        }

        if (cn > 1) {
            cm.merge(cn, 1, (x, y) -> x + y);
        }

        return cm;
    }
}
