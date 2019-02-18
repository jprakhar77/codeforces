package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class _1036E {
    class Geometry {

        class point {
            long x;
            long y;

            public point() {
            }

            public point(long x, long y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                point point = (point) o;
                return Double.compare(point.x, x) == 0 &&
                        Double.compare(point.y, y) == 0;
            }

            @Override
            public int hashCode() {

                return Objects.hash(x, y);
            }
        }

        class line {
            long a;
            long b;
            long c;

            //ax + by = c
            public line(long a, long b, long c) {
                this.a = a;
                this.b = b;
                this.c = c;
            }

            //y = mx + c
            public line(long m, long c) {
                this.a = -m;
                this.b = 1;
                this.c = c;
            }

            //for line passing through (x1,y1) AND (x2,y2)
            public line(long x1, long y1, long x2, long y2) {
                this.a = y2 - y1;
                this.b = x1 - x2;
                this.c = a * x1 + b * y1;
            }

            public point intersect(line line) {
                long det = a * line.b - line.a * b;

                if (det == 0) {
                    return null;
                } else {
                    point point = new point();

                    if ((line.b * c - b * line.c) % det != 0)
                        return null;
                    if ((a * line.c - line.a * c) % det != 0)
                        return null;

                    point.x = (line.b * c - b * line.c) / det;
                    point.y = (a * line.c - line.a * c) / det;

                    return point;
                }
            }

            public boolean passesThroughSegment(point point, long x1, long x2, long y1, long y2) {
                long expectedC = a * point.x + b * point.y;

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

            long getSlope() {
                return -a / b;
            }
        }
    }

    class line {
        int x1;
        int y1;
        int x2;
        int y2;

        public line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        Geometry geometry = new Geometry();

        line[] rawlines = new line[n];

        Geometry.line[] lines = new Geometry.line[n];

        for (int i = 0; i < n; i++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();

            lines[i] = geometry.new line(x1, y1, x2, y2);
            rawlines[i] = new line(x1, y1, x2, y2);
        }

        long ans = coveringIntegerCoordinates(rawlines[0]);

        for (int i = 1; i < n; i++) {
            ans += coveringIntegerCoordinates(rawlines[i]);
            Set<Geometry.point> set = new HashSet<>();
            for (int j = 0; j < i; j++) {
                Geometry.point intersect = lines[j].intersect(lines[i]);
                if (intersect != null && lines[i].passesThroughSegment(intersect, rawlines[i].x1, rawlines[i].x2, rawlines[i].y1, rawlines[i].y2) && lines[j].passesThroughSegment(intersect, rawlines[j].x1, rawlines[j].x2, rawlines[j].y1, rawlines[j].y2))
                    set.add(intersect);
            }
            ans -= set.size();
        }

        out.print(ans);
    }

    int coveringIntegerCoordinates(line line) {
        return gcd(Math.abs(line.x1 - line.x2), Math.abs(line.y1 - line.y2)) + 1;
    }

    int gcd(int a, int b) {
        if (b == 0)
            return a;
        else return gcd(b, a % b);
    }
}
