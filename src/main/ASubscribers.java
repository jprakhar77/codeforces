package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ASubscribers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();

        int s = a + b;

        int min, max;

        if (s > n) {
            min = s - n;
        } else {
            min = 0;
        }

        max = Math.min(a, b);

        out.println(max + " " + min);
    }
}
