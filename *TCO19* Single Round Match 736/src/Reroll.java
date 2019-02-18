public class Reroll {

    public int minimumDice(int target, int[] dice) {
        int n = dice.length;

        int ans = n;
        for (int i = 0; i < (1 << n); i++) {

            int cs = 0;
            int l = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    cs += dice[j];
                } else {
                    l++;
                }
            }

            if (target >= cs + l && target <= cs + l * 6) {
                ans = Math.min(ans, l);
            }
        }

        return ans;
    }
}
