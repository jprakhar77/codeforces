package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class ReductionGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();

            int[] a = in.nextIntArray(n);

            Arrays.sort(a);

            int sum = 0;
            for (int val : a) {
                sum += val;
            }

            int max = a[n - 1];

            if (max <= k || n == 1) {
                out.println(sum);
                continue;
            }

            if (n == 2) {
                if (a[0] <= k) {
                    out.println(sum);
                    continue;
                } else {
                    int ex = a[0] - k;

                    out.println(sum - 2 * ex);
                    continue;
                }
            }

            int remsum = 0;

            for (int i = 0; i < n - 2; i++) {
                if (a[i] > k) {
                    remsum += (a[i] - k);
                }
            }

            int sumnm1 = Math.max(a[n - 2] - k, 0);

            if (remsum >= sumnm1) {

                remsum += sumnm1;

                if (remsum % 2 == 0) {
                    out.println(sum - remsum);
                } else {
                    out.println(sum - remsum - 1);
                }
            }
            else {
                sumnm1 -= remsum;

                int ex = sumnm1;

                out.println(sum - 2 * remsum - 2 * ex);
            }
        }
    }
}
