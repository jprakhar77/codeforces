package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class AdaMatrix {
    int max = (1 << 10) + 5;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        int[][] a = new int[max][max];
        int[][] b = new int[max][max];

        int[][] c = new int[max][max];
        int[][] d = new int[max][max];

        boolean[] aswap = new boolean[max];
        boolean[] bswap = new boolean[max];

        t:
        while (t-- > 0) {
            int n = in.nextInt();

            for (int i = 0; i < n; i++) {
                aswap[i] = bswap[i] = false;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    a[i][j] = in.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    b[i][j] = in.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int num1 = a[i][j];
                    int num2 = a[j][i];

                    if (i <= j) {
                        c[i][j] = Math.min(num1, num2);
                    } else {
                        c[i][j] = Math.max(num1, num2);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int num1 = b[i][j];
                    int num2 = b[j][i];

                    if (i <= j) {
                        d[i][j] = Math.min(num1, num2);
                    } else {
                        d[i][j] = Math.max(num1, num2);
                    }
                }
            }

//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    int num = a[i][j];
//
//                    boolean x = aswap[i] ^ aswap[j];
//
//                    if (x) {
//                        num = a[j][i];
//                    }
//
//                    c[i][j] = num;
//                }
//            }
//
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    int num = a[i][j];
//
//                    boolean x = bswap[i] ^ bswap[j];
//
//                    if (x) {
//                        num = b[j][i];
//                    }
//
//                    d[i][j] = num;
//                }
//            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (c[i][j] != d[i][j]) {
                        out.println("No");
                        continue t;
                    }
                }
            }

            out.println("Yes");
        }
    }
}
