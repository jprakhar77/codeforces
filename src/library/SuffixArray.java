package library;

import java.util.ArrayList;
import java.util.List;

public class SuffixArray {

    String s;
    int n;
    int[][] rankMat;
    int[] rank;
    int[] invRank;
    int[] lcp;
    char lc;

    int log;

    public SuffixArray(String s, char lc) {
        super();
        this.s = s;
        this.n = s.length();
        this.lc = lc;

        this.rank = new int[n];
        this.invRank = new int[n];
        this.lcp = new int[n];

        log = 0;

        while ((1 << log) < n) {
            log++;
        }
    }

    void suffixArray() {

        int[][] rank = new int[n][log + 1];

        for (int i = 0; i < n; i++) {
            rank[i][0] = s.charAt(i) - lc;
        }

        for (int j = 1; j <= log; j++) {

            List<rank> ranks = new ArrayList<SuffixArray.rank>();

            for (int i = 0; i < n; i++) {
                ranks.add(new rank(i, rank[i][j - 1], i + (1 << (j - 1)) >= n ? -1 : rank[i
                        + (1 << (j - 1))][j - 1]));
            }
            ranks.sort((r1, r2) -> {
                if (r1.cr == r2.cr) {
                    return r1.nr - r2.nr;
                } else {
                    return r1.cr - r2.cr;
                }
            });

            rank[ranks.get(0).i][j] = 0;

            rank pr = ranks.get(0);
            for (int i = 1; i < n; i++) {
                rank cr = ranks.get(i);
                if (cr.cr == pr.cr && cr.nr == pr.nr) {
                    rank[cr.i][j] = rank[pr.i][j];
                } else {
                    rank[cr.i][j] = rank[pr.i][j] + 1;
                }
                pr = cr;
            }
        }

        this.rankMat = rank;

        for (int i = 0; i < n; i++) {
            this.rank[i] = this.rankMat[i][log];
        }

        for (int i = 0; i < n; i++) {
            this.invRank[this.rank[i]] = i;
        }
    }

    void lcp() {
        for (int i = 1; i < n; i++) {
            lcp[i] = lcp(invRank[i - 1], invRank[i]);
        }
    }

    int lcp(int x, int y) {
        int ans = 0;
        for (int i = log; i >= 0; i--) {
            if (rankMat[x][i] == rankMat[y][i]) {
                x += (1 << i);
                y += (1 << i);
                ans += (1 << i);
            }
        }

        return ans;
    }

    class rank {
        int i;
        int cr;
        int nr;

        public rank(int i, int cr, int nr) {
            super();
            this.i = i;
            this.cr = cr;
            this.nr = nr;
        }
    }
}