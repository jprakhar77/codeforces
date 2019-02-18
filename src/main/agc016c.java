package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class agc016c {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int r = in.nextInt();
        int c = in.nextInt();

        int h = in.nextInt();
        int w = in.nextInt();

        long[][] ans = new long[r][c];

        if (h == 1 && w == 1) {
            out.print("No");
            return;
        }


        long neg = -1000000000;
        for (int i = h - 1; i < r; i += h) {
            for (int j = w - 1; j < c; j += w) {
                ans[i][j] = neg;
            }
        }

        long pos = 999999999;
        for (int i = 0; i < r; i += h) {
            for (int j = 0; j < c; j += w) {
                ans[i][j] = pos;
            }
        }

        long sum = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sum += ans[i][j];
            }
        }

        if (sum > 0) {
            out.println("Yes");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    out.print(ans[i][j] + " ");
                }
                out.println();
            }
            return;
        } else {
            out.print("No");
        }
    }
}
