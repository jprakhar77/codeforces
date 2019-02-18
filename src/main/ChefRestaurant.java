package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class ChefRestaurant {
    class pair {
        int s;
        int e;

        public pair(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        pair[] pairs = new pair[n];
        for (int i = 0; i < n; i++) {
            int l = in.nextInt();
            int r = in.nextInt();

            pairs[i] = new pair(l, r);
        }

        Arrays.sort(pairs, (p1, p2) -> p1.s - p2.s);

        for (int i = 0; i < m; i++) {
            int p = in.nextInt();

            int lo = 0;
            int hi = n - 1;

            int ans = n;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                if (pairs[mid].e > p) {
                    ans = Math.min(ans, mid);
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            if (ans == n) {
                out.println(-1);
            } else {
                if (pairs[ans].s <= p) {
                    out.println(0);
                } else {
                    out.println(pairs[ans].s - p);
                }
            }
        }
    }
}
