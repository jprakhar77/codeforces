package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class agc025a {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int sum = 0;

        while (n > 0) {
            sum += (n % 10);
            n /= 10;
        }

        if (sum == 1) {
            sum *= 10;
        }

        out.println(sum);
    }
}
