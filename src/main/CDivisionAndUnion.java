package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CDivisionAndUnion {
    class inte {
        int l;
        int s;
        int i;

        public inte(int l, int s, int i) {
            this.l = l;
            this.s = s;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();

            List<inte> intes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int l = in.nextInt();
                int r = in.nextInt();

                inte i1 = new inte(l, 1, i);
                inte i2 = new inte(r + 1, -1, i);

                intes.add(i1);
                intes.add(i2);
            }

            intes.sort((i1, i2) ->
            {
                if (i1.l == i2.l) {
                    return i1.s - i2.s;
                } else {
                    return i1.l - i2.l;
                }
            });

            Set<Integer> ind = new HashSet<>();
            int id = 0;
            int cr = 0;
            int ins = intes.size();
            boolean poss = false;
            for (int i = 0; i < ins; i++) {
                inte ci = intes.get(i);

                if (ci.s == -1) {
                    cr--;
                    id++;

                    if (cr == 0 && i < ins - 1) {
                        poss = true;
                        break;
                    }
                } else {
                    cr++;
                    ind.add(ci.i);
                }
            }

            if (!poss) {
                out.println(-1);
                continue;
            } else {
                for (int i = 0; i < n; i++) {
                    if (ind.contains(i)) {
                        out.print("1 ");
                    } else {
                        out.print("2 ");
                    }
                }
                out.println();
            }
        }
    }
}
