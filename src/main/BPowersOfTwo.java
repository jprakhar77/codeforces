package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class BPowersOfTwo {

    Map<Integer, Integer> fm = new HashMap<>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        for (int i = 0; i < n; i++) {
            fm.merge(a[i], 1, (x, y) -> x + y);
        }

        Map<Integer, Set<Integer>> g = new HashMap<>();

        int[] tp = new int[32];

        tp[0] = 1;

        for (int i = 1; i <= 31; i++) {
            tp[i] = tp[i - 1] * 2;
        }

        for (int i = 0; i < n; i++) {
            if (!g.containsKey(a[i])) {
                g.put(a[i], new HashSet<>());
            }

            for (int j = 0; j < 32; j++) {
                int rem = tp[j] - a[i];

                if (rem == a[i]) {
                    continue;
                }
                if (fm.containsKey(rem)) {
                    g.get(a[i]).add(rem);
                }
            }
        }
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        for (int key : fm.keySet()) {
            if (g.get(key).size() == 1) {
                dq.addLast(key);
            }
        }

        int ans = 0;

        while (!dq.isEmpty()) {
            int pe = dq.removeFirst();

            int f = fm.get(pe);
            Set<Integer> set = g.get(pe);
            for (int num : set) {

                int fc = fm.get(num);

                int min = Math.min(fc, f);

                ans += min;

                fm.put(num, fc - min);
                fm.put(pe, f - min);
                //fm.remove(num);

                g.get(num).remove(pe);

                if (g.get(num).size() == 1) {
                    dq.addLast(num);
                }
            }
        }

        for (int key : fm.keySet()) {
            int val = fm.get(key);
            if ((key & (key - 1)) == 0) {
                if (val > 1) {
                    ans += (val / 2);
                }
            }
        }

        out.println(ans);
    }
}
