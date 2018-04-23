package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _961D {
    class slope {
        int x;
        int y;

        int inf = (int) 2e9;

        public slope(int x, int y) {
            int gcd = gcd(x, y);
            x /= gcd;
            y /= gcd;
            this.x = x;
            this.y = y;
        }

        int gcd(int a, int b) {
            if (b == 0)
                return a;
            else
                return gcd(b, a % b);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            slope slope = (slope) o;
            return x == slope.x &&
                    y == slope.y;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        if (n <= 4) {
            out.println("YES");
            return;
        }

        slope slope = new slope(x[1] - x[0], y[1] - y[0]);
        List<Integer> f = new ArrayList<>();

        int sn = -1;
        for (int j = 2; j < n; j++) {
            slope cur = new slope(x[j] - x[0], y[j] - y[0]);
            if (!cur.equals(slope)) {
                f.add(j);
            }
        }

        if (f.size() <= 2) {
            out.println("YES");
            return;
        }

        int sn1 = f.get(0);
        int sn2 = f.get(1);

        slope = new slope(x[sn2] - x[sn1], y[sn2] - y[sn1]);

        Set<Integer> s = new HashSet<>();

        for (int j = 2; j < f.size(); j++) {
            slope cur = new slope(x[f.get(j)] - x[sn1], y[f.get(j)] - y[sn1]);
            if (!cur.equals(slope))
                s.add(j);
        }

        if (s.size() == 0) {
            out.println("YES");
            return;
        }


        slope = new slope(x[2] - x[0], y[2] - y[0]);
        f = new ArrayList<>();

        f.add(1);
        for (int j = 3; j < n; j++) {
            slope cur = new slope(x[j] - x[0], y[j] - y[0]);
            if (!cur.equals(slope)) {
                f.add(j);
            }
        }

        if (f.size() <= 2) {
            out.println("YES");
            return;
        }

        sn1 = f.get(0);
        sn2 = f.get(1);

        slope = new slope(x[sn2] - x[sn1], y[sn2] - y[sn1]);

        s = new HashSet<>();

        for (int j = 2; j < f.size(); j++) {
            slope cur = new slope(x[f.get(j)] - x[sn1], y[f.get(j)] - y[sn1]);
            if (!cur.equals(slope))
                s.add(j);
        }

        if (s.size() == 0) {
            out.println("YES");
            return;
        }

        slope slope2 = new slope(x[2] - x[1], y[2] - y[1]);

        Set<Integer> nf = new HashSet<>();

        for (int i = 3; i < n; i++) {
            slope slope1 = new slope(x[i] - x[1], y[i] - y[1]);

            if (!slope1.equals(slope2)) {
                nf.add(i);
            }
        }

        HashSet<slope> ss = new HashSet<slope>();

        for (Integer i : nf) {
            slope slope1 = new slope(x[i] - x[0], y[i] - y[0]);

            ss.add(slope1);
        }

        if (ss.size() > 1) {
            out.println("NO");
        } else {
            out.println("YES");
        }
    }
}
