package library;

public class NextPermutation {

    public int[] nextPermute(int[] perm) {

        int n = perm.length;

        int k = -1;
        for (int i = 0; i < n - 1; i++) {
            if (perm[i] < perm[i + 1]) {
                k = i;
            }
        }

        if (k == -1) {
            return null;
        }

        int l = -1;
        for (int i = k + 1; i < n - 1; i++) {
            if (perm[i] > perm[k]) {
                l = i;
            }
        }

        for (int i = k + 1, j = n - 1; i < j; i++, j--) {
            swap(perm, i, j);
        }

        return perm;
    }

    void swap(int[] perm, int i, int j) {
        int t = perm[i];
        perm[i] = perm[j];
        perm[j] = t;
    }
}
