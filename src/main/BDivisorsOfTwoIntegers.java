package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class BDivisorsOfTwoIntegers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        TreeMap<Integer, Integer> tm = new TreeMap<>();

        for (int val : a) {
            tm.merge(val, 1, (x, y) -> x + y);
        }

        int max = tm.lastKey();

        Set<Long> fac = factors(max);

        for (long val : fac) {
            tm.merge((int) val, -1, (x, y) -> x + y);
            int vali = (int) val;
            if (tm.get(vali) == 0) {
                tm.remove(vali);
            }
        }

        out.println(max + " " + tm.lastKey());
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
