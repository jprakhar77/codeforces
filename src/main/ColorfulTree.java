package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class ColorfulTree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] c = in.nextIntArray(n);

        List[] g = new List[n];
        vis = new boolean[n];
        dis = new int[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        while (m-- > 0) {
            int k = in.nextInt();
            int cc = in.nextInt();
            k--;

            int ans = bfs(g, k, cc, c);

            out.println(ans);
        }
    }

    boolean[] vis;
    int[] dis;

    int bfs(List[] g, int u, int cr, int[] c) {
        Arrays.fill(vis, false);
        Arrays.fill(dis, 0);
        ArrayDeque<Integer> q = new ArrayDeque<>();


        int ans = -1;
        q.addLast(u);
        vis[u] =true;

        if (c[u] == cr)
        {
            ans = 0;
        }


        while (!q.isEmpty()) {
            int pe = q.removeFirst();

            for (int i = 0; i < g[pe].size(); i++) {
                int v = (int) g[pe].get(i);

                if (!vis[v]) {
                    dis[v] = dis[pe] + 1;
                    q.addLast(v);
                    vis[v] = true;
                    if (c[v] == cr) {
                        ans = Math.max(ans, dis[v]);

                    }
                }

            }
        }

        return ans;
    }
}
