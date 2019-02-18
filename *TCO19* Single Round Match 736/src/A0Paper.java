public class A0Paper {

    public String canBuild(int[] a) {

        int n = a.length;

        for (int i = n - 2; i >= 0; i--) {
            a[i] += a[i + 1] / 2;
        }

        if (a[0] > 0) {
            return "Possible";
        } else {
            return "Impossible";
        }
    }
}
