package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ECongruenceEquation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        long a = in.nextLong();
        long b = in.nextLong();
        long p = in.nextLong();
        long x = in.nextLong();

//        long lpm = (x / p) * p;
//        long t = x / p;
//
//        long[] ap = new long[(int) p];
//
//        ap[0] = 1;
//
//        for (int i = 1; i < p; i++) {
//            ap[i] = ap[i - 1] * a;
//            ap[i] %= p;
//        }
//
//        long[] miap = new long[(int) p];
//
//        Arrays.fill(miap, -1);
//
//        for (int i = 0; i < p; i++) {
//            if (miap[(int) ap[i]] == -1) {
//                miap[(int) ap[i]] = i;
//            }
//        }

        long maxi = x / (p - 1);

        long xx = maxi * (p - 1);

        long ans = 0;
        for (int r = 0; r < p - 1; r++) {
            long val = b;
            long ap = pow(a, r, p);
            long api = pow(ap, p - 2, p);
            val *= api;
            val %= p;

            long st = r - val;
            if (st < 0) {
                st += p;
            }

            long cmaxi = maxi;

            if (xx + r > x)
            {
                cmaxi--;


            }

            if (st > cmaxi)
                continue;

            long rem = cmaxi - st;

            ans += (rem / p) + 1;
        }

        out.println(ans);
    }

    long pow(long a, long p, long mod) {
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
