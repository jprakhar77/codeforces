package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class agc025b {
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

    int mod = 998244353;

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        long a = in.nextInt();

        long b = in.nextInt();

        long k = in.nextLong();

        long[] ncr = nCr(n, mod);

        long ans = 0;

        for (int i = 0; i <= n; i++) {
            if (i * a > k)
                break;

            long aval = i * a;
            long remval = k - aval;

            if (remval % b != 0)
                continue;

            long j = remval / b;

            if (j > n) {
                continue;
            }

            long ca = 1;

            ca *= ncr[i];
            ca *= ncr[(int) j];

            ca %= mod;

            ans += ca;

            ans %= mod;
        }

        out.println(ans);
    }
}
