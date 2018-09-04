package library;

import java.util.ArrayList;
import java.util.List;

public class ConvexHullTrick {

    class point {
        double x;
        double y;

        public point() {
        }

        public point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    class line {
        double a;
        double b;
        double c;

        //ax + by = c
        public line(double a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        //y = mx + c
        public line(double m, double c) {
            this.a = -m;
            this.b = 1;
            this.c = c;
        }

        //for line passing through (x1,y1) AND (x2,y2)
        public line(double x1, double y1, double x2, double y2) {
            this.a = y2 - y1;
            this.b = x1 - x2;
            this.c = a * x1 + b * y1;
        }

        public point intersect(line line) {
            double det = a * line.b - line.a * b;

            if (det == 0) {
                return null;
            } else {
                point point = new point();

                point.x = (line.b * c - b * line.c) / det;
                point.y = (a * line.c - line.a * c) / det;

                return point;
            }
        }

        double getSlope() {
            return -a / b;
        }
    }

    class ExtendedLine {
        line line;
        point intersectionPoint;

        public ExtendedLine(ConvexHullTrick.line line, point intersectionPoint) {
            this.line = line;
            this.intersectionPoint = intersectionPoint;
        }
    }

    List<ExtendedLine> stack = new ArrayList<>();

    public void addToLast(line l3) {
        if (stack.isEmpty()) {
            stack.add(new ExtendedLine(l3, null));
            return;
        }

        while (stack.size() > 1) {
            ExtendedLine l2 = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            ExtendedLine l1 = stack.get(stack.size() - 1);

            point ip = l3.intersect(l1.line);
            if (isLeft(ip, l2.intersectionPoint)) {
                continue;
            } else {
                stack.add(l2);
                stack.add(new ExtendedLine(l3, l3.intersect(l2.line)));
                return;
            }
        }

        stack.add(new ExtendedLine(l3, l3.intersect(stack.get(stack.size() - 1).line)));
    }

    public boolean isLeft(point p1, point p2) {
        return p1.x <= p2.x;
    }

    public double searchMin(double x) {
        assert !stack.isEmpty();
        if (stack.size() == 1 || stack.get(1).intersectionPoint.x > x) {
            line line = stack.get(0).line;
            return line.getSlope() * x + line.c;
        }

        int lo = 1;
        int hi = stack.size() - 1;

        int ans = 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (stack.get(mid).intersectionPoint.x <= x) {
                ans = Math.max(ans, mid);
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        line line = stack.get(ans).line;
        return line.getSlope() * x + line.c;
    }
}
