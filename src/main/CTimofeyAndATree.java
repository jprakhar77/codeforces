package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CTimofeyAndATree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        c = in.nextIntArray(n);

        ch = new int[100001];

        for (int i = 0; i < n; i++) {
            ch[c[i]]++;
            if (ch[c[i]] == 1)
                tc++;
        }

        ss = new int[n];
        sc = new int[n];

        dfs(0, -1);
        dfs1(0, -1);

        if (ans == -1) {
            out.println("NO");
        } else {
            out.println("YES");
            out.println(ans + 1);
        }
    }

    int[] c;
    int[] ch;
    List[] g;

    int ans = -1;

    int[] ss;
    int[] sc;

    int tc = 0;

    Map<Integer, Integer> map = new HashMap<>();

    void dfs(int u, int p) {
        ss[u] = 1;
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs(v, u);

                ss[u] += ss[v];
            }
        }
    }

    void dfs1(int u, int p) {

        boolean poss = true;

        int color = -1;
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs1(v, u);

            }
        }

        map.clear();
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                if (sc[v] == -1) {
                    poss = false;
                    sc[u] = -1;
                } else if (sc[u] != -1) {
                    color = sc[v];
                    map.merge(sc[v], ss[v], (x, y) -> x + y);
                }
            }
        }

        if (poss) {
            if (map.size() == 0 || (map.size() == 1 && c[u] == color)) {
                sc[u] = c[u];
            } else {
                sc[u] = -1;
            }
            if (ans == -1) {
                int ctc = tc;
                for (int key : map.keySet()) {
                    int val = map.get(key);

                    ch[key] -= val;

                    if (ch[key] == 0) {
                        ctc--;
                    }
                }

                ch[c[u]]--;
                if (ch[c[u]] == 0)
                    ctc--;

                if (ctc <= 1) {
                    ans = u;
                }

                for (int key : map.keySet()) {
                    int val = map.get(key);

                    ch[key] += val;
                }
                ch[c[u]]++;
            }
        }
    }

}
