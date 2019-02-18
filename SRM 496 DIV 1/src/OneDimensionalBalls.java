import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OneDimensionalBalls {

    public long countValidGuesses(int[] fp, int[] sp) {
        int n = fp.length;
        int m = sp.length;

        Arrays.sort(fp);

        Set<Integer> fps = new HashSet<>();
        Set<Integer> sps = new HashSet<>();

        for (int val : fp)
            fps.add(val);

        for (int val : sp)
            sps.add(val);

        long ans = 0;

        Set<Integer> du = new HashSet<>();
        for (int j = 0; j < m; j++) {
            if (fp[0] == sp[j])
                continue;

            long ca = 1;

            fpv = new HashSet<>();
            spv = new HashSet<>();

            int d = Math.abs(fp[0] - sp[j]);

            if (du.contains(d))
                continue;

            for (int val : sp) {
                f = 0;
                se = 0;
                if (spv.contains(val))
                    continue;
                dfs(val, fps, sps, 1, d, -1);

                if (f > se) {
                    ca = 0;
                    break;
                }
                if (se > f) {
                    ca *= se;
                }
            }

            if (fpv.size() < n) {
                ca = 0;
            }

            ans += ca;

            du.add(d);
        }

        return ans;
    }

    Set<Integer> fpv;
    Set<Integer> spv;
    int f = 0;
    int se = 0;

    void dfs(int cv, Set<Integer> fps, Set<Integer> sps, int s, int d, int p) {
        if (s == 0) {
            f++;
            fpv.add(cv);
            for (int val : sps) {
                if (val != p && Math.abs(val - cv) == d) {
                    dfs(val, fps, sps, 1, d, cv);
                }
            }
        } else {
            se++;
            spv.add(cv);
            for (int val : fps) {
                if (val != p && Math.abs(val - cv) == d) {
                    dfs(val, fps, sps, 0, d, cv);
                }
            }
        }
    }
}
