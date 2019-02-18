package library;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;

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