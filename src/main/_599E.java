package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class _599E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();

        m = in.nextInt();

        q = in.nextInt();

        g = new int[n][n];

        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            g[u][v] = 1;
            g[v][u] = 1;
        }

        lca = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                lca[i][j] = -1;
            }
        }
        for (int i = 0; i < q; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.nextInt() - 1;

            if (a == b && c != a) {
                out.println(0);
                return;
            }

            if (lca[a][b] != -1 && lca[a][b] != c) {
                out.println(0);
                return;
            }

            lca[a][b] = c;
            lca[b][a] = c;
        }

        long ans = 0;

        dp = new long[n][1 << n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (1 << n); j++) {
                dp[i][j] = -1;
            }
        }

        ans = rec(0, (1 << n) - 1);

        out.println(ans);

    }

    int n, m, q;
    int[][] g;
    int[][] lca;

    long[][] dp;

    long rec(int r, int mask) {

        mask &= (((1 << n) - 1) ^ (1 << r));

        if (dp[r][mask] != -1)
            return dp[r][mask];

        if ((mask & (mask - 1)) == 0) {
            if (mask != 0) {
                int bit = Integer.numberOfTrailingZeros(mask);
                if (lca[bit][r] != -1 && lca[bit][r] != r) {
                    return dp[r][mask] = 0;
                }
            }
            return dp[r][mask] = 1;
        }

        long fans = 0;

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (lca[i][j] != -1) {
//                    int l = lca[i][j];
//
//                    if (((1 << l) & mask) == 0 && l != r) {
//                        return dp[r][mask] = fans;
//                    }
//                }
//            }
//        }

        int hob = Integer.highestOneBit(mask);
        for (int i = mask; i >= hob; i--) {
            i &= mask;
            long ans = 0;
            Set<Integer> s = new HashSet<>();
            int nr = -1;
            boolean isp = true;
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) != 0) {
                    s.add(j);
                    if (g[r][j] == 1) {
                        if (nr != -1) {
                            isp = false;
                            break;
                        }
                        nr = j;
                    }
                }
            }

            if (!isp)
                continue;

            if (q > 0)
                for (Integer a : s) {
                    for (Integer b : s) {
                        if (lca[a][b] == -1)
                            continue;

                        if (lca[a][b] == r || !s.contains(lca[a][b])) {
                            isp = false;
                            break;
                        }
                    }
                }

            if (q > 0)
                for (int a = 0; a < n; a++) {
                    if (!s.contains(a) && (((1 << a) & mask) != 0 || a == r))
                        for (Integer b : s) {
                            if (lca[a][b] != -1 && lca[a][b] != r)
                                isp = false;
                        }
                }

            if (m > 0)
                for (int a = 0; a < n; a++) {
                    for (Integer b : s) {
                        if (g[b][a] == 1 && !s.contains(a) && a != r)
                            isp = false;
                    }
                }

            if (!isp)
                continue;

            if (nr == -1) {
                for (Integer a : s) {
                    ans += rec(a, i) * rec(r, mask ^ i);
                }
            } else {
                ans += rec(nr, i) * rec(r, mask ^ i);
            }

            fans += ans;
        }

        return (dp[r][mask] = fans);
    }
}
