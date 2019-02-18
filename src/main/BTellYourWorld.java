package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Objects;

public class BTellYourWorld {

    class slope {
        int yd;
        int xd;

        public slope(int yd, int xd) {
            this.yd = yd;
            this.xd = xd;

            int gcd = gcd(yd, xd);

            if (gcd != 0) {
                this.yd /= gcd;
                this.xd /= gcd;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            slope slope = (slope) o;
            return yd == slope.yd &&
                    xd == slope.xd;
        }

        @Override
        public int hashCode() {
            return Objects.hash(yd, xd);
        }
    }

    int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    int ex = (int) 1e9;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] y = in.nextIntArray(n);

        for (int k = 0; k < 2; k++) {
            for (int j = k + 1; j < n; j++) {
                int x1 = k + 1;
                int x2 = j + 1;
                int y1 = y[k];
                int y2 = y[j];

                slope cs = new slope(y2 - y1, x2 - x1);

                boolean poss = true;
                int inc = 1;

                boolean[] d = new boolean[n];
                d[k] = true;
                d[j] = true;
                for (int i = 0; i < n; i++) {
                    if (i == k)
                        continue;
                    x2 = i + 1;
                    y2 = y[i];

                    slope cs2 = new slope(y2 - y1, x2 - x1);

                    if (cs.equals(cs2)) {
                        inc++;
                        d[i] = true;
                    }
                }

                if (inc == n)
                    continue;

                if (inc == n - 1) {
                    out.println("Yes");
                    return;
                }

                slope cs3 = null;

                for (int i = 0; i < n; i++) {
                    if (!d[i]) {
                        x1 = i + 1;
                        y1 = y[i];
                        d[i] = true;
                        break;
                    }
                }

                boolean f = false;
                for (int i = 0; i < n; i++) {
                    if (!d[i]) {
                        x2 = i + 1;
                        y2 = y[i];
                        if (f) {

                            slope cs2 = new slope(y2 - y1, x2 - x1);

                            if (cs3.equals(cs2)) {
                            } else {
                                poss = false;
                                break;
                            }
                        } else {
                            f = true;
                            cs3 = new slope(y2 - y1, x2 - x1);

                            if (!cs3.equals(cs)) {
                                poss = false;
                                break;
                            }
                        }
                    }
                }

                if (poss) {
                    out.println("Yes");
                    return;
                }
            }
        }

        out.println("No");
    }
}
