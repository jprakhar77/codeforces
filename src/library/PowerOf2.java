package library;

public class PowerOf2 {

    int minCoinsToRepresentN(int n) {
        int cv = 1;

        int ans = 0;
        while (n > 0) {
            n -= cv;
            cv *= 2;
            ans++;
        }

        return ans;
    }
}
