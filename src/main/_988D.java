package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _988D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        Integer[] a = new Integer[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Arrays.sort(a);

        Set<Long> s = new HashSet<>();

        for (Integer val : a) {
            s.add((long) val);
        }

        int ans = 0;
        long st = -1;
        int cnt = 0;
        long gap = 0;
        for (long num : a) {
            if (ans == 3)
                break;
            for (int d = 0; d < 32; d++) {
                long val = 1l << d;

                int ca = 0;
                for (long i = num, j = 0; j < 3; i += val, j++) {
                    if (s.contains(i)) {
                        ca++;
                    } else {
                        break;
                    }
                }

                if (ca > ans) {
                    st = num;
                    cnt = ca;
                    gap = val;
                    ans = ca;
                }
            }
        }

        out.println(ans);

        for (long i = st, j = 0; j < cnt; j++, i += gap) {
            out.print(i + " ");
        }
    }
}
