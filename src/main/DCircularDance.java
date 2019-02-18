package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DCircularDance {
    class stu {
        int a1;
        int a2;

        public stu(int a1, int a2) {
            this.a1 = a1;
            this.a2 = a2;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        if (n == 3) {
            out.print("1 2 3");
            return;
        }

        stu[] sa = new stu[n];
        for (int i = 0; i < n; i++) {
            int a1 = in.nextInt() - 1;
            int a2 = in.nextInt() - 1;

            sa[i] = new stu(a1, a2);
        }

        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            //a1
            int i1 = inter(sa[i], sa[sa[i].a1]);

            if (i1 != -1) {
                next[i] = sa[i].a1;
            }

            int i2 = inter(sa[i], sa[sa[i].a2]);

            if (i2 != -1) {
                next[i] = sa[i].a2;
            }
        }

        int ci = 0;

        for (int i = 0; i < n; i++) {
            out.print(ci + 1 + " ");
            ci = next[ci];
        }
    }

    int inter(stu a, stu b) {
        if (a.a1 == b.a1)
            return a.a1;

        if (a.a1 == b.a2)
            return a.a1;

        if (a.a2 == b.a1)
            return a.a2;

        if (a.a2 == b.a2)
            return a.a2;

        return -1;
    }
}
