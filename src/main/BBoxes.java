package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BBoxes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        long sum = 0;

        for (int val : a)
        {
            sum += val;
        }

        long base = (n * (n + 1)) / 2;

        if (sum % base != 0)
        {
            out.println("No");
            return;
        }


    }
}
