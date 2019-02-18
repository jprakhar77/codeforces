package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _675D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            map.put(a[i], i);
        }

        int logn = 0;

        while ((1 << logn) < n) {
            logn++;
        }

        int[][] lca = new int[n][logn + 1];
        int[] h = new int[n];

        TreeSet<Integer> l = new TreeSet<>();
        TreeSet<Integer> r = new TreeSet<>();

        l.add(a[0]);
        r.add(a[0]);

        for (int i = 0; i <= logn; i++) {
            lca[0][i] = -1;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            Integer le = l.ceiling(a[i]);
            Integer re = r.floor(a[i]);

            int p = -1;
            if (le == null) {
                p = map.get(re);
                r.remove(re);
                ans.add(re);
                l.add(a[i]);
                r.add(a[i]);
            } else if (re == null) {
                p = map.get(le);
                l.remove(le);
                ans.add(le);
                l.add(a[i]);
                r.add(a[i]);
            } else {
                int j = logn;

                int cre = map.get(re);
                int cle = map.get(le);

                if (h[cle] > h[cre]) {
                    int t = cle;
                    cle = cre;
                    cre = t;
                }

                for (int k = logn; k >= 0; k--) {
                    if (h[cre] - (1 << k) >= h[cle]) {
                        cre = lca[cre][k];
                    }
                }

                while (j >= 0) {

                    while (j >= 0 && lca[cle][j] == lca[cre][j]) {
                        j--;
                    }

                    if (j >= 0) {
                        cle = lca[cle][j];
                        cre = lca[cre][j];
                    }
                }

                cle = lca[cle][0];
                cre = lca[cre][0];

                cle = a[cle];

                if (a[i] < cle) {
                    p = map.get(re);
                    r.remove(re);
                    ans.add(re);
                    l.add(a[i]);
                    r.add(a[i]);
                } else {
                    p = map.get(le);
                    l.remove(le);
                    ans.add(le);
                    l.add(a[i]);
                    r.add(a[i]);
                }
            }

            lca[i][0] = p;
            h[i] = h[p] + 1;

            for (int j = 1; j <= logn; j++) {
                if (lca[i][j - 1] == -1)
                    lca[i][j] = -1;
                else
                    lca[i][j] = lca[lca[i][j - 1]][j - 1];
            }
        }

        for (int i = 0; i < ans.size(); i++) {
            out.print(ans.get(i) + " ");
        }
    }
}
