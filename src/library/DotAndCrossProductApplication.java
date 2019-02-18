package library;


public class DotAndCrossProductApplication {

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

    double crossProductWrtOrigin(point p1, point p2) {
        return p1.x * p2.y - p2.x * p1.y;
    }

    double crossProduct(point p1, point p2, point p0) {
        return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
    }

    enum Orientation {
        LEFT,
        RIGHT,
        COLLINEAR
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
