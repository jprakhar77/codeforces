package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CWhenIHitMyPocket {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long k = in.nextInt();
        long a = in.nextInt();
        long b = in.nextInt();

        if (b - a <= 2) {
            out.println(k + 1);
        } else {
            if (k <= a) {
                out.println(k + 1);
            } else {
                long rs = k - (a - 1);

                long ans = a;
                ans += (rs / 2) * (b - a);
                if (rs % 2 != 0) {
                    ans++;
                }

                out.println(ans);
            }
        }
    }
}
