package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class BCollatzProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int s = in.nextInt();

        Set<Long> set = new HashSet<>();

        set.add((long) s);

        long pn = s;
        for (int i = 2; ; i++) {
            long num = -1;
            if (pn % 2 == 0) {
                num = pn / 2;
            } else {
                num = 3 * pn + 1;
            }

            if (set.contains(num)) {
                out.println(i);
                return;
            }

            set.add(num);
            pn = num;
        }
    }
}
