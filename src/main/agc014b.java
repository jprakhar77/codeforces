package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class agc014b {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] c = new int[n];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            c[a]++;
            c[b]++;
        }

        for (int i = 0; i < n; i++) {
            if (c[i] % 2 != 0) {
                out.println("NO");
                return;
            }
        }

        out.println("YES");


    }
}
