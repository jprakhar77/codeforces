package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;

public class _965D {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int w = in.nextInt();
        int l = in.nextInt();

        int[] a = new int[w];

        int sum = 0;
        for (int i = 0; i < w - 1; i++) {
            a[i] = in.nextInt();
            sum += a[i];
        }

        int lo = 0;
        int hi = sum;

        int ans = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            a[w - 1] = mid;
            if (check(l, w, a, mid)) {
                ans = Math.max(ans, mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        out.println(ans);
    }


    boolean check(int l, int w, int[] a, int f) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        dq.add(f);

        for (int i = 0; i < w; i++) {
            while (dq.size() > l)
                dq.removeLast();

            int cs = a[i];

            while (cs > 0 && dq.size() > 0) {
                int ln = dq.getLast();

                if (ln <= cs) {
                    cs -= ln;
                    dq.removeLast();
                    continue;
                } else {
                    ln -= cs;
                    cs = 0;
                    dq.removeLast();
                    dq.addLast(ln);
                    break;
                }
            }

            if (cs == 0) {
                dq.addFirst(a[i]);
            } else {
                dq.addFirst(a[i] - cs);
            }
        }

        if (dq.getFirst() == f)
            return true;
        return false;
    }
}
