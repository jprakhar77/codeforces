public class SquareCutout {

    public int verify(String[] cutout) {
        int n = cutout.length;
        int m = cutout[0].length();

        int mini = n;
        int minj = n;

        int maxi = -1;
        int maxj = -1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cutout[i].charAt(j) == '#') {
                    mini = Math.min(mini, i);
                    minj = Math.min(minj, j);

                    maxi = Math.max(maxi, i);
                    maxj = Math.max(maxj, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i >= mini && j >= minj && i <= maxi && j <= maxj) {
                    if (cutout[i].charAt(j) != '#')
                        return 0;
                }
            }
        }

        if (mini == n)
            return 1;

        int rh = maxi - mini + 1;
        int rw = maxj - minj + 1;

        if (rh != rw) {
            if (rh < rw) {
                if (mini > 0 && maxi < n - 1)
                    return 0;
            } else {
                if (minj > 0 && maxj < m - 1)
                    return 0;
            }
        }

        int max = Math.max(1, Math.max(maxj - minj + 1, maxi - mini + 1));

        return max;
    }
}
