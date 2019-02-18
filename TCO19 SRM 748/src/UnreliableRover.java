import java.util.ArrayList;
import java.util.List;

public class UnreliableRover {

    public long getArea(String direction, int[] minSteps, int[] maxSteps) {

        int n = direction.length();

        int qm = 0;

        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (direction.charAt(i) == '?') {
                l.add(i);
                qm++;
            }
        }

        long[] max = new long[10000001];

        long maxup = 0;

        for (int set = 0; set < (1 << qm); set++) {
            int up = 0;
            long left = 0;
            for (int i = 0; i < l.size(); i++) {
                int ind = l.get(i);
                if ((set & (1 << i)) != 0) {
                    up += max[ind];
                } else {
                    left += max[ind];
                }
            }

            max[up] = Math.max(max[up], left);

            maxup = Math.max(maxup, up);
        }

        for (int i = 10000000 - 1; i >= 0; i--) {
            max[i] = Math.max(max[i], max[i + 1]);
        }

        long minx = 0;
        long miny = 0;
        long maxx = 0;
        long maxy = 0;

        for (int i = 0; i < n; i++) {
            char ch = direction.charAt(i);
            if (direction.charAt(i) != '?') {
                if (ch == 'N') {
                    miny += minSteps[i];
                    maxy += maxSteps[i];
                } else if (ch == 'S') {
                    miny += maxSteps[i];
                    maxy += minSteps[i];
                } else if (ch == 'E') {
                    maxx += maxSteps[i];
                    minx += minSteps[i];
                } else {
                    maxx += minSteps[i];
                    minx += minSteps[i];
                }
            }
        }

        long ans = (maxx - minx + 1) * (maxy - miny + 1);

        ans += 2 * max[0] * (maxy - miny + 1);

        ans += 2 * maxup * (maxx - minx + 1);

        long val = 0;
        for (int i = 0; i <= 10000000; i++) {
            val += max[i];
        }

        val *= 4;

        ans += val;

        return ans;
    }
}
