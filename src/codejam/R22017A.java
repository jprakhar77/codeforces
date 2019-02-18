package codejam;

import java.io.*;
import java.util.InputMismatchException;

public class R22017A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        int c = 1;
        while (t-- > 0) {
            int n = in.nextInt();
            int p = in.nextInt();

            int[] a = new int[n];

            int ans = 0;

            int[] r = new int[4];

            if (p == 2) {
                for (int i = 0; i < n; i++) {
                    r[in.nextInt() % 2]++;
                }

                ans = r[0];

                ans += (r[1] + 1) / 2;

            } else if (p == 3) {
                for (int i = 0; i < n; i++) {
                    r[in.nextInt() % 3]++;
                }

                ans = r[0];

                ans += Math.min(r[1], r[2]);

                int min = Math.min(r[1], r[2]);
                r[1] -= min;
                r[2] -= min;

                int max = Math.max(r[1], r[2]);

                ans += (max + 2) / 3;
            } else {
                for (int i = 0; i < n; i++) {
                    r[in.nextInt() % 4]++;
                }

                ans = r[0];

                int min = Math.min(r[3], r[1]);

                ans += min;

                r[3] -= min;
                r[1] -= min;

                int max = Math.max(r[3], r[1]);

                while (max > 1 && r[2] > 0) {
                    max -= 2;
                    r[2]--;

                    ans++;
                }

                if (max > 1) {
                    ans += (max + 3) / 4;
                } else if (max == 1) {
                    ans += r[2] / 2;
                    ans++;
                } else {
                    if (r[2] % 2 == 1)
                        ans += (r[2] / 2) + 1;
                    else
                        ans += r[2] / 2;
                }
            }

            out.println("Case #" + c + ": " + ans);
            c++;
        }
    }

    public static void main(String[] args) throws  Throwable{
        InputStream inputStream = new FileInputStream("/Users/prakharjain/Downloads/cj.obj");
        OutputStream outputStream = new FileOutputStream("/Users/prakharjain/Downloads/cj.out");
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        R22017A solver = new R22017A();
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
