package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;

public class _1041F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int y1 = in.nextInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int m = in.nextInt();

        int y2 = in.nextInt();

        int[] b = new int[m];

        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }

        int ans = 2;

        for (int l = 0; (1 << l) <= 1000000000; l++) {
            int dx = (1 << l);

            int cl = 2 * dx;

            HashMap<Integer, Integer> am = new HashMap<>();

            int ca = 0;
            for (int num : a) {
                am.merge(num % cl, 1, (x, y) -> x + y);
            }

            HashMap<Integer, Integer> bm = new HashMap<>();
            for (int num : b) {
                bm.merge(num % cl, 1, (x, y) -> x + y);
            }

            for (int num : am.keySet()) {
                ca = Math.max(ca, am.get(num) + bm.getOrDefault((num + dx) % cl, 0));
            }

            for (int num : bm.keySet()) {
                ca = Math.max(ca, bm.get(num) + am.getOrDefault((num + dx) % cl, 0));
            }

            ans = Math.max(ans, ca);
        }

        out.println(ans);
    }
}
