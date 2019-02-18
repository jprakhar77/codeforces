package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AVasyaAndBook {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long t = in.nextInt();

        while (t-- > 0) {
            long n = in.nextInt();
            long x = in.nextInt();
            long y = in.nextInt();
            long d = in.nextInt();

            long dif = Math.abs(x - y);

            if (dif % d == 0) {
                out.println(dif / d);
            } else {
                long ans = -1;

                if ((y - 1) % d == 0) {
                    ans = (x - 1 + d - 1) / d;
                    ans += (y - 1) / d;
                }

                if ((n - y) % d == 0) {
                    long ans2 = (n - x + d - 1) / d;
                    ans2 += (n - y) / d;

                    if (ans != -1)
                        ans = Math.min(ans, ans2);
                    else
                        ans = ans2;
                }

                out.println(ans);
            }
        }
    }
}
