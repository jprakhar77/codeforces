package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class UsefulNumber {

    boolean s = true;

    List[] fac;
    List[] pf;
    int[] cnt = new int[100001];
    int[] nof = new int[100001];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);

        if (s) {
            fac = new List[100001];
            pf = new List[100001];

            for (int i = 1; i <= 100000; i++) {
                fac[i] = new ArrayList<Integer>();
                pf[i] = primeFactors(i);
                int psz = pf[i].size();
                for (int j = 0; j < (1 << psz); j++) {
                    int mul = 1;
                    int cnt = 0;
                    for (int k = 0; k < psz; k++) {
                        if (((1 << k) & j) != 0) {
                            int cnum = (int) pf[i].get(k);
                            mul *= cnum;
                            cnt++;
                        }

                    }
                    fac[i].add(mul);
                    nof[mul] = cnt;
                }
            }

            s = false;
        }

        Arrays.fill(cnt, 0);

        long ans = 1;

        for (int i = 0; i < n; i++) {
            int num = a[i];
            List<Integer> cfl = fac[num];
            for (int j = 0; j < cfl.size(); j++) {
                int cf = cfl.get(j);
                cnt[cf]++;
                ans = Math.max(ans, cnt[cf] * nof[cf]);
            }
        }

//        for (int i = 0; i < n; i++) {
//            int num = a[i];
//
//            int psz = pf[num].size();
//
//            for (int j = 0; j < (1 << psz); j++) {
//                List<Integer> cl = new ArrayList<>();
//                int mul = 1;
//                for (int k = 0; k < psz; k++) {
//                    if (((1 << k) & j) != 0) {
//                        int cnum = (int) pf[num].get(k);
//                        cl.add(cnum);
//                        mul *= cnum;
//                    }
//                }
//
//                ans = Math.max(ans, cl.size() * cnt[mul]);
//            }
//        }

        out.println(ans);
    }

    List<Integer> primeFactors(int n) {
        List<Integer> pfl = new ArrayList<>();

        int cn = n;
        for (int i = 2; i * i <= cn; i++) {
            if (cn % i == 0) {
                pfl.add(i);
                while (cn % i == 0) {
                    cn /= i;
                }
            }
        }

        if (cn > 1) {
            pfl.add(cn);
        }

        return pfl;
    }

    Set<Integer> factors(int n) {
        Set<Integer> factors = new HashSet<>();

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }

        return factors;
    }
}
