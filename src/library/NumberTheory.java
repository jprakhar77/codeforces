package library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberTheory {
    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    void _2And2InvPowers(int n, long mod) {
        long[] pt = new long[n + 1];

        pt[0] = 1;

        for (int i = 1; i <= n; i++) {
            pt[i] = (pt[i - 1] * 2) % mod;
        }

        long[] pti = new long[n + 1];

        pti[0] = pow(pt[0], mod - 2, (int) mod);
        long ti = pow(2, mod - 2, (int) mod);
        for (int i = 1; i <= n; i++) {
            pti[i] = pti[i - 1] * ti;
            pti[i] %= mod;
        }
    }

    List<Integer> prime(int n) {
        boolean[] isp = new boolean[n + 1];

        Arrays.fill(isp, true);

        isp[1] = false;

        for (int i = 2; i <= n; i++) {
            if (isp[i]) {
                for (int j = 2; j * i <= n; j++) {
                    isp[i * j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (isp[i])
                primes.add(i);
        }

        return primes;
    }

    long[] nCr(int n, int mod) {
        long[] ncr = new long[n + 1];

        ncr[0] = 1;

        for (int i = 1; i <= n; i++) {
            ncr[i] = ncr[i - 1] * (n - (i - 1));
            ncr[i] %= mod;
            ncr[i] *= pow(i, mod - 2, mod);
            ncr[i] %= mod;
        }

        return ncr;
    }
}
