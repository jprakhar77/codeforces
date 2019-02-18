package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class CChristmasEve {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        Integer[] a = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Arrays.sort(a);

        long ans = Long.MAX_VALUE;
        for (int i = 0; i <= n - k; i++) {
            ans = Math.min(ans, a[i + k - 1] - a[i]);
        }

        out.println(ans);
    }
}
