package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class _605C {
    class point {
        double x;
        double y;

        public point() {
        }

        public point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public point(point p) {
            this.x = p.x;
            this.y = p.y;
        }
    }

    class LineSegmentAndPolygons {
        double crossProduct(point p1, point p2, point p0) {
            return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
        }

        double eps = 1e-10;

        Orientation orientationOrderedPoints(point p0, point p1, point p2) {
            double crossProduct = crossProduct(p1, p2, p0);
            if (Math.abs(crossProduct) <= eps) {
                return Orientation.COLLINEAR;
            } else if (crossProduct < 0) {
                return Orientation.RIGHT;
            } else {
                return Orientation.LEFT;
            }
        }

        boolean pointOnLineSegment(point p, point p1, point p2) {
            Orientation or = orientationOrderedPoints(p1, p, p2);

            double minX = Math.min(p1.x, p2.x);
            double maxX = Math.max(p1.x, p2.x);

            double minY = Math.min(p1.y, p2.y);
            double maxY = Math.max(p1.y, p2.y);

            if (or == Orientation.COLLINEAR && minX <= p.x && p.x <= maxX
                    && minY <= p.y && p.y <= maxY) {
                return true;
            }

            return false;
        }

        boolean lineSegmentIntersect(point p1, point p2, point p3, point p4) {
            Orientation or1 = orientationOrderedPoints(p1, p2, p3);
            Orientation or2 = orientationOrderedPoints(p1, p2, p4);
            Orientation or3 = orientationOrderedPoints(p3, p4, p1);
            Orientation or4 = orientationOrderedPoints(p3, p4, p2);

            if (or1 != or2 && or1 != Orientation.COLLINEAR && or2 != Orientation.COLLINEAR
                    && or3 != or4 && or3 != Orientation.COLLINEAR
                    && or4 != Orientation.COLLINEAR)
                return true;

            if (or1 == Orientation.COLLINEAR && pointOnLineSegment(p3, p1, p2))
                return true;

            if (or2 == Orientation.COLLINEAR && pointOnLineSegment(p4, p1, p2))
                return true;

            if (or3 == Orientation.COLLINEAR && pointOnLineSegment(p1, p3, p4))
                return true;

            if (or4 == Orientation.COLLINEAR && pointOnLineSegment(p2, p3, p4))
                return true;

            return false;
        }

        double inf = 1e15;

        boolean pointInsidePolygon(point p, point[] polygonVertices) {
            point extendedPoint = new point(inf, p.y);

            int n = polygonVertices.length;

            int intersectionCount = 0;
            int vertexIntersectionCount = 0;
            for (int i = 0; i < n; i++) {
                int nextVertex = (i + 1) % n;

                if (pointOnLineSegment(p, polygonVertices[i], polygonVertices[nextVertex])) {
                    return true;
                }

                if (pointOnLineSegment(polygonVertices[i], p, extendedPoint)) {
                    vertexIntersectionCount++;
                }
            }

            if (vertexIntersectionCount % 2 == 1) {
                return true;
            }

            for (int i = 0; i < n; i++) {
                int nextVertex = (i + 1) % n;

                if (lineSegmentIntersect(p, extendedPoint, polygonVertices[i],
                        polygonVertices[nextVertex])) {
                    intersectionCount++;
                }
            }

            if (intersectionCount % 2 == 1)
                return true;

            return false;
        }
    }

    enum Orientation {
        LEFT,
        RIGHT,
        COLLINEAR
    }

    public class GrahamScanConvexHull {
        //Confirm what should be the epsilon
        double eps = 1e-10;

        double crossProduct(point p1, point p2, point p0) {
            return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
        }

        Orientation orientationOrderedPoints(point p0, point p1, point p2) {
            double crossProduct = crossProduct(p1, p2, p0);
            if (Math.abs(crossProduct) <= eps) {
                return Orientation.COLLINEAR;
            } else if (crossProduct < 0) {
                return Orientation.RIGHT;
            } else {
                return Orientation.LEFT;
            }
        }

        point[] vertices;
        int n;

        public GrahamScanConvexHull(point[] vertices) {
            this.vertices = vertices;
            this.n = vertices.length;
        }

        void swap(int i, int j) {
            if (i != j) {
                point temp = vertices[i];
                vertices[i] = vertices[j];
                vertices[j] = temp;
            }
        }

        double distanceSquare(point p1, point p2) {
            return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
        }

        Comparator<point> getComparator(point p0) {
            return (point p1, point p2) ->
            {
                Orientation orientation = orientationOrderedPoints(p0, p1, p2);

                int returnVal;
                switch (orientation) {
                    case COLLINEAR:
                        double dis1 = distanceSquare(p0, p1);
                        double dis2 = distanceSquare(p0, p2);

                        returnVal = (int) Math.signum(dis1 - dis2);
                        break;

                    case LEFT:
                        returnVal = -1;
                        break;

                    case RIGHT:
                        returnVal = 1;
                        break;

                    default:
                        throw new RuntimeException();
                }

                return returnVal;
            };
        }

        point[] convexHull() {
            int minPointInd = 0;

            for (int i = 1; i < n; i++) {
                if (vertices[i].x < vertices[minPointInd].x ||
                        (vertices[i].x == vertices[minPointInd].x
                                && vertices[i].y < vertices[minPointInd].y)) {
                    minPointInd = i;
                }
            }

            swap(0, minPointInd);

            Arrays.sort(vertices, 1, n, getComparator(vertices[0]));

            int m = 1;
            for (int i = 1; i < n; i++) {
                while (i < n - 1 &&
                        orientationOrderedPoints(vertices[0], vertices[i], vertices[i + 1])
                                == Orientation.COLLINEAR) {
                    i++;
                }

                vertices[m] = vertices[i];
                m++;
            }

            if (m < 3) {
                return new point[0];
            }

            ArrayDeque<point> stack = new ArrayDeque<>();

            stack.addFirst(vertices[0]);
            stack.addFirst(vertices[1]);
            stack.addFirst(vertices[2]);

            for (int i = 3; i < m; i++) {
                point top = null;
                point nextToTop = null;

                do {
                    if (top != null)
                        stack.removeFirst();
                    top = stack.getFirst();
                    stack.removeFirst();
                    nextToTop = stack.getFirst();
                    stack.addFirst(top);
                } while (orientationOrderedPoints(nextToTop, top, vertices[i]) != Orientation.LEFT);

                stack.addFirst(vertices[i]);
            }

            point[] convexHullVertices = new point[stack.size()];

            for (int i = 0; stack.size() > 0; i++) {
                convexHullVertices[i] = stack.removeFirst();
            }

            return convexHullVertices;
        }
    }

    class LineRectangleIntersection {

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

                if (det == 0) { // Parallel lines case
                    return null;
                } else {
                    point point = new point();

                    //Integer coordinates only when det divides the below numerators
                    point.x = (line.b * c - b * line.c) / det;
                    point.y = (a * line.c - line.a * c) / det;

                    return point;
                }
            }

            public boolean passesThroughSegment(point point, long x1, long x2, long y1, long y2) {
                double expectedC = a * point.x + b * point.y;

                if (x1 > x2) {
                    long t = x1;
                    x1 = x2;
                    x2 = t;
                }

                if (y1 > y2) {
                    long t = y1;
                    y1 = y2;
                    y2 = t;
                }

                return expectedC == c && point.x >= x1 && point.x <= x2 && point.y >= y1 && point.y <= y2;
            }

            double getSlope() {
                return -a / b;
            }
        }

        class rectangle {
            int x1;
            int y1;
            int x2;
            int y2;

            public rectangle(int x1, int y1, int x2, int y2) {
                this.x1 = x1;
                this.y1 = y1;
                this.x2 = x2;
                this.y2 = y2;
            }
        }

        rectangle intersection(rectangle r1, rectangle r2) {
            int x1 = r1.x1;
            int x2 = r1.x2;
            int y1 = r1.y1;
            int y2 = r1.y2;

            int x3 = r2.x1;
            int x4 = r2.x2;
            int y3 = r2.y1;
            int y4 = r2.y2;

            int x5 = max(x1, x3);
            int y5 = max(y1, y3);
            int x6 = min(x2, x4);
            int y6 = min(y2, y4);

            return new rectangle(x5, y5, x6, y6);
        }
    }

     class DotAndCrossProductApplication {

        double crossProductWrtOrigin(point p1, point p2) {
            return p1.x * p2.y - p2.x * p1.y;
        }

        double crossProduct(point p1, point p2, point p0) {
            return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
        }

        double eps = 1e-6;

        Orientation orientationOrderedPoints(point p0, point p1, point p2) {
            double crossProduct = crossProduct(p1, p2, p0);
            if (Math.abs(crossProduct) <= eps) {
                return Orientation.COLLINEAR;
            } else if (crossProduct < 0) {
                return Orientation.RIGHT;
            } else {
                return Orientation.LEFT;
            }
        }

        double distanceSquare(point p1, point p2) {
            return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
        }

        double distance(point p1, point p2) {
            return Math.sqrt(distanceSquare(p1, p2));
        }

        double distanceSquare(point p) {
            return (p.x) * (p.x) + (p.y) * (p.y);
        }

        double distance(point p) {
            return Math.sqrt(distanceSquare(p));
        }

        double dotProduct(point p2, point p1, point p4, point p3) {
            return (p2.x - p1.x) * (p4.x - p3.x) + (p2.y - p1.y) * (p4.y - p3.y);
        }

        double dotProduct(point p1, point p2) {
            return p1.x * p2.x + p1.y * p2.y;
        }

        point vector(point p2, point p1) {
            return new point(p2.x - p1.x, p2.y - p1.y);
        }

        //Angle between line segment (p1, p2) and (p3, p4)
        double angleDotProduct(point p2, point p1, point p4, point p3) {
            double len1 = distance(p1, p2);
            double len2 = distance(p3, p4);

            double dot = dotProduct(p2, p1, p4, p3);

            double angle = Math.acos(dot / (len1 * len2));

            return angle;
        }

        //Angle between vectors to p1 and to p2 from origin
        double angleDotProduct(point p1, point p2) {
            double len1 = distance(p1);
            double len2 = distance(p2);

            double dot = dotProduct(p1, p2);

            double angle = Math.acos(dot / (len1 * len2));

            return angle;
        }

        double anglePolarCoordinates(point p2, point p1, point p4, point p3)
        {
            point vec1 = vector(p2, p1);
            point vec2 = vector(p4, p3);

            double theta1 = Math.atan2(vec1.y, vec1.x);
            double theta2 = Math.atan2(vec2.y, vec2.x);

            return Math.abs(theta1 - theta2);
        }

        double anglePolarCoordinates(point p1, point p2)
        {
            double theta1 = Math.atan2(p1.y, p1.x);
            double theta2 = Math.atan2(p2.y, p1.x);

            return Math.abs(theta1 - theta2);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();

        point pq = new point(p, q);

        point[] ps = new point[n];
        double maxa = 0;
        double maxb = 0;
        for (int i = 0; i < n; i++) {
            ps[i] = new point(in.nextInt(), in.nextInt());
            maxa = Math.max(maxa, ps[i].x);
            maxb = Math.max(maxb, ps[i].y);
        }

        double lo = 0;
        double hi = 1000000;

        double ans = 1e7;

        double mid = 1;

        point[] nps = new point[n + 3];

        for (int j = 0; j < n; j++) {
            nps[j] = new point();
            nps[j].x = mid * ps[j].x;
            nps[j].y = mid * ps[j].y;
        }

        nps[n] = new point(0, 0);
        nps[n + 1] = new point(mid * maxa, 0);
        nps[n + 2] = new point(0, mid * maxb);

        GrahamScanConvexHull cv = new GrahamScanConvexHull(nps);
        point[] cvp = cv.convexHull();

//        LineSegmentAndPolygons lsp = new LineSegmentAndPolygons();
//
//        boolean inside = lsp.pointInsidePolygon(pq, cvp);
//
//        if (inside) {
//            ans = Math.min(ans, mid);
//            hi = mid;
//        } else {
//            lo = mid;
//        }


        LineRectangleIntersection lri = new LineRectangleIntersection();

        LineRectangleIntersection.line pqline = lri.new line(0, 0, p, q);

        LineSegmentAndPolygons lsp = new LineSegmentAndPolygons();

        int np = (10000000 + p - 1) / p;
        int nq = (10000000 + q - 1) / q;

        int minm = Math.min(np, nq);
        np = minm * p;
        nq = minm * q;

        point zp = new point(0, 0);
        point ep = new point(np, nq);

        n = cvp.length;
        point ipf = null;
        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;

            LineRectangleIntersection.line line = lri.new line(cvp[i].x, cvp[i].y, cvp[next].x, cvp[next].y);

            if(lsp.lineSegmentIntersect(zp, ep, cvp[i], cvp[next])) {
                point ip = line.intersect(pqline);

                if (ip.x > 0 && ip.y > 0)
                {
                    ipf = ip;
                    break;
                }
            }
        }

        DotAndCrossProductApplication dcp = new DotAndCrossProductApplication();
        ans = dcp.distance(pq) / dcp.distance(ipf);

        out.println(ans);
    }
}
