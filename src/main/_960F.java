package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Map;
import java.util.TreeMap;

public class _960F {
    class e {
        int a;
        int b;
        int w;


        public e(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        e[] es = new e[m];

        for (int i = 0; i < m; i++) {

            es[i] = new e(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }

        TreeMap[] tms = new TreeMap[n];

        for (int i = 0; i < n; i++) {
            tms[i] = new TreeMap<Integer, Integer>();
        }

        for (int i = 0; i < m; i++) {

            int a = es[i].a;
            int b = es[i].b;
            int w = es[i].w;

            Map.Entry<Integer, Integer> be = tms[a].floorEntry(w - 1);

            int nv = be == null ? 1 : be.getValue() + 1;

            Map.Entry<Integer, Integer> be2 = tms[b].floorEntry(w);

            if (be2 != null && be2.getValue() >= nv) {
                continue;
            }

            Map.Entry<Integer, Integer> ce = null;
            while ((ce = tms[b].ceilingEntry(w + 1)) != null && ce.getValue() <= nv) {
                tms[b].remove(ce.getKey());
            }

            tms[b].put(w, nv);

        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, tms[i].size() > 0 ? (int) tms[i].lastEntry().getValue() : 0);
        }

        out.println(ans);
    }
}
