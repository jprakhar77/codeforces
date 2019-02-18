package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AAntiAdjacency {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int max = 1 + (k - 1) * 2;

        if (max <= n) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
