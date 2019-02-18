package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BPath {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int[][] g = new int[4][4];

        for (int i = 0; i < 3; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            a--;
            b--;

            g[a][b] = 1;
            g[b][a] = 1;
        }

        NextPermutation np = new NextPermutation();

        int[] a = new int[4];

        for (int i = 0; i < 4; i++) {
            a[i] = i;
        }

        do {
            boolean poss = true;
            for (int i = 0; i < 3; i++) {
                if (g[a[i]][a[i + 1]] == 0) {
                    poss = false;
                    break;
                }
            }

            if (poss) {
                out.println("YES");
                return;
            }
        } while ((a = np.nextPermutation(a)) != null);

        out.println("NO");
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
