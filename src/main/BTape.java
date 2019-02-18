package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BTape {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int k = in.nextInt();

        int[] a = in.nextIntArray(n);

        List<Integer> l = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            l.add(a[i] - a[i - 1] - 1);
        }

        long ans = a[n - 1] - a[0] + 1;

        l.sort(Comparator.reverseOrder());
        for (int i = 0; i < Math.min(k - 1, l.size()); i++)
        {
            ans -= l.get(i);
        }

        out.println(ans);
    }
}
