import java.util.ArrayList;
import java.util.List;

public class CubesOnATable {

    class coord {
        int x;
        int y;
        int z;

        public coord(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public int[] placeCubes(int s) {
        if (s <= 4 || s == 6 || s == 7) {
            return new int[]{};
        }
        List<coord> cs = new ArrayList<>();
        int rem = 0;
        if (s % 4 == 0) {
            cs.add(new coord(0, 0, 0));
            cs.add(new coord(1, 0, 0));
            rem = s - 8;
        } else if (s % 4 == 1) {
            cs.add(new coord(0, 0, 0));
            rem = s - 5;
        } else if (s % 4 == 2) {
            cs.add(new coord(0, 0, 0));
            cs.add(new coord(2, 0, 0));
            rem = s - 10;
        } else if (s % 4 == 3) {
            cs.add(new coord(0, 0, 0));
            cs.add(new coord(1, 0, 0));
            cs.add(new coord(2, 0, 0));
            rem = s - 11;
        }

        for (int i = 0, y = 1; i < rem / 4; i++, y++) {
            cs.add(new coord(0, 0, y));
        }

        int[] ans = con(cs);
        System.out.print(ans);

        return ans;
    }

    int[] con(List<coord> ans) {
        int[] fans = new int[ans.size() * 3];

        for (int i = 0; i < ans.size(); i++) {
            coord cc = ans.get(i);
            fans[i * 3] = cc.x;
            fans[i * 3 + 1] = cc.y;
            fans[i * 3 + 2] = cc.z;
        }

        return fans;
    }
}
