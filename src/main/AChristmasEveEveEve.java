package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AChristmasEveEveEve {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int d = in.nextInt();

        if (d == 25) {
            out.println("Christmas");
        } else if (d == 24) {
            out.println("Christmas Eve");
        } else if (d == 23) {
            out.println("Christmas Eve Eve");
        } else {
            out.println("Christmas Eve Eve Eve");
        }
    }
}
