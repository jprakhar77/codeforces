package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _949B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextLong();
        int q = in.nextInt();

        while (q-- > 0) {
            long x = in.nextLong();

            if (x % 2 == 1) {
                out.println((x + 1) / 2);
            } else {
                long cx = x;

                while (cx % 2 == 0) {
                    long onum = n - cx / 2;
                    cx += onum;
                }

                out.println((cx + 1) / 2);
            }
        }
    }
}
