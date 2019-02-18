package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class FIvanAndBurgers {
    class query {
        int l;
        int r;
        int i;
        int ans;

        public query(int l, int r, int i) {
            this.l = l;
            this.r = r;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        n = in.nextInt();

        a = in.nextIntArray(n);

        q = in.nextInt();

        queries = new query[q];

        for (int i = 0; i < q; i++) {
            queries[i] = new query(in.nextInt() - 1, in.nextInt() - 1, i);
        }

        for (int i = 0; i < q; i++) {
            query cq = queries[i];

            if (cq.l == cq.r) {
                cq.ans = a[cq.l];
            }
        }

        dac(0, n - 1, queries, q);

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < q; i++) {
            ans.append(queries[i].ans);
            ans.append("\n");
        }

        out.println(ans);
    }

    int n;
    int q;
    int[] a;
    query[] queries;

    void dac(int s, int e, query[] cq, int sz) {
        int cqsz = sz;

        if (s + 1 > e)
            return;

        int mid = (s + e) / 2;

        int[][] fa = new int[mid - s + 1][20];
        int[][] sa = new int[e - mid][20];

        fa[mid - s][0] = a[mid];

        for (int i = mid - s - 1; i >= 0; i--) {
            int num = a[i + s];

            int lz = Integer.numberOfLeadingZeros(num);
            for (int j = 0; j < 20; j++) {
                if (fa[i + 1][j] == 0) {
                    fa[i][j] = num;
                    break;
                } else {
                    int clz = Integer.numberOfLeadingZeros(fa[i + 1][j]);

                    if (clz == lz) {
                        fa[i][j] = fa[i + 1][j];
                        num ^= fa[i + 1][j];
                        lz = Integer.numberOfLeadingZeros(num);
                    } else if (clz > lz) {
                        for (int k = j; k < 19; k++) {
                            fa[i][k + 1] = fa[i + 1][k];
                        }
                        fa[i][j] = num;
                        break;
                    } else {
                        fa[i][j] = fa[i + 1][j];
                    }
                }
            }
        }

        sa[0][0] = a[mid + 1];

        for (int i = 1; i < e - mid; i++) {
            int num = a[i + mid + 1];

            int lz = Integer.numberOfLeadingZeros(num);
            for (int j = 0; j < 20; j++) {
                if (sa[i - 1][j] == 0) {
                    sa[i][j] = num;
                    break;
                } else {
                    int clz = Integer.numberOfLeadingZeros(sa[i - 1][j]);

                    if (clz == lz) {
                        sa[i][j] = sa[i - 1][j];
                        num ^= sa[i - 1][j];
                        lz = Integer.numberOfLeadingZeros(num);
                    } else if (clz > lz) {
                        for (int k = j; k < 19; k++) {
                            sa[i][k + 1] = sa[i - 1][k];
                        }
                        sa[i][j] = num;
                        break;
                    } else {
                        sa[i][j] = sa[i - 1][j];
                    }
                }
            }
        }

        //List<query> lq = new ArrayList<>();
        //List<query> rq = new ArrayList<>();
        query[] lq = new query[cqsz];
        query[] rq = new query[cqsz];
        int x = 0, y = 0;
        for (int i = 0; i < cqsz; i++) {
            query q = cq[i];
            int cl = q.l;
            int cr = q.r;

            if (cl <= mid && cr > mid && cl >= s && cr <= e) {
                int[] ff = fa[cl - s].clone();
                int[] ss = sa[cr - mid - 1].clone();

                int[] ans = new int[20];

                int ind = 0;
                for (int j = 19; j >= 0; j--) {
                    int num = -1;

                    for (int k = 0; k < ff.length; k++) {
                        if (((1 << j) & ff[k]) != 0 && ff[k] <= (1 << (j + 1)) - 1) {
                            if (num == -1) {
                                num = ff[k];
                                ans[ind++] = num;
                            } else {
                                ff[k] ^= num;
                            }
                        }
                    }

                    for (int k = 0; k < ss.length; k++) {
                        if (((1 << j) & ss[k]) != 0 && ss[k] <= (1 << (j + 1)) - 1) {
                            if (num == -1) {
                                num = ss[k];
                                ans[ind++] = num;
                            } else {
                                ss[k] ^= num;
                            }
                        }
                    }
                }

                int fans = 0;
                for (int j = 0; j < ans.length; j++) {
                    fans = Math.max(fans, fans ^ ans[j]);
                }

                q.ans = fans;
            } else {
                if (cl <= mid) {
                    lq[x++] = q;
                } else {
                    rq[y++] = q;
                }
            }
        }

        dac(s, mid, lq, x);
        dac(mid + 1, e, rq, y);
    }
}
