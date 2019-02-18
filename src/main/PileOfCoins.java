package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class PileOfCoins {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int p = in.ni();
            int q = in.ni();

            if (p % 2 == 0 || q % 2 == 0) {
                out.println("Ashish");
            } else {
                out.println("Jeel");
            }
        }
    }
}
