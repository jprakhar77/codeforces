package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class agc010c {
    List[] g;
    int[] a;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;

            g[u].add(v);
            g[v].add(u);
        }

        long val = dfs(0, -1);

        if ((g[0].size() == 1 || val == 0) && isp) {
            out.print("YES");
        } else {
            out.print("NO");
        }
    }

    boolean isp = true;

    long dfs(int u, int p) {

        long sum = 0;
        int tot = 0;
        List<Long> rl = new ArrayList<>();

        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                tot++;
                long val = dfs(v, u);
                sum += val;
                rl.add(val);
            }
        }

        rl.sort((x, y) -> (int) Math.signum(x - y));

        long minsum = 0;

        if (tot == 0) {
            return a[u];
        }

        if (tot == 1) {
            if (sum != a[u]) {
                isp = false;
                return -1;
            } else {
                return sum;
            }
        }

        for (int i = 0; i < rl.size() - 1; i++) {
            minsum += rl.get(i);
        }

        if (sum < a[u]) {
            isp = false;
            return -1;
        }

        if (sum == a[u]) {
            return sum;
        }

        if (sum > a[u]) {
            long rem = sum - a[u];
            if (rem <= a[u] && 2 * rem <= sum && minsum >= rem) {
                return a[u] - rem;
            } else {
                isp = false;
                return -1;
            }
        }

        return -1;
    }

}
