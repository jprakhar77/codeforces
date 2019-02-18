package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _955D {
    class Kmp<T extends Comparable<T>> {
        T[] s;
        T[] p;
        int n;
        int m;
        int[] f;

        int[] ans;
        int[] ansi;

        int sa;

        public Kmp(T[] s, T[] p) {
            super();
            this.s = s;
            this.p = p;
            this.n = s.length;
            this.m = p.length;
            this.f = new int[m];
            this.ans = new int[n + 1];
            for (int i = 1; i <= n; i++)
                this.ans[i] = n;
            this.ansi = new int[n];
            this.sa = n;
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

        int matches(int k) {

            int matches = 0;

            int j = 0;
            for (int i = 0; i < n; i++) {
                if (s[i].equals(p[j])) {
                    j++;
                    if (ans[j] == n && i >= k - 1) {
                        ans[j] = i;
                    }
                    if (j == m) {
                        if (sa == n) {
                            sa = i;
                        }
                        matches++;
                        j = f[j - 1] + 1;
                    }
                } else {
                    while (j > 0 && !p[j].equals(s[i])) {
                        j = f[j - 1] + 1;
                    }

                    if (p[j].equals(s[i])) {
                        j++;
                        if (ans[j] == n && i >= k - 1) {
                            ans[j] = i;
                        }
                    }
                }
            }

            return matches;
        }

        void can() {
            boolean[] vis = new boolean[m + 1];

            for (int i = m; i > 0; i--) {
                if (!vis[i]) {
                    int p = i;
                    vis[p] = true;
                    int c = f[p - 1] + 1;
                    while (c > 0) {
                        ans[c] = Math.min(ans[p], ans[c]);
                        vis[c] = true;
                        p = c;
                        c = f[p - 1] + 1;
                    }
                }
            }
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int k = in.nextInt();

        String s = in.next();
        String t = in.next();

        Kmp<Character> kmp1 = new Kmp<Character>(
                s.chars().mapToObj(c -> (char) c).toArray(Character[]::new), t.chars().mapToObj(c -> (char) c).toArray(Character[]::new));

        kmp1.fail();
        kmp1.matches(k);
        kmp1.can();

        String sr = new StringBuilder(s).reverse().toString();
        String tr = new StringBuilder(t).reverse().toString();

        Kmp<Character> kmp2 = new Kmp<Character>(
                sr.chars().mapToObj(c -> (char) c).toArray(Character[]::new), tr.chars().mapToObj(c -> (char) c).toArray(Character[]::new));


        kmp2.fail();
        kmp2.matches(k);
        kmp2.can();

        for (int i = 0; i <= m; i++) {
            int lai = kmp1.ans[i];

            int rai = kmp2.ans[m - i];

            if (i > k || m - i > k)
                continue;

            int raia = n - 1 - rai;

            if (i == m && kmp1.sa != n) {
                lai = Math.max(k - 1, kmp1.sa);
                raia = n - k;
            } else if (m - i == m && kmp2.sa != n) {
                lai = k - 1;
                raia = Math.min(n - 1 - kmp2.sa, n - k);
            }
            if (lai < raia && lai - k + 1 >= 0 && raia + k - 1 < n) {

                out.println("Yes");
                out.print(lai + 1 - k + 1);
                out.print(" ");
                out.print(raia + 1);

                return;
            }
        }

        out.println("No");
    }
}
