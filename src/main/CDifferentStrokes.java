package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class CDifferentStrokes {
    class seg {
        long a;
        long b;
        long sum;

        public seg(long a, long b) {
            this.a = a;
            this.b = b;
            this.sum = a + b;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        long[] a = new long[n];
        long[] b = new long[n];

        TreeMap<Long, Integer> tm = new TreeMap<>();

        List<seg> segs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();

            //tm.merge(a[i] - b[i], 1, (x, y) -> x + y);
            segs.add(new seg(a[i], b[i]));
        }

        long ta = 0;
        long ao = 0;

        segs.sort((x, y) -> (int) Math.signum(y.sum - x.sum));

        long d = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                ta += segs.get(i).a;
            } else {
                ao += segs.get(i).b;

            }
        }

        out.println(ta - ao);
    }
}
