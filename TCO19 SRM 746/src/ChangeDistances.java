public class ChangeDistances {

    public String[] findGraph(String[] g) {
        int n = g.length;
        String[] ans = new String[n];

        for (int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    s.append('0');
                } else {
                    if (g[i].charAt(j) == '1') {
                        s.append('0');
                    } else {
                        s.append('1');
                    }
                }
            }
            ans[i] = s.toString();
        }

        return ans;
    }
}
