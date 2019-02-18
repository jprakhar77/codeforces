package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.TreeSet;

public class _830C {

    class seg implements Comparable<seg> {
        long l;
        long h;
        long aibyk;

        public seg(long l, long h, long aibyk) {
            this.l = l;
            this.h = h;
            this.aibyk = aibyk;
        }

        @Override
        public int compareTo(seg o) {
            return (int) Math.signum(this.l - o.l);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long k = in.nextLong();

        long ts = 0;

        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            ts += a[i];
        }

        TreeSet[] tm = new TreeSet[n];

        for (int i = 0; i < n; i++) {
            tm[i] = new TreeSet<seg>();
        }

        for (int i = 0; i < n; i++) {
            long num = a[i];

            long sq = (long) Math.floor(Math.sqrt(num));


            for (int j = 1; j <= sq; j++) {
                long mind = (num + j - 1) / j;
                long maxd = j > 1 ? (num % (j - 1) == 0 ? ((num / (j - 1)) - 1) : (num) / (j - 1)) : num;

                seg seg1 = new seg(mind, maxd, j);

                long m = (num + j - 1) / j;

                seg seg2 = new seg(j, j, m);

                tm[i].add(seg1);
                tm[i].add(seg2);
            }
        }

        long d = 1;
//        for (seg seg : (TreeSet<seg>) cm[0]) {
//            long l = seg.l;
//            long h = seg.h;
//
//            long sum = seg.aibyk;
//            for (int i = 1; i < n; i++) {
//                seg cseg = ((TreeSet<seg>) cm[i]).floor(new seg(h, l, -1));
//
//                if (cseg == null) {
//                    sum = -1;
//                    break;
//                }
//
//                sum += cseg.aibyk;
//            }
//
//            if (sum > 0) {
//                d = Math.max(d, (ts + k) / sum);
//            }
//        }

        for(int i = 0; i < n; i++)
        {
            for (seg seg : (TreeSet<seg>) tm[0])
            {

            }
        }

        out.println(d);

    }
}
