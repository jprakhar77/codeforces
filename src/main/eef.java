package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class eef {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        String as = in.next();
        String bs = in.next();

        StringBuilder a = new StringBuilder(as);
        StringBuilder b = new StringBuilder(bs);

//        while (a.length() < b.length()) {
//            a.insert(0, '0');
//        }
//
//        while (b.length() < a.length()) {
//            b.insert(0, '0');
//        }

        //n = Math.max(n, m);

        long[] _2pow = new long[200005];

        _2pow[0] = 1;

        for (int i = 1; i <= 200004; i++) {
            _2pow[i] = _2pow[i - 1] * 2;
            _2pow[i] %= mod;

        }
        int co = 0;

        long ans = 0;
        for (int i = 0; i < m; i++) {
            if (b.charAt(i) == '1') {
                co++;
            }

            int ai = n - 1 - (m - 1 - i);
            if (ai >= 0 && a.charAt(ai) == '1') {
                ans += ((co * _2pow[n - 1 - ai]) % mod);
                ans %= mod;
            }
        }

        out.println(ans);
    }

    int mod = 998244353;
}
