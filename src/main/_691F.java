package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashMap;
import java.util.TreeMap;

public class _691F {
    int maxp = 3 * (int) 1e6;
    int maxpr = (int) Math.sqrt(maxp);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] a = new int[n];

        HashMap<Integer, Long> cm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
            cm.put(a[i], cm.getOrDefault(a[i], 0L) + 1);
        }

        int m = in.nextInt();

        long[] dp = new long[maxp + 1];

        for (Integer num : cm.keySet()) {
            long c1 = cm.get(num);

            for (int i = 1; i * num <= maxp; i++) {
                if (cm.containsKey(i)) {
                    if (i == num) {
                        dp[i * num] += c1 * (c1 - 1);
                    } else {
                        long c2 = cm.get(i);
                        dp[i * num] += c1 * c2;
                    }
                }
            }
        }

        TreeMap<Integer, Long> cmc = new TreeMap<>(cm);

        long ex = 0;
        long rc = 0;
        for (Integer key : cmc.descendingKeySet()) {
            long cv = cmc.get(key);

            cmc.put(key, cv + rc);

            rc += cv;
        }

        for (Integer num : cm.keySet()) {
            long c1 = cm.get(num);

            int mul = (maxp + num) / num;

            Integer mulcm = cmc.ceilingKey(mul);

            if (mulcm != null) {
                long c2 = cmc.get(mulcm);

                if (mulcm <= num) {
                    ex += c1 * (c2 - 1);
                } else {
                    ex += c1 * c2;
                }
            }

        }

        dp[maxp] += ex;

        for (int i = maxp - 1; i >= 1; i--) {
            dp[i] += dp[i + 1];
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int p = in.nextInt();
            ans.append(dp[p]);
            ans.append("\n");
        }

        out.println(ans);
    }
}
