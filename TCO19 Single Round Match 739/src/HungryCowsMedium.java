import java.util.Arrays;
import java.util.Comparator;

public class HungryCowsMedium {

    public long getWellFedTime(int[] ca, int[] barnPositions) {

        int n = ca.length;
        int m = barnPositions.length;

        Integer[] cowAppetites = new Integer[n];

        long sum = 0;

        for (int i = 0; i < n; i++) {
            cowAppetites[i] = ca[i];
            sum += cowAppetites[i];
        }

        Arrays.sort(barnPositions);
        Arrays.sort(cowAppetites, Comparator.reverseOrder());

        long lo = 0;
        long hi = sum + barnPositions[0];

        long ans = hi;

        while (lo <= hi) {
            long mid = (lo + hi) / 2;

            if (isp(cowAppetites, barnPositions, mid, n, m)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    boolean isp(Integer[] ca, int[] bp, long time, int n, int m) {
        int j = 0;

        long[] ct = new long[m];
        long[] ex = new long[m];

        for (int i = 0; i < m; i++) {
            while (j < n) {
                if (bp[i] + ex[i] + ca[j] <= time - ct[i]) {
                    ex[i] += ca[j];
                    j++;
                } else {
                    if (i == m - 1)
                        return false;

                    ct[i + 1] = bp[i] + ex[i] + ca[j] - time + ct[i];

                    if (bp[i + 1] + ca[j] > time)
                        return false;

                    j++;
                    break;
                }
            }
        }

        if (j < n)
            return false;
        return true;
    }
}
