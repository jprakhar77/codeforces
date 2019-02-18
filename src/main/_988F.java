package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class _988F {
    class seg {
        int l;
        int r;

        public seg(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    class um {
        int x;
        int w;

        public um(int x, int w) {
            this.x = x;
            this.w = w;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();

        int n = in.nextInt();

        int m = in.nextInt();

        seg[] segs = new seg[n];

        for (int i = 0; i < n; i++) {
            int l = in.nextInt();
            int r = in.nextInt();

            segs[i] = new seg(l, r);
        }

        long[] uw = new long[a + 1];

        for (int i = 0; i <= a; i++) {
            uw[i] = inf;
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int w = in.nextInt();

            uw[x] = Math.min(uw[x], w);
        }

        if (uw[a] == inf) {
            uw[a] = 0;
        }

        long[] dp = new long[a + 1];

        for (int i = 0; i <= a; i++) {
            dp[i] = inf;
        }

        Arrays.sort(segs, (s1, s2) -> s1.l - s2.l);

        int[] lsi = new int[a + 1];
        for (int i = a; i >= 0; i--) {
            if (uw[i] != inf) {
                lsi[i] = -1;
                for (int j = 0; j < n; j++) {
                    if (segs[j].l < i && segs[j].r >= i) {
                        lsi[i] = i;
                        break;
                    } else if (segs[j].r < i) {
                        lsi[i] = segs[j].r;
                    } else {
                        break;
                    }
                }
            }
        }

        dp[a] = 0;

        for (int i = a - 1; i >= 0; i--) {
            if (uw[i] != inf) {
                if (segs[n - 1].r <= i) {
                    dp[i] = 0;
                    continue;
                }
                for (int j = i + 1; j <= a; j++) {
                    if (uw[j] != inf) {
                        int li = lsi[j];
                        if (li <= i) {
                            dp[i] = Math.min(dp[i], dp[j]);
                        } else {
                            dp[i] = Math.min(dp[i], (li - i) * uw[i] + dp[j]);
                        }
                    }
                }
            }
        }

        long min = inf;

        for (int i = segs[0].l; i >= 0; i--) {
            min = Math.min(min, dp[i]);
        }

        if (min == inf) {
            out.println(-1);
            return;
        }

        out.println(min);
    }

    long inf = 10000000000l;
}
