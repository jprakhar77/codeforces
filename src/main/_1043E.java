package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class _1043E {
    class sc {
        int i;
        int x;
        int y;
        long sub;

        public sc(int i, int x, int y) {
            this.i = i;
            this.x = x;
            this.y = y;
            this.sub = x - y;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        sc[] scs = new sc[n];
        for (int i = 0; i < n; i++) {
            scs[i] = new sc(i, in.nextInt(), in.nextInt());
        }

        long[] ans = new long[n];

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            int min = Math.min(scs[u].x + scs[v].y, scs[v].x + scs[u].y);

            ans[u] -= min;
            ans[v] -= min;
        }

        Arrays.sort(scs, (sc1, sc2) -> (int) Math.signum(sc1.sub - sc2.sub));

        long[] xpre = new long[n];
        long[] ypre = new long[n];

        xpre[0] = scs[0].x;
        ypre[0] = scs[0].y;

        for (int i = 1; i < n; i++) {
            xpre[i] = scs[i].x + xpre[i - 1];
            ypre[i] = scs[i].y + ypre[i - 1];
        }

        long toty = ypre[n - 1];

        for (int i = 0; i < n; i++) {
            int ind = scs[i].i;
            if (i > 0)
                ans[ind] += xpre[i - 1];

            ans[ind] += toty;

            ans[ind] -= ypre[i];

            ans[ind] += ((long) n - i - 1) * scs[i].x;
            ans[ind] += ((long) i) * scs[i].y;
        }

        for (int i = 0; i < n; i++) {
            out.print(ans[i] + " ");
        }
    }
}
