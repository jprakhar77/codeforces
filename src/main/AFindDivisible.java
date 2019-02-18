package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AFindDivisible {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();

            out.println(l + " " + (l * 2));
        }
    }
}
