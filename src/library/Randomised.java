package library;

import java.util.Random;

public class Randomised {
    Random random = new Random(System.nanoTime());

    int[] nextRandomPermutation(int[] num) {
        int n = num.length;

        for (int i = n; i > 1; i--) {
            int randomIndex = random.nextInt(i);
            int temp = num[i - 1];
            num[i - 1] = num[randomIndex];
            num[randomIndex] = temp;
        }

        return num;
    }
}
