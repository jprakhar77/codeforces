package main;

import java.io.*;
import java.util.InputMismatchException;

public class NthMagicalNumber {
    class Solution {
        int mod = (int) 1e9 + 7;

        public int nthMagicalNumber(int n, int a, int b) {
            if (a > b) {
                int t = a;
                a = b;
                b = t;
            }

            long val = 0;

            long g = gcd(a, b);

            long lcm = (a * b) / g;

            long ca = lcm / a;
            long cb = lcm / b;

            long tc = ca + cb - 1;

            val = val + lcm * (n / tc);
            long tcr = n % tc;

            long amul = 0;
            long bmul = 0;

            long vala = val;
            long valb = val;

            while (tcr > 0) {
                long nv1 = vala + (amul + 1) * a;
                long nv2 = valb + (bmul + 1) * b;

                if (nv1 < nv2) {
                    amul++;
                    val = vala + amul * a;
                } else {
                    bmul++;
                    val = valb + bmul * b;
                }
                tcr--;
            }

            return (int) (val % mod);
        }

        long gcd(long a, long b) {
            if (b == 0)
                return a;
            else
                return gcd(b, a % b);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Solution solution = new Solution();
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();

        out.println(solution.nthMagicalNumber(n, a, b));
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        NthMagicalNumber solver = new NthMagicalNumber();
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
