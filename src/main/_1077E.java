package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class _1077E {
    public void solve(long testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        Map<Integer, Integer> fm = new HashMap<>();

        for (int val : a) {
            fm.merge(val, 1, (x, y) -> x + y);
        }

        int max = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int key : fm.keySet()) {
            int val = fm.get(key);
            tm.merge(val, 1, (x, y) -> x + y);
            max = Math.max(max, val);
        }

        long ans = 0;
        for (int i = 1; i <= max; i++) {
            long ca = 0;

            Map<Integer, Integer> fc = new HashMap<>();
            for (int j = i; ; j *= 2) {
                Integer ceil = tm.ceilingKey(j);

                if (ceil != null && tm.get(ceil) > fc.getOrDefault(ceil, 0)) {
                    ca += j;
                    fc.merge(ceil, 1, (x, y) -> x + y);
                } else {
                    break;
                }
            }

            ans = Math.max(ans, ca);
        }

        out.print(ans);

    }
}
