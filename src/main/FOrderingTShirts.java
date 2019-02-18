package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.TreeMap;

public class FOrderingTShirts {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long c = in.nextLong();

        long[] s = in.nextLongArray(2 * n - 1);

        int m = 2 * n - 1;

        long ans = 0;

        for (int i = 0; i < m; i++) {
            s[i] = Math.min(s[i], c);
        }

        long[] t = new long[n];

        t[0] = s[0];

        long[] tp = new long[n];
        long[] sp = new long[m];

        tp[0] = t[0];
        sp[0] = s[0];
        ans = t[0];

        for (int i = 1; i < n; i++) {
            int lo = 0;
            int hi = i - 1;

            long min = Long.MAX_VALUE;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                long ssum = sp[2 * i - 2];

                if (mid > 0) {
                    ssum -= sp[2 * mid - 1];
                }

                if (ssum <= c) {
                    long tsum = t[i - 1];

                    if (mid > 0) {
                        tsum -= t[mid - 1];
                    }
                    min = Math.min(min, tsum - ssum);
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            lo = 0;
            hi = i - 1;

            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                long ssum = sp[2 * i - 2];

                if (mid > 0) {
                    ssum -= sp[2 * mid - 1];
                }

                if (ssum > c) {
                    long tsum = t[i - 1];

                    if (mid > 0) {
                        tsum -= t[mid - 1];
                    }
                    min = Math.min(min, tsum - c);
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }


            tp[i] = Math.min(c, s[2 * i] + s[2 * i - 1] - min);
            ans += tp[i];
            sp[2 * i - 1] = sp[2 * i - 2] + s[2 * i - 1];
            sp[2 * i] = sp[2 * i - 1] + s[2 * i];
        }

//        long[] ss = s.clone();
//
//        TreeMap<Long, Integer> cm = new TreeMap<>();
//
//        long[] fl = new long[m];
//        long[] cb = new long[m];
//        long[] up = new long[m];
//
//        long rs = ss[0];
//
//        int j = 0;
//        long gb = 0;
//        long lb = 0;
//        for (int i = 2; i < m; i += 2) {
//            rs += ss[i - 1];
//            rs += ss[i];
//
//            fl[i] = ss[i - 1];
//
//            add(cm, fl[i]);
//
//            while (j <= i - 2 && rs > c) {
//                long diff = rs - c;
//                if (j % 2 == 1) {
//                    if (s[j] + s[j + 1] <= diff) {
//                        rem(cm, fl[j + 1]);
//                        rs -= s[j] + s[j + 1];
//                        j += 2;
//                    } else {
//                        if (s[j] > diff) {
//                            rem(cm, fl[j + 1]);
//                            long nv = s[j] - diff;
//                            s[j] = nv;
//                            cb[j + 1] = ss[j - 1] + up[j + 1] - nv;
//                            lb = cb[j + 1];
//                        } else {
//                            rem(cm, fl[j + 1]);
//                            diff -= s[j];
//                            cb[j + 1] = up[j + 1] + diff;
//                            lb = cb[j + 1];
//                            j++;
//                        }
//                        rs = c;
//                    }
//                } else {
//                    if (diff >= s[j]) {
//                        rs -= s[j];
//                        j++;
//                    } else {
//                        s[j] = s[j] - diff;
//                        cb[j] = ss[j] + up[j] - s[j];
//                        lb = cb[j];
//                        rs = c;
//                    }
//                }
//            }
//
//
//            if (j + 1 == i) {
//                if (rs > c) {
//                    rem(cm, fl[i]);
//                    long rem = c - s[i];
//                    s[j] = rem;
//                    fl[i] = s[j];
//                    add(cm, fl[i]);
//                }
//
//                if (j > 0) {
//                    gb = Math.min(s[j], ss[j - 1]);
//                    cb[i] = gb;
//                    ans += s[j] - gb;
//                    up[i] = s[j] - gb;
//                } else {
//                    ans += s[j];
//                    gb = 0;
//                    up[i] = s[j];
//                }
//            } else {
//                long maxb = cm.firstKey();
//                long val = lb;
//                val = Math.min(maxb, lb);
//                val -= gb;
//                gb = val;
//                up[i] = s[i - 1] - val;
//                ans += s[i - 1] - val;
//                cb[i] = val;
//            }
//        }

        out.println(ans);
    }

    void rem(TreeMap<Long, Integer> tm, long val) {
        tm.merge(val, -1, (x, y) -> x + y);
        if (tm.get(val) == 0) {
            tm.remove(val);
        }
    }

    void add(TreeMap<Long, Integer> tm, long val) {
        tm.merge(val, 1, (x, y) -> x + y);
    }
}
