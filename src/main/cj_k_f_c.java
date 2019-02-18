package main;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class cj_k_f_c {
    class BasicSeries {

        double eps = 1e-6;

        long sumofGeometricSeries(int firstTerm, double ratio, int numberOfTerms) {
            if (ratio == 1) {
                return firstTerm * numberOfTerms;
            } else {
                double rPowerN = Math.pow(ratio, numberOfTerms);
                long val = firstTerm;
                val *= (1 - rPowerN);
                val /= (1 - ratio);

                return (long) (val + eps);
            }
        }

        double pow(double a, long p) {
            if (p == 0) {
                return 1;
            }

            double t = pow(a, p / 2);

            if (p % 2 != 0) {
                return (t * t * a);
            } else {
                return (t * t);
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        for (int tt = 1; tt <= t; tt++) {
            int l = in.nextInt();
            int n = in.nextInt();
            long k = in.nextLong();

            long ans = 0;

            if (k <= n) {
                out.println("Case #" + tt + ": " + k);
                continue;
            }

            if (l == 1) {
                out.println("Case #" + tt + ": " + 0);
                continue;
            }

//            long num = l;
//            int max = 1;
//
//            while (num < 10000000000000000l) {
//                num *= l;
//                max++;
//            }
//
//            max = Math.min(max, 41);

            int nn = Math.min(100, n);

            //System.out.println(tt + " " + nn);

            if (n % 2 == 1) {
                int mid = n / 2;

                int s = mid - 40;
                int e = mid + 40;

                for (int ps = 1; ps <= nn; ps++) {
                    long rk = k;
                    int[] pal = new int[ps];

                    Arrays.fill(pal, -1);

                    if (ps % 2 == 1) {
                        s = Math.max(0, mid - ps / 2);
                        e = Math.min(n - 1, mid + ps / 2);
                    } else {
                        s = Math.max(0, mid - (ps / 2) + 1);
                        e = Math.min(n - 1, mid + ps / 2);
                    }

                    boolean isp = true;
                    for (int x = s, y = e; x <= y; x++, y--) {
                        int i = x - s;
                        int opt = -1;
                        int next = y - s;
                        long pcnt = -1;
                        for (int sch = (i == 0 ? 1 : 0); sch < l; sch++) {

                            pal[i] = sch;

                            pal[next] = sch;

                            long cnt = (i == 0 ? n : 0);
                            for (int exps = i; exps <= ps; exps++) {
                                if (exps > 0)
                                    cnt += cal(pal, exps, i, l);
                                if (cnt >= inf)
                                    break;
                            }

                            if (cnt == rk) {
                                pcnt = 0;
                                opt = sch;
                                rk = 0;
                                break;
                            }

                            pal[i] = -1;
                            pal[next] = -1;

                            if (cnt < rk) {
                                opt = sch;
                                pcnt = cnt;
                                continue;
                            } else {
                                break;
                            }
                        }
                        if (opt < 0) {
                            isp = false;
                            break;
                        } else {
                            rk -= pcnt;
                            pal[i] = opt;
                            pal[next] = opt;
                        }
                    }

                    if (isp && rk == 0) {
                        ans = n - nn + ps;
                        break;
                    }
                }
            } else {
                int mid = n / 2;

                int s = mid - 41;
                int e = mid + 39;

                for (int ps = 1; ps <= nn; ps++) {
                    long rk = k;
                    int[] pal = new int[ps];

                    Arrays.fill(pal, -1);

                    if (ps % 2 == 1) {
                        s = Math.max(0, mid - ps / 2);
                        e = Math.min(n - 1, mid + ps / 2);
                    } else {
                        s = Math.max(0, mid - (ps / 2));
                        e = Math.min(n - 1, mid + ps / 2 - 1);
                    }

                    boolean isp = true;
                    for (int x = s, y = e; x <= y; x++, y--) {
                        int i = x - s;
                        int opt = -1;
                        int next = y - s;
                        long pcnt = -1;
                        for (int sch = (i == 0 ? 1 : 0); sch < l; sch++) {

                            pal[i] = sch;

                            pal[next] = sch;

                            long cnt = (i == 0 ? n : 0);
                            for (int exps = i; exps <= ps; exps++) {
                                if (exps > 0)
                                    cnt += cal(pal, exps, i, l);
                                if (cnt >= inf)
                                    break;
                            }

                            if (cnt == rk) {
                                pcnt = 0;
                                opt = sch;
                                rk = 0;
                                break;
                            }

                            pal[i] = -1;
                            pal[next] = -1;

                            if (cnt < rk) {
                                opt = sch;
                                pcnt = cnt;
                                continue;
                            } else {
                                break;
                            }
                        }
                        if (opt < 0) {
                            isp = false;
                            break;
                        } else {
                            rk -= pcnt;
                            pal[i] = opt;
                            pal[next] = opt;
                        }
                    }

                    if (isp && rk == 0) {
                        ans = n - nn + ps;
                        break;
                    }
                }
            }

            out.println("Case #" + tt + ": " + ans);
        }


    }

    long inf = (long) 1e15;

    long cal(int[] pal, int exps, int fs, int l) {
        int n = pal.length;

        int lfs = n - fs;

        long ans = 1;

        if (fs == 0) {
            ans = 0;
            for (int k = 0; k < n - exps + 1; k++) {
                long ca = 1;
                for (int i = k, j = exps - 1; i < k + exps; i++, j--) {
                    if (i == 0 || i == n - 1) {
                        ca *= (pal[i] - 1);
                    } else {
                        ca *= l;
                    }
                    if (ca >= inf)
                        return inf;
                }
                ans += ca;
                if (ans >= inf)
                    return inf;
            }
            return ans;
        }
        for (int i = 0, j = exps - 1; i <= j; i++, j--) {
            if (i == j) {
                if (pal[i] == -1) {
                    ans *= l;
                } else if (i >= fs && i < lfs) {
                    ans *= pal[i];
                }
            } else {
                if (i >= fs && j < lfs) {
                    if (pal[i] == -1 && pal[j] == -1) {
                        ans *= l;
                    } else if (pal[i] == -1) {
                        ans *= pal[j];
                    } else if (pal[j] == -1) {
                        ans *= pal[i];
                    } else {
                        ans *= Math.min(pal[i], pal[j]);
                    }
                } else {
                    if (i < fs && j >= lfs) {
                        if (pal[i] != pal[j]) {
                            return 0;
                        }
                    } else if (i < fs) {
                        if (pal[j] != -1 && pal[j] <= pal[i])
                            return 0;
                    } else if (j >= lfs) {
                        if (pal[i] != -1 && pal[i] <= pal[j])
                            return 0;
                    }
                }
            }
            if (ans >= inf)
                return inf;
        }

        return ans;
    }

    public static void main(String[] args) throws Throwable {
        InputStream inputStream = new FileInputStream("in.in");
        OutputStream outputStream = new FileOutputStream("owt");
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        cj_k_f_c solver = new cj_k_f_c();
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
