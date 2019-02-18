package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ATable {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        int[][] a = new int[n][m];

        boolean isc = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();

                if (a[i][j] == 1) {
                    if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                        out.println(2);
                        return;
                    }
                }
            }
        }

        out.println(4);

    }
}
