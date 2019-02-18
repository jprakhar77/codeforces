package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CDoorsBreakingAndRepairing {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int x = in.nextInt();

        int y = in.nextInt();

        int[] a = in.nextIntArray(n);

        if (x > y) {
            out.println(n);
            return;
        }

        int l = 0;
        for (int val : a) {
            if (val <= x) {
                l++;
            }
        }

        out.println((l + 1) / 2);
    }
}
