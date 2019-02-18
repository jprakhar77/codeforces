package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1073D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        long t = in.nextLong();

        int[] a = new int[n];

        in.readArray(a, n, 0);

        long sum = 0;
        for (int val : a) {
            sum += val;
        }

        int cc = n;

        long sumrem = t;

        long ans = 0;
        while (cc > 0) {
            if (sumrem >= sum) {
                long time = sumrem / sum;
                ans += time * cc;
                sumrem -= sum * time;
            }

            for (int i = 0; i < n; i++) {
                if (a[i] == 0)
                    continue;
                if (a[i] <= sumrem) {
                    ans++;
                    sumrem -= a[i];
                } else {
                    sum -= a[i];
                    a[i] = 0;
                    cc--;
                }
            }
        }

        out.println(ans);
    }
}
