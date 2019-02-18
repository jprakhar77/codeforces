package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class _965E {
    class trie {
        class node {
            node[] nodes;
            boolean isend;

            public node(boolean isend) {
                this.nodes = new node[26];
                this.isend = isend;
            }

            public node() {
                this.nodes = new node[26];
            }
        }

        node root = new node();

        void insert(String s) {
            node cur = root;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int ci = s.charAt(i) - 'a';

                if (cur.nodes[ci] == null) {
                    cur.nodes[ci] = new node();
                }

                cur = cur.nodes[ci];
            }

            cur.isend = true;
        }

        class result {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            int extra;
        }

        result dfs(node u) {
            List<result> pql = new ArrayList<>();

            result ans = new result();
            for (int i = 0; i < u.nodes.length; i++) {
                node child = u.nodes[i];

                if (child == null)
                    continue;

                result res = dfs(child);

                ans = merge(ans, res);
            }

            if (ans.pq.size() > 0)
                ans.extra++;

            if (u.isend) {
                ans.pq.add(-ans.extra);
            } else if (u != root) {
                int max = ans.pq.poll();
                ans.pq.add(-ans.extra);
            }

            return ans;
        }

        result merge(result r1, result r2) {
            if (r1.pq.size() > r2.pq.size()) {
                result t = r1;
                r1 = r2;
                r2 = t;
            }

            int diff = r1.extra - r2.extra;
            while (r1.pq.size() > 0) {
                r2.pq.add(r1.pq.poll() + diff);
            }

            return r2;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        String[] sa = new String[n];

        for (int i = 0; i < n; i++) {
            sa[i] = in.next();
        }

        trie trie = new trie();

        for (int i = 0; i < n; i++) {
            trie.insert(sa[i]);
        }

        trie.result result = trie.dfs(trie.root);

        int ans = 0;

        while (result.pq.size() > 0) {
            ans += result.pq.poll() + result.extra;
        }

        out.println(ans);
    }
}
