package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _1044B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();

            List[] g = new List[n];

            in.readUndirectedGraph(g, n, n - 1, 1);

            Set<Integer> k1s = new HashSet<>();

            int k1 = in.nextInt();
            for (int i = 0; i < k1; i++) {
                k1s.add(in.nextInt() - 1);
            }

            Set<Integer> k2s = new HashSet<>();

            int k2 = in.nextInt();
            int my = -1;

            boolean isd = false;
            for (int i = 0; i < k2; i++) {
                int num = in.nextInt() - 1;
                k2s.add(num);
            }

            for (int val : k2s) {
                out.println("B " + (val + 1));
                out.flush();
                my = in.nextInt();

                if (k1s.contains(my - 1)) {
                    out.println("C " + my);
                    out.flush();
                    isd = true;
                }

                break;
            }

            if (isd)
                continue;

            f = -1;
            dfs(my - 1, -1, g, k1s);

            out.println("A " + (f + 1));
            out.flush();

            int se = in.nextInt();

            if (k2s.contains(se - 1)) {
                out.println("C " + (f + 1));
            } else {
                out.println("C -1");
            }

            out.flush();
        }
    }

    int f = -1;

    void dfs(int u, int p, List[] g, Set<Integer> s) {
        if (f == -1 && s.contains(u)) {
            f = u;
            return;
        }
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs(v, u, g, s);
            }
        }
    }
}
