package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BBuildAContest {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        int[] a = in.nextIntArray(m);

        int dv = 0;

        int[] val = new int[n + 1];

        for (int i = 0; i < m; i++) {
            val[a[i]]++;

            if (val[a[i]] == 1) {
                dv++;
            }

            if (dv == n) {
                for (int j = 1; j <= n; j++) {
                    val[j]--;
                    if(val[j] == 0)
                        dv--;
                }
                out.print(1);
            } else {
                out.print(0);
            }
        }
    }
}
