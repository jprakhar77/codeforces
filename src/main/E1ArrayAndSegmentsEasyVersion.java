package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class E1ArrayAndSegmentsEasyVersion {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        int[] a = in.nextIntArray(n);

        int[] ss = new int[m];
        int[] se = new int[m];

        for (int i = 0; i < m; i++) {
            ss[i] = in.nextInt() - 1;
            se[i] = in.nextInt() - 1;
        }

        int ans = 0;
        List<Integer> al = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                List<Integer> l1 = new ArrayList<>();
                //inc i dec j
                int ca = a[i] - a[j];
                for (int k = 0; k < m; k++) {
                    if (ss[k] > i && ss[k] <= j && se[k] >= j) {
                        ca++;
                        l1.add(k + 1);
                    }
                }

                if (ca > ans) {
                    ans = ca;
                    al = l1;
                }


                List<Integer> l2 = new ArrayList<>();
                ca = a[j] - a[i];
                for (int k = 0; k < m; k++) {
                    if (ss[k] <= i && se[k] >= i && se[k] < j) {
                        ca++;
                        l2.add(k + 1);
                    }
                }

                if (ca > ans) {
                    ans = ca;
                    al = l2;
                }
            }
        }

        out.println(ans);

        out.println(al.size());
        for (int i = 0; i < al.size(); i++) {
            out.print(al.get(i) + " ");
        }
    }
}
