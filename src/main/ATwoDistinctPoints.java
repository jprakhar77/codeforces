package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ATwoDistinctPoints {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int q = in.nextInt();

        while (q-- > 0) {
            int l1 = in.nextInt();
            int r1 = in.nextInt();
            int l2 = in.nextInt();
            int r2 = in.nextInt();

            //out.println(Math.min(l1, l2) + " " + Math.max(r1, r2));

            if (l1 <= l2) {
                out.println(l1 + " " + r2);
            } else {
                out.println(r1 + " " + l2);
            }

        }
    }
}
