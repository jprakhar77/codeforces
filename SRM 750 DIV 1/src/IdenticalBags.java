public class IdenticalBags {

    public long makeBags(long[] candy, long bagSize) {

        int n = candy.length;

        long lo = 0;
        long hi = (long) 1e18 + 5;

        long ans = 0;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;

            if (ispos(mid, candy, bagSize)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    boolean ispos(long num, long[] c, long bs) {
        long f = 0;
        if (num == 0)
        {
            return true;
        }
        for (int i = 0; i < c.length; i++) {
            f += c[i] / num;
        }

        if (f >= bs) {
            return true;
        } else {
            return false;
        }
    }
}
