import java.util.HashSet;
import java.util.Set;

public class LightbulbGame {

    public int countWinningMoves(String[] board) {
        int n = board.length;
        int m = board[0].length();

        int[][] grundy = new int[n][m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
//                if (board[i].charAt(j) == '0')
//                {
//                    grundy[i][j] = 0;
//                    continue;
//                }
                Set<Integer> s = new HashSet<>();
                s.add(0);

                for (int k = j + 1; k < m; k++) {
                    s.add(grundy[i][k]);
                }

                for (int k = i + 1; k < n; k++) {
                    s.add(grundy[k][j]);
                }

                for (int k = 0; ; k++) {
                    if (!s.contains(k)) {
                        grundy[i][j] = k;
                        break;
                    }
                }
            }
        }

        int state = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == '1') {
                    state ^= grundy[i][j];
                }
            }
        }

        int[][] mb = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mb[i][j] = board[i].charAt(j) - '0';
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mb[i][j]--;
                ans += cal(mb, grundy, n, m);

                for (int k = j + 1; k < m; k++) {
                    mb[i][k]++;
                    ans += cal(mb, grundy, n, m);
                    mb[i][k]--;
                }

                for (int k = i + 1; k < n; k++) {
                    mb[k][j]++;
                    ans += cal(mb, grundy, n, m);
                    mb[k][j]--;
                }

                mb[i][j]++;
            }
        }

        return ans;
    }

    int cal(int[][] b, int[][] g, int n, int m) {
        int state = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (b[i][j] < 0)
                    return 0;
                if (b[i][j] > 0) {
                    int v = b[i][j];

                    while (v > 0) {
                        state ^= g[i][j];
                        v--;
                    }
                }
            }
        }

        if (state == 0) {
            return 1;
        }

        return 0;
    }
}
