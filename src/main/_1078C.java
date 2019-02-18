package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.List;

public class _1078C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        if (n <= 2) {
            out.print(n);
            return;
        }

        g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);
        //_2inv = pow(2, mod - 2, mod);

        dp = new long[n][3];
        //size = new int[n];

        pow = new long[n + 1];

        pow[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow[i] = (2 * pow[i - 1]) % mod;
        }

        sat = new long[n];
        unsat = new long[n];
        single = new long[n];

        dfs(0, -1);

        long ans = dp[0][0] + dp[0][2];
        ans %= mod;
        if (ans < 0)
            ans += mod;
        out.print(ans);
    }

    List[] g;
    long[][] dp;

    int mod = 998244353;

    long[] pow;

    long[] sat;
    long[] single;
    long[] unsat;

    long satOneMul;
    long sat2OneMul;

    //0 - size 1, 1 - uns, 2 - sat
    void dfs(int u, int p) {
        if (g[u].size() == 1 && u != 0) {
            dp[u][0] = 1;
            dp[u][1] = 0;
            dp[u][2] = 0;
            return;
        }

        int ind = 0;

        int cn = g[u].size() - 1;

        if (u == 0) {
            cn++;
        }

        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                dfs(v, u);
            }
        }

        satOneMul = 1;
        sat2OneMul = 1;

        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                sat[ind] = dp[v][2];
                single[ind] = dp[v][0];
                unsat[ind] = dp[v][1];
                satOneMul *= (sat[ind] + single[ind]);
                satOneMul %= mod;
                sat2OneMul *= (2 * sat[ind] + single[ind]);
                sat2OneMul %= mod;
                ind++;
            }
        }

        //2
        for (int i = 0; i < cn; i++) {
            dp[u][2] += (((unsat[i] + single[i]) * sat2OneMul) % mod) * pow((2 * sat[i] + single[i]) % mod, mod - 2, mod);
            dp[u][2] %= mod;
        }

        dp[u][0] = satOneMul;

        //1
        dp[u][1] = sat2OneMul - satOneMul;
        dp[u][1] %= mod;

//        long[][] cdp = new long[cn][4];
//
//        cdp[0][0] = zeroPre[0];
//        cdp[0][1] = oddPre[0];
//        cdp[0][2] = evenPre[0];
//
//        for (int i = 1; i < cn; i++) {
//            //0
//            cdp[i][0] = cdp[i - 1][0] * zeroPre[i];
//            //1
//            cdp[i][1] = cdp[i - 1][0] * oddPre[i] + cdp[i - 1][1] * zeroPre[i];
//            //2
//            cdp[i][2] = cdp[i - 1][0] * evenPre[i] + cdp[i - 1][2] * zeroPre[i];
//            //3
//            cdp[i][3] = cdp[i - 1][1] * evenPre[i] + cdp[i - 1][2] * oddPre[i] + cdp[i - 1][3] * zeroPre[i];
//
//            for (int j = 0; j < 4; j++) {
//                cdp[i][j] %= mod;
//            }
//        }
//
//        dp[u][0] = cdp[cn - 1][3] + cdp[cn - 1][2] + cdp[cn - 1][0];
//        dp[u][1] = cdp[cn - 1][2];
//        dp[u][2] = cdp[cn - 1][1] + cdp[cn - 1][0];
//
//        for (int j = 0; j < 3; j++) {
//            dp[u][j] %= mod;
//        }
//
//        //evenPre = in.calculatePrefixSum(evenPre, mod);
//
////        for (int j = 0; j < evenPre.length; j++) {
////            dp[u][0] += (evenPre[j] * tot) % mod;
////            dp[u][0] %= mod;
////        }
////
////        for (int j = 0; j < evenPre.length; j++) {
////            dp[u][0] -= (evenPre[j] * evenPre[j]) % mod;
////            dp[u][0] %= mod;
////        }
////
////        dp[u][0] *= _2inv;
////        dp[u][0] %= mod;
////
////        long t0 = (cn * (cn - 1)) / 2;
////        t0 %= mod;
////
////        dp[u][0] += t0 * (1 + tot0);
////        dp[u][0] %= mod;
////
////        for (int j = 0; j < cn; j++) {
////            dp[u][0] -= ((cn - 1) * zeroPre[j]) % mod;
////            dp[u][0] %= mod;
////        }
////
////        for (int j = 0; j < cn; j++) {
////            dp[u][0] += (evenPre[j] + 1) + tot0 - zeroPre[j];
////            dp[u][1] += (evenPre[j]) + tot0 - zeroPre[j];
////            dp[u][0] %= mod;
////            dp[u][1] %= mod;
////        }
////
////        for (int j = 0; j < cn; j++) {
////            if (sz[j] > 1) {
////                dp[u][2] += (oddPre[j]) + tot0 - zeroPre[j];
////                dp[u][2] %= mod;
////            }
////        }
////
////        dp[u][0] += tot0;
////        dp[u][2] += tot0;
////
////        dp[u][0] %= mod;
////        dp[u][2] %= mod;
    }

    int[] size;

    void dfs0(int u, int p) {
        size[u] = 1;
        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                dfs0(v, u);
                size[v] += size[u];
            }
        }
    }

    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }
}
