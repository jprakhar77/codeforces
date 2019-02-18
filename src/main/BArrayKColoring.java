package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class BArrayKColoring {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int k = in.nextInt();

        int[] a = in.nextIntArray(n);

        List[] l = new List[5001];

        for (int i = 0; i <= 5000; i++) {
            l[i] = new ArrayList();
        }


        int max = 0;
        for (int i = 0; i < n; i++) {
            int val = a[i];
            l[val].add(i);
            max = Math.max(max, l[val].size());
        }

        if (max > k) {
            out.println("NO");
            return;
        }

        TreeSet<Integer> ks = new TreeSet<>();

        for (int i = 1; i <= k; i++) {
            ks.add(i);
        }

        int[] ans = new int[n];
        for (int i = 1; i <= 5000; i++) {
            boolean[] dh = new boolean[k + 1];
            for (int j = 0; j < l[i].size(); j++) {
                int val = (int) l[i].get(j);

                if (ks.size() > 0) {
                    int rc = ks.pollFirst();

                    ans[val] = rc;
                    dh[rc] = true;
                } else {
                    for (int t = 1; t <= k; t++) {
                        if (!dh[t]) {
                            ans[val] = t;
                            dh[t] = true;
                            break;
                        }
                    }
                }
            }
        }

        out.println("YES");
        for (int i = 0; i < n; i++) {
            out.print(ans[i] + " ");
        }

    }
}
