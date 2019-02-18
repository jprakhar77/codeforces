package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DMakotoAndABlackboard {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        int k = in.nextInt();


        long[] inv = new long[51];

        for (int i = 50; i >= 0; i--) {
            inv[i] = pow(i, mod - 2, mod);
        }

//        long[] invs = new long[50];
//
//        invs[49] = inv[49];
//
//        for (int i = 48; i >= 0; i--) {
//            invs[i] = invs[i + 1] + inv[i];
//            invs[i] %= mod;
//        }

        HashMap<Long, Integer> pf = primeFactors(n);

        Set<Long> factors = factors(n);

        long[][][] ppa = new long[50][][];

        long ans = 0;

        for (long fac : factors) {

            long ca = 1;
            long num = fac;
            for (long pfac : pf.keySet()) {
                int maxcnt = pf.get(pfac);

                int cnt = 0;

                while (num % pfac == 0) {
                    num /= pfac;
                    cnt++;
                }

                long[][] cdp = null;

                if (ppa[maxcnt] == null) {
                    ppa[maxcnt] = cal(maxcnt, k, inv);
                }

                cdp = ppa[maxcnt];

                ca *= cdp[cnt][k];
                ca %= mod;
            }

            fac %= mod;

            ans += fac * ca;
            ans %= mod;
        }


        out.println(ans);
    }

    long[][] cal(int pp, int k, long[] inv) {
        long[][] dp = new long[pp + 1][k + 1];

        dp[pp][0] = 1;

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= pp; j++) {
                for (int l = j; l <= pp; l++) {
                    dp[j][i] += dp[l][i - 1] * inv[l + 1];
                    dp[j][i] %= mod;
                }
            }
        }

        return dp;
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

    int mod = 1_000_000_000 + 7;

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
