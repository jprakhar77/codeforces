//package main;

import java.io.*;
import java.util.InputMismatchException;

public class _100923F {
    class GaussianElimination {
        int n;
        double[][] mat;

        boolean isSingular;

        public GaussianElimination(int n, double[][] mat) {
            this.n = n;
            this.mat = new double[n][n + 1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= n; j++) {
                    this.mat[i][j] = mat[i][j];
                }
            }
        }

        double[] compute() {
            rowEchelonForm();

            if (isSingular) {
                return null;
            }

            return backSubstitution();
        }

        double[][] rowEchelonForm() {
            for (int i = 0; i < n; i++) {
                swapForNumericalStability(i);

                if (mat[i][i] == 0) {
                    isSingular = true;
                    return mat;
                }

                for (int j = i + 1; j < n; j++) {
                    double factor = mat[j][i] / mat[i][i];

                    for (int k = i; k <= n; k++) {
                        mat[j][k] -= factor * mat[i][k];
                    }

                    mat[j][i] = 0;
                }
            }

            return mat;
        }

        void swapForNumericalStability(int row) {
            double valMax = mat[row][row];
            int indMax = row;
            for (int i = row + 1; i < n; i++) {
                if (mat[i][row] > valMax) {
                    valMax = mat[i][row];
                    indMax = i;
                }
            }
            if (indMax != row) {
                swapRows(row, indMax);
            }
        }

        void swapRows(int row1, int row2) {
            for (int i = 0; i <= n; i++) {
                double temp = mat[row1][i];
                mat[row1][i] = mat[row2][i];
                mat[row2][i] = temp;
            }
        }

        double[] backSubstitution() {
            double[] x = new double[n];

            x[n - 1] = mat[n - 1][n] / mat[n - 1][n - 1];
            for (int i = n - 2; i >= 0; i--) {
                double val = mat[i][n];
                for (int j = i + 1; j < n; j++) {
                    val -= x[j] * mat[i][j];
                }
                x[i] = val / mat[i][i];
            }

            return x;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();

            double[][] mat = new double[n][n + 1];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = in.nextInt();
                }
            }

            double[][] p = new double[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mat[j][n] = ((i == j) ? 1 : 0);
                }
                GaussianElimination gauss = new GaussianElimination(n, mat);
                double[] x = gauss.compute();
                assert x != null;

                for (int j = 0; j < n; j++) {
                    p[j][i] = x[j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    out.print(p[i][j] + " ");
                }
                out.println();
            }
        }

    }

    public static void main(String[] args) throws Throwable {
        InputStream inputStream = new FileInputStream("bujor.IN");
        OutputStream outputStream = new FileOutputStream("bujor.out");
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        _100923F solver = new _100923F();
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
