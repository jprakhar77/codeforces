package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class BTeamsForming {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        Arrays.sort(a);

        int ans = 0;
        for (int i = 1; i < n; i += 2) {
            if (a[i] > a[i - 1]) {
                ans += a[i] - a[i - 1];
            }
        }

        out.println(ans);
    }
}
