package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BContests {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int a = in.nextInt();
        int b = in.nextInt();

        int[] p = in.nextIntArray(n);

        int[] c = new int[3];

        for (int val : p) {
            if (val <= a) {
                c[0]++;
            } else if (val <= b) {
                c[1]++;
            } else {
                c[2]++;
            }
        }

        int ans = c[0];

        for (int i = 1; i < 3; i++) {
            ans = Math.min(ans, c[i]);
        }

        out.println(ans);
    }
}
