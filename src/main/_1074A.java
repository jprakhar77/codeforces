package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _1074A {
    class h {
        int x;
        int y;
        int i;

        public h(int x, int y, int i) {
            this.x = x;
            this.y = y;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List<Integer> v = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            v.add(x);
        }

        n++;
        v.add((int) 1e9);

        v.sort((x, y) -> x - y);

        List<h> hs = new ArrayList<>();
        List<h> he = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int x1 = in.nextInt();
            int x2 = in.nextInt();

            x1--;
            int y = in.nextInt();

            hs.add(new h(x1, y, i));
            he.add(new h(x2, y, i));
        }
        hs.sort((x, y) -> x.x - y.x);
        he.sort((x, y) -> x.x - y.x);

        int st = 0;

        Set<Integer> cs = new HashSet<>();

        int csi = 0;
        int cei = 0;

        int ans = m;

        Map<Integer, Integer> map = new HashMap<>();

        Set<Integer> ex = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int e = v.get(i);
            while (csi < m && hs.get(csi).x <= st) {
                if (!ex.contains(hs.get(csi).i)) {
                    if (i == 0)
                        cs.add(hs.get(csi).i);
                    map.merge(hs.get(csi).y, 1, (x, y) -> x + y);
                } else
                    ex.remove(hs.get(csi).i);
                csi++;
            }

            while (cei < m && he.get(cei).x < e) {
                if (!cs.contains(he.get(cei).i)) {
                    ex.add(he.get(cei).i);
                } else {
                    cs.remove(he.get(cei).i);
                    map.merge(he.get(cei).y, -1, (x, y) -> x + y);
                    if (map.get(he.get(cei).y) == 0) {
                        map.remove(he.get(cei).y);
                    }
                }
                cei++;
            }

            ans = Math.min(ans, i + cs.size());

            st = e;
        }

        out.println(ans);

    }
}
