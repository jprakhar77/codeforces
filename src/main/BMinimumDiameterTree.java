package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.List;

public class BMinimumDiameterTree {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        double s = in.nextInt();

        List[] g = new List[n];

        in.readUndirectedGraph(g, n, n - 1, 1);

        int l = 0;

        for (int i = 0; i < n; i++) {
            if (g[i].size() == 1) {
                l++;
            }
        }

        double ans = (s / l) * 2;

        out.println(ans);
    }
}
