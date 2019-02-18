package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DHarlequin {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        boolean iso = false;

        for (int i = 0; i < n; i++) {
            if (a[i] % 2 != 0) {
                iso = true;
            }
        }

        if (iso) {
            out.println("first");
        } else {
            out.println("second");
        }
    }
}
