package main;

import java.io.*;
import java.util.*;

public class Main {
    class edge {
        int u;
        int v;
        int w;

        public edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    class DSU<T> {

        Map<T, T> parent = new HashMap<>();
        Map<T, Integer> rank = new HashMap<>();

        Map<T, Integer> size = new HashMap<>();

        T createSet(T x) {
            parent.put(x, x);
            rank.put(x, 0);
            size.put(x, 1);
            return x;
        }

        T findSet(T x) {
            T par = parent.get(x);
            if (!x.equals(par)) {
                parent.put(x, findSet(par));
                return parent.get(x);
            }
            return par;
        }

        T mergeSets(T x, T y) {
            T rx = findSet(x);
            T ry = findSet(y);

            if (rx.equals(ry)) {
                return rx;
            }

            T fp = null;
            int psize = size.get(rx) + size.get(ry);

            if (rank.get(rx) > rank.get(ry)) {
                parent.put(ry, rx);
                fp = rx;
            } else {
                parent.put(rx, ry);
                fp = ry;
            }

            if (rank.get(rx).equals(rank.get(ry))) {
                rank.put(ry, rank.get(ry) + 1);
            }

            size.put(fp, psize);

            return fp;
        }
    }

    int mod = (int) 1e9;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        List<edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();

            edges.add(new edge(u - 1, v - 1, w));

        }

        edges.sort((e1, e2) -> e2.w - e1.w);

        long[] cumw = new long[m];

        cumw[0] = edges.get(m - 1).w;
        for (int i = 1; i < m; i++) {
            int ai = m - 1 - i;
            cumw[i] = cumw[i - 1] + edges.get(ai).w;
        }

        DSU<Integer> dsu = new DSU<>();

        for (int i = 0; i < n; i++) {
            dsu.createSet(i);
        }

        long ans = 0;
        for (int i = 0; i < m; i++) {
            edge ce = edges.get(i);

            int r1 = dsu.findSet(ce.u);
            int r2 = dsu.findSet(ce.v);

            if (r1 != r2) {
                long s1 = dsu.size.get(r1);
                int s2 = dsu.size.get(r2);

                int ai = m - 1 - i;
                long cans = s1 * s2;
                cans %= mod;
                cans *= cumw[ai];
                cans %= mod;

                ans += cans;
                ans %= mod;
            }

            dsu.mergeSets(r1, r2);
        }

        out.println(ans);
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Main solver = new Main();
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

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}
