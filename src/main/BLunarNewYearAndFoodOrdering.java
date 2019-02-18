package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.PriorityQueue;

public class BLunarNewYearAndFoodOrdering {
    class dish {
        long remain;
        long cost;
        int ind;

        public dish(long remain, long cost, int ind) {
            this.remain = remain;
            this.cost = cost;
            this.ind = ind;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.ni();
        int m = in.ni();

        long[] a = in.nla(n);
        long[] c = in.nla(n);

        PriorityQueue<dish> dishes = new PriorityQueue<>((d1, d2) ->
        {
            if (d1.cost == d2.cost) {
                return d1.ind - d2.ind;
            } else {
                return (int) Math.signum(d1.cost - d2.cost);
            }
        });

        for (int i = 0; i < n; i++) {
            dishes.add(new dish(a[i], c[i], i));
        }

        long[] nremains = new long[n];

        for (int i = 0; i < n; i++) {
            nremains[i] = a[i];
        }

        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            t--;

            long d = in.nextInt();

            long ans = 0;
            if (nremains[t] >= d) {
                nremains[t] -= d;
                dishes.add(new dish(nremains[t], c[t], t));
                ans += c[t] * d;

                out.println(ans);
            } else {
                ans += nremains[t] * c[t];
                long cd = d - nremains[t];
                nremains[t] = 0;

                while (cd > 0 && !dishes.isEmpty()) {
                    dish curdish = dishes.poll();

                    if (nremains[curdish.ind] != curdish.remain)
                        continue;

                    if (curdish.remain >= cd) {
                        nremains[curdish.ind] -= cd;
                        dishes.add(new dish(nremains[curdish.ind], c[curdish.ind], curdish.ind));
                        ans += cd * c[curdish.ind];
                        cd = 0;
                    } else {
                        cd -= curdish.remain;
                        nremains[curdish.ind] = 0;
                        ans += curdish.remain * c[curdish.ind];
                    }
                }

                if (cd > 0) {
                    out.println(0);
                }else {
                    out.println(ans);
                }
            }
        }
    }
}
