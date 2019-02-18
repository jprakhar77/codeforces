package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _274A {
    long inf = (long) 1e18;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long k = in.nextInt();

        if (k == 1) {
            out.println(n);
            return;
        }

        long[] a = new long[n];

        Set<Long> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            s.add(a[i]);
        }

        Arrays.sort(a);

        for (int i = 0; i < n; i++) {
            long num = a[i];

            if (s.contains(num)) {
                if (s.contains(k * num)) {
                    s.remove(k * num);
                }
            }
        }

        out.println(s.size());
    }
}
