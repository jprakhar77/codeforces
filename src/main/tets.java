package main;

public class tets {

    public static void main(String[] args) {
        long sum = 0;
        int ans = 0;
        for (int i = 2; i <= 30000; i++) {
            if (i % 2 == 0 || i % 3 == 0)
                ans++;
            if (i % 2 == 0 && i % 3 != 0)
                sum += i;
        }

        System.out.println(sum);

    }
}
