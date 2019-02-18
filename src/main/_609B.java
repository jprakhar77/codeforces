package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _609B {
    class wb {
        int w;
        int b;
        int i;

        public wb(int w, int b, int i) {
            this.w = w;
            this.b = b;
            this.i = i;
        }
    }

    class vc {
        int v;
        int c;

        public vc(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }

    class fe {
        int u;
        int v;

        public fe(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        wb[] wbs = new wb[m];

        for (int i = 0; i < m; i++) {
            int w = in.nextInt();
            int b = in.nextInt();

            wbs[i] = new wb(w, b, i);
        }

        Arrays.sort(wbs, (wb1, wb2) ->
        {
            if (wb1.w == wb2.w) {
                return wb2.b - wb1.b;
            } else {
                return wb1.w - wb2.w;
            }
        });

        PriorityQueue<vc> pq = new PriorityQueue<>((vc1, vc2) -> vc1.c - vc2.c);

        TreeSet<Integer> fvs = new TreeSet<>();

        for (int i = 3; i <= n; i++) {
            fvs.add(i);
        }

        if (wbs[0].b == 0) {
            out.println(-1);
            return;
        }

        pq.add(new vc(1, 1));
        pq.add(new vc(2, 1));

        int fer = m - n + 1;

        ArrayDeque<fe> fes = new ArrayDeque<>();

        fe[] ans = new fe[m];

        Set<Integer> uv = new HashSet<>();
        uv.add(1);
        uv.add(2);

        ans[wbs[0].i] = new fe(1, 2);

        for (int i = 1; i < m; i++) {
            if (wbs[i].b == 0) {
                if (fes.size() == 0) {
                    out.println(-1);
                    return;
                }
                fe fe = fes.removeFirst();
                //out.println(fe.u + " " + fe.v);
                ans[wbs[i].i] = new fe(fe.u, fe.v);
                fer--;
            } else {
                int fv = fvs.pollFirst();
                vc tv = pq.poll();
                //out.println(fv + " " + tv.v);
                ans[wbs[i].i] = new fe(fv, tv.v);
                pq.add(new vc(tv.v, tv.c + 1));
                pq.add(new vc(fv, 1));

                for (int v : uv) {
                    if (fes.size() >= fer) {
                        break;
                    }
                    if (v != tv.v) {
                        fes.addLast(new fe(fv, v));
                    }
                }

                uv.add(fv);
            }
        }

        for (int i = 0; i < m; i++) {
            out.println(ans[i].u + " " + ans[i].v);
        }
    }
}
