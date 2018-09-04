package library;

public class NotationConverters {
    int binaryToDecReverse(String num) {
        int n = num.length();

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '1') {
                ans += (1 << (n - 1 - i));
            }
        }

        return ans;
    }

    int binaryToDec(String num) {
        int n = num.length();

        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '1') {
                ans += (1 << i);
            }
        }

        return ans;
    }
}
