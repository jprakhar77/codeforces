import java.util.ArrayDeque;

public class MazeWithKeys {

    String[] a;

    class coord {
        int i;
        int j;

        public coord(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    int r;
    int c;

    public int startingPoints(String[] a) {
        this.a = a;

        r = a.length;

        c = a[0].length();

        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (a[i].charAt(j) == '.') {
                    if (poss0(i, j))
                        continue;
                    if (poss(i, j)) {
                        ans++;
                    }
                }

            }
        }

        return ans;
    }

    ArrayDeque[] pendq;
    boolean[] avkeys;
    ArrayDeque<coord> q;
    boolean[][] vis;

    boolean poss0(int ri, int ci) {

        q = new ArrayDeque<>();

        vis = new boolean[r][c];

        q.addLast(new coord(ri, ci));
        vis[ri][ci] = true;

        while (!q.isEmpty()) {
            coord dqe = q.removeFirst();

            if (isfinish(dqe)) {
                return true;
            }

            process0(dqe.i, dqe.j);
        }

        return false;
    }

    void process0(int ri, int ci) {
        if (ri < 0 || ri >= r) {
            return;
        }

        if (ci < 0 || ci >= c) {
            return;
        }

        char ch = a[ri].charAt(ci);

        if (a[ri].charAt(ci) == '#')
            return;

        if (ch >= 'A' && ch <= 'Z') {
            return;
        }

        addNeigh(ri, ci);
    }

    boolean poss(int ri, int ci) {

        q = new ArrayDeque<>();

        vis = new boolean[r][c];

        pendq = new ArrayDeque[26];

        for (int i = 0; i < 26; i++) {
            pendq[i] = new ArrayDeque<coord>();
        }

        avkeys = new boolean[26];

        q.addLast(new coord(ri, ci));
        vis[ri][ci] = true;

        while (!q.isEmpty()) {
            coord dqe = q.removeFirst();

            if (isfinish(dqe)) {
                return true;
            }

            process(dqe.i, dqe.j);
        }

        return false;
    }

    void process(int ri, int ci) {
        if (ri < 0 || ri >= r) {
            return;
        }

        if (ci < 0 || ci >= c) {
            return;
        }
        char ch = a[ri].charAt(ci);

        if (a[ri].charAt(ci) == '#')
            return;

        if (ch >= 'A' && ch <= 'Z') {
            int val = ch - 'A';

            if (avkeys[val]) {
                addNeigh(ri, ci);
            } else {
                pendq[val].addLast(new coord(ri, ci));
            }

            return;
        }

        if (ch >= 'a' && ch <= 'z') {
            int val = ch - 'a';

            avkeys[val] = true;

            addNeigh(ri, ci);

            for (coord ccoord : (ArrayDeque<coord>) pendq[val]) {
                q.addLast(ccoord);
            }

            pendq[val].clear();

            return;
        }

        addNeigh(ri, ci);
    }

    void addNeigh(int ri, int ci) {
        add(ri - 1, ci);
        add(ri, ci - 1);
        add(ri, ci + 1);
        add(ri + 1, ci);
    }

    void add(int ri, int ci) {
        if (ri < 0 || ri >= r) {
            return;
        }

        if (ci < 0 || ci >= c) {
            return;
        }

        if (!vis[ri][ci]) {
            q.addLast(new coord(ri, ci));
            vis[ri][ci] = true;
        }
    }

    boolean isfinish(coord coord) {
        if (a[coord.i].charAt(coord.j) == '*') {
            return true;
        }
        return false;
    }
}
