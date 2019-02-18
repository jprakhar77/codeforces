package fastio;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.*;
import java.util.function.BiFunction;

public class InputReader {
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

    public String ns() {
        return next();
    }

    public int ni() {
        return nextInt();
    }

    public long nl() {
        return nextLong();
    }

    public int[] nia(int n) {
        return nextIntArray(n);
    }

    public long[] nla(int n) {
        return nextLongArray(n);
    }

    public String[] ng(int n) {
        String[] g = new String[n];

        for (int i = 0; i < n; i++) {
            g[i] = ns();
        }

        return g;
    }

    public char[][] ng(int n, int m) {
        char[][] g = new char[n][m];

        for (int i = 0; i < n; i++) {
            g[i] = ns().toCharArray();
        }

        return g;
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

    public void readArray(int[] a, int n, int offset) {
        for (int i = 0; i < n; i++) {
            a[i] = nextInt() - offset;
        }
    }

    public void readArray(long[] a, int n, long offset) {
        for (int i = 0; i < n; i++) {
            a[i] = nextLong() - offset;
        }
    }

    public void readUndirectedGraph(List[] g, int numOfVertices, int numOfEdges, int offset) {
        for (int i = 0; i < numOfVertices; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < numOfEdges; i++) {
            int u = nextInt() - offset;
            int v = nextInt() - offset;

            if (u == v)
                continue;

            g[u].add(v);
            g[v].add(u);
        }
    }

    public void readDirectedGraph(List[] g, int numOfVertices, int numOfEdges, int offset) {
        for (int i = 0; i < numOfVertices; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < numOfEdges; i++) {
            int u = nextInt() - offset;
            int v = nextInt() - offset;

            g[u].add(v);
        }
    }

    public void readUndirectedGraph(Set[] g, int numOfVertices, int numOfEdges, int offset) {
        for (int i = 0; i < numOfVertices; i++) {
            g[i] = new HashSet();
        }

        for (int i = 0; i < numOfEdges; i++) {
            int u = nextInt() - offset;
            int v = nextInt() - offset;

            g[u].add(v);
            g[v].add(u);
        }
    }

    public void readDirectedGraph(Set[] g, int numOfVertices, int numOfEdges, int offset) {
        for (int i = 0; i < numOfVertices; i++) {
            g[i] = new HashSet();
        }

        for (int i = 0; i < numOfEdges; i++) {
            int u = nextInt() - offset;
            int v = nextInt() - offset;

            g[u].add(v);
        }
    }

    public void readTree(List[] g, int numOfVertices, int offset) {
        int numOfEdges = numOfVertices - 1;

        for (int i = 0; i < numOfVertices; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 0; i < numOfEdges; i++) {
            int p = nextInt() - offset;

            g[p].add(i + 1);
            g[i + 1].add(p);
        }
    }

    public void readTree(Set[] g, int numOfVertices, int offset) {
        int numOfEdges = numOfVertices - 1;

        for (int i = 0; i < numOfVertices; i++) {
            g[i] = new HashSet();
        }

        for (int i = 0; i < numOfEdges; i++) {
            int p = nextInt() - offset;

            g[p].add(i + 1);
            g[i + 1].add(p);
        }
    }

    public int ceil(int a, int b) {
        int ceil = (a + b - 1) / b;
        return ceil;
    }

    public long ceil(long a, long b) {
        long ceil = (a + b - 1) / b;
        return ceil;
    }

    public int[] calculatePrefixSum(int[] a) {
        int n = a.length;

        int[] prefixSum = new int[n];

        prefixSum[0] = a[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }

        return prefixSum;
    }

    public long[] calculatePrefixSum(long[] a) {
        int n = a.length;

        long[] prefixSum = new long[n];

        prefixSum[0] = a[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
        }

        return prefixSum;
    }

    public long[] calculatePrefixMin(long[] a) {
        int n = a.length;

        long[] prefixMin = new long[n];

        prefixMin[0] = a[0];

        for (int i = 1; i < n; i++) {
            prefixMin[i] = Math.min(prefixMin[i - 1], a[i]);
        }

        return prefixMin;
    }

    public long[] calculatePrefixMax(long[] a) {
        int n = a.length;

        long[] prefixMax = new long[n];

        prefixMax[0] = a[0];

        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], a[i]);
        }

        return prefixMax;
    }

    public long[] reverse(long[] a) {
        int n = a.length;

        for (int i = 0, j = n - 1; i < j; i++, j--) {
            long temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        return a;
    }

    public long[] calculatePrefixSum(long[] a, long mod) {
        int n = a.length;

        long[] prefixSum = new long[n];

        prefixSum[0] = a[0] % mod;

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + a[i];
            prefixSum[i] %= mod;
        }

        return prefixSum;
    }

    public List<Long> calculatePrefixSum(List<Integer> list) {
        List<Long> cumList = new ArrayList<>();

        if (list.isEmpty())
            return cumList;

        cumList.add((long) list.get(0));

        long curSum = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            curSum += list.get(i);

            cumList.add(curSum);
        }

        return cumList;
    }

    public long[] calculateSuffixSum(long[] a) {
        return calculateSuffixFunc(a, (x, y) -> x + y);
    }

    public long[] calculateSuffixMin(long[] a) {
        return calculateSuffixFunc(a, (x, y) -> Math.min(x, y));
    }

    public long[] calculateSuffixMax(long[] a) {
        return calculateSuffixFunc(a, (x, y) -> Math.max(x, y));
    }

    public long[] calculateSuffixFunc(long[] a, BiFunction<Long, Long, Long> biFunction) {
        int n = a.length;

        long[] suffixMin = new long[n];

        suffixMin[n - 1] = a[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = biFunction.apply(suffixMin[i + 1], a[i]);
        }

        return suffixMin;
    }

    public long[] calculateSuffixSum(long[] a, int mod) {
        int n = a.length;

        long[] suffixSum = new long[n];

        suffixSum[n - 1] = a[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + a[i];
            suffixSum[i] %= mod;
        }

        return suffixSum;
    }

    public int squareRoot(int num) {
        int squareRoot = (int) Math.sqrt(num);

        while (squareRoot * squareRoot <= num) {
            squareRoot++;
        }

        return squareRoot - 1;
    }
}