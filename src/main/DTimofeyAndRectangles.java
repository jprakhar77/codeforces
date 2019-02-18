package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.List;

public class DTimofeyAndRectangles {
    class rect {
        int x1;
        int y1;
        int x2;
        int y2;
        int i;

        public rect(int x1, int y1, int x2, int y2, int i) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.i = i;
        }
    }

    class range {
        int l;
        int r;
        int i;

        public range(int l, int r, int i) {
            this.l = l;
            this.r = r;
            this.i = i;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        rect[] rects = new rect[n];

        out.println("YES");
        for (int i = 0; i < n; i++) {
            int x1 = in.nextInt();
            int y1 = in.nextInt();
            int x2 = in.nextInt();
            int y2 = in.nextInt();

            x2 += 1000000000;
            y1 += 1000000000;

            rects[i] = new rect(x1, y1, x2, y2, i);

            int ans = 0;

            if (x2 % 2 == 1) {
                ans += 1;
            }

            if (y1 % 2 == 1) {
                ans += 2;
            }

            out.println(ans + 1);
        }

//        Map<Integer, List<range>> xlmap = new HashMap<>();
//        Map<Integer, List<range>> xrmap = new HashMap<>();
//        Map<Integer, List<range>> ylmap = new HashMap<>();
//        Map<Integer, List<range>> yrmap = new HashMap<>();
//
//        for (int i = 0; i < n; i++) {
//            rect cr = rects[i];
//
//            //left ver
//            range cr1 = new range(cr.y1, cr.y2, i);
//
//            //right ver
//            range cr2 = new range(cr.y1, cr.y2, i);
//
//            //up hor
//            range cr3 = new range(cr.x1, cr.x2, i);
//
//            range cr4 = new range(cr.x1, cr.x2, i);
//
//            if (!xlmap.containsKey(cr.x1)) {
//                xlmap.put(cr.x1, new ArrayList<>());
//            }
//
//            if (!ylmap.containsKey(cr.y1)) {
//                ylmap.put(cr.y1, new ArrayList<>());
//            }
//
//            if (!xrmap.containsKey(cr.x2)) {
//                xrmap.put(cr.x2, new ArrayList<>());
//            }
//
//            if (!yrmap.containsKey(cr.y2)) {
//                yrmap.put(cr.y2, new ArrayList<>());
//            }
//
//            xlmap.get(cr.x1).add(cr1);
//            ylmap.get(cr.y1).add(cr3);
//            xrmap.get(cr.x2).add(cr2);
//            yrmap.get(cr.y2).add(cr4);
//        }
//
//        for (int key : xlmap.keySet()) {
//            xlmap.get(key).sort((t1, t2) -> t1.l - t2.l);
//        }
//
//        for (int key : ylmap.keySet()) {
//            ylmap.get(key).sort((t1, t2) -> t1.l - t2.l);
//        }
//
//        for (int key : xrmap.keySet()) {
//            xrmap.get(key).sort((t1, t2) -> t1.l - t2.l);
//        }
//
//        for (int key : yrmap.keySet()) {
//            yrmap.get(key).sort((t1, t2) -> t1.l - t2.l);
//        }
//
//        g = new List[n];
//
//        for (int i = 0; i < n; i++) {
//            g[i] = new ArrayList();
//        }
//
//        for (int i = 0; i < n; i++) {
//            rect cr = rects[i];
//
//            List<range> cl = xrmap.get(cr.x1);
//            range r1 = indices(cl, cr.y1, cr.y2);
//
//            for (int j = r1.l; j <= r1.r; j++) {
//                g[cr.i].add(cl.get(j).i);
//                //g[cl.get(j).i].add(cr.i);
//            }
//
//            cl = ylmap.get(cr.y2);
//            r1 = indices(cl, cr.x1, cr.x2);
//
//            for (int j = r1.l; j <= r1.r; j++) {
//                g[cr.i].add(cl.get(j).i);
//                //g[cl.get(j).i].add(cr.i);
//            }
//
//            cl = xlmap.get(cr.x2);
//            r1 = indices(cl, cr.y1, cr.y2);
//
//            for (int j = r1.l; j <= r1.r; j++) {
//                g[cr.i].add(cl.get(j).i);
//                //g[cl.get(j).i].add(cr.i);
//            }
//
//            cl = yrmap.get(cr.y1);
//            r1 = indices(cl, cr.x1, cr.x2);
//
//            for (int j = r1.l; j <= r1.r; j++) {
//                g[cr.i].add(cl.get(j).i);
//                //g[cl.get(j).i].add(cr.i);
//            }
//        }
//
//        int[] ans = new int[n];
//
//        List<Integer> vd = new ArrayList<>();
//
//        for (int i = 0; i < n; i++) {
//            vd.add(i);
//        }
//
//        vd.sort((v1, v2) -> g[v2].size() - g[v1].size());
//
//        for (int k = 0; k < n; k++) {
//
//            int i = vd.get(k);
//
//            Set<Integer> s = new HashSet<>();
//
//            for (int j = 0; j < g[i].size(); j++) {
//                int v = (int) g[i].get(j);
//
//                if (ans[v] != 0) {
//                    s.add(ans[v]);
//                }
//            }
//
//            for (int j = 1; j <= 4; j++) {
//                if (!s.contains(j)) {
//                    ans[i] = j;
//                    break;
//                }
//            }
//        }
//
//        out.println("YES");
//
//        for (int i = 0; i < n; i++) {
//            out.println(ans[i]);
//        }
    }

    range indices(List<range> ranges, int l, int r) {

        if (ranges == null || ranges.isEmpty()) {
            return new range(-1, -2, -1);
        }
        int min = ranges.size();

        int lo = 0;
        int hi = min - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (ranges.get(mid).r > l) {
                min = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        int max = -1;

        lo = 0;
        hi = ranges.size() - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (ranges.get(mid).l < r) {
                max = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return new range(min, max, -1);
    }

    List[] g;
}
