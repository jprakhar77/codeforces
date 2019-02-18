package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.Objects;

public class BNewYearAndTheTreasureGeolocation {
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
        int n = in.nextInt();

        pair[] coo = new pair[n];

        for (int i = 0; i < n; i++) {
            coo[i] = new pair(in.nextInt(), in.nextInt());
        }

        pair[] clu = new pair[n];

        for (int i = 0; i < n; i++) {
            clu[i] = new pair(in.nextInt(), in.nextInt());
        }

        pair[] tre = new pair[n];

        for (int i = 0; i < n; i++) {
            tre[i] = new pair(coo[0].x + clu[i].x, coo[0].y + clu[i].y);
        }

        for (int i = 0; i < n; i++) {
            HashMap<pair, Integer> clum = new HashMap<>();
            for (int j = 0; j < n; j++) {
                clum.merge(clu[j], 1, (c1, c2) -> c1 + c2);
            }

            pair ct = tre[i];

            boolean poss = true;
            for (int j = 0; j < n; j++) {
                pair cc = coo[j];
                int rx = ct.x - cc.x;
                int ry = ct.y - cc.y;

                pair rp = new pair(rx, ry);
                if (clum.containsKey(rp)) {
                    clum.merge(rp, -1, (x, y) -> x + y);

                    if (clum.get(rp) == 0) {
                        clum.remove(rp);
                    }
                } else {
                    poss = false;
                    break;
                }
            }

            if (poss) {
                out.println(ct.x + " " + ct.y);
                return;
            }
        }
    }
}
