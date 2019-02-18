package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CStreamline {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        Integer[] a = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Arrays.sort(a);

        List<Integer> l = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            l.add(a[i] - a[i - 1]);
        }

        l.sort(Comparator.reverseOrder());

        int ans = a[n - 1] - a[0];
        int d= m - 1;

        for (int i = 0; i < Math.min(d, l.size()); i++)
        {
            ans -= l.get(i);
        }

        out.println(ans);
    }
}
