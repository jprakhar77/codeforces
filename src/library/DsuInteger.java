package library;

public class DsuInteger {

    public DsuInteger(int n) {
        this.n = n;
        this.parent = new int[n];
        this.rank = new int[n];
        this.size = new int[n];
    }

    int[] parent;
    int[] rank;
    int[] size;
    int n;


    int createSet(int x) {
        parent[x] = x;
        rank[x] = 0;
        size[x] = 1;
        return x;
    }

    int findSet(int x) {
        int par = parent[x];
        if (x != par) {
            parent[x] = findSet(par);
            return parent[x];
        }
        return par;
    }

    int mergeSets(int x, int y) {
        int rx = findSet(x);
        int ry = findSet(y);

        if (rx == ry) {
            return rx;
        }

        int fp = -1;

        if (rank[rx] > rank[ry]) {
            parent[ry] = rx;
            fp = rx;
        } else {
            parent[rx] = ry;
            fp = ry;
        }

        size[fp] = size[rx] + size[ry];

        if (rank[rx] == rank[ry]) {
            rank[ry] = rank[ry] + 1;
        }

        return fp;
    }
}