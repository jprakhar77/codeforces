package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class _1076E {

    class DfsSubtree {

        int[] dfsOrder;
        int[] h;
        int[] subStart;
        int[] subEnd;

        int n;
        List[] g;

        public DfsSubtree(List[] g) {
            this.n = g.length;
            this.g = g;
            this.dfsOrder = new int[n];
            this.h = new int[n];
            this.subStart = new int[n];
            this.subEnd = new int[n];
        }

        HashMap<Integer, TreeMap<Integer, Long>> hm = new HashMap<>();

        int dfs(int u, int p, int st, int ch) {
            dfsOrder[st] = u;
            h[u] = ch; // Store as per per question
            subStart[u] = st;

            if (!hm.containsKey(ch)) {
                hm.put(ch, new TreeMap<>());
            }

            hm.get(ch).put(st, 0l);

            st++;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v == p)
                    continue;

                st = dfs(v, u, st, ch + 1);
            }

            subEnd[u] = st - 1;

            return st;
        }

        boolean isInSubtree(int u, int v) {
            if (h[v] >= subStart[u] && h[v] <= subEnd[u]) {
                return true;
            }

            return false;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        List[] g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        int m = in.nextInt();

        DfsSubtree sub = new DfsSubtree(g);
        sub.dfs(0, -1, 0, 0);

        HashMap<Integer, TreeMap<Integer, Long>> hm = sub.hm;

        for (int i = 0; i < m; i++) {
            int v = in.nextInt();
            v--;

            int d = in.nextInt();

            long x = in.nextInt();

            int ch = sub.h[v];

            int st = sub.subStart[v];

            hm.get(ch).merge(st, x, (a, b) -> a + b);

            Integer ceil = hm.get(ch).ceilingKey(st + 1);

            if (ceil != null) {
                hm.get(ch).merge(ceil, -x, (a, b) -> a + b);
            }

            int nh = ch + d + 1;

            if (!hm.containsKey(nh))
                continue;

            TreeMap<Integer, Long> cm = hm.get(nh);

            Integer ceil1 = cm.ceilingKey(st);
            Integer ceil2 = cm.ceilingKey(sub.subEnd[v] + 1);

            if (ceil1 != null) {
                cm.merge(ceil1, -x, (a, b) -> a + b);

                if (ceil2 != null) {
                    cm.merge(ceil2, x, (a, b) -> a + b);
                }
            }
        }

        for (int h : hm.keySet()) {
            long pv = 0;
            TreeMap<Integer, Long> cm = hm.get(h);
            for (int val : cm.keySet()) {
                cm.merge(val, pv, (a, b) -> a + b);
                pv = cm.get(val);
            }
        }

        long[] ans = new long[n];

        dfs(0, -1, ans, sub, g);

        for (int i = 0; i < n; i++) {
            out.print(ans[i] + " ");
        }
    }

    void dfs(int u, int p, long[] ans, DfsSubtree sub, List[] g) {
        if (p == -1) {
            ans[u] = sub.hm.get(sub.h[u]).get(sub.subStart[u]);
        } else {
            ans[u] = sub.hm.get(sub.h[u]).get(sub.subStart[u]) + ans[p];
        }

        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                dfs(v, u, ans, sub, g);
            }
        }
    }
}
