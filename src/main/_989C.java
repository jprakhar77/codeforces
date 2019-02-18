package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _989C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();

        char[][] ans = new char[50][50];

        int j = 0;

        boolean isa1 = true;
        if (a > 1) {
            isa1 = false;
            b -= 1;
        }

        int cb = 0, cc = 0, cd = 0;

        while (true) {
            for (int i = 0; i < 50; i += 2) {
                if (cb == b) {
                    if (cc == c) {
                        if (cd == d) {
                            ans[j][i] = 'A';
                        } else {
                            ans[j][i] = 'D';
                            cd++;
                        }
                    } else {
                        ans[j][i] = 'C';
                        cc++;
                    }
                } else {
                    ans[j][i] = 'B';
                    cb++;
                }
            }
            if (cd == d)
                break;
            j += 2;
        }

        for (int k = 0; k <= j; k++) {
            int st = 0;
            int inc = 1;
            if (k % 2 == 0) {
                st = 1;
                inc = 2;
            }

            for (int i = st; i < 50; i += inc) {
                ans[k][i] = 'A';
            }
        }

        j++;
        for (int i = 0; i < 50; i++) {
            ans[j][i] = 'A';
        }

        if (!isa1) {
            a--;

            j++;
            for (int i = 0; i < 50; i++) {
                ans[j][i] = 'B';
            }

            int ca = 0;

            j++;
            int sj = j;
            while (true) {
                for (int i = 0; i < 50; i += 2) {
                    if (ca == a) {
                        ans[j][i] = 'B';
                    } else {
                        ans[j][i] = 'A';
                        ca++;
                    }
                }
                if (ca == a)
                    break;
                j += 2;
            }

            for (int k = sj; k <= j; k++) {
                int st = 0;
                int inc = 1;
                if ((k - sj) % 2 == 0) {
                    st = 1;
                    inc = 2;
                }

                for (int i = st; i < 50; i += inc) {
                    ans[k][i] = 'B';
                }
            }
        }

        out.print(j + 1);
        out.print(" ");
        out.println(50);

        for (int i = 0; i <= j; i++) {
            for (int k = 0; k < 50; k++) {
                out.print(ans[i][k]);
            }
            out.println();
        }
    }
}
