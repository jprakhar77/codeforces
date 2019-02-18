package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class GGuestStudent {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int tc = in.nextInt();

        o:
        while (tc-- > 0) {
            int k = in.nextInt();

            int[] a = new int[7];

            int n = 7;

            int tot = 0;
            int ex = 7;
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();

                tot += a[i];

                if (a[i] == 1) {
                    ex = 6 - i;
                }
            }

            int ans = inf;
            i:
            for (int i = 0; i < n; i++) {
                if (a[i] == 1) {
                    int remthis = 0;

                    for (int j = i; j < 7; j++) {
                        remthis += a[j];
                        if (remthis == k) {
                            ans = Math.min(ans, j - i + 1);
                            continue i;
                        }
                    }

                    int ca = 7 - i;

                    int rem = k - remthis;

                    ca += 7 * (rem / tot);

                    rem -= tot * (rem / tot);
                    if (rem != 0) {
                        for (int j = 0; j < 7; j++) {
                            ca++;
                            rem -= a[j];

                            if (rem == 0) {
                                ans = Math.min(ans, ca);
                                continue i;
                            }
                        }
                    } else {
                        ca -= ex;
                        ans = Math.min(ans, ca);
                    }
                }
            }

            out.println(ans);
        }
    }

    int inf = (int) 1e9;
    ;
}
