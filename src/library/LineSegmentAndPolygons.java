package library;

public class LineSegmentAndPolygons {
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