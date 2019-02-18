package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BDivideCandies {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        long[] ma = new long[m];

        ma[0] = n / m;
        for (int i = 1; i < m; i++) {
            if (n >= i)
                ma[i] = 1 + (n - i) / m;
        }

        long ans = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (((i * i) + (j * j)) % m == 0) {
                    ans += ma[i] * ma[j];
                }
            }
        }

        out.println(ans);

    }
}
