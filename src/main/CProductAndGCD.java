package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;

public class CProductAndGCD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        long p = in.nextLong();

        HashMap<Long, Integer> pf = primeFactors(p);

        long ans = 1;
        for (long key : pf.keySet())
        {
            long val = pf.get(key);

            if (val >= n)
            {
                long t = val / n;
                long ca = 1;

                for (int i = 0; i < t; i++)
                {
                    ca *= key;
                }
                ans *= ca;
            }
        }

        out.println(ans);
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
