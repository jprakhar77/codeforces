package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class APoisonousCookies {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();

        if (c > a + b + 1) {
            c = a + b + 1;
        }

        out.println(b + c);
    }
}
