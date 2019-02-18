package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AFarRelativesBirthdayCake {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String[] g = in.ng(n);

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long c = 0;
            for (int j = 0; j < n; j++) {
                if (g[i].charAt(j) == 'C') {
                    c++;
                }
            }

            ans += (c * (c - 1)) / 2;
        }

        for (int j = 0; j < n; j++) {
            long c = 0;
            for (int i = 0; i < n; i++) {
                if (g[i].charAt(j) == 'C') {
                    c++;
                }
            }

            ans += (c * (c - 1)) / 2;
        }

        out.println(ans);
    }
}
