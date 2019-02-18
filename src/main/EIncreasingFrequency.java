package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class EIncreasingFrequency {
    class seg {
        int s;
        int e;

        public seg(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    int max = (int) 5e5;

    List[] segs = new List[max + 1];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int c = in.nextInt();

        int[] a = in.nextIntArray(n);

        for (int i = 0; i <= max; i++) {
            segs[i] = new ArrayList();
        }

        int ans = 0;

        int[] ca = new int[n];

        for (int i = 0; i < n; i++) {
            if (a[i] == c) {
                ans++;
            }
        }

        if (a[0] == c) {
            ca[0] = 1;
        }

        for (int i = 1; i < n; i++) {
            ca[i] = ca[i - 1] + ((a[i] == c) ? 1 : 0);
        }

        int ss = 0;

        for (int i = 1; i < n; i++) {
            if (a[i] != a[i - 1]) {
                segs[a[i - 1]].add(new seg(ss, i - 1));
                ss = i;
            }
        }

        segs[a[n - 1]].add(new seg(ss, n - 1));

        int aans = ans;

        for (int i = 1; i <= max; i++) {
            if (i == c)
                continue;
            if (segs[i].size() > 0) {
                int cans = 0;

                int cur = 0;

                List<seg> csegs = segs[i];

                seg pseg = null;
                for (int j = 0; j < csegs.size(); j++) {
                    seg cseg = csegs.get(j);
                    if (j > 0) {
                        cur -= segval(pseg.e + 1, cseg.s - 1, ca);
                    }
                    if (cur < 0)
                        cur = 0;
                    cur += (cseg.e - cseg.s + 1);

                    cans = Math.max(cans, aans + cur);

                    pseg = cseg;
                }

                ans = Math.max(ans, cans);
            }
        }

        out.println(ans);

    }

    int segval(int l, int r, int[] arr) {
        int ans = arr[r];

        if (l > 0) {
            ans -= arr[l - 1];
        }

        return ans;
    }
}
