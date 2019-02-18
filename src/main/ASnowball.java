package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ASnowball {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int w = in.nextInt();

        int h = in.nextInt();

        int u1 = in.nextInt();
        int d1 = in.nextInt();

        int u2 = in.nextInt();
        int d2 = in.nextInt();

        long ans = w;
        for (int i = h; i >= 0; i--) {
            ans += i;
            if (i == d1) {
                ans -= u1;
                if (ans < 0)
                    ans = 0;
            }

            if (i == d2) {
                ans -= u2;
                if (ans < 0)
                    ans = 0;
            }
        }

        out.println(ans);
    }
}
