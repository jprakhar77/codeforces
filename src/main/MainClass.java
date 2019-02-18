package main;

import java.io.*;
import java.util.InputMismatchException;

class Solution {

    long[] prefix;
    public int shortestSubarray(int[] a, int k) {

        int n = a.length;

        prefix = new long[n];

        prefix[0] = a[0];
        for(int i = 1; i < n; i++)
        {
            prefix[i] = prefix[i - 1] + a[i];
        }

        int lo = 1;
        int hi = n;

        int ans = n + 1;

        while(lo <= hi)
        {
            int mid = (lo + hi) / 2;

            boolean isp = check(a, mid, k);

            if(isp)
            {
                hi = mid - 1;
                ans = Math.min(ans, mid);
            }
            else
            {
                lo = mid + 1;
            }
        }

        if(ans == n + 1)
        {
            return -1;
        }

        return ans;
    }

    boolean check(int[] a, int l, int k)
    {
        int n = a.length;

        for(int i = 0; i < n - l + 1; i++)
        {
            int en = i + l - 1;

            long sum = prefix[en];

            if(i > 0)
            {
                sum -= prefix[i - 1];
            }

            if(sum >= k)
            {
                return true;
            }
        }

        return false;
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        Solution solver = new Solution();
        int[] a= {44,-25,75,-50,-38,-42,-32,-6,-40,-47};
        int k = 19;
        solver.shortestSubarray(a, k);
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
