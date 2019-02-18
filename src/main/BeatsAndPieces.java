package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BeatsAndPieces {
    NextPermutation np = new NextPermutation();

    int[] fac = new int[11];

    boolean s = true;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        if (s) {
            fac[0] = 1;

            for (int i = 1; i <= 10; i++) {
                fac[i] = i * fac[i - 1];
            }

            s = false;
        }


        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = in.nextIntArray(n);

        int[] b;
        b = a.clone();

        int[] prem = new int[m];

        for (int i = 1; i < m; i++) {
            prem[i] = i;
        }

        int[] l = new int[m];
        int[] r = new int[m];

        for (int i = 0; i < m; i++) {
            l[i] = in.nextInt() - 1;
            r[i] = in.nextInt() - 1;
        }

        for (int i = 0; i < m; i++) {
            app(a, l[i], r[i]);
        }

        int ans = 0;
        do {
            int[] d = b.clone();
            for (int i = 0; i < m; i++) {
                app(d, l[prem[i]], r[prem[i]]);
            }

            boolean isp = true;

            for (int i = 0; i < n; i++) {
                if (a[i] != d[i]) {
                    isp = false;
                    break;
                }
            }

            if (isp) {
                ans++;
            }
        } while ((prem = np.nextPermutation(prem)) != null);

        int g = gcd(ans, fac[m]);

        out.println((ans / g) + "/" + (fac[m] / g));
    }

    int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    int t;

    void app(int[] prem, int l, int r) {
        for (int i = l, j = r; i < j; i++, j--) {
            t = prem[i];
            prem[i] = prem[j];
            prem[j] = t;
        }
    }

    class NextPermutation {

        public int[] nextPermutation(int[] nums) {
            if (nums == null || nums.length < 2)
                return null;

            int p = 0;
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) {
                    p = i;
                    break;
                }
            }

            int q = 0;
            for (int i = nums.length - 1; i > p; i--) {
                if (nums[i] > nums[p]) {
                    q = i;
                    break;
                }
            }

            if (p == 0 && q == 0) {
                reverse(nums, 0, nums.length - 1);
                return null;
            }

            int temp = nums[p];
            nums[p] = nums[q];
            nums[q] = temp;

            if (p < nums.length - 1) {
                reverse(nums, p + 1, nums.length - 1);
            }

            return nums;
        }

        public void reverse(int[] nums, int left, int right) {
            while (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
    }
}
