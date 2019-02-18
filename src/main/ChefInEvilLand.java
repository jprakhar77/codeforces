//package main;
//
//import fastio.InputReader;
//import fastio.OutputWriter;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.TreeSet;
//
//public class ChefInEvilLand {
//    public void solve(int testNumber, InputReader in, OutputWriter out) {
//
//        int p = 13;
//
//        for (int i = 1; i < p; i++) {
//            TreeSet<Long> s = new TreeSet<>();
//            for (int j = 1; ; j++) {
//                long x = (long) Math.pow(i, j);
//                x %= p;
//                if (s.contains(x))
//                    break;
//                s.add(x);
//            }
//
//            out.print(i + " : ");
//
//            for (long val : s) {
//                out.print(val + " ");
//            }
//
//            out.println();
//        }
//    }
//}
