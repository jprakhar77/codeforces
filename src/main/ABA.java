package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ABA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();

        if (b % a == 0) {
            out.println(a + b);
        } else {
            out.println(b - a);
        }
    }
}
