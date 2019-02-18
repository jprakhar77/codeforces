package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BInnaDimaAndSong {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(n);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (2 * a[i] < b[i] || b[i] == 1) {
                ans--;
            } else {
                if (b[i] % 2 == 0) {
                    ans += ((long) b[i] / 2) * (b[i] / 2);
                } else {
                    ans += ((long) b[i] / 2) * (b[i] / 2 + 1);
                }
            }
        }

        out.println(ans);
    }
}
