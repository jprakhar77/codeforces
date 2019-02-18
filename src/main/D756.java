package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D756 {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Map<Long, Integer> m = new HashMap<>();

        int n = in.nextInt();

        for (int i = 1; i <= n; i++) {
            Map<Long, Integer> cm = primeFactors(i);

            for (long key : cm.keySet()) {
                m.merge(key, cm.get(key), (x, y) -> x + y);
            }
        }

        List<Integer> l = new ArrayList<>();
        for (long key : m.keySet()) {
            l.add(m.get(key));
        }

        int ans = 0;
        for (int i = 0; i < l.size(); i++) {
            for (int j = i + 1; j < l.size(); j++) {
                for (int k = 0; k < l.size(); k++) {
                    if (k != i && k != j && l.get(i) >= 4 && l.get(j) >= 4 && l.get(k) >= 2) {
                        ans++;
                    }
                }
            }
        }

        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.size(); j++) {
                if (i != j && l.get(i) >= 24 && l.get(j) >= 2) {
                    ans++;
                }
            }
        }

        for (int i = 0; i < l.size(); i++) {
            for (int j = 0; j < l.size(); j++) {
                if (i != j && l.get(i) >= 14 && l.get(j) >= 4) {
                    ans++;
                }
            }
        }

        for (int i = 0; i < l.size(); i++) {
            if (l.get(i) >= 74) {
                ans++;
            }
        }

        out.println(ans);
    }

    HashMap<Long, Integer> primeFactors(long n) {
        HashMap<Long, Integer> cm = new HashMap<>();

        long cn = n;
        for (long i = 2; i * i <= cn; i++) {
            if (cn % i == 0) {
                while (cn % i == 0) {
                    cn /= i;
                    cm.merge(i, 1, (x, y) -> x + y);
                }
            }
        }

        if (cn > 1) {
            cm.merge(cn, 1, (x, y) -> x + y);
        }

        return cm;
    }
}
