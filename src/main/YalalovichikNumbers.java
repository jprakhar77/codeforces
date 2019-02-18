package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class YalalovichikNumbers {
    int maxn = 100000;

    int mod = 1000000007;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        long[] tp = new long[maxn + 1];

        tp[0] = 1;

        for (int i = 1; i <= maxn; i++) {
            tp[i] = 10 * tp[i - 1];
            tp[i] %= mod;
        }

        while (t-- > 0) {
            String s = in.next();

            int n = s.length();
            long[] pre = new long[n];
            long[] suf = new long[n];

            suf[n - 1] = s.charAt(n - 1) - '0';

            for (int i = n - 2; i >= 0; i--) {
                long num = s.charAt(i) - '0';

                num *= tp[n - 1 - i];
                num %= mod;

                num += suf[i + 1];
                num %= mod;

                suf[i] = num;
            }

            pre[0] = s.charAt(0) - '0';

            for (int i = 1; i < n; i++) {
                long num = s.charAt(i) - '0';

                pre[i] = pre[i - 1] * 10 + num;

                pre[i] %= mod;
            }

            long ans = pre[n - 1];

            for (int i = 1; i < n; i++) {
                long val = suf[i];
                val *= tp[i];
                val %= mod;
                val += pre[i - 1];
                val %= mod;

                ans *= tp[n];
                ans %= mod;
                ans += val;
                ans %= mod;
            }

            out.println(ans);
        }
    }
}
