package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class CLunarNewYearAndNumberDivision {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        Long[] a = new Long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }

        Arrays.sort(a);

        long ans = 0;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            long sum = a[i] + a[j];

            ans += (sum * sum);
        }

        out.println(ans);
    }
}
