package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class agc18c {
    int mod = 998244353;

    class p {
        int a;
        int b;
        int c;

        public p(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int bval() {
            return b - a;
        }

        public int cval() {
            return c - a;
        }

        public int aval() {
            return c - b;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int x = in.nextInt();
        int y = in.nextInt();
        int z = in.nextInt();

        int n = x + y + z;

        p[] ps = new p[n];

        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            ps[i] = new p(a, b, c);
        }

        PriorityQueue<p> pqxb = new PriorityQueue<>((p1, p2) -> p2.bval() - p1.bval());
        PriorityQueue<p> pqxc = new PriorityQueue<>((p1, p2) -> p2.cval() - p1.cval());
        PriorityQueue<p> pqyc = new PriorityQueue<>((p1, p2) -> p2.aval() - p1.aval());

        List<p> zl = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            pqxb.add(ps[i]);
            //pqxc.add(ps[i]);
        }

        for (int i = x; i < x + y; i++) {
            p cp = ps[i];
            long profit = (cp.a - cp.b);
            profit += pqxb.peek().bval();

            if (profit > 0) {
                p np = pqxb.poll();
                pqxb.add(cp);
                pqyc.add(np);
            } else {
                pqyc.add(cp);
            }
        }

        for (p cp : pqxb) {
            pqxc.add(cp);
        }

        for (int i = x + y; i < n; i++) {
            p cp = ps[i];

            long px = (cp.a - cp.c);
            px += pqxc.peek().cval();

            long py = (cp.b - cp.c);
            py += pqyc.peek().aval();

            if (Math.max(px, py) > 0) {
                if (px >= py) {
                    p np = pqxc.poll();
                    pqxc.add(cp);
                    zl.add(np);
                } else {
                    p np = pqyc.poll();
                    pqyc.add(cp);
                    zl.add(np);
                }
            } else {
                zl.add(cp);
            }
        }

        long ans = 0;
        for (p cp : pqxc) {
            ans += cp.a;
        }

        for (p cp : pqyc) {
            ans += cp.b;
        }

        for (p cp : zl) {
            ans += cp.c;
        }

        out.println(ans);
    }

}
