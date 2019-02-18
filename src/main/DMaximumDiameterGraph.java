package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DMaximumDiameterGraph {
    class ver {
        int i;
        int a;

        public ver(int i, int a) {
            this.i = i;
            this.a = a;
        }

        public ver(ver c) {
            this.i = c.i;
            this.a = c.a;
        }

        void dec() {
            this.a = this.a - 1;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        //int[] a = in.nextIntArray(n);

        //Arrays.sort(a);

        ver[] a = new ver[n];
        for (int i = 0; i < n; i++) {
            a[i] = new ver(i, in.nextInt());
        }

        Arrays.sort(a, (v1, v2) -> v1.a - v2.a);

        a[0].a = 1;
        a[1].a = 1;

        int dsum = 0;

        for (int i = 0; i < n; i++) {
            dsum += a[i].a;
        }
        if (2 * n - 2 > dsum) {
            out.println("NO");
            return;
        }

        List[] g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        //Set<Integer> ss = new HashSet<>();
        List<ver> ss = new ArrayList<>();
        List<Integer> ssl = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (a[i].a > 1) {
                ss.add(new ver(a[i]));
            } else {
                ssl.add(a[i].i);
            }
        }

        if (ss.size() > 1) {
            for (int i = 0; i < ss.size(); i++) {
                if (i == 0 || i == ss.size() - 1) {
                    ss.get(i).dec();
                } else {
                    ss.get(i).dec();
                    ss.get(i).dec();
                }
            }
        }

        for (int i = 0; i < ss.size() - 1; i++) {
            int c = ss.get(i).i;
            int ne = ss.get(i + 1).i;

            g[c].add(ne);
            g[ne].add(c);
        }

        int l = n - ss.size();
        int nl = n - l;

        int ans = 0;

        ans = (nl - 1) + 2;
        g[ss.get(0).i].add(ssl.get(0));
        g[ssl.get(0)].add(ss.get(0).i);

        ss.get(0).dec();

        g[ss.get(nl - 1).i].add(ssl.get(1));
        g[ssl.get(1)].add(ss.get(nl - 1).i);

        ss.get(nl - 1).dec();

        int j1 = 0;
        for (int i = 2; i < l; i++) {
            int li = ssl.get(i);
            while (ss.get(j1).a == 0) {
                j1++;
            }

            g[ss.get(j1).i].add(li);
            g[li].add(ss.get(j1).i);

            ss.get(j1).dec();
        }

        out.println("YES " + ans);
        out.println(n - 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < g[i].size(); j++) {
                int v = (int) g[i].get(j);
                if (v > i) {
                    out.println((i + 1) + " " + (v + 1));
                }
            }
        }
    }
}
