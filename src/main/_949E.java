//package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _949E {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        Set<Integer> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            s.add(a[i]);
        }

        List<Integer> l = rec(s, n, 0);

        StringBuilder ans = new StringBuilder();
        ans.append(l.size());
        ans.append(System.lineSeparator());
        for (int i = 0; i < l.size(); i++) {
            ans.append(l.get(i));
            ans.append(" ");
        }

        out.println(ans.toString());
    }

    List<Integer> rec(Set<Integer> a, int n, int div) {
        if (isZero(a, n)) {
            return new ArrayList<>();
        }
        int val = -1;
        if ((val = isOne(a, n)) != -3) {
            List<Integer> l = new ArrayList<>();
            val = isOne(a, n);
            if (val == -2) {
                l.add((1 << div));
                l.add(-1 * (1 << div));
            } else {
                l.add(val * (1 << div));
            }
            return l;
        }
        if (isOdd(a, n)) {
            //-1
            //int[] b = Arrays.copyOf(a, n);
            HashSet<Integer> b = new HashSet<>();
            for (Integer num : a) {
                if (num % 2 == 1 || num % 2 == -1) {
                    b.add(num + 1);
                } else {
                    b.add(num);
                }
            }
            List<Integer> l1 = rec(div(b, n), n, div + 1);
            //1
            b = new HashSet<>();
            for (Integer num : a) {
                if (num % 2 == 1 || num % 2 == -1) {
                    b.add(num - 1);
                } else {
                    b.add(num);
                }
            }
            List<Integer> l2 = rec(div(b, n), n, div + 1);

            if (l1.size() <= l2.size()) {
                l1.add(-1 * (1 << div));
                return l1;
            } else {
                l2.add((1 << div));
                return l2;
            }
        } else {
            Set<Integer> b = new HashSet<>(a);
            List<Integer> l = rec(div(b, n), n, div + 1);
            return l;
        }
    }

    boolean isOdd(Set<Integer> a, int n) {
        for (Integer num : a) {
            if (num % 2 == 1 || num % 2 == -1) {
                return true;
            }
        }
        return false;
    }

    boolean isZero(Set<Integer> a, int n) {
        for (Integer num : a) {
            if (num != 0)
                return false;
        }
        return true;
    }

    int isOne(Set<Integer> a, int n) {
        int mo = 0;
        int z = 0;
        int po = 0;
        for (Integer num : a) {
            if (Math.abs(num) > 1)
                return -3;
            else {
                switch (num) {
                    case -1:
                        mo++;
                        break;
                    case 1:
                        po++;
                        break;
                    case 0:
                        z++;
                        break;
                }
            }
        }
        if (mo > 0 && po > 0)
            return -2;
        return mo > po ? -1 : 1;
    }

    Set<Integer> div(Set<Integer> a, int n) {
        Set<Integer> b = new HashSet<>();
        for (Integer num : a) {
            b.add(num / 2);
        }
        return b;
    }


    int ans = 40;
    List<Integer> ansl = null;

    void opt(List<Integer> l) {
        if (l.size() < ans) {
            ans = l.size();
            ansl = l;
        }
    }

    List<Integer> cal(List<Integer> pn, int[] pp) {
        boolean[] pr = new boolean[21];
        for (int i = 0; i < pn.size(); i++) {
            int num = pn.get(i);
            for (int j = 20; j >= 0; j--) {
                if (pp[j] <= num) {
                    num -= pp[j];
                    pr[j] = true;
                }
            }
        }
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            if (pr[i]) {
                l.add(pp[i]);
            }
        }
        return l;
    }

    List<Integer> cal2(List<Integer> nn, int[] np) {
        boolean[] pr = new boolean[21];
        for (int i = 0; i < nn.size(); i++) {
            int num = nn.get(i);
            for (int j = 20; j >= 0; j--) {
                if (np[j] >= num) {
                    num -= np[j];
                    pr[j] = true;
                }
            }
        }
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            if (pr[i]) {
                l.add(np[i]);
            }
        }
        return l;
    }
}
