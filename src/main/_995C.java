package main;

import fastio.InputReader;
import fastio.OutputWriter;

public class _995C {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
        }

        int cx = 0;
        int cy = 0;

        for (int i = 0; i < n; i++) {
            int nx = cx + x[i];
            int ny = cy + y[i];

            double dis1 = dis(nx, ny);

            int nx2 = cx - x[i];
            int ny2 = cy - y[i];

            double dis2 = dis(nx2, ny2);

            if (dis1 <= dis2) {
                out.print(1 + " ");
                cx = nx;
                cy = ny;
            } else {
                out.print(-1 + " ");
                cx = nx2;
                cy = ny2;
            }
        }
    }

    double dis(long x, long y) {
        double dis = Math.sqrt(x * x + y * y);
        return dis;
    }
}
