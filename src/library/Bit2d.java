package library;

public class Bit2d {
    int r;
    int c;
    int[][] tree;

    public Bit2d(int r, int c) {
        super();
        this.r = r;
        this.c = c;
        this.tree = new int[r + 1][c + 1];
    }

    public int sum(int r1, int c1) {
        r1++;
        c1++;

        int sum = 0;

        while (r1 > 0) {
            int cc1 = c1;

            while (cc1 > 0) {
                sum += tree[r1][cc1];
                cc1 -= (cc1 & -cc1);
            }

            r1 -= (r1 & -r1);
        }

        return sum;
    }

    public void update(int r1, int c1, int v) {
        r1++;
        c1++;

        while (r1 <= r) {
            int cc1 = c1;

            while (cc1 <= c) {
                tree[r1][cc1] += v;
                cc1 += (cc1 & -cc1);
            }

            r1 += (r1 & -r1);
        }
    }
}
