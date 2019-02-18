package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AInnaAndAlarmClock {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[][] g = new int[101][101];

        for (int i = 0; i < n; i++) {
            g[in.nextInt()][in.nextInt()]++;
        }

        int a1 = 0, a2 = 0;
        for (int i = 0; i <= 100; i++) {
            int c = 0;
            for (int j = 0; j <= 100; j++) {
                c += g[i][j];
            }

            if (c > 0) {
                a1++;
            }

        }

        for (int j = 0; j <= 100; j++) {
            int c = 0;
            for (int i = 0; i <= 100; i++) {
                c += g[i][j];
            }

            if (c > 0) {
                a2++;
            }

        }

        out.println(Math.min(a1, a2));
    }
}
