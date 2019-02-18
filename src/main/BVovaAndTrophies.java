package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BVovaAndTrophies {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String s = in.next();

        int cg = 0;

        int max = 0;
        int tot = 0;
        int[] maxa = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'G') {
                tot++;
                cg++;
                maxa[i] = cg;
                max = Math.max(max, cg);
            } else {
                cg = 0;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == 'G' && s.charAt(i + 1) == 'G') {
                maxa[i] = maxa[i + 1];
            }
        }

        int ans = max;

        for (int i = 0; i < n; i++) {
            if (maxa[i] < tot) {
                ans = Math.max(ans, maxa[i] + 1);

            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == 'S') {
                if (s.charAt(i - 1) == 'G' && s.charAt(i + 1) == 'G') {
                    int cur = maxa[i - 1] + maxa[i + 1];

                    if (cur < tot) {
                        ans = Math.max(ans, cur + 1);
                    } else {
                        ans = Math.max(ans, cur);
                    }
                }
            }
        }

        out.println(ans);
    }
}
