package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class Firestarter {
    double max = 1e15;

    class event {
        double d;
        double t;

        public event(double d, double t) {
            this.d = d;
            this.t = t;
        }
    }

    class edge {
        int a;
        int b;
        int l;
        List<event> events = new ArrayList<>();

        public edge(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public edge(int a, int b, int l) {
            this.a = a;
            this.b = b;
            this.l = l;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            edge edge = (edge) o;
            return a == edge.a &&
                    b == edge.b;
        }

        @Override
        public int hashCode() {

            return Objects.hash(a, b);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();

        edge[] edges = new edge[m];

        Map<edge, edge> edgeinfo = new HashMap<>();

        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int l = in.nextInt();

            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }

            edges[i] = new edge(a, b, l);
            edgeinfo.put(edges[i], edges[i]);
        }

        double[] vertexmin = new double[n];

        Arrays.fill(vertexmin, max);

        for (int i = 0; i < q; i++) {
            long t = in.nextLong();
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int x = in.nextInt();

            edge edge = edgeinfo.get(new edge(Math.min(a, b), Math.max(a, b)));

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
                x = edge.l - x;
            }

            edge.events.add(new event(x, t));

            vertexmin[a] = Math.min(vertexmin[a], t + x);
            vertexmin[b] = Math.min(vertexmin[b], t + edge.l - x);

            //edgeinfo.remove(edge);
            //edgeinfo.put(edge, edge);
        }

        double ans = 0;
        for (edge edge : edgeinfo.keySet()) {
            //edge = edgeinfo.get(edge);
            ans = Math.max(cal(edge, vertexmin), ans);
        }

        out.println(ans);
    }

    double cal(edge edge, double[] vertexmin) {
        double lo = 0;
        double hi = 1e15;

        double ans = 1e15;
        for (int i = 0; i < 1000; i++) {
            double mid = (lo + hi) / 2;

            if (isposs(edge, mid, vertexmin)) {
                ans = Math.min(ans, mid);
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return ans;
    }

    class range {
        double st;
        double en;

        public range(double st, double en) {
            this.st = st;
            this.en = en;
        }

        public double getSt() {
            return st;
        }

        public double getEn() {
            return en;
        }
    }

    boolean isposs(edge edge, double ct, double[] vertexmin) {
        List<range> ranges = new ArrayList<>();

        if (vertexmin[edge.a] < ct) {
            ranges.add(new range(edge.a, edge.a + ct - vertexmin[edge.a]));
        }

        if (vertexmin[edge.b] < ct) {
            ranges.add(new range(edge.b - (ct - vertexmin[edge.b]), edge.b));
        }

        for (int i = 0; i < edge.events.size(); i++) {
            event event = edge.events.get(i);

            if (event.t < ct) {
                ranges.add(new range(event.d - (ct - event.t), event.d + (ct - event.t)));
            }
        }

        return iscover(edge.a, edge.b, ranges);
    }

    boolean iscover(int s, int e, List<range> ranges) {

        if (ranges.size() == 0) {
            if (s == e)
                return true;
            return false;
        }

        ranges.sort(Comparator.comparing(range::getSt));

        double start = ranges.get(0).st;

        if (start > s) {
            return false;
        }

        double end = ranges.get(0).en;

        for (int i = 1; i < ranges.size(); i++) {
            range range = ranges.get(i);
            if (range.st > end)
                return false;
            if (range.en <= end)
                continue;
            else {
                end = Math.max(end, range.en);
            }
        }

        if (end < e)
            return false;

        return true;
    }
}
