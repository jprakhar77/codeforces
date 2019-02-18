import java.util.Arrays;

public class CliqueParty {

    public int maxsize(int[] a, int k) {
        int n = a.length;

        Arrays.sort(a);

        int ans = 2;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long maxd = a[j] - a[i];

                long mind = (long) Math.ceil((double) maxd / k);

                int lni = i;
                int cans = 1;
                for (int l = i + 1; l <= j; l++) {
                    if (a[l] - a[lni] >= mind) {
                        cans++;
                        lni = l;
                    }
                }

                ans = Math.max(ans, cans);
            }
        }

        return ans;
    }
}
