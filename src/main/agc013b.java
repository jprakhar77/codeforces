package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class agc013b {
    List[] g;
    boolean[] v;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            g[a].add(b);
            g[b].add(a);
        }

        v = new boolean[n];

        dfs(0);

        v = new boolean[n];

        dfs2(le);

        v = new boolean[n];

        for (int num : ans) {
            v[num] = true;
        }

        int sz = 0;

        isd = false;
        dfs3(0);
        for (int num : ans) {
            sz++;
        }

        out.println(sz);
        for (int num : ans) {
            out.print((num + 1) + " ");
        }
    }

    boolean isd = false;
    boolean zf = false;
    int ef = 0;

    void dfs2(int u) {
        if (isd)
            return;

        v[u] = true;
        ans.add(u);
        if (ans.contains(0)) {
            isd = true;
            return;
        }

        for (int ve : (List<Integer>) g[u]) {
            if (!v[ve]) {
                dfs2(ve);
            }
        }

        if (!isd) {
            ans.remove(u);
        }
    }

    void dfs3(int u) {
        if (isd)
            return;

        v[u] = true;
        if (u != 0)
            ans.add(u);

        int c = 0;
        for (int ve : (List<Integer>) g[u]) {
            if (!v[ve]) {
                c++;
                dfs3(ve);
            }
        }

        if (c == 0) {
            isd = true;
            return;
        }
    }

    LinkedHashSet<Integer> ans = new LinkedHashSet<>();
    LinkedHashSet<Integer> ans2 = new LinkedHashSet<>();

    Set<Integer> l = new HashSet<>();
    int le = -1;

    void dfs(int u) {
        v[u] = true;

        int c = 0;
        for (int ve : (List<Integer>) g[u]) {
            if (!v[ve]) {
                c++;
                if (u == 0) {
                    ef++;
                }
                dfs(ve);
            }
        }

        if (c == 0) {
            le = u;
            l.add(u);
        }
        if (g[u].size() == 1 && u == 0) {
            l.add(u);
        }
    }
}
