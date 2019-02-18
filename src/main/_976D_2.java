package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _976D_2 {
    class edge {
        int u;
        int v;

        public edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    List<edge> edges = new ArrayList<>();

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();

        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            d[i] = in.nextInt();
        }

        vl = new List[n];

        for (int i = 0; i < n; i++) {
            vl[i] = new ArrayList();
        }

        solve(d, 0, n - 1);

        out.println(edges.size());

        for (int i = 0; i < edges.size(); i++) {
            edge edge = edges.get(i);
            out.println(edge.u + " " + edge.v);
        }
    }

    int n;
    int cv = 1;

    List[] vl;

    void createClique(int[] d, int s, int m) {
        int nov = m;

        for (int i = cv; i < cv + nov; i++) {
            vl[s].add(i);
        }

        cv = cv + nov;

        for (int i = 0; i < vl[s].size(); i++) {
            for (int j = i + 1; j < vl[s].size(); j++) {
                edges.add(new edge((int) vl[s].get(i), (int) vl[s].get(j)));
            }
        }

    }

    void solve(int[] d, int s, int e) {
        if (e - s == 0) {
            int nov = d[s] + 1;

            createClique(d, s, nov);
            return;
        }

        if (e - s == 1) {
            createClique(d, s, d[s]);

            int nov = d[e] - d[s] + 1;

            for (int i = cv; i < cv + nov; i++) {
                vl[e].add(i);
            }

            for (int i = cv; i < cv + nov; i++) {
                for (int j = 0; j < vl[s].size(); j++) {
                    edges.add(new edge(i, (int) vl[s].get(j)));
                }
            }

            cv = cv + nov;
            return;
        }

        int[] nd = new int[n];

        for (int i = s + 1; i < e; i++) {
            nd[i] = d[i] - d[s];
        }

        solve(nd, s + 1, e - 1);

        int nov1 = d[e] - d[e - 1];
        for (int i = cv; i < cv + nov1; i++) {
            vl[e].add(i);
        }

        cv = cv + nov1;

        createClique(d, s, d[s]);

        for (int i = s + 1; i <= e; i++) {
            for (int j = 0; j < vl[i].size(); j++) {
                int cv1 = (int) vl[i].get(j);
                for (int k = 0; k < vl[s].size(); k++) {
                    int cv2 = (int) vl[s].get(k);

                    edges.add(new edge(cv1, cv2));
                }
            }
        }
    }
}
