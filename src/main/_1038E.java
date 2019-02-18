package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.PriorityQueue;

public class _1038E {
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

        PriorityQueue[][] l = new PriorityQueue[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                l[i][j] = new PriorityQueue<Integer>((x, y) -> y - x);
            }
        }

        for (int i = 0; i < n; i++) {
            int c1 = in.nextInt() - 1;
            int v = in.nextInt();
            int c2 = in.nextInt() - 1;

            if (c1 > c2) {
                int t = c1;
                c1 = c2;
                c2 = t;
            }

            l[c1][c2].add(v);
        }

        NextPermutation np = new NextPermutation();

        int[] a = new int[8];

        for (int i = 0; i < 8; i++) {
            a[i] = i + 1;
        }


        do {

            int[] b = new int[8];

            for (int i = 0; i < 8; i++) {
                b[i] = a[i] - 1;

                if (b[i] >= 4) {
                    b[i] -= 4;
                }
            }

            for (int i = 0; i < 8; i++) {
                process(0, i, b, l);
            }

        } while ((a = np.nextPermutation(a)) != null);

        out.print(ans);
    }

    long ans = 0;

    void process(int s, int e, int[] a, PriorityQueue[][] pq) {
        PriorityQueue[][] pq2 = new PriorityQueue[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i <= j)
                    pq2[i][j] = new PriorityQueue(pq[i][j]);
                else
                    pq2[i][j] = pq2[j][i];
            }
        }

        long ca = 0;
        int re = s;
        for (int i = s; i <= e; i++) {
            int cv = a[i];
            while (pq2[cv][cv].size() > 0) {
                ca += (int) pq2[cv][cv].poll();
            }
            re = i;
            if (i < e) {
                int nv = a[i + 1];
                if (pq2[cv][nv].size() > 0) {
                    ca += (int) pq2[cv][nv].poll();
                } else {
                    break;
                }
            }
        }

        for (int i = s; i <= re; i++) {
            int cv = a[i];
            for (int j = 0; j < 4; j++) {
                while (pq2[cv][j].size() >= 2) {
                    ca += (int) pq2[cv][j].poll();
                    ca += (int) pq2[cv][j].poll();
                }
            }
        }

        ans = Math.max(ans, ca);
    }
}
