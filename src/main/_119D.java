package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _119D {
    class ZFunction {
        String s;
        int n;

        public ZFunction(String s) {
            this.s = s;
            this.n = s.length();
            this.z = new int[n];
            calculate();
        }

        int[] z;

        int[] calculate() {
            int leftMatch = 0;
            int rightMatch = 0;

            for (int i = 1; i < n; i++) {
                if (rightMatch >= i) {
                    int z0 = z[i - leftMatch];
                    if (i + z0 - 1 >= rightMatch) {
                        int prevRightMatch = rightMatch;
                        for (int j = rightMatch + 1; j < n; j++) {
                            if (s.charAt(j) == s.charAt(j - i)) {
                                rightMatch++;
                            } else {
                                break;
                            }
                        }
                        if (rightMatch > prevRightMatch) {
                            leftMatch = i;
                        }
                        z[i] = rightMatch - i + 1;
                    } else {
                        z[i] = z0;
                    }
                } else {
                    if (s.charAt(0) == s.charAt(i)) {
                        leftMatch = rightMatch = i;
                        for (int j = i + 1; j < n; j++) {
                            if (s.charAt(j) == s.charAt(j - i)) {
                                rightMatch++;
                            } else {
                                break;
                            }
                        }
                        z[i] = rightMatch - i + 1;
                    }
                }
            }

            return z;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        StringBuilder a = new StringBuilder(in.readLine(false));
        StringBuilder b = new StringBuilder(in.readLine(false));

        int n = a.length();

        if (a.length() != b.length()) {
            out.println("-1 -1");
            return;
        }

        StringBuilder ra = new StringBuilder(a).reverse();
        StringBuilder rb = new StringBuilder(b).reverse();

        StringBuilder bha = concat(b, a);
        StringBuilder rahb = concat(ra, b);
        StringBuilder rbha = concat(rb, a);

        ZFunction z1 = new ZFunction(bha.toString());
        ZFunction z2 = new ZFunction(rahb.toString());
        ZFunction z3 = new ZFunction(rbha.toString());

        int a1 = -1, a2 = -1;

//        if (z3.z[bn + 1] == bn)
//        {
//            out.println();
//        }

        int[] nz2 = new int[n];

        for (int i = 0; i < n; i++) {
            nz2[i] = z2.z[n + 1 + i] + i;
        }

        int[] maxz2 = new int[n];

        maxz2[0] = nz2[0];
        for (int i = 1; i < n; i++) {
            maxz2[i] = Math.max(nz2[i], maxz2[i - 1]);
        }

        for (int j = n - 1; j > 0; j--) {
            if (z3.z[n + 1] >= j) {
                int nr = n - j;

                if (nz2[0] >= nr) {
                    out.println((j - 1) + " " + j);
                    return;
                }

                int lo = 0;
                int hi = z1.z[n + 1 + j];

                int vi = n;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;

                    if (maxz2[mid] >= nr) {
                        vi = Math.min(vi, mid);
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }

                if (vi < n && maxz2[vi] >= nr) {
                    out.println((j - 1) + " " + (j + vi));
                    return;
                }
            }
        }

        out.println("-1 -1");
    }

    StringBuilder concat(StringBuilder s1, StringBuilder s2) {
        return new StringBuilder(s1).append('\0').append(s2);
    }

}
