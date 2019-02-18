import java.util.ArrayList;
import java.util.List;

public class TransformBoardDiv1 {

    public int[] getOperations(String[] start, String[] target) {


        int n = start.length;
        int m = start[0].length();

        //w to b if req


        char[][] inter = new char[n][m];

        for (int i = 0; i < n; i++)
            inter[i] = start[i].toCharArray();


        List<Integer> l = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (inter[i][j] == '.' && target[i].charAt(j) == '#') {
                    boolean f = false;
                    i:
                    for (int x = 0; x <= i; x++) {
                        for (int y = 0; y <= j; y++) {
                            if (x == i && y == j)
                                continue;

                            if (inter[x][y] == '#' && target[x].charAt(y) == '.') {
                                f = true;

                                if (x == i || y == j) {
                                    l.add(x * 1000000 + y * 10000 + i * 100 + j);
                                    swap(inter, x, y, i, j);
                                } else {
                                    if (inter[i][y] == '.') {
                                        l.add(x * 1000000 + y * 10000 + i * 100 + y);
                                        swap(inter, x, y, i, y);
                                        l.add(i * 1000000 + y * 10000 + i * 100 + j);
                                        swap(inter, i, y, i, j);
                                    } else {
                                        l.add(i * 1000000 + y * 10000 + i * 100 + j);
                                        swap(inter, i, y, i, j);
                                        l.add(x * 1000000 + y * 10000 + i * 100 + y);
                                        swap(inter, x, y, i, y);
                                    }
                                }

                                break i;
                            }
                        }
                    }

                    if (!f) {
                        return new int[]{-1};
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (inter[i][j] == '#' && target[i].charAt(j) == '.') {
                    boolean f = false;
                    i:
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < m; y++) {
                            if (x > i)
                                continue;
                            else if (x == i && y >= j)
                                continue;

                            if (inter[x][y] == '#' && target[x].charAt(y) == '.') {
                                f = true;

                                if (x == i || y == j) {
                                    l.add(x * 1000000 + y * 10000 + i * 100 + j);
                                    swap2(inter, x, y, i, j);
                                } else {
                                    if (inter[i][y] == '.') {
                                        l.add(x * 1000000 + y * 10000 + i * 100 + y);
                                        swap(inter, x, y, i, y);
                                        l.add(i * 1000000 + y * 10000 + i * 100 + j);
                                        swap2(inter, i, y, i, j);
                                    } else {
                                        l.add(i * 1000000 + y * 10000 + i * 100 + j);
                                        swap2(inter, i, y, i, j);
                                        l.add(x * 1000000 + y * 10000 + i * 100 + y);
                                        swap(inter, x, y, i, y);
                                    }
                                }

                                break i;
                            }
                        }
                    }
                }
            }
        }

        boolean iss = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (inter[i][j] != target[i].charAt(j)) {
                    iss = false;
                }
            }
        }

        if (!iss) {
            return new int[]{-1};
        }

        int[] ans = new int[l.size()];

        for (int i = 0; i < l.size(); i++) {
            int num = l.get(i);
            //System.out.println(num / 1000000 + " " + num % 10000 + " " + num % 100 + " " + );
            for (int j = 0; j < 4; j++)
            {
                System.out.print(num % 100 + " ");
                num /= 100;
            }
            System.out.println();
            ans[i] = l.get(i);
        }

        return ans;
    }

    void swap(char[][] c, int x, int y, int i, int j) {
        char ch = c[x][y];

        c[x][y] = c[i][j];
        c[i][j] = ch;
    }

    void swap2(char[][] c, int x, int y, int i, int j) {
        c[x][y] = '.';
        c[i][j] = '.';
    }
}
