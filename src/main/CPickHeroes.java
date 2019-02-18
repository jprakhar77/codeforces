package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CPickHeroes {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int m = in.nextInt();

        int[] p = in.nextIntArray(2 * n);

        int[] c1 = new int[2 * n + 1];
        int[] c2 = new int[2 * n + 1];

        Arrays.fill(c1, -1);
        Arrays.fill(c2, -1);

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            c1[a] = b;
            c1[b] = a;
            set.add(a);
            set.add(b);
        }


        Set<Integer> done = new HashSet<>();
        int t = in.nextInt();
        if (t == 1) {
            int nex;
            for (int i = 1; i <= 2 * n; i++) {
                if (c1[i] != -1 && !done.contains(i)) {
                    int a = i;
                    int b = c1[i];

                    if (p[a - 1] >= p[b - 1]) {
                        out.println(a);
                        out.flush();
                        nex = in.nextInt();
                        done.add(a);
                        done.add(nex);
                    } else {
                        out.println(b);
                        out.flush();
                        nex = in.nextInt();
                        done.add(b);
                        done.add(nex);
                    }
                }
            }

            while (done.size() < 2 * n) {
                int max = 0;
                int ind = -1;
                for (int i = 1; i <= 2 * n; i++) {
                    if (!set.contains(i) && !done.contains(i) && max < p[i - 1]) {
                        max = Math.max(max, p[i - 1]);
                        ind = i;
                    }
                }

                out.println(ind);
                out.flush();
                nex = in.nextInt();
                done.add(ind);
                done.add(nex);
            }
        } else {
            int mdone = 0;
            while (done.size() < 2 * n) {
                int nex = in.nextInt();
                done.add(nex);

                if (set.contains(nex)) {
                    out.println(c1[nex]);
                    out.flush();
                    mdone++;
                    done.add(nex);
                    done.add(c1[nex]);
                } else {
                    for (int i = 1; i <= 2 * n; i++) {
                        if (c1[i] != -1 && !done.contains(i)) {
                            int a = i;
                            int b = c1[i];

                            if (p[a - 1] >= p[b - 1]) {
                                out.println(a);
                                out.flush();
                                nex = in.nextInt();
                                done.add(a);
                                done.add(nex);
                            } else {
                                out.println(b);
                                out.flush();
                                nex = in.nextInt();
                                done.add(b);
                                done.add(nex);
                            }
                        }
                    }

                    while (done.size() < 2 * n) {
                        int max = 0;
                        int ind = -1;
                        for (int i = 1; i <= 2 * n; i++) {
                            if (!set.contains(i) && !done.contains(i) && max < p[i - 1]) {
                                max = Math.max(max, p[i - 1]);
                                ind = i;
                            }
                        }

                        out.println(ind);
                        out.flush();
                        done.add(ind);

                        if (done.size() < 2 * n) {
                            nex = in.nextInt();
                            done.add(nex);
                        }
                    }
//                    if (mdone < m) {
//                        for (int i = 1; i <= 2 * n; i++) {
//                            if (c1[i] != -1 && !done.contains(i)) {
//                                int a = i;
//                                int b = c1[i];
//
//                                if (p[a - 1] >= p[b - 1]) {
//                                    out.println(a);
//                                    out.flush();
//                                    nex = in.nextInt();
//                                } else {
//                                    out.println(b);
//                                    out.flush();
//                                    nex = in.nextInt();
//                                }
//
//                                mdone++;
//                                done.add(a);
//                                done.add(nex);
//                                break;
//                            }
//                        }
//
//                    } else {
//                        int max = 0;
//                        int ind = -1;
//                        for (int i = 1; i <= 2 * n; i++) {
//                            if (!set.contains(i) && !done.contains(i)) {
//                                max = Math.max(max, p[i - 1]);
//                                ind = i;
//                            }
//                        }
//
//                        out.println(ind);
//                        out.flush();
//                        nex = in.nextInt();
//                        done.add(ind);
//                        done.add(nex);
//                    }
                }
            }
        }
    }
}
