package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class CRegionSeparation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.nextInt();

        a = in.nextIntArray(n);

        for (int val : a) {
            tot += val;
        }

        g = new List[n];

        in.readTree(g, n, 1);

        sum = new long[n];
        cnt = new int[n + 1];

        //dfs0(0, -1);

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.addLast(0);

        boolean[] vis = new boolean[n];

        vis[0] = true;

        ArrayDeque<Integer> stack2 = new ArrayDeque<>();

        while (!stack.isEmpty()) {
            int pe = stack.removeLast();

            stack2.addLast(pe);

            for (int i = 0; i < g[pe].size(); i++) {
                int v = (int) g[pe].get(i);

                if (!vis[v]) {
                    stack.addLast(v);
                    vis[v] = true;
                }
            }
        }

        vis = new boolean[n];

        while (!stack2.isEmpty()) {
            int pe = stack2.removeLast();
            vis[pe] = true;

            sum[pe] = a[pe];

            for (int i = 0; i < g[pe].size(); i++) {
                int v = (int) g[pe].get(i);

                if (vis[v]) {
                    sum[pe] += sum[v];
                }
            }

            long g = gcd(tot, sum[pe]);

            if (tot / g <= n) {
                cnt[(int) (tot / g)]++;
            }
        }

        //factors = factors(tot);
        //pf = primeFactors(tot);

        int[] cnt2 = cnt.clone();

        for (int i = 1; i <= n; i++) {
            if (tot % i == 0) {
                for (int j = 2; j * i <= n; j++) {
                    if (tot % (j * i) == 0) {
                        //long res = tot / (j * i);
                        cnt2[j * i] += cnt[i];
                    }
                }
            }
        }

        int[] dp = new int[n + 1];

//        for (long fac : factors) {
//            long times = tot / fac;
//
//            if (tot / fac <= n && times == cnt2[(int) times]) {
//                ff.add(fac);
//                //dp[(int) times]++;
//                //poss.put(fac, true);
//            } else {
//                //poss.put(fac, false);
//            }
//        }

        dp[1] = 1;

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[i] == 0)
                continue;
            for (int j = 2; j * i <= n; j++) {
                if (i * j == cnt2[i * j]) {
                    dp[i * j] += dp[i];
                    dp[i * j] %= mod;
                }
            }
            ans += dp[i];
            ans %= mod;
        }

        out.println(ans);
//        int an = ff.size();
//
//        ff.sort(Comparator.naturalOrder());
//
//        long[] dp = new long[an];
//
//        long ans = 1;
//
//        dp[an - 1] = 1;
//
//        for (int i = an - 2; i >= 0; i--) {
//            long cn = ff.get(i);
//            for (int j = i + 1; j < an; j++) {
//                long nn = ff.get(j);
//
//                if (nn % cn == 0) {
//                    dp[i] += dp[j];
//                    dp[i] %= mod;
//                }
//            }
//
//            ans += dp[i];
//            ans %= mod;
//        }
//
//        out.println(ans);
    }

    int mod = 1000000007;

    //Map<Long, Boolean> poss = new HashMap<>();
    //TreeSet<Long> factors;
    //List<Long> ff = new ArrayList<>();

    //Set<Long> pf;
    long tot;
    long[] sum;
    int[] a;

    int n;

    int[] cnt;

    List[] g;

    //Map<Long, Integer> map = new HashMap<>();

    void dfs0(int u, int p) {
        sum[u] = a[u];

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);

            if (v != p) {
                dfs0(v, u);
                sum[u] += sum[v];
            }
        }

        //map.merge(sum[u], 1, (x, y) -> x + y);

        long g = gcd(sum[u], tot);

        if (tot / g <= n) {
            cnt[(int) (tot / g)]++;
        }
    }

    long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    TreeSet<Long> factors(long n) {
        TreeSet<Long> factors = new TreeSet<>(Comparator.reverseOrder());

        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }

        return factors;
    }

    Set<Long> primeFactors(long n) {

        Set<Long> set = new HashSet<>();

        long cn = n;
        for (long i = 2; i * i <= cn; i++) {
            if (cn % i == 0) {
                set.add(i);
                while (cn % i == 0) {
                    cn /= i;
                }
            }
        }

        if (cn > 1) {
            set.add(cn);
        }

        return set;
    }
}
