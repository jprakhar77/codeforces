package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class _1043F {
    int[] sieve(int n) {
        int[] sieve = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            sieve[i] = i;
        }

        for (int i = 2; i * i <= n; i++) {
            if (sieve[i] == i) {
                for (int j = i; j * i <= n; j++) {
                    if (sieve[j * i] == j * i)
                        sieve[j * i] = i;
                }
            }
        }

        return sieve;
    }

    int[] mobius(int n) {
        int[] sieve = sieve(n);

        int[] mobius = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            Set<Integer> primeFactors = new HashSet<>();

            int curNum = i;

            boolean isSquareFree = true;
            while (curNum > 1) {
                int nextPrime = sieve[curNum];

                if (primeFactors.contains(nextPrime)) {
                    isSquareFree = false;
                    break;
                }

                primeFactors.add(nextPrime);
                curNum /= nextPrime;
            }

            if (isSquareFree) {
                if ((primeFactors.size() & 1) == 0) {
                    mobius[i] = 1;
                } else {
                    mobius[i] = -1;
                }
            }
        }

        return mobius;
    }

    int[][] nCr(int n, int r, int mod) {
        int[][] ncr = new int[n + 1][r + 1];

        ncr[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            ncr[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r; j++) {
                ncr[i][j] = ncr[i - 1][j - 1] + ncr[i - 1][j];
                ncr[i][j] %= mod;
            }
        }

        return ncr;
    }

    int max = 0;

    int mod = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        in.readArray(a, n, 0);

        LinkedHashSet<Integer> lhs = new LinkedHashSet<>();

        for (int val : a) {
            lhs.add(val);
            max = Math.max(max, val);
        }

        n = lhs.size();

        //int[] mobius = mobius(max);

        int[][] ncr = nCr(max, 7, mod);

        long[][] dp = new long[8][max + 1];

        for (int i = max; i >= 1; i--) {

            int num = 0;
            for (int j = 1; j * i <= max; j++) {
                if (lhs.contains(j * i)) {
                    num++;
                }
            }
            for (int j = 1; j <= 7; j++) {
                dp[j][i] = ncr[num][j];

                for (int k = 2; k * i <= max; k++) {
                    dp[j][i] -= dp[j][i * k];
                    dp[j][i] %= mod;
                }
            }
        }

        for (int j = 1; j <= 7; j++) {
            if (dp[j][1] != 0) {
                out.println(j);
                return;
            }
        }

        out.println(-1);
    }
}
