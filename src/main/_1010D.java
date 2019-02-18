package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class _1010D {

    enum vertex {
        AND,
        OR,
        XOR,
        NOT,
        IN
    }

    vertex[] vtype;
    byte[][] loc;
    byte[][] glob;
    byte[] val;
    List[] g;
    int[] par;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        vtype = new vertex[n];
        val = new byte[n];
        par = new int[n];
        g = new List[n];

        par[0] = -1;

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>(2);
        }

        for (int i = 0; i < n; i++) {
            String s = in.next();

            vtype[i] = vertex.valueOf(s);

            if (vtype[i] == vertex.IN) {
                val[i] = (byte) in.nextInt();
            } else if (vtype[i] == vertex.NOT) {
                int a = in.nextInt() - 1;
                g[i].add(a);
                par[a] = i;
            } else {
                int a = in.nextInt() - 1;
                int b = in.nextInt() - 1;
                g[i].add(a);
                g[i].add(b);
                par[a] = i;
                par[b] = i;
            }
        }

        loc = new byte[n][2];
        glob = new byte[n][2];

        glob[0][0] = glob[0][1] = 1;

        dfs0();
        dfs1();

        StringBuilder ans = new StringBuilder();

        int mv = val[0];
        for (int i = 0; i < n; i++) {
            if (vtype[i] == vertex.IN) {
                if (glob[i][1 - val[i]] == 1) {
                    ans.append(1 - mv);
                } else {
                    ans.append(mv);
                }
            }
        }

        out.println(ans.toString());
    }

    void dfs0() {

        ArrayDeque<Integer> dq1 = new ArrayDeque<>();
        dq1.addFirst(0);

        ArrayDeque<Integer> dq2 = new ArrayDeque<>();

        while (dq1.size() > 0) {

            int u = dq1.removeFirst();

            dq2.addFirst(u);

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);
                dq1.addFirst(v);
            }
        }

        while (dq2.size() > 0) {
            int u = dq2.removeFirst();

            if (vtype[u] == vertex.IN) {
                continue;
            }

            byte cv = -1;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);
                switch (vtype[u]) {
                    case OR:
                        if (cv == -1)
                            cv = 0;
                        cv |= val[v];
                        break;
                    case AND:
                        if (cv == -1)
                            cv = 1;
                        cv &= val[v];
                        break;
                    case NOT:
                        cv = (byte) (1 - val[v]);
                        break;
                    case XOR:
                        if (cv == -1)
                            cv = 0;
                        cv ^= val[v];
                        break;
                }
            }

            val[u] = (byte) cv;
        }
    }

    void dfs1() {

        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.addFirst(0);

        while (dq.size() > 0) {

            int u = dq.removeFirst();

            byte ones = 0;
            byte zeroes = 0;

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);
                if (val[v] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
            }

            if (u != 0) {
                glob[u][0] = loc[par[u]][0];
                if (glob[u][0] == 1) {
                    byte cv = val[par[u]];
                    if (cv == 1) {
                        glob[u][0] &= glob[par[u]][0];
                    } else {
                        glob[u][0] &= glob[par[u]][1];
                    }
                }
                glob[u][1] = loc[par[u]][1];
                if (glob[u][1] == 1) {
                    byte cv = val[par[u]];
                    if (cv == 1) {
                        glob[u][1] &= glob[par[u]][0];
                    } else {
                        glob[u][1] &= glob[par[u]][1];
                    }
                }
            }

            switch (vtype[u]) {
                case OR:
                    loc[u][0] = (byte) ((ones == 1) ? 1 : 0);
                    loc[u][1] = (byte) ((ones == 0) ? 1 : 0);
                    break;
                case AND:
                    loc[u][0] = (byte) ((zeroes == 0) ? 1 : 0);
                    loc[u][1] = (byte) ((zeroes == 1) ? 1 : 0);
                    break;
                case NOT:
                    loc[u][0] = 1;
                    loc[u][1] = 1;
                    break;
                case XOR:
                    loc[u][0] = 1;
                    loc[u][1] = 1;
                    break;
            }

            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);
                dq.addFirst(v);
            }
        }
    }
}
