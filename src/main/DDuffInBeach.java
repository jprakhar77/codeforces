package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DDuffInBeach {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long l = in.nextLong();

        int k = in.nextInt();

        long rem = n;
        long ak = k;
        if (l < n * k) {
            if (l % n == 0) {
                ak = l / n;
            } else {
                rem = l % n;
                ak = l / n;
            }
        } else {
            if (l % n != 0) {
                rem = l % n;
            }
        }

        Integer[] a = new Integer[n];
        Integer[] b = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = a[i];
        }

        Arrays.sort(b);

        Map<Integer, Integer> map = new HashMap<>();

        int z = 0;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(b[i]))
                map.put(b[i], z++);
        }

        int an = z;

        int[] m1 = new int[an];
        int[] m2 = new int[an];

        for (int i = 0; i < n; i++) {
            m1[map.get(a[i])]++;
        }
        for (int i = 0; i < rem; i++) {
            m2[map.get(a[i])]++;
        }


        long[][] dp = new long[(int) ak][an];
        long[][] dpp = new long[(int) ak][an];

        if (ak > 0) {
            for (int i = 0; i < an; i++) {
                dp[0][i] = m1[i];
            }


            dpp[0] = in.calculatePrefixSum(dp[0]);
        }

        for (int i = 1; i < ak; i++) {
            for (int j = 0; j < an; j++) {
                long val = m1[j];
                dp[i][j] = (val) * dpp[i - 1][j];
                dp[i][j] %= mod;
            }
            dpp[i] = in.calculatePrefixSum(dp[i], mod);
        }

        long ans = 0;//dpp[(int) ak - 1][(int) an - 1];

        long times = l / n;
        times %= mod;
        for (int i = 0; i < an; i++) {
            for (int j = 1; j <= ak; j++) {
                ans += (dp[j - 1][i] * (times - j + 1)) % mod;
                ans %= mod;
            }
        }
//        if (ak > 1) {
//            ans += dp[(int) ak - 2][n - 1] - dp[(int) ak - 2][(int) rem - 1];
//            ans %= mod;
//
//            if (ans < 0) {
//                ans += mod;
//            }
//        }

        if (rem != n) {
            if (ak < k) {
                ak++;
            }
            long[][] dp2 = new long[(int) ak][n];
            for (int j = 0; j < an; j++) {
                long val = m2[j];
                //ans += ((val) * dpp[(int) ak - 1][j] + dp[(int) ak - 1][j] + val) % mod;
                dp2[0][j] = val;
                ans += dp2[0][j];
                ans %= mod;
            }
            for (int i = 1; i < ak; i++) {
                for (int j = 0; j < an; j++) {
                    long val = m2[j];
                    dp2[i][j] = (val) * dpp[i - 1][j];
                    dp2[i][j] %= mod;
                    ans += dp2[i][j];
                    ans %= mod;
                }
            }
        }

        if (ans < 0) {
            ans += mod;
        }
        out.println(ans);
    }

    int mod = 1000000007;
}
