public class LongMansionDiv1 {

    public long minimalTime(int[] t, int sx, int sy, int ex, int ey) {
        long ans = Long.MAX_VALUE;

        int n = t.length;

        if (sx > ex) {
            int temp = sx;
            sx = ex;
            ex = temp;
        }

        if (sy > ey) {
            int temp = sy;
            sy = ey;
            ey = temp;
        }

        long[][] sum = new long[n][n];

        for (int i = 0; i < n; i++) {
            sum[i][i] = t[i];
            for (int j = i + 1; j < n; j++) {
                sum[i][j] = sum[i][j - 1] + t[j];
            }
        }

        if (sy == ey) {
            return sum[sx][ex];
        }

        for (int i = 0; i < n; i++) {
            long cans = 0;
            if (sx <= i && i <= ex) {
                cans = sum[sx][ex] + sum[i][i] * (ey - sy);
            } else if (i < sx) {
                cans = sum[i][sx] + sum[i][ex] + sum[i][i] * (ey - sy - 1);
            } else {
                cans = sum[sx][i] + sum[ex][i] + sum[i][i] * (ey - sy - 1);
            }
            ans = Math.min(ans, cans);
        }

        return ans;
    }
}
