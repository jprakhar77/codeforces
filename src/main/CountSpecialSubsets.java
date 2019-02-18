package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountSpecialSubsets {
    Set<Integer> primeFactors(int n) {

        Set<Integer> set = new HashSet<>();

        int cn = n;
        for (int i = 2; i * i <= cn; i++) {
            if (cn % i == 0) {
                set.add(i);
                while (cn % i == 0) {
                    cn /= i;
                }
            }
        }

        if (cn > 1) {
            set.add(cn);
        }

        return set;
    }


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

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        int[] sieve = sieve(1000000);

        while (t-- > 0) {
            int n = in.nextInt();

            int x = in.nextInt();

            int[] a = in.nextIntArray(n);


            Set<Integer> xp = primeFactors(x);

            List<Integer> xl = new ArrayList<>(xp);

            List<Integer> l = new ArrayList<>();
            List<Integer> vl = new ArrayList<>();

            int val = -1;
            for (int i = 0; i < n; i++) {
                if ((val = find(a[i], sieve, xp, xl)) != -1) {
                    l.add(a[i]);
                    vl.add(val);
                }
            }

            n = l.size();

            if (n == 0) {
                out.println("0");
                continue;
            }

            int m = xp.size();

            long[][] dp = new long[n + 1][1 << m];

            dp[0][vl.get(0)] = 1;
            dp[0][0] += 1;

            for (int i = 1; i < n; i++) {
                int cv = vl.get(i);
                for (int j = 0; j < (1 << m); j++) {
                    int eind = j | cv;
                    dp[i][j] += dp[i - 1][j];
                    dp[i][j] %= mod;
                    dp[i][eind] += dp[i - 1][j];
                    dp[i][eind] %= mod;
                }
            }

            out.println(dp[n - 1][(1 << m) - 1]);
        }
    }

    int mod = 1_000_000_007;

    int find(int num, int[] sieve, Set<Integer> xp, List<Integer> xl) {
        int cnum = num;
        while (num > 1) {
            int pf = sieve[num];

            if (!xp.contains(pf)) {
                return -1;
            }

            num /= pf;
        }

        int value = 0;
        for (int i = 0; i < xl.size(); i++) {
            if (cnum % xl.get(i) == 0) {
                value |= (1 << i);
            }
        }

        return value;
    }
}
