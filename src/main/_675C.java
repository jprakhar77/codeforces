package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class _675C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        //hel_675D_67
        int n = in.nextInt();

        int[] a = new int[2 * n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        for (int i = n; i < 2 * n; i++) {
            a[i] = a[i - n];
        }

        long[] pre = new long[2 * n];

        pre[0] = a[0];

        for (int i = 1; i < 2 * n; i++) {
            pre[i] = a[i] + pre[i - 1];
        }

        Map<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.merge(pre[i], 1, (x, y) -> x + y);
        }

        int ans = n - map.get(0l);
        for (int i = n; i < 2 * n; i++) {
            map.merge(pre[i], 1, (x, y) -> x + y);
            map.merge(pre[i - n], -1, (x, y) -> x + y);
            if (map.get(pre[i - n]) <= 0) {
                map.remove(pre[i - n]);
            }

            ans = Math.min(ans, n - map.getOrDefault(pre[i - n], 0));
        }

        out.print(ans);
    }
}
