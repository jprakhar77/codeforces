package main;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class timus_1185 {
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
        double eps = 1e-6;

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

    //Angle between vectors to p1 and to p2 from origin
    double angleDotProduct(point p1, point p2) {
        double len1 = distance(p1);
        double len2 = distance(p2);

        double dot = dotProduct(p1, p2);

        double angle = Math.acos(dot / (len1 * len2));

        return angle;
    }

    double anglePolarCoordinates(point p2, point p1, point p4, point p3) {
        point vec1 = vector(p2, p1);
        point vec2 = vector(p4, p3);

        double theta1 = Math.atan2(vec1.y, vec1.x);
        double theta2 = Math.atan2(vec2.y, vec2.x);

        return Math.abs(theta1 - theta2);
    }

    double anglePolarCoordinates(point p1, point p2) {
        double theta1 = Math.atan2(p1.y, p1.x);
        double theta2 = Math.atan2(p2.y, p1.x);

        return Math.abs(theta1 - theta2);
    }

    double angleDotProduct(point p2, point p1, point p4, point p3) {
        double len1 = distance(p1, p2);
        double len2 = distance(p3, p4);

        double dot = dotProduct(p2, p1, p4, p3);

        double angle = Math.acos(dot / (len1 * len2));

        return angle;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int l = in.nextInt();

        point[] points = new point[n];

        for (int i = 0; i < n; i++) {
            points[i] = new point(in.nextInt(), in.nextInt());
        }

        GrahamScanConvexHull cv = new GrahamScanConvexHull(points);

        point[] cvp = cv.convexHull();

        int m = cvp.length;

        double[] dis = new double[m];

        point[] vec = new point[m];

        double ans = 0;
        for (int i = 0; i < m; i++) {
            int next = (i + 1) % m;

            dis[i] = distance(cvp[i], cvp[next]);

            vec[i] = vector(cvp[next], cvp[i]);

            ans += dis[i];
        }

        for (int i = 0; i < m; i++) {
            int next = (i + 1) % m;

            double angle = angleDotProduct(vec[i], vec[next]);

            ans += (angle * l);
        }

        System.out.println((long) (ans + 0.5));
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        timus_1185 solver = new timus_1185();
        solver.solve(1, in, out);
        out.close();
    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

        public void println(long i) {
            writer.println(i);
        }

    }

    static class InputReader {
        private boolean finished = false;

        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int peek() {
            if (numChars == -1) {
                return -1;
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    return -1;
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                if (Character.isValidCodePoint(c)) {
                    res.appendCodePoint(c);
                }
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        private String readLine0() {
            StringBuilder buf = new StringBuilder();
            int c = read();
            while (c != '\n' && c != -1) {
                if (c != '\r') {
                    buf.appendCodePoint(c);
                }
                c = read();
            }
            return buf.toString();
        }

        public String readLine() {
            String s = readLine0();
            while (s.trim().length() == 0) {
                s = readLine0();
            }
            return s;
        }

        public String readLine(boolean ignoreEmptyLines) {
            if (ignoreEmptyLines) {
                return readLine();
            } else {
                return readLine0();
            }
        }

        public BigInteger readBigInteger() {
            try {
                return new BigInteger(nextString());
            } catch (NumberFormatException e) {
                throw new InputMismatchException();
            }
        }

        public char nextCharacter() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            return (char) c;
        }

        public double nextDouble() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E') {
                    return res * Math.pow(10, nextInt());
                }
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E') {
                        return res * Math.pow(10, nextInt());
                    }
                    if (c < '0' || c > '9') {
                        throw new InputMismatchException();
                    }
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public boolean isExhausted() {
            int value;
            while (isSpaceChar(value = peek()) && value != -1) {
                read();
            }
            return value == -1;
        }

        public String next() {
            return nextString();
        }

        public SpaceCharFilter getFilter() {
            return filter;
        }

        public void setFilter(SpaceCharFilter filter) {
            this.filter = filter;
        }

        public int[] nextIntArray(int n) {
            int[] array = new int[n];
            for (int i = 0; i < n; ++i) array[i] = nextInt();
            return array;
        }

        public int[] nextSortedIntArray(int n) {
            int array[] = nextIntArray(n);
            Arrays.sort(array);
            return array;
        }

        public int[] nextSumIntArray(int n) {
            int[] array = new int[n];
            array[0] = nextInt();
            for (int i = 1; i < n; ++i) array[i] = array[i - 1] + nextInt();
            return array;
        }

        public long[] nextLongArray(int n) {
            long[] array = new long[n];
            for (int i = 0; i < n; ++i) array[i] = nextLong();
            return array;
        }

        public long[] nextSumLongArray(int n) {
            long[] array = new long[n];
            array[0] = nextInt();
            for (int i = 1; i < n; ++i) array[i] = array[i - 1] + nextInt();
            return array;
        }

        public long[] nextSortedLongArray(int n) {
            long array[] = nextLongArray(n);
            Arrays.sort(array);
            return array;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }

    }
}
