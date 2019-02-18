package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BFoodsLovedByEveryone {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        int[][] a = new int[n][m];


        for (int i = 0; i < n; i++) {
            int t = in.nextInt();
            for (int j = 0; j < t; j++) {
                int num = in.nextInt();

                a[i][num - 1] = 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            boolean poss = true;

            for (int j = 0; j < n; j++) {
                if (a[j][i] == 0) {
                    poss = false;
                    break;
                }
            }

            if (poss) {
                ans++;
            }
        }

        out.println(ans);
    }

}
