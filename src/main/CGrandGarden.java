package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CGrandGarden {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] h = in.nextIntArray(n);

        int ans = 0;
        while (true) {
            boolean f = false;
            for (int i = 0; i < n; i++) {
                if (h[i] > 0) {
                    f = true;
                } else {
                    if (f) {
                        ans++;
                        f = false;
                    }
                }
            }

            if (f) {
                ans++;
            }

            boolean allz = true;
            for (int i = 0; i < n; i++) {
                if (h[i] > 0) {
                    h[i]--;
                    allz = false;
                }
            }

            if (allz)
            {
                break;
            }
        }

        out.println(ans);
    }
}
