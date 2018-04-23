package library;

class BinaryIndexedTree {
    int n;
    int[] tree;

    public BinaryIndexedTree(int n) {
        super();
        this.n = n;
        this.tree = new int[n + 1];
    }

    public int sum(int idx) {
        idx++;

        int sum = 0;

        while (idx > 0) {
            sum += tree[idx];
            idx -= (idx & -idx);
        }

        return sum;
    }

    public void update(int idx, int v) {
        idx++;

        while (idx <= n) {
            tree[idx] += v;
            idx += (idx & -idx);
        }
    }
}
