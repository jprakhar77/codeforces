package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

public class RectangleFit {
    class point {
        int i;
        int x;
        int y;

        public point(int i, int x, int y) {
            this.i = i;
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            point point = (point) o;
            return i == point.i &&
                    x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(i, x, y);
        }

        public int getI() {
            return i;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int a = in.nextInt();

        point[] points = new point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new point(i, in.nextInt(), in.nextInt());
        }

        TreeSet<point> xsorted = new TreeSet<>(Comparator.comparing(point::getX).thenComparing(point::getI));
        TreeSet<point> ysorteddesc = new TreeSet<>(Comparator.comparing(point::getY).reversed().thenComparing(point::getI));

        for (int i = 0; i < n; i++) {
            xsorted.add(points[i]);
            //ysorteddesc.add(points[i]);
        }

        int ans = 0;

        for (int x = 1; x <= 1000000; x++) {
            while (xsorted.size() > 0 && xsorted.first().x <= x) {
                ysorteddesc.add(xsorted.pollFirst());
            }
            long maxy = a / x;

            while (ysorteddesc.size() > 0 && ysorteddesc.first().y > maxy) {
                ysorteddesc.pollFirst();
            }

            ans = Math.max(ans, ysorteddesc.size());
        }

        out.println(ans);
    }
}
