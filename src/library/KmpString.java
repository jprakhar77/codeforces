package library;

class KmpString {
    String s;
    String p;
    int n;
    int m;
    int[] f;

    public KmpString(String s, String p) {
        super();
        this.s = s;
        this.p = p;
        this.n = s.length();
        this.m = p.length();
        this.f = new int[m];
    }

    void fail() {
        f[0] = -1;

        for (int i = 1; i < m; i++) {
            int c = f[i - 1];

            f[i] = -1;
            while (c > -1) {
                if (p.charAt(c + 1) == p.charAt(i)) {
                    f[i] = c + 1;
                    break;
                } else {
                    c = f[c];
                }
            }

            if (f[i] == -1) {
                if (p.charAt(0) == p.charAt(i)) {
                    f[i] = 0;
                }
            }
        }
    }

    int matches() {

        int matches = 0;

        int j = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == p.charAt(j)) {
                j++;
                if (j == m) {
                    matches++;
                    j = f[j - 1] + 1;
                }
            } else {
                while (j > 0 && p.charAt(j) != s.charAt(i)) {
                    j = f[j - 1] + 1;
                }

                if (p.charAt(j) == s.charAt(i)) {
                    j++;
                }
            }
        }

        return matches;
    }
}