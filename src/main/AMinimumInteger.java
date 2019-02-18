package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AMinimumInteger {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int q = in.nextInt();

        while (q-- > 0) {
            long l = in.nextInt();
            long r = in.nextInt();
            long d = in.nextInt();

//            long t = (l + d - 1) / d;
//
//            out.println(t * d);

            if (d < l) {
                out.println(d);
            } else {
                long t = (r + 1 + d - 1) / d;
                out.println(t * d);

            }
        }
    }
}
