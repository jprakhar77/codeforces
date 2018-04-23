package library;

class Kmp<T extends Comparable<T>> {
    T[] s;
    T[] p;
    int n;
    int m;
    int[] f;

    public Kmp(T[] s, T[] p) {
        super();
        this.s = s;
        this.p = p;
        this.n = s.length;
        this.m = p.length;
        this.f = new int[m];
    }

    void fail() {
        f[0] = -1;

        for (int i = 1; i < m; i++) {
            int c = f[i - 1];

            f[i] = -1;
            while (c > -1) {
                if (p[c + 1].equals(p[i])) {
                    f[i] = c + 1;
                    break;
                } else {
                    c = f[c];
                }
            }

            if (f[i] == -1) {
                if (p[0].equals(p[i])) {
                    f[i] = 0;
                }
            }
        }
    }

    int matches() {

        int matches = 0;

        int j = 0;
        for (int i = 0; i < n; i++) {
            if (s[i].equals(p[j])) {
                j++;
                if (j == m) {
                    matches++;
                    j = f[j - 1] + 1;
                }
            } else {
                while (j > 0 && !p[j].equals(s[i])) {
                    j = f[j - 1] + 1;
                }

                if (p[j].equals(s[i])) {
                    j++;
                }
            }
        }

        return matches;
    }
}