package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BPermutation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int rk = k;

        int sn = 1;
        for (int i = 0; i < 2 * n; i += 2) {
            if (rk > 0) {
                out.print(sn + " " + (sn + 1) + " ");
                sn += 2;
                rk--;
            } else {
                out.print((sn + 1) + " " + (sn) + " ");
                sn += 2;
            }
        }
    }
}
