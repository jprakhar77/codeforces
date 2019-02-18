package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class EMahmoudAndEhabAndTheXorMST {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();

        long cn = n;

        long ans = 0;
        long cv = 1;
        while (cn > 1) {
            if (cn % 2 == 0) {
                ans += cv * (cn / 2);
                cn = cn / 2;
            } else {
                ans += cv * (cn / 2);
                cn = cn / 2 + 1;
            }
            cv *= 2;
        }

        out.println(ans);
    }
}
