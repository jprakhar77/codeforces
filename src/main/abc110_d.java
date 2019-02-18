package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;

public class abc110_d {
    int mod = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();


        int[][] dp = new int[n][31];

        for (int j = 0; j <= 30; j++) {
            dp[n - 1][j] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= 30; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i + 1][j - k];
                    dp[i][j] %= mod;
                }
            }
        }

        HashMap<Long, Integer> pf = primeFactors(m);

        long ans = 1;

        for (long p : pf.keySet()) {
            int cnt = pf.get(p);

            ans *= dp[0][cnt];
            ans %= mod;
        }

        out.println(ans);
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
}
