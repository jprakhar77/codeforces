package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class _1036F {
    class test {
        long num;
        int i;

        public test(long num, int i) {
            this.num = num;
            this.i = i;
        }
    }

    long sqrt(long num) {
//        long lo = 1;
//        long hi = num;
//
//        long ans = 1;
//        while (lo <= hi) {
//            long mid = lo + (hi - lo) / 2;
//
//            if (num / mid >= mid) {
//                ans = Math.max(ans, mid);
//                lo = mid + 1;
//            } else {
//                hi = mid - 1;
//            }
//        }
//
//        return ans;

        long ans = (long) Math.sqrt(num);

        while (ans * ans <= num)
            ans++;

        return ans - 1;
    }

    double eps = 1e-10;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        TreeMap<Long, List<Integer>> tm = new TreeMap<>();
        TreeMap<Long, Long> tm2 = new TreeMap<>();

        for (int tt = 0; tt < t; tt++) {
            long n = in.nextLong();

            tm.merge(n, new ArrayList<Integer>(Arrays.asList(tt)), (x, y) ->
            {
                x.addAll(y);
                return x;
            });

            tm2.put(n, 0l);

        }

        boolean[] isd = new boolean[1000001];

        for (long i = 2; i <= 1e6; i++) {
            if (!isd[(int) i]) {
                long cv = i * i;
                while (true) {
                    if (cv <= 1000000)
                        isd[(int) cv] = true;

                    long cvsq = sqrt(cv);

                    if (cvsq > 1e6 && cvsq * cvsq == cv) {
                    } else {
                        Long ceil = tm.ceilingKey(cv);
                        if (ceil != null) {
                            tm2.merge(ceil, 1l, (x, y) -> x + y);
                        } else {
                            break;
                        }
                    }
                    if ((((long) 1e18) / i) >= cv) {
                        cv *= i;
                    } else {
                        break;
                    }
                }
            }
        }

        long pval = 0;
        for (Long key : tm2.keySet()) {
            tm2.merge(key, pval, (x, y) -> x + y);
            long sqrt = sqrt(key);
            pval = tm2.get(key);
            if (sqrt > 1000000) {
                tm2.merge(key, sqrt - 1000000, (x, y) -> x + y);
            }
        }

        long[] ans = new long[t];

        for (Long key : tm.keySet()) {
            for (Integer ind : tm.get(key)) {
                ans[ind] = key - tm2.get(key) - 1;
            }
        }

        for (int i = 0; i < t; i++) {
            out.println(ans[i]);
        }
    }
}
