package library;

import java.util.ArrayList;
import java.util.List;

public class DsuTree {
    //Here it is calculating the layer number (relative to current node)
    // with max num of nodes for each subtree
    //Similar to the idea to small to large set merge
    //Get the array for the largest subtree(largest height subtree here)
    // AND then iterate through the other children to get the merged array
    // for the current node
    void dfs(int u, int p, int[] ht, List[] g, List[] da) {
        int max = -1;
        int maxi = -1;
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);
            if (v != p) {
                dfs(v, u, ht, g, da);
                if (ht[v] > max) {
                    max = ht[v];
                    maxi = v;
                    da[u] = da[v];
                }
            }
        }

        if (da[u] == null) {
            da[u] = new ArrayList<Integer>();
        }

        da[u].add(0, 1);

        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);
            if (v != p && v != maxi) {
                for (int j = 0; j < da[v].size(); j++) {
                    da[u].set(j + 1, (int) da[u].get(j + 1) + (int) da[v].get(j));
                }
            }
        }

        max = 1;
        maxi = 0;

        for (int i = 0; i < da[u].size(); i++) {
            if ((int) da[u].get(i) > max) {
                max = (int) da[u].get(i);
                maxi = i;
            }
        }
    }

}
