package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FMahmoudAndEhabAndYetAnotherXorTask {
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
            int l = in.nextInt();
            int x = in.nextInt();
            qs.add(new q(i, l, x));
        }

        qs.sort((q1, q2) -> q1.l - q2.l);

        Set<Integer> s = new HashSet<>();
        int j = 0;
        int ans = 1;

        s.add(0);

        int[] ansa = new int[q];
        for (int i = 0; i < q; i++) {
            q cq = qs.get(i);

            int l = cq.l;
            int x = cq.x;

            while (j < l) {
                if (s.contains(a[j])) {
                    ans *= 2;
                    ans %= mod;
                } else {
                    HashSet<Integer> ns = new HashSet<>();
                    for (int num : s) {
                        ns.add(num ^ a[j]);
                    }

                    s.addAll(ns);
                }
                j++;
            }

            if (s.contains(x)) {
                ansa[cq.qi] = ans;
            } else {
                ansa[cq.qi] = 0;
            }
        }

        for (int i = 0; i < q; i++) {
            out.println(ansa[i]);
        }
    }

    int mod = 1000000007;
}
