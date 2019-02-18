import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Solution {
    class Pair<S, T> {
        S a;
        T b;

        public Pair(S a, T b) {
            this.a = a;
            this.b = b;
        }

        public S getKey() {
            return a;
        }

        public T getValue() {
            return b;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int t = in.nextInt();

        List<Pair<Integer, Integer>> pl = new ArrayList<>();

        pl.add(new Pair<>(0, 0));

        //System.out.println(pl.size());
        int[][] dp = new int[501][501];

        for (int r = 0; r <= 33; r++) {
            for (int b = 0; b <= 33; b++) {
                if (r + b == 0)
                    continue;
                for (int i = 500; i >= r; i--) {
                    for (int j = 500; j >= b; j--) {
                        dp[i][j] = Math.max(dp[i][j], 1 + dp[i - r][j - b]);
                    }
                }
            }
        }

        for (int tc = 1; tc <= t; tc++) {
            int r = in.nextInt();
            int b = in.nextInt();

            System.out.println("Case #" + tc + ": " + dp[r][b]);
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Solution solver = new Solution();
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
