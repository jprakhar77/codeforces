package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class _1059E {
    List[] g;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        L = in.nextInt();

        S = in.nextLong();

        w = new int[n];

        for (int i = 0; i < n; i++) {
            w[i] = in.nextInt();
            if (w[i] > S) {
                out.println(-1);
                return;
            }
        }

        g = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList();
        }

        for (int i = 1; i < n; i++) {
            int p = in.nextInt() - 1;
            g[p].add(i);
            g[i].add(p);
        }

        height = new int[n];
        res = new res[n];

        dfs(0, -1);

        ans += res[0].tm.size();

        out.println(ans);
    }

    int[] height;
    int[] w;
    int L;
    long S;
    res[] res;

    long ans = 0;

    void dfs(int u, int p) {

        int heightVer = -1;
        int maxHeight = 0;

        for (int v : (List<Integer>) g[u]) {
            if (v != p) {
                dfs(v, u);
                int newHeight = height[v] + 1;

                if (newHeight > maxHeight) {
                    maxHeight = newHeight;
                    heightVer = v;
                }
            }
        }

        height[u] = maxHeight;

        if (heightVer != -1) {
            res[u] = res[heightVer];
            res[u].extraLength++;
            res[u].extraWeight += w[u];

            res resu = res[u];

            Integer lastKey = resu.tm.size() > 0 ? resu.tm.lastKey() : null;
            while (lastKey != null && lastKey + resu.extraLength > L) {
                ans++;
                resu.tm.remove(lastKey);
                if (resu.tm.size() > 0)
                    lastKey = resu.tm.lastKey();
                else
                    lastKey = null;
            }

            Map.Entry<Integer, Long> firstEntry = resu.tm.firstEntry();
            while (firstEntry != null && firstEntry.getValue() + resu.extraWeight > S) {
                ans++;
                resu.tm.remove(firstEntry.getKey());
                firstEntry = resu.tm.firstEntry();
            }


            for (int v : (List<Integer>) g[u]) {
                if (v != p && v != heightVer) {
                    res resv = res[v];
                    int exL = resv.extraLength;
                    long exW = resv.extraWeight;
                    for (Integer len : resv.tm.keySet()) {
                        long wei = resv.tm.get(len);
                        len += exL;
                        wei += exW;

                        len++;
                        wei += w[u];

                        len -= resu.extraLength;
                        wei -= resu.extraWeight;

                        if (resu.tm.containsKey(len)) {
                            ans++;

                            resu.tm.merge(len, wei, (x, y) -> Math.min(x, y));
                        } else {
                            Integer floor = resu.tm.floorKey(len);
                            Integer ceil = resu.tm.ceilingKey(len);

                            while (ceil != null && resu.tm.get(ceil) >= wei) {
                                ans++;
                                resu.tm.remove(ceil);

                                ceil = resu.tm.ceilingKey(len);
                            }

                            if (floor != null && resu.tm.get(floor) <= wei) {
                                ans++;
                            } else {
                                if (wei + resu.extraWeight <= S && len + resu.extraLength <= L)
                                    resu.tm.put(len, wei);
                                else ans++;
                            }
                        }
                    }
                }
            }

            if (resu.tm.size() == 0) {
                resu.tm.put(1 - resu.extraLength, w[u] - resu.extraWeight);
            }
//            int len = 0 - resu.extraLength;
//            long wei = w[u] - resu.extraWeight;
//            Integer ceil = resu.cm.ceilingKey(len);
//
//            while (ceil != null && resu.cm.get(ceil) >= wei) {
//                ans++;
//                resu.cm.remove(ceil);
//
//                ceil = resu.cm.ceilingKey(len);
//            }
        } else {
            res[u] = new res();
            res resu = res[u];
            res[u].tm.put(1 - resu.extraLength, w[u] - resu.extraWeight);
//            int len = 0 - resu.extraLength;
//            long wei = w[u] - resu.extraWeight;
//            Integer ceil = resu.cm.ceilingKey(len);
//
//            while (ceil != null && resu.cm.get(ceil) >= wei) {
//                ans++;
//                resu.cm.remove(ceil);
//
//                ceil = resu.cm.ceilingKey(len);
//            }
        }
    }


    class res {
        TreeMap<Integer, Long> tm = new TreeMap<>();
        long extraWeight;
        int extraLength;
    }
}
