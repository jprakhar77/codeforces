package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;

public class AppyLovesOne {
    class MinSegmentTree {

        int[] st;
        int[] a;
        int n;

        public MinSegmentTree(int n, int[] a) {
            this.a = a;
            st = new int[4 * n];
            this.n = n;
        }

        int build(int i, int r1, int r2) {
            if (r1 == r2) {
                st[i] = r1;
                return st[i];
            } else {
                int fi = build(i * 2 + 1, r1, (r1 + r2) / 2);
                int si = build(i * 2 + 2, (r1 + r2) / 2 + 1, r2);

                st[i] = (a[fi] >= a[si]) ? fi : si;

                return st[i];
            }
        }

        int query(int i, int ra, int rb, int r1, int r2) {
            if (ra > r2 || rb < r1) {
                return -1;
            }

            if (ra >= r1 && rb <= r2) {
                return st[i];
            }

            int p1 = query(i * 2 + 1, ra, (ra + rb) / 2, r1, r2);
            int p2 = query(i * 2 + 2, (ra + rb) / 2 + 1, rb, r1, r2);

            if (p1 == -1) {
                return p2;
            } else if (p2 == -1) {
                return p1;
            } else {
                return a[p1] >= a[p2] ? p1 : p2;
            }
        }

    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int k = in.nextInt();

        int[] a = in.nextIntArray(n);

        String s = in.next();

        int[] b = new int[2 * n];

        System.arraycopy(a, 0, b, 0, n);
        System.arraycopy(a, 0, b, n, n);


        int[] loi = new int[2 * n];

        if (b[2 * n - 1] == 1) {
            loi[2 * n - 1] = 2 * n - 1;
        } else {
            loi[2 * n - 1] = -1;
        }

        for (int i = 2 * n - 2; i >= 0; i--) {
            if (b[i] == 0) {
                loi[i] = -1;
            } else {
                if (b[i + 1] == 1) {
                    loi[i] = loi[i + 1];
                } else {
                    loi[i] = i;
                }
            }
        }

        int[] c = new int[2 * n];

        c[0] = b[0];
        for (int i = 1; i < 2 * n; i++) {
            if (b[i] == 1) {
                c[i] = c[i - 1] + b[i];

                if (c[i] > k)
                    c[i] = k;
            } else {
                c[i] = 0;
            }
        }

        int[] d = new int[2 * n];

        for (int i = 0; i < 2 * n; i++) {
            if (b[i] == 1) {
                int li = loi[i];
                int fi = i - c[i] + 1;

                if (li - fi + 1 <= k) {
                    d[i] = c[i];
                }
            }
        }

        MinSegmentTree st = new MinSegmentTree(2 * n, d);

        st.build(0, 0, 2 * n - 1);

        int[] ans = new int[n + 1];

        Arrays.fill(ans, -1);

        int cpos = n;

        for (int i = 0; i < q; i++) {
            char ch = s.charAt(i);

            if (ch == '!') {
                cpos--;
                if (cpos < 0) {
                    cpos += (n + 1);
                }
            } else {
                if (ans[cpos] != -1) {
                    out.println(ans[cpos]);
                } else {
                    int last = cpos + n - 1;

                    int li = loi[cpos];

//                    if (li >= last) {
//                        ans[cpos] = n;
//                    } else if (li != -1) {
//                        ans[cpos] = li - cpos + 1;
//
//                        ans[cpos] = Math.max(ans[cpos], c[st.query(0, 0, 2 * n - 1, li + 1, last)]);
//                    } else {
//                        ans[cpos] = c[st.query(0, 0, 2 * n - 1, cpos, last)];
//                    }

                    if (li == -1) {
                        int fi = cpos;

                        int t1 = c[last];

                        int ali = last;

                        if (t1 >= 1) {
                            ali = last - t1;

                            if (t1 <= k) {
                                ans[cpos] = t1;
                            }
                        }

                        if (fi <= ali) {
                            ans[cpos] = Math.max(ans[cpos], c[st.query(0, 0, 2 * n - 1, fi, ali)]);
                        }
                    } else {
                        int fi = li + 1;

                        if (li - cpos + 1 <= k) {
                            ans[cpos] = li - cpos + 1;
                        }

                        int t1 = c[last];

                        int ali = last;

                        if (t1 >= 1) {
                            ali = last - t1;

                            if (t1 <= k) {
                                ans[cpos] = Math.max(ans[cpos], t1);
                            }
                        }

                        if (fi <= ali) {
                            ans[cpos] = Math.max(ans[cpos], c[st.query(0, 0, 2 * n - 1, fi, ali)]);
                        }
                    }

                    out.println(ans[cpos]);
                }
            }
        }

    }
}
