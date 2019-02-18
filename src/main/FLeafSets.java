package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FLeafSets {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        k = in.nextInt();

        g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (g[i].size() > 1) {
                root = i;
                break;
            }
        }

        root = 0;

        dp = new int[n];
        postOrderTraversal(g, root, n);
        ans++;

        out.println(ans);
    }

    List[] g;
    int[] dp;

    int k;

    int ans = 0;

    void postOrderTraversal(List[] g, int root, int n) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.addLast(root);

        boolean[] vis = new boolean[n];

        vis[root] = true;

        ArrayDeque<Integer> stack2 = new ArrayDeque<>();

        while (!stack.isEmpty()) {
            int pe = stack.removeLast();

            stack2.addLast(pe);

            for (int i = 0; i < g[pe].size(); i++) {
                int v = (int) g[pe].get(i);

                if (!vis[v]) {
                    stack.addLast(v);
                    vis[v] = true;
                }
            }
        }

        vis = new boolean[n];

        //int[] la = new int[n];

        o:
        while (!stack2.isEmpty()) {
            int u = stack2.removeLast();
            vis[u] = true;

            if (g[u].size() == 1) {
                continue;
            }

            //List<Integer> l = new ArrayList<>();

            int cn = g[u].size() - 1;
            if (u == root)
                cn++;
            Integer[] la = new Integer[cn];
            int j = 0;
            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (vis[v]) {
                    //Do operations requiring children values
                    la[j] = dp[v] + 1;
                    j++;
                }
            }

            //Do other operations required for this node

            Arrays.sort(la, 0, cn, Comparator.reverseOrder());

            for (int i = 0; i < cn - 1; i++) {
                if (la[i] + la[i + 1] > k) {
                    ans++;
                } else {
                    dp[u] = la[i];
                    continue o;
                }
            }

            dp[u] = la[cn - 1];
        }
    }

}
