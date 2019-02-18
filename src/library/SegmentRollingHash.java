package library;

public class SegmentRollingHash {
    int prime = 1000099519;

    long[] pow;
    int n;

    public SegmentRollingHash(int n) {
        this.n = n;
        this.hash = new long[n];
        this.pow = new long[n + 1];
    }

    long[] hash;

    void initForNumber(int[] a, int r) {
        hash[0] = a[0];

        for (int i = 1; i < n; i++) {
            hash[i] = ((hash[i - 1] * r) + a[i]) % prime;
        }

        pow[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow[i] = (pow[i - 1] * r) % prime;
        }
    }

    void initForString(String s, char baseChar, int r) {
        hash[0] = s.charAt(0) - baseChar;

        for (int i = 1; i < n; i++) {
            hash[i] = ((hash[i - 1] * r) + (s.charAt(i) - baseChar)) % prime;
        }

        pow[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow[i] = (pow[i - 1] * r) % prime;
        }
    }

    long segmentHash(int l, int r) {
        if (l == 0) {
            return hash[r];
        }
        int size = r - l + 1;

        long leftHash = hash[l - 1] * pow[size];
        leftHash %= prime;

        long rightHash = hash[r];

        long segHash = rightHash - leftHash;

        if (segHash >= prime)
            segHash %= prime;

        if (segHash < 0) {
            segHash += prime;
        }

        return segHash;
    }
}
