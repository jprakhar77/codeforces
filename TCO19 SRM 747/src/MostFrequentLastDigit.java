public class MostFrequentLastDigit {

    public int[] generate(int n, int d) {
        int[] ans = new int[n];

        if (d % 2 == 0) {
            int r = d / 2;

            if (r == 0) {
                r = 5;
            }
            int num = 0;

            for (int i = 0; i < n; i++) {
                ans[i] = num * 10 + r;
                num++;
            }

            for (int i = 0; i < n; i++) {
                System.out.println(ans[i]);
            }

            return ans;
        } else {
            int r = d / 2;
            int r2 = r + 1;

            if (r == 0) {
                r = 5;
                r2 = 6;
            }

            int num = 0;

            for (int i = 0; i < n; i += 2) {
                ans[i] = num * 10 + r;
                if (i + 1 < n) {
                    num++;
                    ans[i + 1] = num * 10 + r2;
                }
                num++;
            }

            for (int i = 0; i < n; i++) {
                System.out.println(ans[i]);
            }

            return ans;
        }
    }
}
