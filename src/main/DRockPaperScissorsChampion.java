package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DRockPaperScissorsChampion {
    class SegmentTree {
        int[] st;
        int n;

        public SegmentTree(int n) {
            st = new int[4 * n];
            this.n = n;
        }

        void build(int i, int[] a, int r1, int r2) {
            if (r1 == r2) {
                st[i] = a[r1];
            } else {
                build(i * 2 + 1, a, r1, (r1 + r2) / 2);
                build(i * 2 + 2, a, (r1 + r2) / 2 + 1, r2);

                st[i] = st[i * 2 + 1] + st[i * 2 + 2];
            }
        }

        int query(int i, int ra, int rb, int r1, int r2) {
            if (ra > r2 || rb < r1) {
                return 0;
            }

            if (ra >= r1 && rb <= r2) {
                return st[i];
            }

            int p1 = query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2);
            int p2 = query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2);

            return p1 + p2;
        }

        int update(int i, int ra, int rb, int ind, int val) {
            if (ra == rb && rb == ind) {
                st[i] = val;
                return st[i];
            }

            if (ra > ind || rb < ind) {
                return st[i];
            }

            int p1 = update(i * 2 + 1, ra, (ra + rb) / 2, ind, val);
            int p2 = update(i * 2 + 2, (ra + rb) / 2 + 1, rb, ind, val);

            return st[i] = p1 + p2;
        }

        int first(int i, int ra, int rb) {
            if (ra == rb) {
                if (st[i] > 0)
                    return ra;
                else return -1;
            }

            if (st[i * 2 + 1] > 0) {
                return first(i * 2 + 1, ra, (ra + rb) / 2);
            } else {
                return first(i * 2 + 2, (ra + rb) / 2 + 1, rb);
            }
        }

        int last(int i, int ra, int rb) {
            if (ra == rb) {
                if (st[i] > 0)
                    return ra;
                else return -1;
            }

            if (st[i * 2 + 2] > 0) {
                return last(i * 2 + 2, (ra + rb) / 2 + 1, rb);
            } else {
                return last(i * 2 + 1, ra, (ra + rb) / 2);
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int q = in.nextInt();

        StringBuilder s = new StringBuilder(in.next());

        int[] ra = new int[n];
        int[] pa = new int[n];
        int[] sa = new int[n];

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'R') {
                ra[i] = 1;
                tr++;
            } else if (s.charAt(i) == 'P') {
                pa[i] = 1;
                tp++;
            } else {
                sa[i] = 1;
                ts++;
            }
        }

        SegmentTree sr = new SegmentTree(n);
        sr.build(0, ra, 0, n - 1);

        SegmentTree sp = new SegmentTree(n);
        sp.build(0, pa, 0, n - 1);

        SegmentTree ss = new SegmentTree(n);
        ss.build(0, sa, 0, n - 1);

        int i = -1;
        do {
            if (i >= 0) {
                int p = in.nextInt();
                char ch = in.nextCharacter();

                p--;
                if (s.charAt(p) != ch) {
                    if (s.charAt(p) == 'R') {
                        ra[p] = 0;
                        tr--;
                        sr.update(0, 0, n - 1, p, 0);
                    } else if (s.charAt(p) == 'P') {
                        pa[p] = 0;
                        sp.update(0, 0, n - 1, p, 0);
                    } else {
                        sa[p] = 0;
                        ss.update(0, 0, n - 1, p, 0);
                    }

                    if (ch == 'R') {
                        ra[p] = 1;
                        sr.update(0, 0, n - 1, p, 1);
                    } else if (ch == 'P') {
                        pa[p] = 1;
                        sp.update(0, 0, n - 1, p, 1);
                    } else {
                        sa[p] = 1;
                        ss.update(0, 0, n - 1, p, 1);
                    }

                    s.setCharAt(p, ch);
                }
            }

            int ans = n;

            ans -= cal(pa, sp, sa, ss, ra, sr, n);
            ans -= cal(ra, sr, pa, sp, sa, ss, n);
            ans -= cal(sa, ss, ra, sr, pa, sp, n);

            out.println(ans);

            i++;
        } while (i < q);
    }

    int tp = 0;
    int tr = 0;
    int ts = 0;

    int cal(int[] fa, SegmentTree fs, int[] sa, SegmentTree ss, int[] ta, SegmentTree ts, int n) {

        int tf = ts.first(0, 0, n - 1);

        if (tf == -1) {
            int sf = ss.first(0, 0, n - 1);
            if (sf == -1) {
                return 0;
            } else {
                return fs.query(0, 0, n - 1, 0, n - 1);
            }
        }

        int tl = ts.last(0, 0, n - 1);

        int sf = ss.first(0, 0, n - 1);

        int ans = 0;
        if (sf != -1 && sf < tf) {
            ans += fs.query(0, 0, n - 1, sf, tf);
        }

        int sl = ss.last(0, 0, n - 1);

        if (sl > tl) {
            ans += fs.query(0, 0, n - 1, tl, sl);
        }

        return ans;

//        long scnt = ts.query(0, 0, n - 1, 0, n - 1);
//
//        if (scnt == 0) {
//            long tcnt = ss.query(0, 0, n - 1, 0, n - 1);
//            if (tcnt == 0) {
//                return 0;
//            } else
//                return (int) fs.query(0, 0, n - 1, 0, n - 1);
//        }
//
//        int ans = 0;
//        int ind2 = -1;
//        if (sa[0] == 1) {
//            int lo = 0;
//            int hi = n - 1;
//
//            int ind = -1;
//            while (lo <= hi) {
//                int mid = (lo + hi) / 2;
//
//                long cnt = ss.query(0, 0, n - 1, 0, mid);
//
//                if (cnt == mid + 1) {
//                    lo = mid + 1;
//                    ind = mid;
//                } else {
//                    hi = mid - 1;
//                }
//            }
//
//            if (ind < n - 1 && fa[ind + 1] == 1) {
//                lo = ind + 1;
//                hi = n - 1;
//
//                ind2 = ind + 1;
//                while (lo <= hi) {
//                    int mid = (lo + hi) / 2;
//
//                    long cnt = fs.query(0, 0, n - 1, ind + 1, mid);
//
//                    if (cnt == mid - ind) {
//                        lo = mid + 1;
//                        ind2 = mid;
//                    } else {
//                        hi = mid - 1;
//                    }
//                }
//
//                ans += (ind2 - ind);
//            }
//        }
//
//
//        if (sa[n - 1] == 1) {
//            int lo = 0;
//            int hi = n - 1;
//
//            int ind = n;
//            while (lo <= hi) {
//                int mid = (lo + hi) / 2;
//
//                long cnt = ss.query(0, 0, n - 1, mid, n - 1);
//
//                if (cnt == n - mid) {
//                    hi = mid - 1;
//                    ind = mid;
//                } else {
//                    lo = mid + 1;
//                }
//            }
//
//            if (ind > 0 && fa[ind - 1] == 1 && ind2 < ind - 1) {
//                lo = 0;
//                hi = ind - 1;
//
//                int ind3 = ind - 1;
//                while (lo <= hi) {
//                    int mid = (lo + hi) / 2;
//
//                    long cnt = fs.query(0, 0, n - 1, mid, ind - 1);
//
//                    if (cnt == ind - mid) {
//                        hi = mid - 1;
//                        ind3 = mid;
//                    } else {
//                        lo = mid + 1;
//                    }
//                }
//
//                ans += (ind - ind3);
//            }
//        }
//
//        return ans;
    }
}
