package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class YalalovichikStrings {
    class ele {
        char c;
        int cnt;

        public ele(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }
    }

    class pair {
        char c1;
        char c2;
        int cnt1;
        int cnt2;

        public pair(char c1, char c2, int cnt1, int cnt2) {
            this.c1 = c1;
            this.c2 = c2;
            this.cnt1 = cnt1;
            this.cnt2 = cnt2;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            String s = in.next();

            int rc = 1;
            List<ele> eles = new ArrayList<>();
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) != s.charAt(i - 1)) {
                    eles.add(new ele(s.charAt(i - 1), rc));
                    rc = 1;
                } else {
                    rc++;
                }
            }

            eles.add(new ele(s.charAt(n - 1), rc));

            List[][] pl = new List[26][26];

            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    pl[i][j] = new ArrayList();
                }
            }

            for (int i = 1; i < eles.size(); i++) {
                ele pe = eles.get(i - 1);
                ele ce = eles.get(i);

                pl[pe.c - 'a'][ce.c - 'a'].add(new pair(pe.c, ce.c, pe.cnt, ce.cnt));
            }

            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    ((List<pair>) pl[i][j]).sort((p1, p2) ->
                    {
                        if (p1.cnt1 == p2.cnt1)
                            return p1.cnt2 - p2.cnt2;
                        else
                            return p1.cnt1 - p2.cnt1;
                    });
                }
            }

            long ans = 0;
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    int pi = -1;

                    List<pair> cpl = pl[i][j];

                    int cn = cpl.size();

                    if (cn == 0)
                        continue;

                    int max = cpl.get(cn - 1).cnt1;
                    long[] ca = new long[max + 1];

                    for (int k = 0; k < cpl.size(); k++) {
                        pair cp = cpl.get(k);
                        ca[cp.cnt1] = Math.max(ca[cp.cnt1], cp.cnt2);
                    }

                    for (int k = max - 1; k >= 0; k--) {
                        ca[k] = Math.max(ca[k], ca[k + 1]);
                    }

                    for (int k = max; k >= 1; k--) {
                        ans += ca[k];
                    }
//                    for (int k = 0; k < cpl.size(); k++) {
//                        pair cp = cpl.get(k);
//                        if (k < cpl.size() - 1) {
//                            pair np = cpl.get(k + 1);
//
//                            if (np.cnt1 >= cp.cnt1 && np.cnt2 >= cp.cnt2) {
//                                continue;
//                            } else {
//                                long remc1 = cp.cnt1;
//
//                                if (pi != -1) {
//                                    remc1 -= cpl.get(pi).cnt1;
//                                }
//
//                                ans += remc1 * cp.cnt2;
//
//                                pi = k;
//                            }
//                        } else {
//                            long remc1 = cp.cnt1;
//
//                            if (pi != -1) {
//                                remc1 -= cpl.get(pi).cnt1;
//                            }
//
//                            ans += remc1 * cp.cnt2;
//
//                            pi = k;
//                        }
//                    }
                }
            }

            int[] h = new int[26];

            for (int i = 0; i < eles.size(); i++) {
                ele ce = eles.get(i);

                h[ce.c - 'a'] = Math.max(h[ce.c - 'a'], ce.cnt);
            }

            for (int i = 0; i < 26; i++) {
                ans += h[i];
            }

            out.println(ans);
        }
    }
}
