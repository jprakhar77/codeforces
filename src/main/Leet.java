package main;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Leet {

    class point {
        double x;
        double y;

        public point() {
        }

        public point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    double distanceSquare(point p1, point p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }

    double distance(point p1, point p2) {
        return Math.sqrt(distanceSquare(p1, p2));
    }

    double dotProduct(point p2, point p1, point p4, point p3) {
        return (p2.x - p1.x) * (p4.x - p3.x) + (p2.y - p1.y) * (p4.y - p3.y);
    }

    double dotProduct(point p1, point p2) {
        return p1.x * p2.x + p1.y * p2.y;
    }

    point vector(point p2, point p1) {
        return new point(p2.x - p1.x, p2.y - p1.y);
    }

    class _1033E {
        List[] g;

        class edge {
            int u;
            int v;

            public edge(int u, int v) {
                this.u = u;
                this.v = v;
            }
        }

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

        class IsBipartite {
            List[] g;
            int n;

            public IsBipartite(List[] g) {
                this.g = g;
                this.n = g.length;
                this.vis = new boolean[n];
                this.level = new int[n];
                this.color = new int[n];
                this.colorVis = new boolean[n];
                this.parent = new int[n];
            }

            boolean[] vis;
            boolean[] colorVis;
            int[] level;
            int[] color;
            int[] parent;

            int zeroColorNodes = 0;
            int oneColorNodes = 0;

            HashSet<Integer> nonBipartiteCycle = new HashSet<>();

            //undirected graph, obviously
            boolean isBipartite(int u) {
                ArrayDeque<Integer> queue = new ArrayDeque<>();

                queue.addLast(u);
                vis[u] = true;
                level[u] = 0;
                parent[u] = -1;

                while (queue.size() > 0) {
                    int front = queue.removeFirst();

                    for (int v : (List<Integer>) g[front]) {
                        if (vis[v]) {
                            if (level[v] == level[front]) {
                                findCycle(v, front);
                                return false;
                            }
                        } else {
                            queue.addLast(v);
                            vis[v] = true;
                            level[v] = level[front] + 1;
                            parent[v] = front;
                        }
                    }
                }

                return true;
            }

            void findCycle(int u, int v) {
                while (u != v) {
                    nonBipartiteCycle.add(u);
                    nonBipartiteCycle.add(v);

                    u = parent[u];
                    v = parent[v];
                }

                nonBipartiteCycle.add(u);
            }

            void assign2Colors(int u) {
                dfs(u, -1);
            }

            HashSet<Integer> zeroNodes = new HashSet<>();
            HashSet<Integer> oneNodes = new HashSet<>();

            void dfs(int u, int prevColor) {
                if (prevColor == -1) {
                    color[u] = 0;
                } else {
                    color[u] = 1 - prevColor;
                }

                if (color[u] == 0) {
                    zeroColorNodes++;
                    zeroNodes.add(u);
                } else {
                    oneColorNodes++;
                    oneNodes.add(u);
                }

                colorVis[u] = true;

                for (int v : (List<Integer>) g[u]) {
                    if (!colorVis[v])
                        dfs(v, color[u]);
                }
            }
        }

        public void solve(int testNumber, InputReader in, OutputWriter out) {
//            BitSet b = new BitSet(10);
//
//            b.set(2);
//
//            System.out.println(b.length());

            out.println((int)(-2.23/2));
        }

        List<Integer> prime(int n) {
            boolean[] isp = new boolean[n + 1];

            Arrays.fill(isp, true);

            isp[1] = false;

            for (int i = 2; i <= n; i++) {
                if (isp[i]) {
                    for (int j = 2; j * i <= n; j++) {
                        isp[i * j] = false;
                    }
                }
            }

            List<Integer> primes = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                if (isp[i])
                    primes.add(i);
            }

            return primes;
        }

        edge searchEdge(Set<Integer> s1, Set<Integer> s2, InputReader in, OutputWriter out) {
            if (s1.size() < s2.size()) {
                Set<Integer> tem = s1;
                s1 = s2;
                s2 = tem;
            }

//        Set<Integer> s = new HashSet<>(s1);
//        s.addAll(s2);
//
//        int total = numOfEdges(s, in, out);

            while (true) {
                if (s1.size() == 1 && s2.size() == 1) {
                    HashSet<Integer> s1s2 = new HashSet<>(s1);
                    s1s2.addAll(s2);
                    int s1s2Edges = numOfEdges(s1s2, in, out);
                    if (s1s2Edges > 0) {
                        return new edge(s1.iterator().next(), s2.iterator().next());
                    } else {
                        break;
                    }
                }

                if (s1.size() < s2.size()) {
                    Set<Integer> tem = s1;
                    s1 = s2;
                    s2 = tem;
                }

                int s1Size = s1.size();
                Set<Integer> s3 = new HashSet<>();
                Set<Integer> s4 = new HashSet<>();
                int s3Size = s1Size / 2;
                int i = 0;
                for (int v : s1) {
                    if (i < s3Size) {
                        s3.add(v);
                    } else {
                        s4.add(v);
                    }
                    i++;
                }

                int s3Edges = numOfEdges(s3, in, out);
                int s2Edges = numOfEdges(s2, in, out);

                HashSet<Integer> s3s2 = new HashSet<>(s3);
                s3s2.addAll(s2);

                int s3s2Edges = numOfEdges(s3s2, in, out);

                if (s3s2Edges > s3Edges + s2Edges) {
                    s1 = s3;
                } else {
                    s1 = s4;
                }
            }

            return null;
        }

        Map<Set<Integer>, Integer> cache = new HashMap<>();

        int numOfEdges(Set<Integer> s, InputReader in, OutputWriter out) {
            if (s.size() <= 1) {
                return 0;
            }

            if (s.size() <= 10) {
                if (cache.containsKey(s)) {
                    return cache.get(s);
                }
            }
            System.out.println("? " + s.size());
            for (int num : s) {
                System.out.print((num + 1) + " ");
            }
            System.out.println();
            System.out.flush();

            int edges = in.nextInt();

            if (s.size() <= 10) {
                Set<Integer> newSet = Collections.unmodifiableSet(new HashSet<>(s));
                cache.put(newSet, edges);
            }

            return edges;
        }
    }

    class Solution {
        public String shortestSuperstring(String[] a) {
            int n = a.length;

            int[][] len = new int[n][n];

            for(int i = 0; i < n; i++)
            {
                for(int j = 0; j < n; j++)
                {
                    len[i][j] = cal(a[i], a[j]);
                }
            }

            int[][] dp = new int[1 << n][n + 1];

            for(int i = 0; i < (1 << n); i++)
            {
                for(int j = 0; j <= n; j++)
                {
                    if(i == 0 && j == 0)
                    {
                        dp[i][j] = 0;
                        continue;
                    }

                    int nj = j - 1;
                    if(j == 0 || (i & (1 << nj)) == 0)
                    {
                        dp[i][j] = inf;
                        continue;
                    }

                    dp[i][j] = inf;

                    int ni = (i & (~(1 << nj)));
                    for(int k = 0; k < n; k++)
                    {
                        if(((1 << k) & ni) != 0 && dp[ni][k + 1] != inf)
                        {
                            dp[i][j] = Math.min(dp[ni][k + 1] + a[nj].length() - len[k][nj], dp[i][j]);
                        }
                    }
                }
            }

            int ans = inf;

            for(int i = 1; i <= n; i++)
                ans = Math.min(ans, dp[(1 << n) - 1][i]);

            return String.valueOf(ans);
        }

        int inf = 1000000;

        int cal(String a, String b)
        {
            int al = a.length();
            int bl = b.length();
            for(int i = 1; i < al; i++)
            {
                boolean poss = true;
                for(int j = 0; j < bl; j++)
                {
                    if(i + j >= al)
                        break;

                    if(a.charAt(i + j) != b.charAt(j))
                    {
                        poss = false;
                        break;
                    }
                }

                if(poss)
                {
                    return al - i;
                }
            }

            return 0;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
//        _1033E sol = new _1033E();
//        sol.solve(1, new InputReader(System.in), null);

//        Solution solution = new Solution();
//
//        String ans = solution.shortestSuperstring(new String[]{"alex","loves","leetcode"});
//
//        out.println(ans);

        out.println(5.6 + 5.8);
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Leet solver = new Leet();
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
