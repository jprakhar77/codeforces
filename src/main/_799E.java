package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _799E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        if (k > m) {
            out.println(-1);
            return;
        }

        int[] c = in.nextIntArray(n);

        int a = in.nextInt();

        int[] x = in.nextIntArray(a);

        int b = in.nextInt();

        int[] y = in.nextIntArray(b);

        Set<Integer> xs = new HashSet<>();

        for (int val : x) {
            xs.add(val - 1);
        }

        Set<Integer> ys = new HashSet<>();

        for (int val : y) {
            ys.add(val - 1);
        }

        List<Integer> abl = new ArrayList<>();

        List<Integer> al = new ArrayList<>();
        List<Integer> bl = new ArrayList<>();

        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (xs.contains(i) && ys.contains(i)) {
                abl.add(c[i]);
            } else if (xs.contains(i)) {
                al.add(c[i]);
            } else if (ys.contains(i)) {
                bl.add(c[i]);
            } else {
                l.add(c[i]);
            }
        }

        abl.sort((n1, n2) -> n1 - n2);
        al.sort((n1, n2) -> n1 - n2);
        bl.sort((n1, n2) -> n1 - n2);
        l.sort((n1, n2) -> n1 - n2);

        long ans = inf;

        List<Long> abll = in.calculatePrefixSum(abl);
        List<Long> all = in.calculatePrefixSum(al);
        List<Long> bll = in.calculatePrefixSum(bl);
        List<Long> ll = in.calculatePrefixSum(l);


        int aptr = al.size();
        int bptr = bl.size();
        int ptr = l.size();

        for (int i = 0; i <= abl.size(); i++) {

            int abrem = Math.max(0, k - i);

            if (abrem > all.size() || abrem > bll.size() || i + 2 * abrem > m || i + all.size() + bll.size() + ll.size() < m) {
                continue;
            }

            long csum = 0;

            if (i > 0) {
                csum += abll.get(i - 1);
            }

            int totm = i + aptr + bptr + ptr;

            abrem = 0;

            while (totm > m) {
                if (aptr > abrem && bptr > abrem && ptr > 0) {
                    if (l.get(ptr - 1) >= al.get(aptr - 1) && l.get(ptr - 1) >= bl.get(bptr - 1)) {
                        ptr--;
                    } else if (al.get(aptr - 1) >= bl.get(bptr - 1)) {
                        aptr--;
                    } else {
                        bptr--;
                    }
                } else if (aptr > abrem && bptr > abrem) {
                    if (al.get(aptr - 1) >= bl.get(bptr - 1)) {
                        aptr--;
                    } else {
                        bptr--;
                    }
                } else if (aptr > abrem && ptr > 0) {
                    if (l.get(ptr - 1) >= al.get(aptr - 1)) {
                        ptr--;
                    } else {
                        aptr--;
                    }
                } else if (bptr > abrem && ptr > 0) {
                    if (l.get(ptr - 1) >= bl.get(bptr - 1)) {
                        ptr--;
                    } else {
                        bptr--;
                    }
                } else if (aptr > abrem) {
                    aptr--;
                } else if (bptr > abrem) {
                    bptr--;
                } else {
                    ptr--;
                }
                totm--;
            }

            abrem = Math.max(0, k - i);
            int ex = 0;

            if (Math.max(abrem, aptr) > 0) {
                csum += all.get(Math.max(abrem, aptr) - 1);
                if (abrem > aptr) {
                    ex += abrem - aptr;
                }
            }

            if (Math.max(abrem, bptr) > 0) {
                csum += bll.get(Math.max(abrem, bptr) - 1);
                if (abrem > bptr) {
                    ex += abrem - bptr;
                }
            }

            if (ptr - ex > 0)
                csum += ll.get(ptr - ex - 1);

            ans = Math.min(ans, csum);
        }

        if (ans == inf) {
            out.println(-1);
        } else {
            out.println(ans);
        }
    }

    long inf = (long) 1e15;
}
