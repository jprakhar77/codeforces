package library;

public class Geometry {

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
}
