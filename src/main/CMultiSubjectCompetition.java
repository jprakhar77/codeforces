package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CMultiSubjectCompetition {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List[] st = new List[m];

        for (int i = 0; i < m; i++) {
            st[i] = new ArrayList();
        }
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            int r = in.nextInt();

            st[s - 1].add(r);
        }

        for (int i = 0; i < m; i++) {
            st[i].sort((x, y) -> (int) y - (int) x);
        }

        //Set<Integer> cs = new HashSet<>();

        int ind = 0;
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            if (st[i].size() > 0 && (int) st[i].get(0) > 0) {
                arr[ind] = i;
                ind++;
            }
        }

        long ans = 0;
        long[] csum = new long[m];


        for (int i = 1; ind > 0; i++) {

            long cur = 0;

            //Set<Integer> tr = new HashSet<>();
            int cind = 0;
            for (int k = 0; k < ind; k++) {
                int su = arr[k];
                if (st[su].size() >= i) {
                    long ccur = csum[su] + (int) st[su].get(i - 1);
                    if (ccur > 0) {
                        cur += ccur;
                        csum[su] = ccur;
                        arr[cind] = su;
                        cind++;
                    } else {
                    }
                } else {
                }
            }

            ans = Math.max(ans, cur);

            //cs.removeAll(tr);
            ind = cind;
        }

        out.println(ans);
    }

}
