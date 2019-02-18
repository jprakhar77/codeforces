package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CEhabAndA2OperationTask {
    int max = (int) 1e6;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int cmax = max;

        for (int i = 0; i < n; i++) {
            cmax = Math.min(max + i - a[i], cmax);
        }

        int toadd = cmax;

        out.println(n + 1);

        out.println("1 " + n + " " + toadd);

        for (int i = 0; i < n; i++) {
            a[i] += toadd;
        }

        for (int i = 0; i < n; i++) {
            out.println("2 " + (i + 1) + " " + (a[i] - i));
        }
    }
}
