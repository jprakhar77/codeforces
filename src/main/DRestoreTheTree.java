package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.List;

public class DRestoreTheTree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int m = in.nextInt();

        List[] g = new List[n];

        in.readDirectedGraph(g, n, n - 1 + m, 1);

        int[] ind = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < g[i].size(); j++) {
                int v = (int) g[i].get(j);

                ind[v]++;
            }
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (ind[i] == 0) {
                root = i;
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.addLast(root);

        int[] ans = new int[n];


        while (!q.isEmpty()) {
            int i = q.removeFirst();

            for (int j = 0; j < g[i].size(); j++) {
                int v = (int) g[i].get(j);

                if (ind[v] == 1) {
                    ans[v] = i + 1;
                    q.addLast(v);
                } else {
                    ind[v]--;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            out.println(ans[i]);
        }
    }
}
