package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BDivTimesMod {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int k = in.nextInt();

        for (int i = k - 1; i > 0; i--) {
            if (n % i == 0) {
                int rem = n / i;

                out.println(k * rem + i);
                return;
            }
        }
    }
}
