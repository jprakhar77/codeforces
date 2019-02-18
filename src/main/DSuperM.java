package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DSuperM {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        int[] ac = in.nextIntArray(m);

        for (int val : ac) {
            acs.add(val - 1);
        }

        pre = new int[n][];
        suf = new int[n][];
        preom = new int[n][];
        sufom = new int[n][];

        subtot = new int[n];
        sub = new int[n];

        subp = new boolean[n];

        upo = new int[n];
        upt = new int[n];

        ans = new int[n];

        count = new int[n];
        dfs(0, -1);


        tot = count[0];

        ans[0] = sub[0];

        dfs2(0, -1, -1);

        int ver = -1;
        int ca = 1000000000;
        for (int i = 0; i < n; i++) {
            if (ans[i] < ca) {
                ca = ans[i];
                ver = i + 1;
            }
        }

        out.println(ver);
        out.println(ca);
    }

    List[] g;

    int[][] pre;
    int[][] suf;
    int[][] preom;
    int[][] sufom;

    int[] subtot;
    int[] sub;

    boolean[] subp;

    int[] upo;
    int[] upt;

    int[] count;

    int tot;


    int[] ans;

    Set<Integer> acs = new HashSet<>();

    void dfs(int u, int p) {

        if (acs.contains(u)) {
            subp[u] = true;
            count[u] = 1;
        }

        int cn = g[u].size() - 1;

        if (u == 0) {
            cn++;
        }
        int[] valo = new int[cn];
        int[] valt = new int[cn];

        int ind = 0;
        for (int j = 0; j < g[u].size(); j++) {
            int v = (int) g[u].get(j);

            if (v != p) {
                dfs(v, u);
                count[u] += count[v];
                if (subp[v]) {
                    subtot[u] += subtot[v];
                    subtot[u] += 2;
                    subp[u] = true;
                    valo[ind] = sub[v] + 1;
                    valt[ind] = subtot[v] + 2;
                }
                ind++;
            }
        }

        if (cn == 0)
            return;
        int max = 0;

        pre[u] = new int[cn];
        preom[u] = new int[cn];
        suf[u] = new int[cn];
        sufom[u] = new int[cn];

        pre[u][0] = valt[0];

        for (int i = 1; i < cn; i++) {
            pre[u][i] = pre[u][i - 1] + valt[i];
        }

        max = valt[0] - valo[0];

        preom[u][0] = valo[0];

        for (int i = 1; i < cn; i++) {
            max = Math.max(max, valt[i] - valo[i]);
            preom[u][i] = pre[u][i];
            preom[u][i] -= max;
        }

        suf[u][cn - 1] = valt[cn - 1];

        for (int i = cn - 2; i >= 0; i--) {
            suf[u][i] = suf[u][i + 1] + valt[i];
        }

        max = valt[cn - 1] - valo[cn - 1];
        sufom[u][cn - 1] = valo[cn - 1];

        for (int i = cn - 2; i >= 0; i--) {
            max = Math.max(max, valt[i] - valo[i]);
            sufom[u][i] = suf[u][i];
            sufom[u][i] -= max;
        }

        sub[u] = preom[u][cn - 1];
    }

    void dfs2(int u, int p, int pi) {
        if (u != 0) {
            int oth = 0;
            //lo
            int val1 = 0;
            if (pi > 0) {
                val1 += preom[p][pi - 1];
                oth++;
            }
            if (pi < suf[p].length - 1) {
                val1 += suf[p][pi + 1];
            }
            val1 += upt[p];
            val1 += subtot[u];
            if (tot > count[u])
                val1++;

            //ho
            int val2 = 0;
            if (pi > 0) {
                val2 += pre[p][pi - 1];
            }
            if (pi < suf[p].length - 1) {
                val2 += sufom[p][pi + 1];
            }
            val2 += upt[p];
            val2 += subtot[u];
            if (tot > count[u])
                val2++;

            //upo
            int val3 = 0;
            if (pi > 0) {
                val3 += pre[p][pi - 1];
            }
            if (pi < suf[p].length - 1) {
                val3 += suf[p][pi + 1];
            }
            val3 += upo[p];
            val3 += subtot[u];

            if (tot > count[u])
                val3++;

            //lo

            int val4 = 0;
            if (pi > 0) {
                val4 += pre[p][pi - 1];
            }
            if (pi < suf[p].length - 1) {
                val4 += suf[p][pi + 1];
            }
            val4 += upt[p];
            val4 += sub[u];
            if (tot > count[u])
                val4 += 2;

            ans[u] = Math.min(Math.min(val1, val2), Math.min(val3, val4));

            upo[u] = Math.min(Math.min(val1, val2), val3) - subtot[u];
            upt[u] = val4 - sub[u];
        }

        int ind = 0;
        for (int j = 0; j < g[u].size(); j++) {
            int v = (int) g[u].get(j);

            if (v != p) {
                dfs2(v, u, ind);
                ind++;
            }
        }
    }
}
