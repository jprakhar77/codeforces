package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class Distances {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        t:
        while (t-- > 0) {
            int n = in.nextInt();

            long[] a = in.nextLongArray(n);
            long[] b = in.nextLongArray(n);

            if (a[0] != 0 || b[n - 1] != 0 || a[n - 1] != b[0] || a[n - 1] == 0) {
                out.println("No");
                continue;
            }

//            long max = Integer.MAX_VALUE;
//
//            for (int i = 1; i < n - 1; i++) {
//                max = Math.min(max, a[i] + b[i]);
//            }
//
//            if (a[n - 1] > max || b[0] > max || a[n - 1] != b[0]) {
//                out.println("No");
//                continue;
//            }
//
//            out.println("Yes");

            long[] min = new long[n];

            min[0] = inf;
            min[n - 1] = inf;
            for (int i = 1; i < n - 1; i++) {
                if (a[i] == 0 || b[i] == 0) {
                    out.println("No");
                    continue t;
                }
                min[i] = a[i] + b[i];
            }

            long[] premin = in.calculatePrefixMin(min);
            long[] sufmin = in.calculateSuffixMin(min);

            for (int i = 1; i < n - 1; i++) {
                long val = premin[i - 1];
                val = Math.min(val, sufmin[i + 1]);
                val = Math.min(val, a[n - 1]);

                val += b[i];

                if (val < a[i]) {
                    out.println("No");
                    continue t;
                }

                val -= b[i];
                val += a[i];

                if (val < b[i]) {
                    out.println("No");
                    continue t;
                }
            }

            if (premin[n - 1] < a[n - 1]) {
                out.println("No");
                continue;
            }

            out.println("Yes");
        }
    }

    long inf = 1000000000000l;
}
