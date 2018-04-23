package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int[] mnm = new int[n];

        mnm[0] = 1;

        for (int i = 1; i < n; i++) {
            mnm[i] = Math.max(a[i] + 1, mnm[i - 1]);
        }

        for (int i = n - 2; i >= 0; i--) {
            mnm[i] = Math.max(mnm[i], mnm[i + 1] - 1);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(0, mnm[i] - a[i] - 1);
        }

        out.println(ans);
    }
}
