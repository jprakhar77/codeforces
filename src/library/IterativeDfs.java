package library;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class IterativeDfs {
    void postOrderTraversal(List[] g, int root, int n) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        stack.addLast(root);

        boolean[] vis = new boolean[n];

        vis[root] = true;

        ArrayDeque<Integer> stack2 = new ArrayDeque<>();

        while (!stack.isEmpty()) {
            int pe = stack.removeLast();

            stack2.addLast(pe);

            for (int i = 0; i < g[pe].size(); i++) {
                int v = (int) g[pe].get(i);

                if (!vis[v]) {
                    stack.addLast(v);
                    vis[v] = true;
                }
            }
        }

        vis = new boolean[n];

        o:
        while (!stack2.isEmpty()) {
            int u = stack2.removeLast();
            vis[u] = true;

            if (g[u].size() == 1) {
                //Do operation for leaf
                continue;
            }

            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < g[u].size(); i++) {
                int v = (int) g[u].get(i);

                if (vis[v]) {
                    //Do operations requiring children values
                }
            }

            //Do other operations required for this node
        }
    }
}
