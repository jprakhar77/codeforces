package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class ZFrog3 {
    class ConvexHullTrick {

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

                    //Be careful of decimal precision issues here. BigDecimal can be helpful.
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

        //Use this when the lines are given in order of decreasing slopes
        public void addToLastMinimumSlope(line l3) {
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

        //Use this if the lines were given in the order of decreasing slopes
        public double searchMinSortedSlopeDesc(double x) {
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

        //Use this when the lines are given in the order of increasing slopes
        //Note that lines from the front of the list to the back are in the order from right to left
        //if you draw them graphically
        public void addToLastMaximumSlope(line l3) {
            if (stack.isEmpty()) {
                stack.add(new ExtendedLine(l3, null));
                return;
            }

            while (stack.size() > 1) {
                ExtendedLine l2 = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);
                ExtendedLine l1 = stack.get(stack.size() - 1);

                point ip = l3.intersect(l1.line);
                if (isRight(ip, l2.intersectionPoint)) {
                    continue;
                } else {
                    stack.add(l2);
                    stack.add(new ExtendedLine(l3, l3.intersect(l2.line)));
                    return;
                }
            }

            stack.add(new ExtendedLine(l3, l3.intersect(stack.get(stack.size() - 1).line)));
        }

        public boolean isRight(point p1, point p2) {
            return p1.x >= p2.x;
        }

        //Use this if the lines were given in the order of increasing slopes
        //Note that lines from the front of the list to the back are in the order from right to left
        //if you draw them graphically
        public double searchMinSortedSlopeAsc(double x) {
            assert !stack.isEmpty();
            if (stack.size() == 1 || stack.get(1).intersectionPoint.x <= x) {
                line line = stack.get(0).line;
                return line.getSlope() * x + line.c;
            }

            int lo = 1;
            int hi = stack.size() - 1;

            int ans = 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                if (stack.get(mid).intersectionPoint.x >= x) {
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

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        long c = in.nextLong();

        long[] h = in.nextLongArray(n);

        double[] dp = new double[n];

        dp[0] = 0;

        ConvexHullTrick cht = new ConvexHullTrick();

        cht.addToLastMinimumSlope(cht.new line(-2 * h[0], h[0] * h[0] + c));

        for (int i = 1; i < n; i++) {
            double val = cht.searchMinSortedSlopeDesc(h[i]);

            dp[i] = val + h[i] * h[i];

            cht.addToLastMinimumSlope(cht.new line(-2 * h[i], h[i] * h[i] + c + dp[i]));
        }

        out.println((long) (dp[n - 1] + eps));
    }

    double eps = 1e-6;
}
