package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        String[] mat = new String[n];

        for (int i = 0; i < n; i++) {
            mat[i] = in.next();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i].charAt(j) == '#') {
                    for (int x = i + 1; x < n; x++) {
                        if (mat[x].charAt(j) == '#') {
                            for (int y = 0; y < m; y++) {
                                if (mat[i].charAt(y) != mat[x].charAt(y)) {
                                    out.println("No");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[j].charAt(i) == '#') {
                    for (int x = i + 1; x < m; x++) {
                        if (mat[j].charAt(x) == '#') {
                            for (int y = 0; y < n; y++) {
                                if (mat[y].charAt(i) != mat[y].charAt(x)) {
                                    out.println("No");
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }

        out.println("Yes");
    }
}
