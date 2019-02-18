package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class BArrayStabilization {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        Integer[] a = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Arrays.sort(a);

        if (n == 2) {
            out.print(0);
            return;
        }

        out.print(Math.min(a[n - 1] - a[1], a[n - 2] - a[0]));
    }
}
