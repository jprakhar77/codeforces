package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class BAccordion {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String s = in.next();

        int n = s.length();

        int[] li = new int[n];

//        if (s.charAt(n - 1) == '|') {
//            li[n - 1] = n - 1;
//        } else li[n - 1] = -1;
//        for (int i = n - 2; i >= 0; i--) {
//            if (s.charAt(i) != '|') {
//                li[i] = -1;
//                continue;
//            }
//            if (s.charAt(i + 1) == '|') {
//                li[i] = li[i + 1];
//            } else {
//                li[i] = i;
//            }
//        }
//
//        int ans = -1;
//        for (int i = 0; i < n - 3; i++) {
//            if (s.charAt(i) == '[' && s.charAt(i + 1) == ':') {
//                if (s.charAt(i + 2) == ':' && s.charAt(i + 3) == ']') {
//                    ans = Math.max(ans, 4);
//                } else if (li[i + 2] != -1) {
//                    int val = li[i + 2];
//
//                    if (val < n - 2 && s.charAt(val + 1) == ':' && s.charAt(val + 2) == ']') {
//                        ans = Math.max(ans, val - i + 1 + 2);
//                    }
//                }
//            }
//        }
//
//        out.println(ans);

        int fli = -1;
        int bfi = -1;


        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '[') {
                fli = i;
                break;
            }
        }

        if (fli == -1) {
            out.println(-1);
            return;
        }
        for (int j = fli + 1; j < n; j++) {
            if (s.charAt(j) == ':') {
                fli = j;
                break;
            }
        }

        if (s.charAt(fli) != ':') {
            out.println(-1);
            return;
        }

        s = new StringBuilder(s).reverse().toString();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ']') {
                bfi = i;
                break;
            }
        }

        if (bfi == -1) {
            out.println(-1);
            return;
        }
        for (int j = bfi + 1; j < n; j++) {
            if (s.charAt(j) == ':') {
                bfi = j;
                break;
            }
        }

        if (s.charAt(bfi) != ':') {
            out.println(-1);
            return;
        }

        fli = n - 1 - fli;

        if (fli == -1 || bfi == -1 || bfi >= fli) {
            out.println(-1);
            return;
        }

        int ans = 4;
        for (int i = bfi; i < fli; i++) {
            if (s.charAt(i) == '|')
                ans++;
        }

        out.println(ans);
    }
}
