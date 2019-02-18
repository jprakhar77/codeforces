package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class ALaptops {
    class l {
        int a;
        int b;

        public l(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        l[] ls = new l[n];

        for (int i = 0; i < n; i++) {
            ls[i] = new l(in.ni(), in.ni());
        }

        Arrays.sort(ls, (l1, l2) -> l1.a - l2.a);

        int hb = ls[0].b;

        for (int i = 1; i < n; i++) {
            if (ls[i].b < hb) {
                out.println("Happy Alex");
                return;
            }
            hb = Math.max(hb, ls[i].b);
        }

        out.println("Poor Alex");
    }
}
