package main;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class Leet2 {
    class Solution {
        Set<Integer> s = new HashSet<>();
        boolean[] rr;

        public int totalNQueens(int n) {
            this.n = n;
            rr = new boolean[n];
            bt(0);

            return ans;
        }

        int ans = 0;
        int n;

        void bt(int si) {
            if (si == n) {
                ans++;
                return;
            }

            for (int i = 0; i < n; i++) {
                if (!rr[i]) {
                    int diff = i - si;
                    if (s.contains(diff)) {
                        continue;
                    }
                    rr[i] = true;
                    s.add(diff);
                    bt(si + 1);
                    s.remove(diff);
                    rr[i] = false;
                }
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
//        int fc = 0;
//        for (int n = 0; n <= 1000; n++) {
//            int[] pn = new int[51];
//            for (int i = 1; i <= 50; i++) {
//                pn[i] = pn[i - 1] + i;
//            }
//
//            int[] aa = new int[101];
//
//            for (int i = 1; i <= 100; i++) {
//                if (i % 2 == 0) {
//                    aa[i] = pn[i / 2] + pn[i / 2 - 1];
//                } else {
//                    aa[i] = 2 * pn[i / 2];
//                }
//            }
//
//            StringBuilder ans = new StringBuilder();
//
//            int sum = n;
//            char glc = 'a';
//            int c = 0;
//            for (int j = 99; j >= 2; j -= 2) {
//                while (sum >= aa[j]) {
//                    StringBuilder cs = new StringBuilder();
//
//                    char lc = '\0';
//                    if (glc == 'a') {
//                        for (int i = 0; i < j; i++) {
//                            if (i % 2 == 0) {
//                                cs.append('a');
//                                lc = 'a';
//                            } else {
//                                cs.append('b');
//                                lc = 'b';
//                            }
//                        }
//
//                        ans.append(cs);
//                        sum -= aa[j];
//                        if (sum > 0) {
//                            for (int k = 0; k < c; k++)
//                                //ans.append(lc);
//                                c++;
//                            //ans.append(lc);
//                        }
//                        glc = lc;
//                    } else {
//                        for (int i = 0; i < j; i++) {
//                            if (i % 2 == 0) {
//                                cs.append('b');
//                                lc = 'b';
//                            } else {
//                                cs.append('a');
//                                lc = 'a';
//                            }
//                        }
//
//                        ans.append(cs);
//                        sum -= aa[j];
//                        if (sum > 0) {
//                            for (int k = 0; k < c; k++)
//                                //ans.append(lc);
//                                //ans.append(lc);
//                                c++;
//                        }
//                        glc = lc;
//                    }
//                }
//            }
//
//            if (sum > 0) {
//                ans.append("ab");
//            }
//
//            String s = ans.toString();
//
//            int ansc = 0;
//            for (int i = 0; i < s.length(); i++) {
//                for (int j = i; j < s.length(); j++) {
//                    String cs = s.substring(i, j + 1);
//                    String rs = new StringBuilder(cs).reverse().toString();
//
//                    boolean poss = true;
//                    for (int k = 0; k < cs.length(); k++) {
//                        if (cs.charAt(k) == rs.charAt(k)) {
//                            poss = false;
//                            break;
//                        }
//                    }
//
//                    if (poss) {
//                        ansc++;
//                    }
//                }
//            }
//
//            if (ansc != n) {
//                System.out.println("Expected:" + n + " Got : " + ansc);
//                fc++;
//            } else {
//            }
//        }
//
//        System.out.println(fc);

        int c = 10000;
        long l = 10000000000l;
        System.out.println(c * l);
    }

    int max = 1000000000;

    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = System.in;
        OutputStream outputStream = new FileOutputStream("out.out");
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Leet2 solver = new Leet2();
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
