package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class agc014a {
    class trip {
        int a;
        int b;
        int c;

        public trip(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            trip trip = (trip) o;
            return a == trip.a &&
                    b == trip.b &&
                    c == trip.c;
        }

        @Override
        public int hashCode() {

            return Objects.hash(a, b, c);
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

//        if (a == b && b == c) {
//            out.println(-1);
//            return;
//        }

        Set<trip> trips = new HashSet<>();
        trips.add(new trip(a, b, c));

        int ans = 0;
        while (a % 2 == 0 && b % 2 == 0 && c % 2 == 0) {
            int ha = a / 2;
            int hb = b / 2;
            int hc = c / 2;

            a = hb + hc;
            b = ha + hc;
            c = ha + hb;

            if (trips.contains(new trip(a, b, c))) {
                out.println(-1);
                return;
            }

            ans++;
            trips.add(new trip(a, b, c));
        }

        out.println(ans);
    }
}