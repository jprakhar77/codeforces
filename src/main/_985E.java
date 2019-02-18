package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class _985E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int d = in.nextInt();

        Integer[] a = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Arrays.sort(a);

        int[] dp = new int[n + 1];

        dp[0] = 1;

        int[] pre = new int[n + 1];

        pre[0] = 1;

        for (int i = 1; i < k; i++) {
            pre[i] = pre[i - 1];
        }

        for (int i = k; i <= n; i++) {

            int p2 = i - k;

            if (a[p2] + d < a[i - 1]) {
                pre[i] = pre[i - 1];
                continue;
            }

            int lo = 0;
            int hi = p2;

            int p1 = hi;

            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                if (a[mid] + d >= a[i - 1]) {
                    p1 = Math.min(p1, mid);
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            int pre2 = pre[p2];


            if (p1 > 0) {
                pre2 -= pre[p1 - 1];
            }

            if (pre2 > 0) {
                dp[i] = 1;
            }

            pre[i] = pre[i - 1] + dp[i];
        }

        if (dp[n] == 1) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }
}
