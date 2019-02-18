package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;

public class CLexicographicConstraints {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = in.nextIntArray(n);

        int lo = 1;
        int hi = n;

        int ans = n;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (isp(a, n, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        out.println(ans);
    }

    class seg {
        int l;
        int r;
        int val;

        public seg(int l, int r, int val) {
            this.l = l;
            this.r = r;
            this.val = val;
        }
    }

    int max = 1_000_000_000;

    boolean isp(int[] a, int n, int base) {
        ArrayDeque[] segs = new ArrayDeque[n];

        for (int i = 0; i < n; i++) {
            segs[i] = new ArrayDeque<seg>();

        }

        segs[0].add(new seg(0, max - 1, 0));

        for (int i = 1; i < n; i++) {
            if (a[i] > a[i - 1]) {
                segs[i] = segs[i - 1];
            } else {

                segs[i] = segs[i - 1];

                ArrayDeque<seg> pseg = segs[i];

                if (pseg.size() == 1 && pseg.getFirst().val == base - 1)
                    return false;

                int tci = a[i] - 1;

                while (pseg.getLast().l > tci) {
                    pseg.removeLast();
                }

//                int lo = 0;
//                int hi = pseg.size() - 1;
//
//                int segi = -1;
//                while (lo <= hi) {
//                    int mid = (lo + hi) / 2;
//
//                    seg mseg = pseg.get(mid);
//
//                    if (mseg.l <= tci && mseg.r >= tci) {
//                        segi = mid;
//                        break;
//                    } else if (mseg.r < tci) {
//                        lo = mid + 1;
//                    } else {
//                        hi = mid - 1;
//                    }
//                }

                seg aseg = pseg.removeLast();

                if (pseg.isEmpty() && aseg.val == base - 1)
                    return false;

                if (aseg.val == base - 1) {
                    aseg = pseg.removeLast();
                    tci = aseg.r;
                }

                if (tci == aseg.l) {
                    if (!pseg.isEmpty() && pseg.getLast().val == aseg.val + 1) {
                        pseg.getLast().r = aseg.l;
                    } else {
                        segs[i].add(new seg(aseg.l, aseg.l, aseg.val + 1));
                    }
                    if (aseg.l + 1 < max) {
                        segs[i].add(new seg(aseg.l + 1, max - 1, 0));
                    }
                } else {
                    segs[i].add(new seg(aseg.l, tci - 1, aseg.val));
                    segs[i].add(new seg(tci, tci, aseg.val + 1));
                    if (tci + 1 < max) {
                        segs[i].add(new seg(tci + 1, max - 1, 0));
                    }
                }
            }
        }

        return true;
    }
}
