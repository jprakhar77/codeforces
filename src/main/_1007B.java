package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class _1007B {
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

    class rp
    {
        int a;
        int b;
        int c;

        public rp(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            rp rp = (rp) o;
            return a == rp.a &&
                    b == rp.b &&
                    c == rp.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        List[] fl = new List[100001];

        for (int i = 1; i <= 100000; i++) {
            fl[i] = factors(i);
        }

        int t = in.nextInt();

        NextPermutation np = new NextPermutation();


        while (t-- > 0) {
            int[] a = new int[3];

            a[0] = in.nextInt();
            a[1] = in.nextInt();
            a[2] = in.nextInt();

            Arrays.sort(a);

            long fans = 0;

            do {
                int f = 0;
                int s = 0;

                int num = a[1];

                long ans = 0;
                for (int i = 0; i < fl[num].size(); i++) {
                    int cnum = (int) fl[num].get(i);
                    while (f < fl[a[0]].size() && (int) fl[a[0]].get(f) <= cnum) {
                        f++;
                    }
                    f--;

                    while (s < fl[a[2]].size() && (int) fl[a[2]].get(s) < cnum) {
                        s++;
                    }

                    ans += (((long) f + 1) * (fl[a[2]].size() - s));
                }

                fans = Math.max(fans, ans);
            } while ((a = np.nextPermutation(a)) != null);

            out.println(fans);
        }
    }

    List<Integer> factors(int n) {
        List<Integer> factors = new ArrayList<>();

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                if (i != n / i)
                    factors.add(n / i);
            }
        }

        factors.sort((x, y) -> x - y);

        return factors;
    }
}
