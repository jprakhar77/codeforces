public class ConsecutiveOnes {

    public long get(long n, int k) {

        long ans = (1L << 50) - 1;

        int lim = 50 - k;
        for (int i = 0; i <= lim; i++) {

            long num = n;
            for (int j = i; j < i + k; j++) {
                num |= (1L << j);
            }

            if (num > n)
                for (int j = 0; j < i; j++) {
                    num &= ~(1L << j);
                }

            ans = Math.min(ans, num);
        }

        return ans;
    }
}
