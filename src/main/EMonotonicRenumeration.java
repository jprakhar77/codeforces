package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EMonotonicRenumeration {
    class interval {
        int l;
        int r;

        public interval(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        Map<Integer, Integer> lm = new HashMap<>();
        Map<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = a[i];

            if (!lm.containsKey(num)) {
                lm.put(num, i);
            }

            hm.put(num, i);
        }

        int[] inters = new int[n];
        int[] intere = new int[n];

        List<interval> intervals = new ArrayList<>();
        for (int key : lm.keySet()) {
            int lv = lm.get(key);
            int hv = hm.get(key);

            if (lv < hv) {
                //intervals.add(new interval(lv, hv));
                inters[lv]++;
                if (hv < n - 1) {
                    intere[hv + 1]--;
                }
            }
        }

        //intervals.sort((i1, i2) -> i1.l - i2.l);

        int c = 0;

        int ans = 1;
        int li = -1;

        int is = 0;
        for (int i = 0; i < n; i++) {
            c += intere[i];

            if (i > 0 && c == 0) {
                intervals.add(new interval(is, i - 1));
                is = i;
            }

            c += inters[i];
        }

        intervals.add(new interval(is, n - 1));

        for (int i = 0; i < intervals.size(); i++) {
            interval ci = intervals.get(i);

            if (ci.l != 0) {
                ans *= 2;
                ans %= mod;
            }

            if (i > 0 && ci.l > intervals.get(i - 1).r + 1) {
                ans *= pow(2, ci.l - intervals.get(i - 1).r - 1, mod);
                ans %= mod;
            }
        }

        int pv = -1;

        if (intervals.size() > 0) {
            pv = intervals.get(intervals.size() - 1).r;
        }

        ans *= pow(2, n - 1 - pv, mod);
        ans %= mod;

        out.println(ans);
    }

    int mod = 998244353;

    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }
}
