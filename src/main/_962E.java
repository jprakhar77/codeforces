package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _962E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long[] x = new long[n];
        char[] c = new char[n];

        int lp = n;
        int rp = -1;
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            c[i] = in.next().charAt(0);
            if (c[i] == 'P') {
                if (lp == n)
                    lp = i;
                rp = i;
            }
        }

        int li = -1;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (c[i] == 'B' || c[i] == 'P') {
                if (li != -1) {
                    ans += x[i] - x[li];
                }
                li = i;
            }
        }

        li = -1;
        for (int i = 0; i < n; i++) {
            if (c[i] == 'R' || c[i] == 'P') {
                if (li != -1) {
                    if (c[li] != 'P' || c[i] != 'P')
                        ans += x[i] - x[li];
                }
                li = i;
            }
        }

        long ans2 = ans;

        if (lp != n && lp != rp) {

            int lr = n;
            int lb = n;
            for (int i = 0; i < lp; i++) {
                if (c[i] == 'R' && lr == n)
                    lr = i;
                if (c[i] == 'B' && lb == n)
                    lb = i;
            }

            ans2 = 0;
            if (lp > lr)
                ans2 += x[lp] - x[lr];

            if (lp > lb)
                ans2 += x[lp] - x[lb];

            int rr = -1;
            int rb = -1;
            for (int i = n - 1; i > rp; i--) {
                if (c[i] == 'R' && rr == -1)
                    rr = i;
                if (c[i] == 'B' && rb == -1)
                    rb = i;
            }

            if (rp < rr)
                ans2 += x[rr] - x[rp];

            if (rp < rb)
                ans2 += x[rb] - x[rp];

            li = lp;

            int clp = lp;

            List<Integer> rpl = new ArrayList<>();
            List<Integer> bpl = new ArrayList<>();
            for (int i = lp + 1; i <= rp; i++) {
                if (c[i] == 'R') {
                    rpl.add(i);
                } else if (c[i] == 'B') {
                    bpl.add(i);
                } else {
                    long min = 2 * (x[i] - x[clp]);

                    long max1 = 0;
                    int cli = clp;
                    for (int j = 0; j < rpl.size(); j++) {
                        int ind = rpl.get(j);

                        max1 = Math.max(x[ind] - x[cli], max1);

                        cli = ind;
                    }

                    max1 = Math.max(max1, x[i] - x[cli]);

                    long max2 = 0;
                    cli = clp;
                    for (int j = 0; j < bpl.size(); j++) {
                        int ind = bpl.get(j);
                        max2 = Math.max(x[ind] - x[cli], max2);
                        cli = ind;
                    }

                    max2 = Math.max(max2, x[i] - x[cli]);

                    min = Math.min(min, 3 * (x[i] - x[clp]) - max1 - max2);
                    ans2 += min;

                    rpl.clear();
                    bpl.clear();

                    clp = i;
                }
            }
        }

        out.println(ans2);
    }
}
