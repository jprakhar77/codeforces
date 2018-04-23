package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;

public class AC_GC_22_C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();

        for (int i = 0; i < n; i++)
            b[i] = in.nextInt();

        Set[] sa = new Set[n];

        for (int i = 0; i < n; i++) {
            sa[i] = new HashSet();
            sa[i].add(a[i]);
        }

        long ans = 0;
        int i;
        for (i = 50; i >= 1; i--) {
            boolean isreq = false;
            for (int j = 0; j < n; j++) {
                isreq |= isreq(sa[j], i, b[j]);
                if (isreq)
                    break;
            }

            if (isreq) {
                ans += (1l << i);
                for (int j = 0; j < n; j++) {
                    sa[j] = mod(sa[j], i);
                }
            }

            boolean done = true;
            for (int j = 0; j < n; j++) {
                if (!sa[j].contains(b[j])) {
                    done = false;
                    break;
                }
            }

            if (done)
                break;
        }

        if (i == 0) {
            out.println(-1);
            return;
        }
        out.println(ans);
    }

    boolean isreq(Set<Integer> set, int k, int b) {
        if (set.contains(b))
            return false;

        for (Integer num : set) {
            if (isposs(num, b, k - 1))
                return false;
        }

        return true;
    }

    boolean isposs(int num, int b, int k) {
        if (num <= 2 * b)
            return false;

        if (num - b <= k)
            return true;

        for (int j = k; j >= 1; j--) {
            int n2 = num % j;

            if (n2 == b)
                return true;

            if (n2 > 2 * b) {
                return true;
            }
        }

        return false;
    }


    Set<Integer> mod(Set<Integer> set, int k) {
        Set<Integer> nset = new HashSet<>(set);
        for (Integer num : set) {
            nset.add(num % k);
        }
        return nset;
    }
}
