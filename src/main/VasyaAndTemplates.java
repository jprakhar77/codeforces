package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class VasyaAndTemplates {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int t = in.nextInt();

        t:
        while (t-- > 0) {
            k = in.nextInt();

            s = in.next();
            a = in.next();
            b = in.next();

            n = s.length();

            Arrays.fill(map, emp);

            uc1.clear();
            uc2.clear();

            kch = (char) (k - 1 + 'a');

            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                char ach = a.charAt(i);
                char bch = b.charAt(i);

                if (a.charAt(i) == b.charAt(i)) {
                    if (uc1.contains(ch) || uc2.contains(ach)) {
                        if (map[ch - 'a'] != a.charAt(i)) {
                            out.println("NO");
                            continue t;
                        }
                    } else {
                        map[ch - 'a'] = a.charAt(i);
                        uc1.add(ch);
                        uc2.add(ach);
                    }
                } else {
                    if (ach > bch) {
                        out.println("NO");
                        continue t;
                    }
                    if (map[ch - 'a'] != emp) {
                        if (map[ch - 'a'] == ach) {
                            int val = solveLtBEqA(i + 1);
                            if (val == -1) {
                                out.println("NO");
                            } else {
                                solveEasy();
                                printSuc(out);
                            }
                        } else if (map[ch - 'a'] == bch) {
                            int val = solveGtAEqB(i + 1);
                            if (val == -1) {
                                out.println("NO");
                            } else {
                                solveEasy();
                                printSuc(out);
                            }
                        } else if (map[ch - 'a'] < ach || map[ch - 'a'] > bch) {
                            out.println("NO");
                            continue t;
                        } else {
                            solveEasy();
                            printSuc(out);
                        }
                        continue t;
                    }
                    char[] mc = map.clone();
                    Set<Character> ucc1 = new HashSet<>(uc1);
                    Set<Character> ucc2 = new HashSet<>(uc2);
                    o:
                    for (char j = ach; j <= bch; j++) {
                        if (!uc2.contains(j)) {
                            map[ch - 'a'] = j;
                            uc1.add(ch);
                            uc2.add(j);
                            if (j == ach) {
                                int val = solveLtBEqA(i + 1);
                                if (val == -1) {
                                    map = mc;
                                    uc1 = ucc1;
                                    uc2 = ucc2;
                                    continue o;
                                } else {
                                    solveEasy();
                                    printSuc(out);
                                    continue t;
                                }
                            } else if (j == bch) {
                                int val = solveGtAEqB(i + 1);
                                if (val == -1) {
                                    map = mc;
                                    uc1 = ucc1;
                                    uc2 = ucc2;
                                    continue o;
                                } else {
                                    solveEasy();
                                    printSuc(out);
                                    continue t;
                                }
                            } else {
                                solveEasy();
                                printSuc(out);
                                continue t;
                            }
                        }
                    }
                    out.println("NO");
                    continue t;
                }
            }

            solveEasy();
            printSuc(out);
        }
    }

    void printSuc(OutputWriter out) {
        out.println("YES");
        for (int i = 0; i < k; i++) {
            out.print(map[i]);
        }
        out.println();
    }

    String s;
    String a;
    String b;

    int n;

    int k;

    char[] map = new char[26];

    Set<Character> uc1 = new HashSet<>();
    Set<Character> uc2 = new HashSet<>();

    char emp = '\0';

    char kch;

    int solveGtAEqB(int si) {
        o:
        for (int i = si; i < n; i++) {
            char ch = s.charAt(i);
            char ach = a.charAt(i);
            char bch = b.charAt(i);
            if (map[ch - 'a'] != emp) {
                if (map[ch - 'a'] < bch) {
                    return i + 1;
                } else if (map[ch - 'a'] > bch) {
                    return -1;
                } else continue;
            }
            for (char j = 'a'; j <= bch; j++) {
                if (!uc2.contains(j)) {
                    map[ch - 'a'] = j;
                    uc1.add(ch);
                    uc2.add(j);
                    if (j < bch) {
                        return i + 1;
                    } else {
                        continue o;
                    }
                }
            }
            return -1;
        }

        return n;
    }

    int solveLtBEqA(int si) {
        o:
        for (int i = si; i < n; i++) {
            char ch = s.charAt(i);
            char ach = a.charAt(i);
            char bch = b.charAt(i);
            if (map[ch - 'a'] != emp) {
                if (map[ch - 'a'] > ach) {
                    return i + 1;
                } else if (map[ch - 'a'] < ach) {
                    return -1;
                } else continue;
            }
            for (char j = kch; j >= ach; j--) {
                if (!uc2.contains(j)) {
                    map[ch - 'a'] = j;
                    uc1.add(ch);
                    uc2.add(j);
                    if (j > ach) {
                        return i + 1;
                    } else {
                        continue o;
                    }
                }
            }
            return -1;
        }

        return n;
    }

    void solveEasy() {
        for (char i = 'a'; i <= kch; i++) {
            if (!uc1.contains(i)) {
                for (char j = 'a'; j <= kch; j++) {
                    if (!uc2.contains(j)) {
                        map[i - 'a'] = j;
                        uc1.add(i);
                        uc2.add(j);
                        break;
                    }
                }
            }
        }
    }
}


