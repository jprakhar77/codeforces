package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ETheFairNutAndRectangles {
    class ConvexHullTrick {

        class point {
            BigDecimal x;
            BigDecimal y;

            public point() {
            }


        }

        class line {
            long a;
            long b;
            long c;

            //ax + by = c
//            public line(double a, double b, double c) {
//                this.a = a;
//                this.b = b;
//                this.c = c;
//            }

            //y = mx + c
            public line(long m, long c) {
                this.a = -m;
                this.b = 1;
                this.c = c;
            }

            //for line passing through (x1,y1) AND (x2,y2)
//            public line(double x1, double y1, double x2, double y2) {
//                this.a = y2 - y1;
//                this.b = x1 - x2;
//                this.c = a * x1 + b * y1;
//            }

            public point intersect(line line) {
                long det = a * line.b - line.a * b;

                if (det == 0) {
                    return null;
                } else {
                    point point = new point();

                    //point.x = (line.b * c - b * line.c) / det;
                    //point.y = (a * line.c - line.a * c) / det;

                    BigDecimal detbd = new BigDecimal(det);
                    point.x = new BigDecimal(c - line.c);
                    point.x = point.x.divide(detbd, MathContext.DECIMAL32);

                    //point.y = new BigDecimal()

                    return point;
                }
            }

            long getSlope() {
                return -a;
            }

//            double getSlope() {
//                return -a / b;
//            }
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

        public boolean isLeft(point p1, point p2) {
            return p1.x.compareTo(p2.x) <= 0;
        }

        public boolean isRight(point p1, point p2) {
            return p1.x.compareTo(p2.x) >= 0;
        }

//        public double searchMinSortedSlopeDesc(double x) {
//            assert !stack.isEmpty();
//            if (stack.size() == 1 || stack.get(1).intersectionPoint.x > x) {
//                line line = stack.get(0).line;
//                return line.getSlope() * x + line.c;
//            }
//
//            int lo = 1;
//            int hi = stack.size() - 1;
//
//            int ans = 1;
//            while (lo <= hi) {
//                int mid = (lo + hi) / 2;
//
//                if (stack.get(mid).intersectionPoint.x <= x) {
//                    ans = Math.max(ans, mid);
//                    lo = mid + 1;
//                } else {
//                    hi = mid - 1;
//                }
//            }
//
//            line line = stack.get(ans).line;
//            return line.getSlope() * x + line.c;
//        }

        public long searchMinSortedSlopeAsc(long x) {
            BigDecimal xbd = new BigDecimal(x);

            assert !stack.isEmpty();
            if (stack.size() == 1) {
                line line = stack.get(0).line;
                return line.getSlope() * x + line.c;
            }

            int lo = 1;
            int hi = stack.size() - 1;

            int ans = 0;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                if (stack.get(mid).intersectionPoint.x.compareTo(xbd) >= 0) {
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

    class rect {
        int x;
        int y;
        long a;
        long val;


        public rect(int x, int y, long a) {
            this.x = x;
            this.y = y;
            this.a = a;
            this.val = (long) x * y - a;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        rect[] rects = new rect[n];

        for (int i = 0; i < n; i++) {
            rects[i] = new rect(in.nextInt(), in.nextInt(), in.nextLong());

        }

        Arrays.sort(rects, (r1, r2) -> r1.x - r2.x);

        long ans = 0;

        long[] dp = new long[n];
        dp[0] = rects[0].val;

        ConvexHullTrick cht = new ConvexHullTrick();
        cht.addToLastMaximumSlope(cht.new line(rects[0].x, -dp[0]));

        ans = dp[0];

        for (int i = 1; i < n; i++) {
            long val = cht.searchMinSortedSlopeAsc(rects[i].y);

            dp[i] = Math.max(rects[i].val - val, rects[i].val);

            ans = Math.max(ans, dp[i]);

            cht.addToLastMaximumSlope(cht.new line(rects[i].x, -dp[i]));
        }

        out.println(ans);
    }
}
