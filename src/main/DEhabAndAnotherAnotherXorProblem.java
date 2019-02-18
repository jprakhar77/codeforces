package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class DEhabAndAnotherAnotherXorProblem {
    public void solve(int testNumber, InputReader in, OutputWriter out) {

        out.println("? 0 0");
        out.flush();

        int ans = in.nextInt();

        int ca = 0;
        int cb = 0;


        for (int i = 29; i >= 0; i--) {
            if (ans == 0) {
                //1 1 or0 0

                int na = ca | (1 << i);
                int nb = cb | (1 << i);
                int nna = na;
                int nnb = cb;

                out.println("? " + nna + " " + nnb);
                out.flush();

                int cans2 = in.nextInt();

                if (cans2 == -1) {
                    ca = na;
                    cb = nb;

                    out.println("? " + ca + " " + cb);
                    out.flush();

                    int cans3 = in.nextInt();

                    ans = cans3;
                    continue;
                } else if (cans2 == 1) {
                    continue;
                }
            } else if (ans == 1) {
                int na = ca | (1 << i);
                int nb = cb | (1 << i);

                out.println("? " + na + " " + nb);
                out.flush();

                int cans = in.nextInt();

                if (cans != ans) {
                    ca = na;

                    out.println("? " + ca + " " + cb);
                    out.flush();

                    int cans2 = in.nextInt();

                    ans = cans2;
                    continue;
                } else {
                    //1 1 or0 0

                    int nna = na;
                    int nnb = cb;

                    out.println("? " + nna + " " + nnb);
                    out.flush();

                    int cans2 = in.nextInt();

                    if (cans2 == -1) {
                        ca = na;
                        cb = nb;
                        ans = cans;
                        continue;
                    } else if (cans2 == 1) {
                        continue;
                    }
                }
            } else if (ans == -1) {
                int na = ca | (1 << i);
                int nb = cb | (1 << i);

                out.println("? " + na + " " + nb);
                out.flush();

                int cans = in.nextInt();

                if (cans != ans) {
                    cb = nb;

                    out.println("? " + ca + " " + cb);
                    out.flush();

                    int cans2 = in.nextInt();

                    ans = cans2;
                    continue;
                } else {
                    //1 1 or0 0

                    int nna = ca;
                    int nnb = nb;

                    out.println("? " + nna + " " + nnb);
                    out.flush();

                    int cans2 = in.nextInt();

                    if (cans2 == -1) {
                        continue;
                    } else if (cans2 == 1) {
                        ca = na;
                        cb = nb;
                        ans = cans;
                        continue;
                    }
                }
            }
        }

        out.println("! " + ca + " " + cb);
        out.flush();

    }
}
