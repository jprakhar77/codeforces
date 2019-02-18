package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _616E {
    long mod = (long) 1e9 + 7;

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        long n = in.nextLong();
        long m = in.nextLong();

        long sum = 0;

        long _2inv = pow(2, mod - 2, (int) mod);
        long gimin = m + 1;
        for (long v = 1; v * v <= n; v++) {
            long imin = (n / (v + 1)) + 1;
            long imax = (n / v) - 1;

            if (imin > m) {
                continue;
            }

            imax = Math.min(imax, m);

            if (imin > imax)
                continue;

            gimin = imin;
            long cn = imax - imin + 1;

            long apsum = (cn % mod) * ((2 * (imin % mod) + (cn - 1)) % mod);
            apsum %= mod;
            apsum *= _2inv;
            apsum %= mod;
            sum += ((v * apsum) % mod);
            sum %= mod;
        }

        for (long i = 1; i * i <= n && i <= m && i < gimin; i++) {
            sum += (((n / i) * i) % mod);
            sum %= mod;
        }

        long ans = (n % mod);
        ans *= (m % mod);

        ans -= sum;

        ans %= mod;

        if (ans < 0) {
            ans += mod;
        }

        out.println(ans);
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
}
