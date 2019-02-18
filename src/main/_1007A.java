package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.TreeMap;

public class _1007A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        TreeMap<Integer, Integer> tm = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            tm.merge(a[i], 1, (x, y) -> x + y);
        }

        Arrays.sort(a);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            Integer num = tm.ceilingKey(a[i] + 1);

            if (num != null) {
                ans++;
                tm.merge(num, -1, (x, y) -> x + y);
                if (tm.get(num) == 0) {
                    tm.remove(num);
                }
            }
        }

        out.println(ans);
    }
}
