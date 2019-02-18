package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ABulletinBoard {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextInt();

        long h = in.nextInt();

        long w = in.nextInt();

        if (n < h || n < w) {
            out.println(0);
            return;
        }

        out.println((n - h + 1) * (n - w + 1));
    }
}
