package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _466E {
    public class DSU<T> {

        Map<T, T> parent = new HashMap<>();

        T createSet(T x) {
            parent.put(x, x);
            return x;
        }

        T findSet(T x) {
            T par = parent.get(x);
            if (!x.equals(par)) {
                parent.put(x, findSet(par));
                return parent.get(x);
            }
            return par;
        }

        T mergeSets(T x, T y) {
            T rx = parent.get(x);
            T ry = parent.get(y);

            T fp = null;

            parent.put(rx, ry);
            fp = ry;

            return fp;
        }
    }


    public class DfsSubtree {

        int[] dfsOrder;
        int[] loc;
        int[] subStart;
        int[] subEnd;
        int[] height;

        int n;
        List[] g;

        public DfsSubtree(int n, List[] g) {
            this.n = n;
            this.g = g;
            dfsOrder = new int[n + 1];
            loc = new int[n + 1];
            subStart = new int[n + 1];
            subEnd = new int[n + 1];
            height = new int[n + 1];
        }

        int dfs(int u, int p, int st, int h) {
            dfsOrder[st] = u;
            loc[u] = st;
            subStart[u] = st;
            height[u] = h;
            st++;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (v == p)
                    continue;

                st = dfs(v, u, st, h + 1);
            }

            subEnd[u] = st - 1;

            return st;
        }

        boolean isInSubtree(int u, int v) {
            if (loc[v] >= subStart[u] && loc[v] <= subEnd[u]) {
                return true;
            }

            return false;
        }
    }

    class request {
        int x;
        int no;
        int doc;

        public request(int x, int no, int doc) {
            this.x = x;
            this.no = no;
            this.doc = doc;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        int dpn = 1;

        DSU<Integer> dsu = new DSU<>();

        for (int i = 1; i <= n; i++) {
            dsu.createSet(i);
        }

        List<request> requests = new ArrayList<>();

        int[] docx = new int[m + 1];
        int[] docp = new int[m + 1];

        int[] p = new int[n + 1];

        int rn = 1;
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();

            if (t == 1) {
                int x = in.nextInt();
                int y = in.nextInt();

                p[x] = y;

                Integer par = dsu.mergeSets(x, y);
            } else if (t == 2) {
                int x = in.nextInt();

                int par = dsu.findSet(x);

                docx[dpn] = x;
                docp[dpn] = par;

                dpn++;
            } else {
                int x = in.nextInt();
                int j = in.nextInt();

                requests.add(new request(x, rn, j));
                rn++;
            }
        }

        List[] g = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 1; i <= n; i++) {
            if (p[i] != 0) {
                g[p[i]].add(i);
            }
        }

        DfsSubtree dfs = new DfsSubtree(n, g);

        int st = 1;
        for (int i = 1; i <= n; i++) {
            int rep = dsu.findSet(i);
            if (dfs.loc[rep] == 0) {
                st = dfs.dfs(rep, -1, st, 1);
            }
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < requests.size(); i++) {
            request req = requests.get(i);

            int doc = req.doc;
            int x = req.x;

            boolean issub = dfs.isInSubtree(x, docx[doc]);

            if (!issub) {
                ans.append("NO");
                ans.append(System.lineSeparator());
                continue;
            }

            int hop = dfs.height[docp[doc]];

            if (hop <= dfs.height[x]) {
                ans.append("YES");
                ans.append(System.lineSeparator());
            } else {
                ans.append("NO");
                ans.append(System.lineSeparator());
            }
        }

        out.println(ans.toString());
    }
}
