package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _908D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        k = in.nextInt();
        pa = in.nextInt();
        pb = in.nextInt();

        dp = new long[2 * k][k + 1];

        for (int i = 0; i < 2 * k; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }

        pab = pow(pa + pb, mod - 2, mod);
        p1 = pa * pab;
        p1 %= mod;
        p2 = pb * pab;
        p2 %= mod;

        pbi = pow(pb, mod - 2, mod);
        pai = pow(pa, mod - 2, mod);

        long ansa = p1 * rec(0, 1);
        ansa %= mod;

        ansa *= (pa + pb);
        ansa %= mod;
        ansa *= pai;

        out.print(ansa % mod);
    }

    int k;
    long pa;
    long pb;
    long[][] dp;
    int mod = (int) 1e9 + 7;
    long p1;
    long p2;
    long pab;
    long pbi;
    long pai;

    long rec(int ck, int ca) {
        if (ck >= k) {
            return ck;
        }
        if (dp[ck][ca] != -1) {
            return dp[ck][ca];
        }

        dp[ck][ca] = 0;

        //Put b
        if (ca < k) {
            dp[ck][ca] += p2 * rec(ck + ca, ca);
            dp[ck][ca] %= mod;
        }

        //Put a
        if (ca < k) {
            dp[ck][ca] += p1 * rec(ck, ca + 1);
            dp[ck][ca] %= mod;
        } else {
            //dp[ck][ca] = (((p2 * (pa + pb)) % mod) * pbi);
            long t1 = 0, t2 = 0;
            t1 = p2 * (ck + ca);
            t1 %= mod;
            t1 *= (pa + pb);
            t1 %= mod;
            t1 *= pbi;
            t1 %= mod;

            t2 = p2;
            t2 %= mod;
            t2 *= p1;
            t2 %= mod;
            t2 *= (pa + pb);
            t2 %= mod;
            t2 *= pbi;
            t2 %= mod;
            t2 *= (pa + pb);
            t2 %= mod;
            t2 *= pbi;
            t2 %= mod;

            dp[ck][ca] += t1 + t2;
            dp[ck][ca] %= mod;
        }

        dp[ck][ca] %= mod;
        return dp[ck][ca];
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
