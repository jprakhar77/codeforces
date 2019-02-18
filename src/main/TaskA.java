package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {


        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }

        sum /= n;

        int ans = -1;

        double diff = 10000;
        for (int i = 0; i < n; i++) {
            if (Math.abs(a[i] - sum) < diff) {
                ans = i;
                diff = Math.abs(a[i] - sum);
            }
        }

        out.println(ans);
    }
}
