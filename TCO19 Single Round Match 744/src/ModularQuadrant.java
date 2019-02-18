public class ModularQuadrant {

    public long sum(int r1, int r2, int c1, int c2) {

        int t = r1;
        r1 = c1;
        c1 = t;

        t = r2;
        r2 = c2;
        c2 = t;

        long ans = 0;

        ans += cal(r2, c2);

        ans -= cal(r2, c1 - 1);

        ans -= cal(r1 - 1, c2);

        ans += cal(r1 - 1, c1 - 1);

        return ans;
    }

    long cal(int r, int c) {

        if (r < 0 || c < 0)
            return 0;

        r++;
        c++;

        long sz = Math.min(r, c);

        long diff = Math.max(r, c) - sz;
        long ans = 0;

        long ot = 0, tt = 0;

        if (sz % 3 == 0) {
            long t = sz / 3;

            if (t > 0) {
                ans += 3 * sum(1, 2, t);

                ans += 2 * sum(5, 6, t);
            }


            if (diff % 3 <= 1) {
                ot = diff / 3;
                tt = diff / 3;
            } else {
                ot = diff / 3 + 1;
                tt = diff / 3;
            }
        } else if (sz % 3 == 1) {
            long t = sz / 3;

            if (t > 0) {
                ans += 3 * sum(1, 2, t);

                ans += 2 * sum(5, 6, t);
            }


            if (diff % 3 == 0) {
                ot = diff / 3;
                tt = diff / 3;
            } else if (diff % 3 == 1) {
                ot = diff / 3 + 1;
                tt = diff / 3;
            } else if (diff % 3 == 2) {
                ot = diff / 3 + 1;
                tt = diff / 3 + 1;
            }
        } else {
            long t = sz / 3;

            ans += 3 * sum(1, 2, t + 1);

            if (t > 0)
                ans += 2 * sum(5, 6, t);


            if (diff % 3 == 0) {
                ot = diff / 3;
                tt = diff / 3;
            } else {
                ot = diff / 3;
                tt = diff / 3 + 1;
            }

        }


        ans += ot * sz;

        ans += 2 * tt * sz;

        return ans;

    }

    long sum(long a, long d, long n) {
        long ans = 0;

        ans = 2 * a + (n - 1) * d;

        ans *= n;

        ans /= 2;

        return ans;
    }
}
