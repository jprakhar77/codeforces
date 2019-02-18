package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CSeatArrangements {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        String[] g = new String[n];

        for (int i = 0; i < n; i++) {
            g[i] = in.next();
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int cec = 0;
            for (int j = 0; j < m; j++) {
                if (g[i].charAt(j) == '.') {
                    cec++;
                } else {
                    if (cec >= k) {
                        ans += (cec - k + 1);
                    }
                    cec = 0;
                }
            }

            if (cec >= k) {
                ans += (cec - k + 1);
            }
        }

        if (k == 1)
        {
            out.println(ans);
            return;
        }

        for (int j = 0; j < m; j++)
        {
            int cec = 0;
            for (int i = 0; i < n; i++)
            {
                if (g[i].charAt(j) == '.') {
                    cec++;
                } else {
                    if (cec >= k) {
                        ans += (cec - k + 1);
                    }
                    cec = 0;
                }
            }

            if (cec >= k) {
                ans += (cec - k + 1);
            }
        }

        out.println(ans);
    }
}
