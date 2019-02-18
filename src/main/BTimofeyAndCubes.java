package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BTimofeyAndCubes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            boolean rev = true;

            if (i >= n / 2) {
                int diff = n - i;
                if (diff % 2 == 1) {
                    rev = true;
                } else {
                    rev = false;
                }
            } else {
                int diff = i + 1;
                if (diff % 2 == 1) {
                    rev = true;
                } else {
                    rev = false;
                }
            }

            if (rev) {
                b[i] = a[n - i - 1];
            } else {
                b[i] = a[i];
            }

            out.print(b[i] + " ");
        }
    }
}
