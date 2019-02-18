public class TestProctoring {

    public double expectedTime(int[] p, int[] q) {
        double ans = 0;

        double prod = 1.0;

        int n = p.length;

        for (int i = 0; i < n; i++) {
            prod *= (((double) p[i]) / q[i]);
        }

        double rem = 1.0;

        for (int i = 1; i <= 1000000; i++) {
            double cur = rem * prod;
            ans += i * cur;
            rem -= cur;
        }

        return ans;
    }
}
