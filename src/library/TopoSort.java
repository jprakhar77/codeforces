package library;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class TopoSort {
    int n;
    List[] g;

    public TopoSort(int n, List[] g) {
        this.n = n;
        this.g = g;
    }

    List<Integer> topoOrder = new ArrayList<>();

    void topoSort() {

        int[] in = new int[n];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < g[i].size(); j++) {
                int v = (int) g[i].get(j);

                in[v]++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                q.addLast(i);
            }
        }

        while (!q.isEmpty()) {
            int ce = q.removeFirst();

            topoOrder.add(ce);
            for (int i = 0; i < g[ce].size(); i++) {
                int v = (int) g[ce].get(i);

                in[v]--;

                if (in[v] == 0) {
                    q.addLast(v);
                }
            }
        }
    }
}
