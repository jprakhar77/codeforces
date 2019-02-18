package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DBalancedTernaryString {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        int n = in.nextInt();

        String s = in.next();

        int[] c = new int[3];

        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';

            c[num]++;
        }

        if (c[0] == c[1] && c[1] == c[2]) {
            out.println(s);
            return;
        }

        StringBuilder ans = new StringBuilder(s);

        int rq = n / 3;

        if (c[1] > rq) {
            if (c[2] > rq) {
                int r2 = c[2] - rq;
                int r1 = c[1] - rq;
                for (int i = 0; i < n; i++) {
                    if (ans.charAt(i) == '2') {
                        if (r2 > 0) {
                            ans.setCharAt(i, '0');
                            r2--;
                        }
                    } else if (ans.charAt(i) == '1') {
                        if (r1 > 0) {
                            ans.setCharAt(i, '0');
                            r1--;
                        }
                    }
                }

                out.println(ans);
            } else if (c[0] > rq) {
                int r2 = c[0] - rq;
                int r1 = c[1] - rq;
                for (int i = n - 1; i >= 0; i--) {
                    if (ans.charAt(i) == '0') {
                        if (r2 > 0) {
                            ans.setCharAt(i, '2');
                            r2--;
                        }
                    } else if (ans.charAt(i) == '1') {
                        if (r1 > 0) {
                            ans.setCharAt(i, '2');
                            r1--;
                        }
                    }
                }

                out.println(ans);
            } else {

                int r0 = rq - c[0];
                for (int i = 0; i < n; i++) {
                    if (s.charAt(i) == '1' && r0 > 0) {
                        ans.setCharAt(i, '0');
                        r0--;
                    }
                }

                int r2 = rq - c[2];

                for (int i = n - 1; i >= 0; i--) {
                    if (ans.charAt(i) == '1' && r2 > 0) {
                        ans.setCharAt(i, '2');
                        r2--;
                    }
                }

                out.println(ans);
            }
        } else {

            List<Integer> l = new ArrayList<>();
            if (c[0] > rq) {
                int r0 = c[0] - rq;
                for (int i = n - 1; i >= 0; i--) {
                    if (ans.charAt(i) == '0' && r0 > 0) {
                        //ans.setCharAt(i, '2');
                        r0--;
                        l.add(i);
                    }
                }
            }
            if (c[2] > rq) {
                int r0 = c[2] - rq;
                for (int i = 0; i < n; i++) {
                    if (ans.charAt(i) == '2' && r0 > 0) {
                        //ans.setCharAt(i, '0');
                        r0--;
                        l.add(i);
                    }
                }
            }

            int r0 = rq - c[0];
            int r1 = rq - c[1];
            int r2 = rq - c[2];

            l.sort(Comparator.naturalOrder());

            for (int i = 0; i < l.size(); i++) {
                int ind = l.get(i);

                if (r0 > 0) {
                    ans.setCharAt(ind, '0');
                    r0--;
                } else if (r1 > 0) {
                    ans.setCharAt(ind, '1');
                    r1--;
                } else if (r2 > 0) {
                    ans.setCharAt(ind, '2');
                    r2--;
                }
            }

            out.println(ans);
        }
    }
}
