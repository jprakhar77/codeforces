package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class _247D {
    class num {
        int i;
        int num;

        public num(int i, int num) {
            this.i = i;
            this.num = num;
        }
    }

    class TopoSort {
        int n;
        Set[] g;
        int m;

        public TopoSort(int n, int m, Set[] g) {
            this.n = n;
            this.g = g;
            this.m = m;
        }

        List<Integer> topoOrder = new ArrayList<>();

        void topoSort() {

            int[] in = new int[n];

            ArrayDeque<Integer> q = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                for (int v : (Set<Integer>) g[i]) {
                    in[v]++;
                }
            }

            for (int i = 0; i < n; i++) {
                if (in[i] == 0) {
                    q.addLast(i);
                }
            }

            while (!q.isEmpty()) {
                int ce = q.removeFirst();

                if (ce < m)
                    topoOrder.add(ce);
                for (int v : (Set<Integer>) g[ce]) {
                    in[v]--;

                    if (in[v] == 0) {
                        q.addLast(v);
                    }
                }
            }
        }
    }

    Set[] g;

    class CycleFinder {
        Set[] g;
        int n;

        public CycleFinder(Set[] g, int n) {
            this.g = g;
            this.n = n;
            this.recstack = new boolean[n];
            this.vis = new boolean[n];
        }

        boolean isCycleDirected() {
            isCycle = false;


            for (int i = 0; i < n; i++) {
                if (!vis[i]) {
                    dfs(i);
                }
            }

            return isCycle;
        }

        boolean isCycle;
        boolean[] recstack;
        boolean[] vis;

        private void dfs(int u) {
            recstack[u] = true;

            vis[u] = true;

            for (int v : (Set<Integer>) g[u]) {
                if (recstack[v]) {
                    isCycle = true;
                }
                if (!vis[v]) {
                    dfs(v);
                }
            }

            recstack[u] = false;
        }
    }


    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();
            }
        }

        g = new Set[n * m + m];

        for (int i = 0; i < n * m + m; i++) {
            g[i] = new HashSet();
        }

        int nc = m;

        for (int i = 0; i < n; i++) {
            List<num> nums = new ArrayList<>();

            for (int j = 0; j < m; j++) {
                nums.add(new num(j, a[i][j]));
            }

            nums.sort((n1, n2) -> n1.num - n2.num);

            int j = 0;
            while (j < m && nums.get(j).num == -1) {
                j++;
            }

            int[] next = new int[m];

            next[m - 1] = m;
            for (int k = m - 2; k >= j; k--) {
                if (nums.get(k).num < nums.get(k + 1).num) {
                    next[k] = k + 1;
                } else {
                    next[k] = next[k + 1];
                }
            }

            int pn = -1;
            for (int k = j; k < m; k++) {
                int l = k;

                while (l < m - 1 && nums.get(l + 1).num == nums.get(l).num) {
                    l++;
                }

                if (l > k) {
                    if (pn != -1) {
                        for (int o = k; o <= l; o++) {
                            g[pn].add(nums.get(o).i);
                        }
                    }
                    if (l < m - 1) {
                        for (int o = k; o <= l; o++) {
                            g[nums.get(o).i].add(nc);
                        }
                        pn = nc;
                        nc++;
                    }
                } else {
                    if (pn != -1) {
                        g[pn].add(nums.get(l).i);
                    }
                    pn = nums.get(l).i;
                }

                k = l;
            }

//            for (; j < m; j++) {
//                num cn = nums.get(j);
//                if (next[j] < m) {
//                    num nn = nums.get(next[j]);
//                    g[cn.i].add(nn.i);
//                }
//            }
        }

        CycleFinder cycle = new CycleFinder(g, nc);

        if (cycle.isCycleDirected()) {
            out.println(-1);
            return;
        }

        TopoSort topoSort = new TopoSort(nc, m, g);
        topoSort.topoSort();

        List<Integer> topo = topoSort.topoOrder;

        for (int i = 0; i < topo.size(); i++) {
            out.print((topo.get(i) + 1) + " ");
        }

    }
}
