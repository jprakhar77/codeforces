package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _1055D {
    class to {
        StringBuilder t1;
        StringBuilder t2;

        String s;
        String t;

        boolean poss = true;

        int sz = 0;

        int st = -1;
        int en = -1;

        public to(StringBuilder t1, StringBuilder t2) {
            this.t1 = t1;
            this.t2 = t2;

            for (int i = 0; i < t1.length(); i++) {
                if (t1.charAt(i) != t2.charAt(i)) {
                    if (st == -1) {
                        st = i;
                    }
                    en = i;
                }
            }

            if (st == -1) {
                s = "";
                t = "";
                return;
            }
            en++;
            s = t1.substring(st, en);
            t = t2.substring(st, en);

            sz = en - st;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        StringBuilder[] s = new StringBuilder[n];

        for (int i = 0; i < n; i++) {
            s[i] = new StringBuilder(in.next());
        }

        StringBuilder[] t = new StringBuilder[n];

        for (int i = 0; i < n; i++) {
            t[i] = new StringBuilder(in.next());
        }

        to[] tos = new to[n];

        for (int i = 0; i < n; i++) {
            tos[i] = new to(s[i], t[i]);
//            if (!tos[i].poss) {
//                out.println("NO");
//                return;
//            }
        }

        Arrays.sort(tos, (t1, t2) -> t2.sz - t1.sz);

        String cs = tos[0].s;
        String ct = tos[0].t;

        int sz = tos[0].sz;

        Set<Integer> ss = new HashSet<>();
        Set<Integer> sso = new HashSet<>();

        for (int i = 1; i < n; i++) {

            int ind = tos[i].t1.indexOf(cs);

            if (ind == -1) {
                if (tos[i].t1.toString().equals(tos[i].t2.toString()))
                    continue;
                else {
                    out.println("NO");
                    return;
                }
            }

            if (tos[i].t1.toString().equals(tos[i].t2.toString())) {
                ss.add(i);
                continue;
            }

            StringBuilder t1c = new StringBuilder(tos[i].t1);

            for (int j = ind; j < ind + sz; j++) {
                t1c.setCharAt(j, ct.charAt(j - ind));
            }

            //StringBuilder tt = tos[i].t1.r(ind, ind + sz, ct);

            if (t1c.toString().equals(tos[i].t2.toString())) {
                sso.add(i);
                continue;
            } else {
                out.println("NO");
                return;
            }
        }

        if (ss.size() > 0) {
            int l = 3000;
            int r = 3000;

            for (int i : sso) {
                int ind = tos[i].t1.indexOf(cs);

                l = Math.min(l, ind);
                r = Math.min(r, tos[i].t1.length() - (ind + sz));
            }

            int lo = 0;
            int hi = l;

            int ansl = lo;

            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                String ccs = tos[0].t1.substring(tos[0].st - mid, tos[0].en);

                boolean poss = true;
                for (int i : sso) {
                    if (tos[i].t1.indexOf(ccs) == -1) {
                        poss = false;
                        break;
                    }
                }

                if (!poss) {
                    hi = mid - 1;
                } else {
                    ansl = Math.max(ansl, mid);
                    lo = mid + 1;
                }
            }

            lo = 0;
            hi = r;

            int ansr = lo;

            while (lo <= hi) {
                int mid = (lo + hi) / 2;

                String ccs = tos[0].t1.substring(tos[0].st - ansl, tos[0].en + mid);

                boolean poss = true;
                for (int i : sso) {
                    if (tos[i].t1.indexOf(ccs) == -1) {
                        poss = false;
                        break;
                    }
                }

                if (!poss) {
                    hi = mid - 1;
                } else {
                    ansr = Math.max(ansr, mid);
                    lo = mid + 1;
                }
            }

            cs = tos[0].t1.substring(tos[0].st - ansl, tos[0].en + ansr);
            ct = tos[0].t2.substring(tos[0].st - ansl, tos[0].en + ansr);

            sz = tos[0].sz + l + r;

            for (int i : ss) {
                if (tos[i].t1.indexOf(cs) != -1) {
                    out.println("NO");
                    return;
                }
            }

            out.println("YES");
            out.println(cs);
            out.println(ct);

        } else {
            out.println("YES");
            out.println(cs);
            out.println(ct);
        }
    }
}
