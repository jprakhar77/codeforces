package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class BPolygon {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] l = in.nextIntArray(n);

        Arrays.sort(l);

        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += l[i];
        }

        if (sum <= l[n - 1]) {
            out.println("No");
        } else {
            out.println("Yes");
        }
    }
}
