package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AIntegerSequenceDividing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        long n = in.nextInt();

        long sum = (n * (n + 1)) / 2;

        if (sum % 2 == 0) {
            out.println(0);
        } else {
            out.println(1);
        }
    }
}
