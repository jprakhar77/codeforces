package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _584E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int[] p = new int[n];

        int[] s = new int[n];

        for (int i = 0; i < n; i++)
            p[i] = in.nextInt();

        int[] spos = new int[n + 1];
        for (int i = 0; i < n; i++) {
            s[i] = in.nextInt();
            spos[s[i]] = i;
        }

        int ans = 0;
        int[] ppos = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ppos[i] = spos[p[i]] - i;
            if (ppos[i] > 0)
                ans += ppos[i];
        }

        out.println(ans);
        List<op> ops = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (ppos[i] < 0) {
                int cv = ppos[i];
                int ci = i;
                for (int j = i - 1; j >= 0 && cv < 0; j--) {
                    if (ppos[j] > 0) {
                        int gap = ci - j;

                        ops.add(new op(j + 1, ci + 1));
                        ppos[j] -= gap;
                        ppos[ci] += gap;

                        int t = ppos[j];
                        ppos[j] = ppos[ci];
                        ppos[ci] = t;

                        ci = j;
                        cv = ppos[ci];
                    }
                }
            }
        }

        out.println(ops.size());

        for (int i = 0; i < ops.size(); i++) {
            op op = ops.get(i);
            out.println(op.f + " " + op.s);
        }
    }

    class op {
        int f;
        int s;

        public op(int f, int s) {
            this.f = f;
            this.s = s;
        }
    }
}
