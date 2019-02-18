package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class BChristmasEveEve {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] p = in.nextIntArray(n);

        Arrays.sort(p);

        long sum = 0;

        for (int i = 0; i < n - 1; i++) {
            sum += p[i];
        }

        sum += p[n - 1] / 2;

        out.println(sum);
    }
}
