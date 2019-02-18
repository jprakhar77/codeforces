package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class SmallestMissingNumbers {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long m = in.nextInt();

        Long[] a = new Long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }

        Arrays.sort(a);

        long found = Math.min(m, a[0] - 1);

        long ans = -1;

        if (found >= m) {
            long last = m;
            ans = (last * (last + 1)) / 2;
            out.println(ans);
            return;
        }

        long presum = a[0];

        for (int i = 1; i < n; i++) {
            found += a[i] - a[i - 1] - 1;
            if (found >= m) {
                long last = a[i] - (found - m + 1);
                ans = (last * (last + 1)) / 2;
                ans -= presum;
                break;
            }
            presum += a[i];
        }

        if (found < m) {
            long last = a[n - 1] + m - found;
            ans = (last * (last + 1)) / 2;
            ans -= presum;
        }


        out.println(ans);
    }
}
