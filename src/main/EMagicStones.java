package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EMagicStones {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] c = in.nextIntArray(n);
        int[] t = in.nextIntArray(n);

        if (c[0] != t[0] || c[n - 1] != t[n - 1]) {
            out.println("No");
            return;
        }
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            l1.add(c[i] - c[i - 1]);
            l2.add(t[i] - t[i - 1]);
        }

        l1.sort(Comparator.naturalOrder());
        l2.sort(Comparator.naturalOrder());

        for (int i = 0; i < n - 1; i++) {
            if (!l1.get(i).equals(l2.get(i))) {
                out.println("No");
                return;
            }
        }

        out.println("Yes");
    }
}
