package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.List;

public class EEhabAndAComponentChoosingProblem {

    long[] dp;
    long[] dp2;

    List[] g;

    void dfs0(int u, int p) {
        dp[u] = a[u];
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs0(v, u);

                dp[u] += dp[v];
            }
        }

        if (dp[u] < 0) {
            dp[u] = 0;
        }

        max = Math.max(max, dp[u]);
    }

    long max;
    int cnt = 0;

    void dfs1(int u, int p) {
        dp2[u] = a[u];
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs1(v, u);

                dp2[u] += dp2[v];
            }
        }

        if (dp2[u] < 0) {
            dp2[u] = 0;
        }

        if (dp2[u] == max) {
            cnt++;
            dp2[u] = 0;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        a = in.nextIntArray(n);

        g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        boolean allneg = true;

        long negmax = (int) -1e9 - 1;
        int nmc = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] > 0) {
                allneg = false;
                break;
            }

            if (negmax < a[i]) {
                negmax = a[i];
                nmc = 1;
            } else if (negmax == a[i]) {
                nmc++;
            }
        }

        if (allneg) {
            out.println((negmax * nmc) + " " + nmc);
            return;
        }

        dp = new long[n];
        dp2 = new long[n];

        dfs0(0, -1);

        dfs1(0, -1);

        out.println((max * cnt) + " " + cnt);

//        PriorityQueue<ver> vers = new PriorityQueue<>((v1, v2) -> v1.d - v2.d);
//
//        int[] deg = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            deg[i] = g[i].size();
//        }
//
//        for (int i = 0; i < n; i++) {
//            if (a[i] <= 0 && deg[i] == 1)
//                vers.add(new ver(i, deg[i]));
//        }
//
//        boolean[] rem = new boolean[n];
//
//        while (vers.size() > 0) {
//            ver cv = vers.poll();
//
//            if (deg[cv.u] != cv.d) {
//                continue;
//            }
//
//            rem[cv.u] = true;
//
//            for (int i = 0; i < g[cv.u].size(); i++) {
//                int v = (int) g[cv.u].get(i);
//
//                if (rem[v])
//                    continue;
//
//                deg[v]--;
//
//                if (deg[v] <= 1 && a[v] <= 0) {
//                    vers.add(new ver(v, deg[v]));
//                }
//            }
//        }
//
//        List[] ng = new List[n];
//
//        for (int i = 0; i < n; i++) {
//            ng[i] = new ArrayList();
//
//            if (rem[i])
//                continue;
//            for (int j = 0; j < g[i].size(); j++) {
//                int v = (int) g[i].get(j);
//
//                if (!rem[v]) {
//                    ng[i].add(v);
//                }
//            }
//        }
//
//        g = ng;
//
//        long sum = 0;
//        int ck = 0;
//
//        vis = new boolean[n];
//
//        List<Long> negs = new ArrayList<>();
//
//        for (int i = 0; i < n; i++) {
//            if (!vis[i]) {
//                csum = 0;
//                if (a[i] < 0) {
//                    dfs(i, g, false);
//                } else if (a[i] > 0) {
//                    dfs(i, g, true);
//                }
//
//                if (csum < 0) {
//                    //negs.add(csum);
//                } else {
//                    //sum += csum;
//                    //ck++;
//                    negs.add(csum);
//                }
//            }
//        }
//
//        negs.sort(Comparator.reverseOrder());
//
//        double ans = (double) sum / ck;
//
//        long fsum = negs.get(0);
//        int fk = 1;
//
////        for (int i = ck - 1, j = 0; i >= 1; i--, j++) {
////            long nsum = sum + negs.get(j);
////
////            if (((double) nsum / i) > ans) {
////                ans = nsum / i;
////                fsum = nsum;
////                fk = i;
////            }
////
////            sum = nsum;
////        }
//
//        for (int i = 1; i < negs.size(); i++) {
//            if (negs.get(i) == fsum) {
//                fk++;
//            } else {
//                break;
//            }
//        }
//
//
//        out.println((fsum * fk) + " " + fk);
    }

    long csum = 0;

    int[] a;
    boolean[] vis;

    void dfs(int u, List[] g, boolean isp) {
        if (isp) {
            if (a[u] < 0) {
                return;
            }
        } else {
            if (a[u] > 0) {
                return;
            }
        }

        vis[u] = true;
        csum += a[u];

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (!vis[v])
                dfs(v, g, isp);
        }
    }

    class ver {
        int u;
        int d;

        public ver(int u, int d) {
            this.u = u;
            this.d = d;
        }
    }
}
