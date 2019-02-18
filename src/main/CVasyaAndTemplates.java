//package main;
//
//import fastio.InputReader;
//import fastio.OutputWriter;
//
//import java.util.Arrays;
//
//public class CVasyaAndTemplates {
//    public void solve(int testNumber, InputReader in, OutputWriter out) {
//        int t = in.nextInt();
//
//        o:
//        while (t-- > 0) {
//            int k = in.nextInt();
//
//            String s = in.next();
//
//            String a = in.next();
//            String b = in.next();
//
//            int n = s.length();
//
//            int i = 0;
//
//            int[] h = new int[k];
//            hinv = new int[k];
//
//
//            state = 0;
//            Arrays.fill(h, -1);
//            for (; i < n; i++) {
//                int ind = s.charAt(i) - 'a';
//                if (a.charAt(i) == b.charAt(i)) {
//                    int val = a.charAt(i) - 'a';
//
//                    if (h[ind] == -1) {
//                        h[ind] = val;
//                        hinv[val] = ind;
//                    } else {
//                        if (h[ind] != val) {
//                            out.println("NO");
//                            continue o;
//                        }
//                    }
//                } else {
//                    if (h[ind] == -1) {
//                        break;
//                    } else {
//                        int val = h[ind];
//
//                        char cc = (char) ('a' + val);
//
//                        if (cc < a.charAt(i) || cc > b.charAt(i)) {
//                            out.println("NO");
//                            continue o;
//                        } else {
//                            if (cc > a.charAt(i) && cc < b.charAt(i)) {
//                                state = 3;
//                            } else if (cc > a.charAt(i)) {
//                                state = 1;
//                            } else if (cc < b.charAt(i)) {
//                                state = 2;
//                            }
//                        }
//                    }
//                }
//            }
//
//            if (i == n) {
//                out.println("YES");
//                //todo
//                continue;
//            }
//
//            if (state == 0) {
//                if (b.charAt(i) - a.charAt(i) >= 2) {
//                    int ind = s.charAt(i) - 'a';
//
//                    for (int j = a.charAt(i) - 'a' + 1; j < b.charAt(i) - 'a'; j++) {
//                        if (hinv[j] == -1) {
//                            h[ind] = j;
//                            hinv[j] = ind;
//                            state = 3;
//                            i++;
//                            break;
//                        }
//                    }
//                }
//            }
//
//            if (i == n) {
//                //tdo
//            }
//
//            if (state == 3) {
//
//            } else if (state == 0) {
//                //try 1st
//                {
//                    int ind = s.charAt(i) - 'a';
//
//                    h[ind] = a.charAt(i) - 'a';
//
//                    state = 2;
//
//                    i++;
//
//                    poss = true;
//
//                    solve1(i, h, s, a, b);
//
//                    if (poss) {
//                        print();
//                        //todo
//                        continue o;
//                    }
//                }
//
//                {
//                    int ind = s.charAt(i) - 'a';
//
//                    h[ind] = b.charAt(i) - 'a';
//
//                    state = 1;
//
//                    i++;
//
//                    poss = true;
//
//                    solve2(i, h, s, a, b);
//
//                    if (poss) {
//                        print();
//                        //todo
//                        continue o;
//                    }
//                }
//
//                out.println("NO");
//                continue o;
//
//            } else if (state == 1) {
//                poss = true;
//                solve1(i, h, s, a, b);
//                if (poss) {
//                    //to
//                }
//            } else if (state == 2) {
//                poss = true;
//                solve2(i, h, s, a, b);
//                if (poss) {
//                    //to
//                }
//            }
//
//            out.println("NO");
//        }
//    }
//
//    int state;
//    boolean poss = true;
//
//    //gte
//    int[] solve1(int i, int[] h, String s, String a, String b) {
//        int n = s.length();
//        for (; i < n; i++) {
//            int ind = s.charAt(i) - 'a';
//            if (h[ind] != -1) {
//                char cc = s.charAt(i);
//
//                if (cc > b.charAt(i)) {
//                    poss = false;
//                    return null;
//                }
//            } else {
//                if (a.charAt(i) == b.charAt(i)) {
//                    int val = a.charAt(i) - 'a';
//                    if (h[ind] != val) {
//                        poss = false;
//                        return null;
//                    }
//                } else if (a.charAt(i) < b.charAt(i)) {
//                    h[ind] = a.charAt(i) - 'a';
//                    state = 3;
//                    return null;
//                } else {
//
//                }
//            }
//        }
//    }
//
//    int[] hinv;
//
//    int[] solve2(int i, int[] h, String s, String a, String b) {
//
//    }
//
//
//    void print() {
//
//    }
//}
