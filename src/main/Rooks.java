package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Rooks {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        Set<Integer> rd = new HashSet<>();

        TreeSet<Integer> ts = new TreeSet<>();
        while (t-- > 0) {
            int n = in.nextInt();

            int k = in.nextInt();

            rd.clear();
            ts.clear();

            for (int i = 1; i <= n; i++) {
                ts.add(i);
            }

            for (int i = 0; i < k; i++) {
                int r = in.nextInt();
                int c = in.nextInt();

                rd.add(r);
                ts.remove(c);
            }

            out.print(n - k);
            for (int i = 1; i <= n; i++) {
                if (!rd.contains(i)) {
                    int cc = ts.first();
                    out.print(" " + i + " " + cc);
                    ts.remove(cc);
                }
            }

            out.println();
        }
    }
}
