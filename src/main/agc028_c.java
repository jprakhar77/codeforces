package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class agc028_c {
    class num {
        long num;
        int i;
        int p;

        public num(long num, int i, int p) {
            this.num = num;
            this.i = i;
            this.p = p;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[][] a = new int[n][2];

        List<num> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a[i][0] = in.nextInt();
            a[i][1] = in.nextInt();

            nums.add(new num(a[i][0], i, 0));
            nums.add(new num(a[i][1], i, 1));
        }

        long sum = 0;
        nums.sort((n1, n2) -> (int) Math.signum(n1.num - n2.num));

        Set<Integer> ind = new HashSet<>();

        for (int i = 0; i < n; i++) {
            ind.add(nums.get(i).i);
            sum += nums.get(i).num;
        }

        if (ind.size() < n) {
            out.println(sum);
            return;
        }

        ind.clear();
        for (int i = 0; i < n; i++) {
            ind.add(nums.get(i).p);
        }

        if (ind.size() == 1) {
            out.println(sum);
            return;
        }

        List<Integer> aInd = new ArrayList<>();
        List<Integer> bInd = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums.get(i).p == 0) {
                aInd.add(i);
            } else {
                bInd.add(i);
            }
        }

        Set<Integer> aIndM = new HashSet<>();
        Set<Integer> bIndM = new HashSet<>();

        aIndM.addAll(min(aInd, nums));
        aIndM.addAll(minS(aInd, nums, a, 1));
        aIndM.addAll(max(aInd, nums));
        aIndM.addAll(maxS(aInd, nums, a, 1));

        bIndM.addAll(min(bInd, nums));
        bIndM.addAll(minS(bInd, nums, a, 0));
        bIndM.addAll(max(bInd, nums));
        bIndM.addAll(maxS(bInd, nums, a, 0));

        long ans = inf;
        for (Integer i : bIndM) {
            for (Integer j : bIndM) {
                if (i == j && bInd.size() > 1)
                    continue;
                for (Integer x : aIndM) {
                    for (Integer y : aIndM) {
                        if (x == y && aInd.size() > 1)
                            continue;

                        long csum = sum;

                        csum -= Math.max(nums.get(y).num, nums.get(i).num);
                        csum += Math.min(a[nums.get(j).i][0], a[nums.get(x).i][1]);

                        ans = Math.min(ans, csum);
                    }
                }
            }
        }

        out.println(ans);
    }

    long inf = (long) 1e15;

    List<Integer> min(List<Integer> l, List<num> nums) {
        List<num> numList = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            int ind = l.get(i);
            numList.add(new num(nums.get(ind).num, ind, -1));
        }

        numList.sort((n1, n2) -> (int) Math.signum(n1.num - n2.num));

        numList = numList.subList(0, Math.min(mir, numList.size()));

        return numList.stream().map(num -> num.i).collect(Collectors.toList());
    }

    List<Integer> minS(List<Integer> l, List<num> nums, int[][] a, int p) {
        List<num> numList = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            int ind = l.get(i);
            numList.add(new num(a[nums.get(ind).i][p], ind, -1));
        }

        numList.sort((n1, n2) -> (int) Math.signum(n1.num - n2.num));

        numList = numList.subList(0, Math.min(mir, numList.size()));

        return numList.stream().map(num -> num.i).collect(Collectors.toList());
    }

    List<Integer> max(List<Integer> l, List<num> nums) {
        List<num> numList = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            int ind = l.get(i);
            numList.add(new num(nums.get(ind).num, ind, -1));
        }

        numList.sort((n1, n2) -> (int) Math.signum(n2.num - n1.num));

        numList = numList.subList(0, Math.min(mir, numList.size()));

        return numList.stream().map(num -> num.i).collect(Collectors.toList());
    }

    List<Integer> maxS(List<Integer> l, List<num> nums, int[][] a, int p) {
        List<num> numList = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            int ind = l.get(i);
            numList.add(new num(a[nums.get(ind).i][p], ind, -1));
        }

        numList.sort((n1, n2) -> (int) Math.signum(n2.num - n1.num));

        numList = numList.subList(0, Math.min(mir, numList.size()));

        return numList.stream().map(num -> num.i).collect(Collectors.toList());
    }

    int mir = 1;
}
