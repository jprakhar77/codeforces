import java.util.*;

public class MaximizingGCD {

    Set<Long> factors(long n) {
        Set<Long> factors = new HashSet<>();

        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }

        return factors;
    }

    class DinicMaxflow {
        int n;
        List<ArrayList<Node>> g;
        int s;
        int t;
        int[] level;

        public DinicMaxflow(int n, int s, int t) {
            this.n = n;
            this.g = new ArrayList<ArrayList<Node>>();
            for (int i = 0; i < n; i++)
                g.add(new ArrayList<Node>());
            this.level = new int[n];
            this.s = s;
            this.t = t;
        }

        void addEdge(int u, int v, int cap) {
            g.get(u).add(new Node(v, 0, cap, g.get(v).size()));
            g.get(v).add(new Node(u, 0, 0, g.get(u).size() - 1));
        }

        boolean bfs() {
            ArrayDeque<Integer> q = new ArrayDeque<Integer>();

            for (int i = 0; i < n; i++) {
                level[i] = -1;
            }

            q.add(s);
            level[s] = 0;

            while (!q.isEmpty()) {
                int u = q.poll();

                ArrayList<Node> ul = g.get(u);
                int pl = level[u];

                for (int i = 0; i < ul.size(); i++) {
                    Node nv = ul.get(i);

                    int v = nv.v;
                    int fa = nv.cap - nv.flow;

                    if (level[v] == -1 && fa > 0) {
                        q.add(v);
                        level[v] = pl + 1;
                    }
                }
            }

            return level[t] != -1;
        }

        int sendFlow(int u, int[] start, int maxFlow) {
            if (u == t) {
                return maxFlow;
            }

            ArrayList<Node> ul = g.get(u);
            for (; start[u] < ul.size(); start[u]++) {
                int i = start[u];

                Node vn = ul.get(i);
                int v = vn.v;
                int f = vn.cap - vn.flow;

                if (level[v] == level[u] + 1 && f > 0) {
                    int flow = sendFlow(v, start, Math.min(f, maxFlow));

                    if (flow == 0) {
                        continue;
                    }

                    vn.flow += flow;
                    g.get(v).get(vn.reverseEdgeIndex).flow -= flow;

                    return flow;
                }
            }

            return 0;
        }

        int maxFlow() {
            int maxFlow = 0;

            while (bfs()) {
                int[] start = new int[n];

                int rf;
                while ((rf = sendFlow(s, start, Integer.MAX_VALUE)) > 0) {
                    maxFlow += rf;
                }
            }

            return maxFlow;
        }

        class Node {
            int v;
            int flow;
            int cap;
            int reverseEdgeIndex;

            public Node(int v, int flow, int cap, int reverseEdgeIndex) {
                super();
                this.v = v;
                this.flow = flow;
                this.cap = cap;
                this.reverseEdgeIndex = reverseEdgeIndex;
            }
        }
    }

    public int maximumGCDPairing(int[] a) {
        int n = a.length;

        Set<Long> facs = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long sum = a[i] + a[j];
                facs.addAll(factors(sum));
            }
        }

        long ans = 1;
        for (long num : facs) {
//            DinicMaxflow dinic = new DinicMaxflow(2 * n + 2, 0, 2 * n + 1);
//
//            for (int i = 0; i < n; i++) {
//                dinic.addEdge(0, i + 1, 1);
//            }
//
//            for (int i = 0; i < n; i++) {
//                dinic.addEdge(n + 1 + i, 2 * n + 1, 1);
//            }
//
//            for (int i = 0; i < n; i++) {
//                for (int j = i + 1; j < n; j++) {
//                    if (i == j)
//                        continue;
//
//                    long sum = a[i] + a[j];
//
//                    if (sum % num == 0) {
//                        dinic.addEdge(i + 1, n + 1 + j, 1);
//                    }
//                }
//            }
//
//            int flow = dinic.maxFlow();
//
//            if (flow == n / 2)
//                ans = Math.max(ans, num);

            long[] na = new long[n];

            Map<Long, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                na[i] = a[i] % num;
                map.merge(na[i], 1, (x, y) -> x + y);
            }

            boolean poss = true;
            for (Long key : map.keySet()) {
                int val = map.get(key);

                long rem = num - key;
                if (key == 0) {
                    if (map.get(key) % 2 == 1) {
                        poss = false;
                        break;
                    }
                } else {
                    if (!map.containsKey(rem) || map.get(rem) != val) {
                        poss = false;
                        break;
                    }
                }
            }

            if (poss) {
                ans = Math.max(ans, num);
            }
        }

        return (int) ans;
    }
}
