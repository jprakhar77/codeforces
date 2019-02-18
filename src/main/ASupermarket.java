package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ASupermarket {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        double min = 1000000000;

        for (int i = 0; i < n; i++) {
            double a = in.nextInt();
            double b = in.nextInt();

            min = Math.min(a / b, min);
        }

        out.println(min * m);
    }
}
