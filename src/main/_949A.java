package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _949A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        List<List<Integer>> ans = new ArrayList<>();
        TreeSet<Integer> z = new TreeSet<>();
        TreeSet<Integer> o = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (o.isEmpty()) {
                    List<Integer> l = new ArrayList<>();
                    l.add(i + 1);
                    ans.add(l);
                    z.add(ans.size() - 1);
                } else {
                    int oi = o.first();
                    ans.get(oi).add(i + 1);
                    z.add(oi);
                    o.remove(oi);
                }
            } else {
                if (z.isEmpty()) {
                    out.println(-1);
                    return;
                } else {
                    int zi = z.first();
                    ans.get(zi).add(i + 1);
                    o.add(zi);
                    z.remove(zi);
                }
            }
        }

        if (!o.isEmpty()) {
            out.println(-1);
            return;
        }

        StringBuilder sans = new StringBuilder();
        sans.append(ans.size());
        sans.append(System.lineSeparator());

        for (int i = 0; i < ans.size(); i++) {
            List<Integer> l = ans.get(i);
            sans.append(l.size() + " ");
            for (int j = 0; j < l.size(); j++) {
                sans.append(l.get(j) + " ");
            }
            sans.append(System.lineSeparator());
        }

        out.println(sans);
    }
}
