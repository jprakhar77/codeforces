package codejam;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class R22017B {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int t = in.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            int n = in.nextInt();
            int c = in.nextInt();
            int m = in.nextInt();

            List[] sa = new List[n];

            for (int i = 0; i < n; i++) {
                sa[i] = new ArrayList<>();
            }

            int[] min = new int[c];
            int fmin = 0;
            for (int i = 0; i < m; i++) {

                int p = in.nextInt() - 1;
                int b = in.nextInt() - 1;

                sa[p].add(b);
                min[b]++;
            }

            for (int i = 0; i < c; i++) {
                fmin = Math.max(fmin, min[i]);
            }


            for (int i = fmin; i <= m; i++) {
                if (sa[0].size() > i)
                    continue;
                int rem = i - sa[0].size();

                int ans = 0;
                boolean isp = true;
                for (int j = 1; j < n; j++) {

                    if (sa[j].size() > i) {
                        int cr = sa[j].size() - i;
                        if (cr > rem) {
                            isp = false;
                            break;
                        }
                        rem -= cr;
                        ans += cr;
                    } else {
                        rem += i - sa[j].size();
                    }
                }

                if (!isp) {
                    continue;
                }

                out.println("Case #" + tc + ": " + i + " " + ans);
                break;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("/Users/prakharjain/Downloads/cj.obj");
        OutputStream outputStream = new FileOutputStream("/Users/prakharjain/Downloads/cj.out");
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        R22017B solver = new R22017B();
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
