package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ADefiniteGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        if (n == 2) {
            out.println(2);
            return;
        }

        out.println(1);
    }

}
