package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class BAverageSuperheroGangPower {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();
        long k = in.nextInt();

        int m = in.nextInt();

        Integer[] a = new Integer[n];

        long tot = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            tot += a[i];
        }

        Arrays.sort(a, Comparator.reverseOrder());

        double ans = ((double) tot) / n;

        long sum = 0;
        //n == 1
        for (int i = 0; i < n; i++) {
            sum += a[i];

            if (n - i - 1 <= m) {
                long ro = m - (n - i - 1);

                ro = Math.min(ro, (i + 1) * k);

                long nsum = sum + ro;

                ans = Math.max(ans, ((double) nsum) / (i + 1));
            }
        }

        out.println(ans);
    }
}
