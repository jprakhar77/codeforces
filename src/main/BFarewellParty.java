package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class BFarewellParty {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int[] h = new int[n];
        List[] ll = new List[n];

        for (int i = 0; i < n; i++) {
            ll[i] = new ArrayList();
        }
        for (int i = 0; i < n; i++) {
            h[a[i]]++;
            ll[a[i]].add(i);
        }

        int[] ans = new int[n];

        int sv = 1;
        for (int i = 0; i < n; i++) {
            int val = h[i];

            if (val == 0)
                continue;

            int rem = n - val;

            if (rem == i) {
                List<Integer> cl = ll[i];

                for (int j = 0; j < cl.size(); j++) {
                    ans[cl.get(j)] = sv;
                }
                sv++;
            } else if (rem < i) {
                int diff = i - rem;

                List<Integer> cl = ll[i];

                if (diff >= val) {
                    out.println("Impossible");
                    return;
                }

                int arem = val - diff;

                if (val % arem == 0) {
                    int t = val / arem;

                    for (int j = 0; j < t; j++) {
                        for (int k = 0; k < arem; k++) {
                            ans[cl.get(arem * j + k)] = sv;
                        }
                        sv++;
                    }
                } else {
                    out.println("Impossible");
                    return;
                }
            } else {
                out.println("Impossible");
                return;
            }
        }

        out.println("Possible");

        for (int i = 0; i < n; i++) {
            out.print(ans[i] + " ");
        }

    }
}
