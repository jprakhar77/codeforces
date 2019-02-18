package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DLunarNewYearAndAWander {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.ni();
        int m = in.ni();

        List[] g = new List[n];

        in.readUndirectedGraph(g, n, m, 1);

        PriorityQueue<Integer> q = new PriorityQueue<>();

        q.add(0);

        Set<Integer> s = new HashSet<>();
        s.add(0);

        int[] ans = new int[n];

        int ind = 0;


        while (!q.isEmpty()) {
            int pe = q.poll();

            ans[ind] = pe + 1;
            ind++;

            for (int j = 0; j < g[pe].size(); j++) {
                int v = (int) g[pe].get(j);

                if (!s.contains(v)) {
                    q.add(v);
                    s.add(v);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            out.print(ans[i] + " ");
        }
    }
}
