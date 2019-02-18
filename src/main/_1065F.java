package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayDeque;
import java.util.List;

public class _1065F {

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Thread th = new Thread(null, new Solve(testNumber, in, out), "Main", 1l << 26);
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Solve implements Runnable {
        List[] g;
        int n;
        int k;

        class HeightAndSubtreeSize {
            int[] depth;
            List[] g;

            void dfs(int u, int p, int currentDepth) {
                depth[u] = currentDepth;
                for (int i = 0; i < g[u].size(); i++) {
                    int v = (int) g[u].get(i);
                    if (v != p) {
                        dfs(v, u, currentDepth + 1);
                    }
                }
            }
        }

        int testNumber;
        InputReader in;
        OutputWriter out;

        public Solve(int testNumber, InputReader in, OutputWriter out) {
            this.testNumber = testNumber;
            this.in = in;
            this.out = out;
        }

        public void run() {
            solve(testNumber, in, out);
        }

        public void solve(int testNumber, InputReader in, OutputWriter out) {
            n = in.nextInt();
            k = in.nextInt();

            g = new List[n];
            in.readTree(g, n, 1);


            //HeightAndSubtreeSize hss = new HeightAndSubtreeSize();

            depth = new int[n];

            dpmax = new int[n];
            dpreach = new int[n];
            mindep = new int[n];

            //hss.depth = depth;
            //hss.g = g;
            //hss.dfs(0, -1, 0);

            ArrayDeque<Integer> stack = new ArrayDeque<>();
            ArrayDeque<Integer> stack2 = new ArrayDeque<>();

            stack.addFirst(0);
            boolean[] vis = new boolean[n];
            vis[0] = true;
            depth[0] = 0;

            while (stack.size() > 0) {
                int pop = stack.removeFirst();

                stack2.addLast(pop);

                for (int v : (List<Integer>) g[pop]) {
                    if (!vis[v]) {
                        stack.addFirst(v);
                        depth[v] = depth[pop] + 1;
                        vis[v] = true;
                    }
                }
            }

            dfs(stack2);

            out.println(dpmax[0]);
        }

        int[] dpmax;
        int[] dpreach;
        int[] mindep;

        int[] depth;

        void dfs(ArrayDeque<Integer> stack) {
            boolean[] vis = new boolean[n];
            while (stack.size() > 0) {
                int u = stack.removeLast();
                vis[u] = true;
                if (u != 0 && g[u].size() == 1) {
                    dpmax[u] = 1;
                    dpreach[u] = 1;
                    mindep[u] = depth[u] - k;
                    continue;
                }

                int curDep = depth[u];

                mindep[u] = curDep;

                for (int v : (List<Integer>) g[u]) {
                    if (vis[v]) {
                        if (mindep[v] <= curDep) {
                            dpreach[u] += dpreach[v];

                            mindep[u] = Math.min(mindep[v], mindep[u]);
                        }
                    }
                }

                dpmax[u] = dpreach[u];

                for (int v : (List<Integer>) g[u]) {
                    if (vis[u]) {
                        int total = dpreach[u];
                        if (mindep[v] <= curDep) {
                            total -= dpreach[v];
                        }
                        total += dpmax[v];

                        dpmax[u] = Math.max(dpmax[u], total);
                    }
                }

                if (mindep[u] >= curDep) {
                    dpreach[u] = 0;
                }
            }
        }
    }
}
