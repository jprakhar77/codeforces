package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ENiceTable {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        if (n == 2 && m == 2) {
            char[][] mat = new char[n][m];

            for (int i = 0; i < n; i++) {
                mat[i] = in.nextString().toCharArray();
            }

            int[] a = new int[4];

            for (int i = 0; i < 4; i++)
                a[i] = i;

            char[][] ansmat = new char[2][2];

            NextPermutation np = new NextPermutation();

            int ans = Integer.MAX_VALUE;

            do {
                char[][] curmat = new char[2][2];

                curmat[0][0] = ap[a[0]];
                curmat[0][1] = ap[a[1]];
                curmat[1][0] = ap[a[2]];
                curmat[1][1] = ap[a[3]];

                int cur = 0;
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (mat[i][j] != curmat[i][j]) {
                            cur++;
                        }
                    }
                }

                if (cur < ans) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            ansmat[i][j] = curmat[i][j];
                        }
                    }
                    ans = cur;
                }
            } while ((a = np.nextPermutation(a)) != null);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++)
                    out.print(ansmat[i][j]);
                out.println();
            }
        } else {
            char[][] mat = new char[n][m];

            for (int i = 0; i < n; i++) {
                mat[i] = in.nextString().toCharArray();
            }

            char[][] ansmat = solve(mat, n, m);

            mat = trans(mat, n, m);
            int t = n;
            n = m;
            m = t;

            int pans = ans;
            char[][] ansmat2 = solve(mat, n, m);

            ansmat2 = trans(ansmat2, n, m);
            t = n;
            n = m;
            m = t;

            if (ans < pans)
            {
                ansmat = ansmat2;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++)
                    out.print(ansmat[i][j]);
                out.println();
            }

            //out.println(ans);

        }

        //out.println(ans);
    }

    int ans = Integer.MAX_VALUE;

    char[] ap = new char[]{'A', 'G', 'C', 'T'};

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

    char[][] solve(char[][] mat, int n, int m) {

        if (m == 2) {
            return solve2(mat, n, m);
        }

        NextPermutation np = new NextPermutation();

        int[] a = new int[4];

        for (int i = 0; i < 4; i++)
            a[i] = i;

        char[][] ansmat = new char[n][m];

        char[][] curmat = new char[n][m];

        TreeSet<Character> cs = new TreeSet<>();
        Set<Character> csm = new HashSet<>();

        for (char ch : ap)
            csm.add(ch);

        do {
            int cur = 0;

            for (int i = 0; i < Math.min(m, 4); i++) {
                curmat[0][i] = ap[a[i]];
                if (curmat[0][i] != mat[0][i])
                    cur++;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == 0 && j < 4)
                        continue;

                    if (j == 0) {
                        curmat[i][j] = curmat[i - 1][2];
                    } else if (j < 4) {
                        cs.clear();
                        cs.addAll(csm);

                        cs.remove(curmat[i][j - 1]);
                        cs.remove(curmat[i - 1][j]);
                        cs.remove(curmat[i - 1][j - 1]);

                        curmat[i][j] = cs.first();
                    } else {
                        curmat[i][j] = curmat[i][j - 4];
                    }

                    if (curmat[i][j] != mat[i][j])
                        cur++;
                }
            }

            if (cur < ans) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        ansmat[i][j] = curmat[i][j];
                    }
                }
                ans = cur;
            }
        } while ((a = np.nextPermutation(a)) != null);

        return ansmat;
    }

    //m == 2
    char[][] solve2(char[][] mat, int n, int m) {

        NextPermutation np = new NextPermutation();

        int[] a = new int[4];

        for (int i = 0; i < 4; i++)
            a[i] = i;

        char[][] ansmat = new char[n][m];

        char[][] curmat = new char[n][m];

        TreeSet<Character> cs = new TreeSet<>();
        Set<Character> csm = new HashSet<>();

        for (char ch : ap)
            csm.add(ch);

        do {
            int cur = 0;

            for (int i = 0; i < Math.min(m, 4); i++) {
                curmat[0][i] = ap[a[i]];
                if (curmat[0][i] != mat[0][i])
                    cur++;
            }

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    cs.addAll(csm);

                    cs.remove(curmat[i - 1][0]);
                    cs.remove(curmat[i - 1][1]);

                    char ch1 = cs.first();
                    char ch2 = cs.last();

                    int c1 = 0;

                    if (ch1 != mat[i][0]) {
                        c1++;
                    }

                    if (ch2 != mat[i][1]) {
                        c1++;
                    }

                    int c2 = 0;

                    if (ch2 != mat[i][0]) {
                        c2++;
                    }

                    if (ch1 != mat[i][1]) {
                        c2++;
                    }

                    if (c1 <= c2) {
                        curmat[i][0] = ch1;
                        curmat[i][1] = ch2;

                        cur += c1;
                    } else {
                        curmat[i][0] = ch2;
                        curmat[i][1] = ch1;

                        cur += c2;
                    }
                }
            }

            if (cur < ans) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        ansmat[i][j] = curmat[i][j];
                    }
                }
                ans = cur;
            }
        } while ((a = np.nextPermutation(a)) != null);

        return ansmat;
    }

    char[][] trans(char[][] mat, int n, int m) {
        char[][] rev = new char[m][n];

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                rev[j][i] = mat[i][j];
            }
        }

        return rev;
    }
}
