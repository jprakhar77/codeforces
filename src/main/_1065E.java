package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1065E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        int a = in.nextInt();

        int[] b = new int[m];

        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }

        long ans = 1;

        int rem = n - 2 * b[m - 1];

        ans = pow(a, rem, mod);

        long _2inv = pow(2, mod - 2, mod);

        for (int i = 0; i < m; i++) {
            int size = b[i];

            if (i > 0) {
                size -= b[i - 1];
            }

            size *= 2;

            long tot = pow(a, size, mod);

            long pal = pow(a, size / 2, mod);

            long totr = tot - pal;

            totr *= _2inv;

            totr %= mod;

            long ca1 = ans * pal;
            long ca2 = ans * totr;

            ans = ca1 + ca2;

            ans %= mod;
        }

        if (ans < 0)
        {
            ans += mod;
        }
        out.println(ans);
    }

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
}
