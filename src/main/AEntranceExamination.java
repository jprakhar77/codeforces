package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AEntranceExamination {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double t = in.nextLong();
        double x = in.nextLong();

        out.println(t / x);
    }
}
