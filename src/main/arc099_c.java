package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class arc099_c {
    class IsBipartite {


        Set[] g;
        int n;

        public IsBipartite(Set[] g) {
            this.g = g;
            this.n = g.length;
            this.vis = new boolean[n];
            this.level = new int[n];
            this.color = new int[n];
            this.colorVis = new boolean[n];
        }

        boolean[] vis;
        boolean[] colorVis;
        int[] level;
        int[] color;

        int zeroColorNodes = 0;
        int oneColorNodes = 0;

        //undirected graph, obviously
        boolean isBipartite(int u) {
            ArrayDeque<Integer> queue = new ArrayDeque<>();

            queue.addLast(u);
            vis[u] = true;
            level[u] = 0;

            while (queue.size() > 0) {
                int front = queue.removeFirst();

                for (int v : (Set<Integer>) g[front]) {
                    if (vis[v]) {
                        if (level[v] == level[front]) {
                            return false;
                        }
                    } else {
                        queue.addLast(v);
                        vis[v] = true;
                        level[v] = level[front] + 1;
                    }
                }
            }

            return true;
        }

        void assign2Colors(int u) {
            dfs(u, -1);
        }


        void dfs(int u, int prevColor) {
            if (prevColor == -1) {
                color[u] = 0;
            } else {
                color[u] = 1 - prevColor;
            }

            if (color[u] == 0)
                zeroColorNodes++;
            else
                oneColorNodes++;

            colorVis[u] = true;

            for (int v : (Set<Integer>) g[u]) {
                if (!colorVis[v])
                    dfs(v, color[u]);
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        Set[] g = new Set[n];

        for (int i = 0; i < n; i++) {
            g[i] = new HashSet();
        }

        int[] u = new int[m];
        int[] v = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                g[i].add(j);
                g[j].add(i);
            }
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;

            u[i] = a;
            v[i] = b;

            g[a].remove(b);
            g[b].remove(a);
        }

        IsBipartite isb = new IsBipartite(g);

        List<cp> cps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isb.vis[i]) {
                boolean isBipar = isb.isBipartite(i);
                if (!isBipar) {
                    out.println(-1);
                    return;
                }
                isb.oneColorNodes = 0;
                isb.zeroColorNodes = 0;
                isb.assign2Colors(i);
                cps.add(new cp(isb.oneColorNodes, isb.zeroColorNodes));
            }
        }

        boolean[][] dp = new boolean[cps.size()][n + 1];

        dp[0][cps.get(0).o] = true;
        dp[0][cps.get(0).z] = true;

        for (int i = 1; i < cps.size(); i++) {
            cp ccp = cps.get(i);
            for (int j = 0; j <= n; j++) {
                if (j >= ccp.o) {
                    dp[i][j] |= dp[i - 1][j - ccp.o];
                }
                if (j >= ccp.z) {
                    dp[i][j] |= dp[i - 1][j - ccp.z];
                }
            }
        }

        int ans = inf;
        for (int i = 0; i <= n; i++) {
            if (dp[cps.size() - 1][i]) {
                int s1 = i;
                int s2 = n - i;

                ans = Math.min(ans, cs(s1) + cs(s2));
            }
        }

        if (ans == inf) {
            out.println(-1);
            return;
        }

        out.println(ans);
    }

    int cs(int i) {
        return (i * (i - 1)) / 2;
    }

    class cp {
        int o;
        int z;

        public cp(int o, int z) {
            this.o = o;
            this.z = z;
        }
    }

    int inf = 100000000;
}
