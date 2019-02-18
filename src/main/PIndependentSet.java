package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.List;

public class PIndependentSet {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        dp = new long[n][2];

        dfs(0, -1);

        out.println((dp[0][0] + dp[0][1]) % mod);
    }

    //0= w
    //1 = b

    List[] g;
    long[][] dp;

    void dfs(int u, int p) {

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs(v, u);
            }
        }


        //w

        long ca = 1;
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                ca *= (dp[v][0] + dp[v][1]);
                ca %= mod;
            }
        }

        dp[u][0] = ca;

        ca = 1;
        //b
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                ca *= (dp[v][0]);
                ca %= mod;
            }
        }

        dp[u][1] = ca;
    }

    int mod = 1000000007;
}
