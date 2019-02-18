package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class arc_tenka_d {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int ss = -1;
        int nos = -1;
        for (int i = 1; i * (i + 1) <= 2 * n; i++) {
            if ((i * (i + 1)) / 2 == n) {
                ss = i;
                nos = i + 1;
                break;
            }
        }

        if (ss == -1) {
            out.println("No");
            return;
        }

        out.println("Yes");

        out.println(nos);

        int nn = 1;

        List<TreeSet<Integer>> tsl = new ArrayList<>();

        int[] cnt = new int[n + 1];

        int si = 0;
        for (int i = 0; i < nos; i++) {
            TreeSet<Integer> ts = new TreeSet<>();
            for (int j = si; j < i; j++) {
                TreeSet<Integer> cs = tsl.get(j);
                if (cs.size() > 0) {
                    int first = cs.first();
                    cs.remove(first);
                    ts.add(first);
                } else {
                    si = j + 1;
                }
            }

            int c = 0;
            while (ts.size() < ss) {
                c++;
                ts.add(nn);
                nn++;
            }

            out.print(ts.size() + " ");

            for (int val : ts) {
                out.print(val + " ");
            }

            out.println();

            for (int j = 0; j < ss - c; j++) {
                int first = ts.first();
                ts.remove(first);
            }

            tsl.add(ts);
        }
    }
}
