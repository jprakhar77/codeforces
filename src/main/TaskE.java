package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class TaskE {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int min = (int) 1e9 + 1;

        for (int i = 0; i < n; i++) {
            min = Math.min(min, a[i]);
        }
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans *= min;
            ans %= 998244353;
        }

        out.println(ans);
    }
}
