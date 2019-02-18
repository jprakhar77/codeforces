package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.*;

public class PrimeDivisors {
    int lim = 1000000;
    List[] mul;

    public PrimeDivisors() {
        boolean[] isp = new boolean[lim + 1];

        int[] min = new int[1000001];
        int[] sop = new int[1000001];

        Arrays.fill(isp, true);

        isp[1] = false;

        for (int i = 2; i <= lim; i++) {
            if (isp[i]) {
                min[i] = i;
                sop[i] = i;
                for (int j = 2; j * i <= lim; j++) {
                    isp[i * j] = false;
                    min[i * j] = i;
                    sop[i * j] += i;
                }
            }
        }

        mul = new List[lim + 1];

        for (int i = 0; i <= lim; i++) {
            mul[i] = new ArrayList<Integer>();
            mul[i].add(i);
        }
        //int ele = 0;
        for (int i = 2; i <= lim; i++) {
            for (int j = 2; j * i <= lim; j++) {
                if (sop[i * j] % sop[i] == 0) {
                    mul[i].add(i * j);
                    //          ele++;
                }
            }
        }
    }

    int[] a = new int[lim];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        //out.println(ele);
        int t = in.nextInt();

        Map[] frea = new Map[lim + 1];

        for (int i = 2; i <= lim; i++) {
            frea[i] = new HashMap<Integer, Integer>();
        }

        int ct = 0;
        while (ct < t) {
            int n = in.nextInt();

            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                frea[a[i]].merge(ct, 1, (x, y) -> (int) x + (int) y);
            }

            ct++;
        }

        long[] ans = new long[t];

        for (int i = 2; i <= lim; i++) {
            int num = i;

            if (frea[num].isEmpty())
                continue;

            for (int j = 0; j < mul[num].size(); j++) {
                //ans[(int) mul[num].get(j)]++;
                int num2 = (int) mul[num].get(j);

                if (frea[num2].isEmpty())
                    continue;

                for (Integer tn : (Set<Integer>) frea[num].keySet()) {
                    int val = (int) frea[num].get(tn);
                    if (num != num2) {
                        ans[tn] += (long) val * (int) frea[num2].getOrDefault(tn, 0);
                    } else {
                        ans[tn] += ((long) val * (val - 1));
                    }
                }
            }
        }

        for (int i = 0; i < t; i++)
            out.println(ans[i]);
        //}

    }


    List<Integer> prime(int n) {
        boolean[] isp = new boolean[n + 1];

        Arrays.fill(isp, true);

        isp[1] = false;

        for (int i = 2; i <= n; i++) {
            if (isp[i]) {
                for (int j = 2; j * i <= n; j++) {
                    isp[i * j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (isp[i])
                primes.add(i);
        }

        return primes;
    }
}
