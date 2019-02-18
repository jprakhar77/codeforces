package library;

import java.util.ArrayDeque;

public class LeftRightMinMax {
    int[] leftIndex;
    int[] rightIndex;

    public void solve(int[] a, int n) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        leftIndex = new int[n];
        rightIndex = new int[n];

        stack.addFirst(0);

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && a[stack.getFirst()] < a[i]) {
                stack.removeFirst();
            }

            if (!stack.isEmpty()) {
                leftIndex[i] = stack.getFirst() + 1;
            }

            stack.addFirst(i);
        }

        stack = new ArrayDeque<>();

        stack.addFirst(n - 1);

        rightIndex[n - 1] = n - 1;

        for (int i = n - 2; i >= 0; i--) {
            while (!stack.isEmpty() && a[stack.getFirst()] <= a[i]) {
                stack.removeFirst();
            }

            if (!stack.isEmpty()) {
                rightIndex[i] = stack.getFirst() - 1;
            } else {
                rightIndex[i] = n - 1;
            }

            stack.addFirst(i);
        }
    }
}
