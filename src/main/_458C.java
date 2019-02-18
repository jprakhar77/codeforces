package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _458C {
    class v {
        int p;
        int c;

        public v(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    int m = 100001;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        pm = new PriorityQueue[m + 1];

        par = new HashSet<>();

        for (int i = 0; i <= m; i++) {
            pm[i] = new PriorityQueue<Integer>((a, b) -> a - b);
        }

        vs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int p = in.nextInt();
            int c = in.nextInt();

            v v = new v(p, c);

            if (v.p != 0)
                vs.add(v);
            //pm[p].merge(c, 1, (x, y) -> (long) x + (long) y);

            pm[p].add(c);

            par.add(p);
        }

        par.remove(0);

        os = pm[0].size();

        ans = Long.MAX_VALUE;

        if (os > n / 2) {
            out.println(0);
            return;
        }

        int lo = Math.max(1, os);
        int hi = n / 2 + 1;

        while (lo <= hi) {

            int i = (lo + hi) >> 1;

            long val = check(i);
            //if (r || ic == max + 1) {
            //long vp = i > 1 ? check(i - 1) : Long.MAX_VALUE;
            long vn = check(i + 1);

            if (val < vn) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
            //} else {
            //    lo = i + 1;
            //}
        }

        out.println(ans);
    }

    boolean r;
    int ic;
    int max;
    Set<Integer> par;
    PriorityQueue[] pm;
    List<v> vs;
    int os;
    long ans = 0;

    long check(int i) {
        for (int p : par) {
            pm[p] = new PriorityQueue<Integer>((a, b) -> a - b);
        }

        for (v v : vs) {
            pm[v.p].add(v.c);
        }

        long c = 0;
        int ic = os;

        int max = 0;
        for (int p : par) {
            while (pm[p].size() >= i) {
                c += (int) pm[p].poll();
                ic++;
            }
            max = Math.max(max, pm[p].size());
        }

        boolean r = false;
        if (ic < i) {
            PriorityQueue<v> vp = new PriorityQueue<>((v1, v2) -> v1.c - v2.c);
            for (int p : par) {
                vp.add(new v(p, (int) pm[p].poll()));
            }

            while (vp.size() > 0 && ic < i) {
                v pe = vp.poll();

                ic++;
                c += pe.c;

                if (pm[pe.p].size() > 0)
                    vp.add(new v(pe.p, (int) pm[pe.p].poll()));
            }

            r = true;
        }

        if (ic >= i) {
            ans = Math.min(ans, c);
            return c;
        }

        return Long.MAX_VALUE;
    }
}
