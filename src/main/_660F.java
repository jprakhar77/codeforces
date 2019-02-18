package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class _660F {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        long ans = dac(a, 0, n - 1, n);

        out.println(ans);
    }

    class val {
        long s1;
        long s2;
        int i;
        val prev;
        val next;

        public val(long s1, long s2, int i) {
            this.s1 = s1;
            this.s2 = s2;
            this.i = i;
        }

        long get(int mul) {
            return s1 * mul + s2;
        }
    }

    long dac(long[] a, int s, int e, int n) {

        if (s == e) {
            return Math.max(0, a[s]);
        }

        int mid = (s + e) / 2;

        long ans1 = dac(a, s, mid, n);
        long ans2 = dac(a, mid + 1, e, n);

        long se = 0;
        long su = 0;
        List<val> vl = new ArrayList<>();

        for (int i = mid + 1; i <= e; i++) {
            se += a[i];
            su += (i - mid) * a[i];
            val cv = new val(se, su, i);
            vl.add(cv);
        }

        vl.sort((v1, v2) ->
        {
            if (v1.s1 != v2.s1)
                return (int) Math.signum(v1.s1 - v2.s1);
            else
                return (int) Math.signum(v1.s2 - v2.s2);
        });

        val head = vl.get(0);

        val cn = head;
        for (int i = 1; i < vl.size(); i++) {
            cn.next = vl.get(i);
            vl.get(i).prev = cn;
            cn = cn.next;
        }

        cn.next = null;

        for (int i = 1; i < vl.size(); i++) {
            val cv = vl.get(i);
            while (cv.prev != null) {
                if (cv.prev.s1 == cv.s1 || (cv.prev.s2 < cv.s2)) {
                    cv.prev = cv.prev.prev;

                    if (cv.prev == null)
                        head = cv;
                    else
                        cv.prev.next = cv;
                } else
                    break;
            }
        }

        cn = head;

        while (cn.next != null) {
            if (cn.get(0) <= cn.next.get(0)) {
                cn = cn.next;
            } else
                break;
        }

        long cans = cn.s2;

        se = 0;
        su = 0;
        for (int i = mid; i >= s; i--) {
            int mul = mid - i + 1;

            se += a[i];
            su += se;

            while (cn.next != null) {
                if (cn.get(mul) <= cn.next.get(mul)) {
                    cn = cn.next;
                } else
                    break;
            }

            cans = Math.max(cans, su + cn.get(mul));

        }

        return Math.max(cans, Math.max(ans1, ans2));
    }
}
