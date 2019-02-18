package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _1078A {
    public void solve(long testNumber, InputReader in, OutputWriter out) {
        long a = in.nextInt();
        long b = in.nextInt();
        long c = in.nextInt();

        long x1 = in.nextInt();
        long y1 = in.nextInt();
        long x2 = in.nextInt();
        long y2 = in.nextInt();

        long ans = Math.abs(x1 - x2) + Math.abs(y1 - y2);

        poi p1m = new poi(x1, y1);
        poi p2m = new poi(x2, y2);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                //0 y co
                //1 x co

                poi p1;
                poi p2;

                long dis = 0;

                if (i == 0) {
                    p1 = yco(a, b, c, x1, y1);
                }
                else {
                    p1 = xco(a, b, c, x1, y1);
                }

                if (j == 0) {
                    p2 = yco(a, b, c, x2, y2);
                }
                else {
                    p2 = xco(a, b, c, x2, y2);
                }

                if (p1 == null || p2 == null)
                    continue;

                dis += dis(p1m, p1);
                dis += dis(p1, p2);
                dis += dis(p2, p2m);

                ans = Math.min(dis, ans);
            }
        }

        out.print(ans);
    }

    double dis(poi p1, poi p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }


    poi xco(long a, long b, long c, long x, long y) {
        if (b == 0)
            return null;

        long ey = (-c - a * x) / b;

        return new poi(x, ey);
    }

    poi yco(long a, long b, long c, long x, long y) {
        if (a == 0)
            return null;

        long ex = (-c - b * y) / a;

        return new poi(ex, y);
    }

    class poi {
        long x;
        long y;

        public poi(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
