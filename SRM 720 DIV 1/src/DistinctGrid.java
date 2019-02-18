public class DistinctGrid {

    public int[] findGrid(int n, int k) {
        int[] ans = new int[n * n];

        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i, l = 0; l < k - 1; l++, j++, j %= n) {
                ans[i * n + j] = num;
                num++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ans[i * n + j] + " ");
            }
            System.out.println();
        }

        return ans;
    }
}
