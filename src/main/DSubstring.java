package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DSubstring {
    class CycleFinder {
        List[] g;
        int n;

        public CycleFinder(List[] g, int n) {
            this.g = g;
            this.n = n;
            this.recstack = new boolean[n];
            this.vis = new boolean[n];
        }

        boolean isCycleDirected() {
            isCycle = false;

            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    dfs(i);
                }
            }

            return isCycle;
        }

        boolean isCycle;
        boolean[] recstack;
        boolean[] vis;

        private void dfs(int u) {
            recstack[u] = true;

            vis[u] = true;

            for (int v : (List<Integer>) g[u]) {
                if (recstack[v]) {
                    isCycle = true;
                }
                if (!vis[v]) {
                    dfs(v);
                }
            }

            recstack[u] = false;
        }
    }


    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int m = in.nextInt();

        String s = in.next();

        List[] g = new List[n];

        in.readDirectedGraph(g, n, m, 1);

        CycleFinder cf = new CycleFinder(g, n);

        boolean is = cf.isCycleDirected();

        if (is) {
            out.println(-1);
            return;
        }

        int[][] dp = new int[n][26];

        Set[] to = new Set[n];

        int[] ind = new int[n];

        for (int i = 0; i < n; i++) {
            to[i] = new HashSet();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < g[i].size(); j++) {
                int v = (int) g[i].get(j);

                to[v].add(i);

                ind[v]++;
            }

        }

        int le = -1;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (ind[i] == 0) {
                q.addLast(i);
                dp[i][s.charAt(i) - 'a'] = 1;
            }
        }

        int ans = 1;

        while (!q.isEmpty()) {
            int i = q.removeFirst();

            for (int j = 0; j < 26; j++) {
                ans = Math.max(ans, dp[i][j]);
            }

            for (int j = 0; j < g[i].size(); j++) {
                int v = (int) g[i].get(j);

                for (int k = 0; k < 26; k++) {
                    dp[v][k] = Math.max(dp[v][k], dp[i][k]);
                }

                int ch = s.charAt(v) - 'a';
                dp[v][ch] = Math.max(dp[v][ch], dp[i][ch] + 1);

                ind[v]--;
                if (ind[v] == 0) {
                    q.addLast(v);
                }
            }
        }

        out.println(ans);
    }
}
