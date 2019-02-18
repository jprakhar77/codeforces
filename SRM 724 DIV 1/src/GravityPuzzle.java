import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class GravityPuzzle {

    int mod = 1000000007;

    public int count(String[] board) {
        int n = board.length;
        int m = board[0].length();

        boolean up = true;
        boolean down = true;
        boolean left = true;
        boolean right = true;

        int c = 0;

        ncr = nCr(100, 100, mod);

        //up
        for (int j = 0; j < m; j++) {
            boolean f = false;
            for (int i = n - 1; i >= 0; i--) {
                if (board[i].charAt(j) == '#') {
                    f = true;
                } else {
                    if (f) {
                        up = false;
                    }
                }
            }
        }

        //down
        for (int j = 0; j < m; j++) {
            boolean f = false;
            for (int i = 0; i < n; i++) {
                if (board[i].charAt(j) == '#') {
                    f = true;
                } else {
                    if (f) {
                        down = false;
                    }
                }
            }
        }

        //left
        for (int i = 0; i < n; i++) {
            boolean f = false;
            for (int j = m - 1; j >= 0; j--) {
                if (board[i].charAt(j) == '#') {
                    f = true;
                } else {
                    if (f) {
                        left = false;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            boolean f = false;
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == '#') {
                    f = true;
                } else {
                    if (f) {
                        right = false;
                    }
                }
            }
        }

        if (left)
            c++;
        if (right)
            c++;
        if (up)
            c++;
        if (down)
            c++;

        if (c == 4)
            return 1;

        if (c == 0)
            return 1;

        if (up && down)
            c--;

        if (left && right)
            c--;

        List<Integer> lc = new ArrayList<>();
        List<Integer> lr = new ArrayList<>();

        for (int j = 0; j < m; j++) {
            int cc = 0;
            for (int i = 0; i < n; i++) {
                if (board[i].charAt(j) == '#') {
                    cc++;
                }
            }
            if (cc > 0)
                lc.add(cc);
        }

        for (int i = 0; i < n; i++) {
            int cc = 0;
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == '#') {
                    cc++;
                }
            }
            if (cc > 0)
                lr.add(cc);
        }

        lc.sort(Comparator.reverseOrder());


        TreeMap<Integer, Integer> tc = new TreeMap<>((x, y) -> y - x);
        TreeMap<Integer, Integer> tr = new TreeMap<>((x, y) -> y - x);

        for (int val : lc) {
            tc.merge(val, 1, (x, y) -> x + y);
        }

        for (int val : lr) {
            tr.merge(val, 1, (x, y) -> x + y);
        }

        int fans = 0;

        if (c >= 2) {
            long ans = 1;

            int rc = m;
            //column
            for (int key : tc.keySet()) {
                int cnt = tc.get(key);

                ans *= ncr[rc][cnt];
                ans %= mod;
                ans *= pow(ncr[n][key], cnt, mod);
                ans %= mod;
                rc -= cnt;
            }

            fans += ans;

            ans = 1;
            int rr = n;
            //row
            for (int key : tr.keySet()) {
                int cnt = tr.get(key);
                ans *= ncr[rr][cnt];
                ans %= mod;
                ans *= pow(ncr[m][key], cnt, mod);
                ans %= mod;
                rr -= cnt;
            }

            fans += ans;
            fans %= mod;

            //inter
            ans = 1;

            int pv = n;
            rc = m;

            for (int key : tc.keySet()) {
                int cnt = tc.get(key);

                ans *= ncr[rc][cnt];
                ans %= mod;
                ans *= ncr[pv][key];
                ans %= mod;
                rc -= cnt;
                pv = key;
            }

            fans -= ans;
            fans %= mod;

            if (fans < 0) {
                fans += mod;
            }

            return fans;
        } else {
            if (up || down) {
                long ans = 1;
                for (int i = 0; i < lc.size(); i++) {
                    ans *= ncr[n][lc.get(i)];
                    ans %= mod;
                }

                fans += ans;

                return fans;
            } else {
                long ans = 1;
                for (int i = 0; i < n; i++) {
                    int cc = 0;
                    for (int j = 0; j < m; j++) {
                        if (board[i].charAt(j) == '#') {
                            cc++;
                        }
                    }
                    ans *= ncr[m][cc];
                    ans %= mod;
                }

                fans += ans;
                return fans;
            }
        }
    }

    long pow(long a, long p, int mod) {
        if (p == 0) {
            return 1;
        }

        long t = pow(a, p / 2, mod);

        if (p % 2 != 0) {
            return (((t * t) % mod) * a) % mod;
        } else {
            return (t * t) % mod;
        }
    }

    int[][] ncr;

    int[][] nCr(int n, int r, int mod) {
        int[][] ncr = new int[n + 1][r + 1];

        ncr[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            ncr[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= r; j++) {
                ncr[i][j] = ncr[i - 1][j - 1] + ncr[i - 1][j];
                ncr[i][j] %= mod;
            }
        }

        return ncr;
    }
}
