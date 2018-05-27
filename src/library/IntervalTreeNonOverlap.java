package library;

public class IntervalTreeNonOverlap {

    int s;
    int e;

    public IntervalTreeNonOverlap(int s, int e) {
        this.s = s;
        this.e = e;
        root = new Node((s + e) / 2);
    }

    static class Interval {
        int s;
        int e;
        int i;
        Interval next;

        public Interval(int i, int s, int e) {
            this.i = i;
            this.s = s;
            this.e = e;
        }
    }

    static class Node {
        int c;
        Interval interval;
        Node left;
        Node right;

        public Node(int c) {
            this.c = c;
        }
    }

    Node root;

    void addInterval(Interval interval) {
        Node ci = root;

        int cs = s;
        int ce = e;

        while (true) {
            if (ci.c >= interval.s && ci.c <= interval.e) {
                ci.interval = interval;
                break;
            } else if (ci.c > interval.e) {
                if (ci.left == null) {
                    ci.left = new Node((cs + ci.c - 1) / 2);
                }
                ce = ci.c - 1;
                ci = ci.left;
            } else {
                if (ci.right == null) {
                    ci.right = new Node((ci.c + 1 + ce) / 2);
                }
                cs = ci.c + 1;
                ci = ci.right;
            }
        }
    }

    Interval searchInterval(int x) {
        Node ci = root;

        while (true) {
            if (ci.interval != null && ci.interval.s <= x && ci.interval.e >= x) {
                return ci.interval;
            } else if (ci.interval == null && x == ci.c) {
                return null;
            } else if (x < ci.c) {
                if (ci.left == null)
                    return null;
                ci = ci.left;
            } else if (x > ci.c) {
                if (ci.right == null)
                    return null;
                ci = ci.right;
            }
        }
    }

    void deleteInterval(Interval interval) {
        Node ci = root;

        while (true) {
            if (ci.c >= interval.s && ci.c <= interval.e) {
                ci.interval = null;
                break;
            } else if (ci.c > interval.e) {
                ci = ci.left;
            } else {
                ci = ci.right;
            }
        }
    }
}
