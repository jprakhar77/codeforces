package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DGridGame {
    class pair {
        int x;
        int y;

        public pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            pair pair = (pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int h = in.nextInt();
        int w = in.nextInt();
        int n = in.nextInt();

        TreeSet[] pairs = new TreeSet[h];

        for (int i = 0; i < h; i++) {
            pairs[i] = new TreeSet();
        }

        for (int i = 0; i < n; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;

            pairs[x].add(y);
        }

        Set<Integer> csa = new HashSet<>();
        Set<Integer> csr = new HashSet<>();

        csa.add(0);
        csr.add(0);

        for (int i = 1; i < h; i++) {
            TreeSet<Integer> cts = (TreeSet<Integer>) pairs[i];
            for (Integer num : cts) {
                if (csa.contains(num)) {
                    out.println(i);
                    return;
                }
            }

            Set<Integer> ns = new HashSet<>();

            for (Integer num : csr) {
                if (!cts.contains(num + 1) && num + 1 < w) {
                    csa.add(num + 1);
                    if (num + 2 < w && !csa.contains(num + 2)) {
                        ns.add(num + 1);
                    }
                } else if (num + 1 < w) {
                    ns.add(num);
                }
            }

            csr = ns;
        }

        out.println(h);
    }

    boolean isv(int x, int y) {
        if (x >= y)
            return true;
        return false;
    }
}
