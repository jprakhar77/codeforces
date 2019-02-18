package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _1041E {
    class edge {
        int u;
        int v;

        public edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] u = new int[n - 1];
        int[] v = new int[n - 1];

        edge[] edges = new edge[n - 1];
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            u[i] = a;
            v[i] = b;

            edges[i] = new edge(a, b);

            //c
            if (b != n) {
                out.println("NO");
                return;
            }
        }

        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < n - 1; i++) {
            s.add(u[i]);
            s.add(v[i]);
        }

        if (!s.contains(n) || !s.contains(n - 1)) {
            out.println("NO");
            return;
        }

        Arrays.sort(edges, (e1, e2) -> e2.u - e1.u);

        TreeSet<Integer> ds = new TreeSet<>((x, y) -> y - x);

        for (int i = 1; i <= n; i++) {
            ds.add(i);
        }

        for (int num : s) {
            ds.remove(num);
        }

        int dss = ds.size();

        int ad = 0;
        for (int i = 1; i < n - 1; i++) {
            if (edges[i].u == edges[i - 1].u) {
                ad++;
            }
        }

        //conf
        if (ad != dss) {
            out.println("NO");
            return;
        }

        ArrayList<edge> ans = new ArrayList<>();

        int ll = n;
        int st = 0;
        for (int i = 1; i < n - 1; i++) {
            if (edges[i].u != edges[i - 1].u) {
                int tc = i - st - 1;

                int fu = edges[i - 1].u;
                int tv = ll;

                int p = fu;

                for (int j = 0; j < tc; j++) {
                    int num = ds.first();

                    if (num > fu || num > ll) {
                        out.println("NO");
                        return;
                    }

                    ds.remove(num);
                    ans.add(new edge(p, num));

                    p = num;
                }

                ans.add(new edge(p, tv));

                st = i;

                ll = fu;
            }
        }

        int tc = n - 1 - st - 1;

        int fu = edges[n - 2].u;
        int tv = ll;

        int p = fu;

        for (int j = 0; j < tc; j++) {
            int num = ds.first();

            ds.remove(num);
            ans.add(new edge(p, num));

            p = num;
        }

        ans.add(new edge(p, tv));

        out.println("YES");
        for (int i = 0; i < ans.size(); i++) {
            edge edge = ans.get(i);
            out.println(edge.u + " " + edge.v);
        }
    }


}
