//package main;
//
//import fastio.InputReader;
//import fastio.OutputWriter;
//
//public class FTrickyInteractor {
//    public void solve(int testNumber, InputReader in, OutputWriter out) {
//        int n = in.nextInt();
//
//        int o = in.nextInt();
//
//        char[] ans = new char[n];
//
//        int lo = 0;
//        int lz = 0;
//
//        int ro = o;
//        int rz = n - o;
//
//        int co = o;
//        int cz = n - o;
//
//        for (int i = 0; i < n; i++) {
//
//            if (lo)
//            while (true) {
//                //if left
//                //if 0
//                int o1 = lz + 1 + (co - lo);
//
//                //if l
//                //if 1
//                int o2 = lz + (co - lo - 1);
//
//                //if r
//                //if 0
//                int o3 = lo + (cz - 1 - lz);
//
//                //if r
//                //if 1
//                int o4 = lo + 1 + (co - 1 - lo);
//
//                int cans = -1;
//
//                out.println("? " + (i + 1) + " " + (i + 1));
//                out.flush();
//
//                int no = in.nextInt();
//
//                if ((no == o1 || no == o3) && no != o2 && no != o4) {
//                    cans = 0;
//                    co =
//                    break;
//                } else if ((no == o2 || no == o4) && no != o1 && no != o3) {
//                    cans = 1;
//                    break;
//                }
//            }
//
//            if (cans == 1) {
//                ans.append('1');
//                lo++;
//                co++;
//            }
//        }
//    }
//}
