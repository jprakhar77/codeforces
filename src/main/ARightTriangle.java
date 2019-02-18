package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ARightTriangle {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        out.println((a * b) / 2);
    }
}
