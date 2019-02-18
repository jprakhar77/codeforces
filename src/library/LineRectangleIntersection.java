package library;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class LineRectangleIntersection {
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
