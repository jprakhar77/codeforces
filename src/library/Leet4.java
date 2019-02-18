package library;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Leet4 {
    class Solution {
        class DsuInteger {

            public DsuInteger(int n) {
                this.n = n;
                this.parent = new int[n];
                this.rank = new int[n];
                this.size = new int[n];
            }

            int[] parent;
            int[] rank;
            int[] size;
            int n;


            int createSet(int x) {
                parent[x] = x;
                rank[x] = 0;
                size[x] = 1;
                return x;
            }

            int findSet(int x) {
                int par = parent[x];
                if (x != par) {
                    parent[x] = findSet(par);
                    return parent[x];
                }
                return par;
            }

            int mergeSets(int x, int y) {
                int rx = findSet(x);
                int ry = findSet(y);

                if (rx == ry) {
                    return rx;
                }

                int fp = -1;

                if (rank[rx] > rank[ry]) {
                    parent[ry] = rx;
                    fp = rx;
                } else {
                    parent[rx] = ry;
                    fp = ry;
                }

                size[fp] = size[rx] + size[ry];

                if (rank[rx] == rank[ry]) {
                    rank[ry] = rank[ry] + 1;
                }

                return fp;
            }
        }
        public boolean equationsPossible(String[] e) {

            int n = e.length;

            DsuInteger dsu = new DsuInteger(26);

            for(int i = 0; i < 26; i++)
            {
                dsu.createSet(i);
            }
            for(int i = 0; i < n; i++)
            {
                String sub = e[i].substring(2, 3);

                char a = e[i].charAt(0);
                char b = e[i].charAt(3);
                if(sub.equals("=") && a != b)
                {
                    int n1 = a - 'a';
                    int n2 = b - 'a';

                    dsu.mergeSets(n1, n2);
                }
            }

            for(int i = 0; i < n; i++)
            {
                String sub = e[i].substring(2, 3);

                char a = e[i].charAt(0);
                char b = e[i].charAt(3);

                if(sub.equals("!"))
                {
                    if(a == b)
                        return false;

                    int s1 = dsu.findSet(a - 'a');
                    int s2 = dsu.findSet(b - 'a');

                    if(s1 == s2)
                    {
                        return false;
                    }
                }

            }

            return true;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Solution solution = new Solution();

//        int[][] grid = {{0, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
//        int[][] hits = {{1, 3}, {3, 5}, {0, 3}, {3, 3}, {1, 1}, {4, 2}, {1, 0}, {3, 0}, {4, 5}, {2, 1}, {4, 4}, {4, 0}, {2, 4}, {2, 5}, {3, 4}, {0, 5}, {0, 4}, {3, 2}, {1, 5}, {4, 1}, {2, 2}, {0, 2}};
//
//        int[] ans = solution.hitBricks(grid, hits);
//
//        for (int i = 0; i < ans.length; i++) {
//            System.out.println(ans[i]);
//        }
//
//        System.out.println(1000000000000000000l % 1000000007);

        String[] s = {"a==b","b!=a"};

        boolean ans = solution.equationsPossible(s);

        System.out.println(ans);
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Leet4 solver = new Leet4();
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
