package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _403A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int tt = in.nextInt();

        while (tt-- > 0) {
            int n = in.nextInt();
            int p = in.nextInt();

            List[] g = new List[n];

            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList();
            }

            for (int i = 0; i < n; i++) {
                int p1 = i > 0 ? i - 1 : n - 1;
                int n1 = i < n - 1 ? i + 1 : 0;

                g[i].add(p1);
                g[i].add(n1);

                int p2 = i > 1 ? i - 2 : i - 2 + n;
                int n2 = i < n - 2 ? i + 2 : i + 2 - n;

                g[i].add(p2);
                g[i].add(n2);
            }

            for (int i = 3; p > 0; i++) {
                for (int j = 0; j < n && p > 0; j++) {
                    int p2 = j > i - 1 ? j - i : j - i + n;
                    int n2 = j < n - i ? j + i : j + i - n;

                    g[j].add(n2);
                    g[n2].add(j);
                    p--;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < g[i].size(); j++) {
                    if (i < (int) g[i].get(j))
                        out.println((i + 1) + " " + ((int) g[i].get(j) + 1));
                }
            }
        }
    }
}
