package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CNNAndTheOpticalIllusion {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int r = in.nextInt();

        int sides = n;

        double angle = (((double)n - 2) * Math.PI) / n;

        double x = ((double) angle) / 2;

        double cosx = Math.cos(x);

        double rr = (r * cosx) / (1 - cosx);

        out.print(rr);
    }
}
