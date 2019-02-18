package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DSashaAndInterestingFactFromGraphTheory {

    long[] ncr;

    int mod = 1000000007;

    long[] fac;
    long[] ifac;

    long[] t;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();

        fac = factorial(2000000, mod);
        ifac = factorialInverse(2000000, mod);

        t = new long[n + 1];

        t[n] = 1;
        for (int i = 2; i < n; i++) {
            t[i] = i * pow(n, n - i - 1, mod);
            t[i] %= mod;
        }


        long ans = 0;

        for (int i = 0; i <= Math.min(n - 2, m - 1); i++) {

            long val = fac[n - 2];
            val *= ifac[n - 2 - i];

            val %= mod;

            val *= t[i + 2];
            val %= mod;

            int re = n - 1;
            re -= (i + 1);

            val *= pow(m, re, mod);
            val %= mod;

            int rw = m;

            rw -= (i + 1);

            val *= fac[rw + i];
            val %= mod;
            val *= ifac[i];
            val %= mod;
            val *= ifac[rw];
            val %= mod;

            ans += val;
            ans %= mod;
        }

        out.println(ans);
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

    long[] factorial(int n, int mod) {
        long[] factorial = new long[n + 1];

        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorial[i] = i * factorial[i - 1];
            factorial[i] %= mod;
        }

        return factorial;
    }

    long[] factorialInverse(int n, int mod) {
        long[] factorialInverse = new long[n + 1];

        long[] inv = new long[n + 1];

        inv[1] = 1;

        for (int i = 2; i <= n; i++) {
            inv[i] = inv[mod % i];
            inv[i] *= (mod - mod / i);
            inv[i] %= mod;
        }

        factorialInverse[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorialInverse[i] = inv[i] * factorialInverse[i - 1];
            factorialInverse[i] %= mod;
        }

        return factorialInverse;
    }
}
