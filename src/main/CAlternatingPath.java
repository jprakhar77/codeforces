package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.Map;

public class CAlternatingPath {
    class DsuInteger {

        public DsuInteger(int n) {
            this.n = n;
            this.parent = new int[n];
            this.rank = new int[n];
            this.size = new int[n];
        }

        int[] parent;
        int[] rank;
        int[] size;
        int n;


        int createSet(int x) {
            parent[x] = x;
            rank[x] = 0;
            size[x] = 1;
            return x;
        }

        int findSet(int x) {
            int par = parent[x];
            if (x != par) {
                parent[x] = findSet(par);
                return parent[x];
            }
            return par;
        }

        int mergeSets(int x, int y) {
            int rx = findSet(x);
            int ry = findSet(y);

            if (rx == ry) {
                return rx;
            }

            int fp = -1;

            if (rank[rx] > rank[ry]) {
                parent[ry] = rx;
                fp = rx;
            } else {
                parent[rx] = ry;
                fp = ry;
            }

            size[fp] = size[rx] + size[ry];

            if (rank[rx] == rank[ry]) {
                rank[ry] = rank[ry] + 1;
            }

            return fp;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int h = in.nextInt();
        int w = in.nextInt();

        String[] g = new String[h];

        for (int i = 0; i < h; i++) {
            g[i] = in.next();
        }

        DsuInteger dsu = new
                DsuInteger(h * w);

        for (int i = 0; i < h * w; i++) {
            dsu.createSet(i);
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int ci = i * w + j;
                if (i > 0) {
                    if (g[i].charAt(j) != g[i - 1].charAt(j)) {
                        int pr = (i - 1) * w + j;
                        dsu.mergeSets(ci, pr);
                    }
                }

                if (j > 0) {
                    if (g[i].charAt(j) != g[i].charAt(j - 1)) {
                        int pr = i * w + j - 1;
                        dsu.mergeSets(ci, pr);
                    }
                }
            }
        }

        Map<Integer, Integer> bm = new HashMap<>();
        Map<Integer, Integer> wm = new HashMap<>();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int ci = i * w + j;

                int rep = dsu.findSet(ci);

                if (!bm.containsKey(rep)) {
                    bm.put(rep, 0);
                    wm.put(rep, 0);
                }

                if (g[i].charAt(j) == '#')
                    bm.merge(rep, 1, (x, y) -> x + y);
                else
                    wm.merge(rep, 1, (x, y) -> x + y);
            }
        }


        long ans = 0;
        for (int key : bm.keySet()) {
            long bs = bm.get(key);
            long ws = wm.get(key);

            ans += bs * ws;
        }

        out.println(ans);
    }

}
