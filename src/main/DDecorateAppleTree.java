package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class DDecorateAppleTree {
    class ver {
        int u;
        int c;

        public ver(int u, int c) {
            this.u = u;
            this.c = c;
        }
    }

    int[] ch;
    int[] cch;
    int[] p;
    List[] g;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        ch = new int[n];
        p = new int[n];
        cch = new int[n];

        for (int i = 1; i < n; i++) {
            p[i] = in.nextInt() - 1;
        }

        p[0] = -1;

        for (int i = 1; i < n; i++) {
            g[i].add(p[i]);
            g[p[i]].add(i);
        }

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                ch[i] = g[i].size();
            } else {
                ch[i] = g[i].size() - 1;
            }
        }

        cch = ch.clone();
        //in.readTree(g, n, 1);

        PriorityQueue<ver> pq = new PriorityQueue<>((v1, v2) ->
        {
            return v1.c - v2.c;
        });

        int l = 0;
        Set<Integer> lvs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (i != 0 && g[i].size() == 1) {
                lvs.add(i);
                l++;
                cch[p[i]]--;
            }
        }

        cost = new int[n];
        addcost = new int[n];


        dfs(0, -1);

        for (int i = 0; i < n; i++) {
            if (cch[i] == 0 && !lvs.contains(i)) {
                pq.add(new ver(i, cost[i]));
            }
        }

        int[] ans = new int[n];

        int cc = 1;
        int cb = l;

        for (int i = 1; i <= n; i++) {
            while (cb < i) {
                ver re = pq.poll();

                cc = Math.max(re.c, cc);

                cb++;

                int cp = p[re.u];

                if (cp != -1) {
                    cch[cp]--;

                    if (cch[cp] == 0) {
                        pq.add(new ver(cp, cost[cp]));
                    }
                }
            }

            ans[i - 1] = cc;
        }

        for (int i = 0; i < n; i++) {
            out.print(ans[i] + " ");
        }
    }

    int[] cost;
    int[] addcost;

    void dfs(int u, int p) {
        if (u != 0 && g[u].size() == 1) {
            cost[u] = 1;
            return;
        }

        int max = 0;
        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                dfs(v, u);
                cost[u] += cost[v];
                max = Math.max(max, cost[v]);
            }
        }

        addcost[u] = cost[u] - max;

    }
}
