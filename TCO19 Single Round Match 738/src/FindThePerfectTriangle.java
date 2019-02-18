public class FindThePerfectTriangle {

    long dis(long x1, long y1, long x2, long y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    public int[] constructTriangle(int area, int perimeter) {

        for (int i = 1; i <= perimeter / 2; i++) {
            int area2 = 2 * area;
            if (area2 % i != 0)
                continue;

            int h = area2 / i;
            for (int j = 1; j < i; j++) {
                long dis1 = dis(0, 0, j, h);

                if (!ispsq(dis1))
                    continue;

                long dis2 = dis(i, 0, j, h);

                if (!ispsq(dis2))
                    continue;


                long dist1 = (long) Math.sqrt(dis1);
                long dist2 = (long) Math.sqrt(dis2);

                if (dist1 > i || dist2 > i || (dist1 + dist2) <= i)
                    continue;

                if (i + dist1 + dist2 == perimeter) {
                    return new int[]{0, 0, j, h, i, 0};
                }
            }
        }

        return new int[]{};
    }

    boolean ispsq(long num) {
        long dq = (long) (Math.sqrt(num));

        if (dq * dq == num)
            return true;

        if ((dq + 1) * (dq + 1) == num)
            return true;

        return false;
    }

    double eps = 1e-1;
}
