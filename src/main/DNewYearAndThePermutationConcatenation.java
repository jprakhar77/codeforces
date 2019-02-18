package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DNewYearAndThePermutationConcatenation {
    int mod = 998244353;

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

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long[] fac = new long[n + 1];

        fac[0] = 1;

        for (int i = 1; i <= n; i++) {
            fac[i] = fac[i - 1] * i;
            fac[i] %= mod;
        }

        long ans = fac[n] * n;
        ans %= mod;

        ans -= (n - 1);
        ans %= mod;

        long nl = n;

        ans -= ((nl - 1) * (nl - 1)) % mod;

        ans %= mod;

        long[] ncr = nCr(n, mod);

        for (int i = 2; i <= n; i++) {
            long val = ncr[i - 1] * fac[i - 1];

            val %= mod;

            long rem = n - i + 1;

            val *= (rem - 1);

            val %= mod;

            val *= (nl - i);
            val %= mod;

            ans -= val;
            ans %= mod;
        }

        if (ans < 0) {
            ans += mod;
        }

        out.println(ans);
    }
}
