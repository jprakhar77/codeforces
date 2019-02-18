public class AliceAndBobEasy {

    public int[] make(int n) {
        if (n % 2 == 1) {
            int[] ans = new int[n];

            ans[0] = 1 << 10;

            for (int i = 1; i < n; i++) {
                ans[i] = ans[i - 1] + 1;
            }

            return ans;
        } else {
            int[] ans = new int[n];

            ans[0] = 1;
            ans[1] = 1 << 10;

            for (int i = 2; i < n; i++) {
                ans[i] = ans[i - 1] + 1;
            }

            return ans;


        }
    }
}
