package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class CookieClicker {
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

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        long c = in.nextInt();
        long s = in.nextInt();

        long[] a = new long[n];
        long[] b = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            b[i] = in.nextInt();
        }

        NextPermutation np = new NextPermutation();

        long t = (c + s - 1) / s;
        for (int i = 0; i < (1 << n); i++) {
            long cc = 0;
            long cb = 0;

            int sz = 0;
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) != 0) {
                    cc += a[j];
                    cb += b[j];
                    sz++;
                }
            }

            if (cc == 0)
                continue;

            int[] p = new int[sz];
            int k = 0;
            for (int j = 0; j < n; j++) {
                if (((1 << j) & i) != 0) {
                    p[k] = j + 1;
                    k++;
                }
            }

//            long it = (cc + s - 1) / s;
//            for (long j = it; ; j++) {
//                if (s * j >= c) {
//                    break;
//                }
//                long rem = s * j - cc;
//                long arem = c - rem;
//                j += (arem + s + cb - 1) / (s + cb);
//                t = Math.min(t, j);
//            }

            do {
                long crem = 0;
                long ct = 0;
                long ctb = 0;
                for (int l = 0; l < sz; l++) {
                    int j = p[l] - 1;
                    long ta = a[j];
                    if (ta <= crem) {
                        ctb += b[j];
                        crem -= a[j];
                        continue;
                    }
                    long rem = ta - crem;
                    long tt = (rem + s + ctb - 1) / (s + ctb);
                    crem += tt * (s + ctb) - ta;
                    ct += tt;
                    ctb += b[j];
                }

                if (crem >= c) {
                    t = Math.min(t, ct);
                } else {
                    long ft = (c - crem + s + ctb - 1) / (s + ctb);
                    t = Math.min(ft + ct, t);
                }
            } while ((p = np.nextPermutation(p)) != null);
        }

        out.println(t);
    }

}
