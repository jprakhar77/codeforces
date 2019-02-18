package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class FMahmoudAndEhabAndYetAnotherXorTaskGauss {
    class q {
        int qi;
        int l;
        int x;

        public q(int qi, int l, int x) {
            this.qi = qi;
            this.l = l;
            this.x = x;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int q = in.nextInt();

        int[] a = in.nextIntArray(n);

        List<q> qs = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            int l = in.nextInt() - 1;
            int x = in.nextInt();
            qs.add(new q(i, l, x));
        }

        int[] tp = new int[n + 1];

        tp[0] = 1;

        for (int i = 1; i <= n; i++) {
            tp[i] = 2 * tp[i - 1];
            tp[i] %= mod;
        }
        qs.sort((q1, q2) -> q1.l - q2.l);

        int x = 0;
        int[] fa = new int[20];
        int[] sa = new int[20];

        int[] ans = new int[q];

        int cr = 0;
        for (int i = 0; i < n; i++) {
            int num = a[i];

            int lz = Integer.numberOfLeadingZeros(num);
            for (int j = 0; j < 20; j++) {
                if (fa[j] == 0) {
                    sa[j] = num;
                    if (num != 0)
                        cr = j + 1;
                    break;
                } else {
                    sa[j] = fa[j];
                    int clz = Integer.numberOfLeadingZeros(fa[j]);

                    if (clz == lz) {
                        num ^= sa[j];
                        lz = Integer.numberOfLeadingZeros(num);
                    } else if (clz > lz) {
                        for (int k = j; k < 19; k++) {
                            sa[k + 1] = fa[k];
                        }
                        sa[j] = num;
                        if (num != 0)
                            cr++;
                        break;
                    }
                }
            }

            fa = sa.clone();
            q cq;
            while (x < qs.size() && (cq = qs.get(x)).l == i) {
                int cn = cq.x;

                lz = Integer.numberOfLeadingZeros(cn);
                for (int j = 0; j < 20; j++) {
                    if (fa[j] == 0) {
                        break;
                    } else {
                        int clz = Integer.numberOfLeadingZeros(fa[j]);

                        if (clz == lz) {
                            cn ^= fa[j];
                            lz = Integer.numberOfLeadingZeros(cn);
                        } else if (clz > lz) {
                            break;
                        }
                    }
                }

                if (cn == 0) {
                    ans[cq.qi] = tp[(i + 1) - cr];
                }

                x++;
            }

        }

        for (int val : ans) {
            out.println(val);
        }

    }

    int mod = 1000000007;
}
