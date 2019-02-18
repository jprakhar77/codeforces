package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class BDuffInLove {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();

        Set<Long> s = primeFactors(n);

        long ans = 1;

        for (long val : s) {
            ans *= val;

        }

        out.println(ans);
    }

    Set<Long> primeFactors(long n) {

        Set<Long> set = new HashSet<>();

        long cn = n;
        for (long i = 2; i * i <= cn; i++) {
            if (cn % i == 0) {
                set.add(i);
                while (cn % i == 0) {
                    cn /= i;
                }
            }
        }

        if (cn > 1) {
            set.add(cn);
        }

        return set;
    }
}
