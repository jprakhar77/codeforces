package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Set;
import java.util.TreeSet;

public class agc014d {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        Set[] g = new Set[n];

        for (int i = 0; i < n; i++) {
            g[i] = new TreeSet();
        }

        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            a--;
            b--;

            g[a].add(b);
            g[b].add(a);
        }

        TreeSet<Integer> leaves = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            int l = 0;
            for (int v : (Set<Integer>) g[i]) {
                if (g[v].size() == 1) {
                    l++;
                    leaves.add(v);
                }
            }

            if (l > 1) {
                out.println("First");
                return;
            }
        }

//        if (n % 2 == 1) {
//            out.println("First");
//            return;
//        }
//
//        Map<Integer, Integer> mp = new HashMap<>();
//
//        int rn = n;
//
//
//        int lev = 0;
//        while (rn > 0) {
//            Map<Integer, Integer> nmp = new HashMap<>();
//
//            TreeSet<Integer> nleaves = new TreeSet<>();
//
//            for (int l : leaves) {
//                if (g[l].size() <= 1) {
//                    if (g[l].size() == 1) {
//                        int v = ((TreeSet<Integer>) g[l]).first();
//                        g[v].remove(l);
//                        nmp.merge(v, 1, (x, y) -> x + y);
//                        nleaves.add(v);
//                    }
//                    rn--;
//                    if (lev % 2 == 1 && mp.getOrDefault(l, 0) > 1) {
//                        out.println("First");
//                        return;
//                    }
//                }
//            }
//
//            leaves = nleaves;
//            mp = nmp;
//            lev++;
//        }
//
//        out.println("Second");

        while (leaves.size() > 0) {
            int u = leaves.first();

            if (g[u].size() == 0) {
                leaves.remove(u);
                continue;
            }

            int v = ((TreeSet<Integer>) g[u]).first();

            g[v].remove(u);

            for (int ver : ((TreeSet<Integer>) g[v])) {
                if (leaves.contains(ver)) {
                    out.println("First");
                    return;
                }
                g[ver].remove(v);

                if (g[ver].size() == 1) {
                    leaves.add(ver);
                }
            }

            leaves.remove(u);
        }

        out.println("Second");
    }
}
