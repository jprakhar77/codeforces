package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _612E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            p[i] = in.nextInt();
        }

        boolean[] vis = new boolean[n + 1];

        Map<Integer, List<List<Integer>>> csm = new HashMap<>();
        List<List<Integer>> oc = new ArrayList<>();

        int[] ans = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (p[i] == i) {
                ans[i] = i;
                vis[i] = true;
                continue;
            }

            if (!vis[i]) {
                List<Integer> nc = new ArrayList<>();
                dfs(i, p, vis, nc);

                if (nc.size() % 2 == 0) {
                    if (!csm.containsKey(nc.size())) {
                        csm.put(nc.size(), new ArrayList<>());
                    }
                    csm.get(nc.size()).add(nc);
                } else {
                    oc.add(nc);
                }
            }

        }

        for (Integer size : csm.keySet()) {
            if (csm.get(size).size() % 2 == 1) {
                out.println("-1");
                return;
            }
        }

        for (int i = 0; i < oc.size(); i++) {
            List<Integer> cl = oc.get(i);

            for (int j = 0, k = cl.size() / 2 + 1; j < cl.size() / 2; j++, k++) {
                ans[cl.get(j)] = cl.get(k);
                ans[cl.get(k)] = cl.get(j + 1);
            }
            ans[cl.get(cl.size() / 2)] = cl.get(0);
        }

        for (Integer size : csm.keySet()) {
            List<List<Integer>> cll = csm.get(size);

            for (int i = 0; i < cll.size() - 1; i += 2) {
                List<Integer> cl1 = cll.get(i);
                List<Integer> cl2 = cll.get(i + 1);

                for (int j = 0; j < size - 1; j++) {
                    ans[cl1.get(j)] = cl2.get(j);
                    ans[cl2.get(j)] = cl1.get(j + 1);
                }
                ans[cl1.get(size - 1)] = cl2.get(size - 1);
                ans[cl2.get(size - 1)] = cl1.get(0);
            }
        }

        for (int i = 1; i <= n; i++) {
            out.print(ans[i] + " ");
        }
    }

    void dfs(int u, int[] p, boolean[] vis, List<Integer> nc) {
        vis[u] = true;
        nc.add(u);

        if (!vis[p[u]])
            dfs(p[u], p, vis, nc);

    }
}
