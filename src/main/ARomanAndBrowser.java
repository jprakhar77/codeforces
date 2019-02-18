package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class ARomanAndBrowser {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = in.nextIntArray(n);

        int ans = 0;
        for (int c = 1; c <= n; c++) {
            int[] na = new int[n];
            for (int i = 1; i <= n; i++) {
                int d = Math.abs(i - c);
                if (d % k == 0) {
                    na[i - 1] = 1;
                }
            }

            int ca = 0;
            for (int i = 0; i < n; i++) {
                if (na[i] == 1)
                    continue;
                if (a[i] == 1) {
                    ca++;
                } else {
                    ca--;
                }
            }

            ca = Math.abs(ca);

            ans = Math.max(ans, ca);
        }

        out.println(ans);
    }
}
