package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class APetyaAndOrigami {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long n = in.nextInt();
        long k = in.nextInt();

        long rn = 2 * n;
        long gn = 5 * n;
        long bn = 8 * n;

        long a1 = (rn + k - 1) / k;
        long a2 = (gn + k - 1) / k;
        long a3 = (bn + k - 1) / k;

        out.println(a1 + a2 + a3);
    }
}
