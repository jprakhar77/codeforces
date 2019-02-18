package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _86D {
    static class MoAlgo {
        int n;
        int q;

        public MoAlgo(query[] queries, int n) {
            this.queries = queries;
            this.q = queries.length;
            this.n = n;
        }

        static class query {
            int i;
            int l;
            int r;

            public query(int i, int l, int r) {
                this.i = i;
                this.l = l;
                this.r = r;
            }
        }

        query[] queries;

        class bucket {
            List<query> queries;

            public bucket() {
                queries = new ArrayList<>();
            }

            public bucket(List<query> queries) {
                this.queries = queries;
            }

            public void addQuery(query query) {
                queries.add(query);
            }
        }

        bucket[] buckets;

        void mo() {
            int sqrt = (int) Math.sqrt(n);

            int bucketCount = sqrt;

            int bucketSize = (n + bucketCount - 1) / bucketCount;

            buckets = new bucket[bucketCount];

            for (int i = 0; i < bucketCount; i++) {
                buckets[i] = new bucket();
            }
            for (int i = 0; i < q; i++) {
                int bucketNum = queries[i].l / bucketSize;

                buckets[bucketNum].addQuery(queries[i]);
            }

            for (int i = 0; i < bucketCount; i++) {
                buckets[i].queries.sort((q1, q2) -> q1.r - q2.r);
            }
        }

        long[] solve(int[] a) {
            long[] ans = new long[q];

            int[] cntMap = new int[1000001];
            for (bucket bucket : buckets) {
                int st = -1;
                int en = -1;

                //Map<Integer, Integer> cntMap = new HashMap<>();
                long cans = 0;
                Arrays.fill(cntMap, 0);
                for (query query : bucket.queries) {
                    int cs = query.l;
                    int ce = query.r;

                    if (st == -1) {
                        for (int i = cs; i <= ce; i++) {
                            long cc = cntMap[a[i]];
                            cans -= cc * cc * a[i];
                            cntMap[a[i]]++;
                            cc++;
                            cans += cc * cc * a[i];
                        }
                    } else {
                        for (int i = en + 1; i <= ce; i++) {
                            long cc = cntMap[a[i]];
                            cans -= cc * cc * a[i];
                            cntMap[a[i]]++;
                            cc++;
                            cans += cc * cc * a[i];
                        }

                        if (cs > st) {
                            for (int i = st; i < cs; i++) {
                                long cc = cntMap[a[i]];
                                cans -= cc * cc * a[i];
                                cntMap[a[i]]--;
                                cc--;
                                cans += cc * cc * a[i];
                            }
                        } else if (cs < st) {
                            for (int i = cs; i < st; i++) {
                                long cc = cntMap[a[i]];
                                cans -= cc * cc * a[i];
                                cntMap[a[i]]++;
                                cc++;
                                cans += cc * cc * a[i];
                            }
                        }
                    }

                    ans[query.i] = cans;
                    st = cs;
                    en = ce;
                }
            }

            return ans;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int t = in.nextInt();

        int[] a = in.nextIntArray(n);

        MoAlgo.query[] queries = new MoAlgo.query[t];

        for (int i = 0; i < t; i++) {
            queries[i] = new MoAlgo.query(i, in.nextInt() - 1, in.nextInt() - 1);
        }

        MoAlgo mo = new MoAlgo(queries, n);
        mo.mo();

        long[] ans = mo.solve(a);

        for (int i = 0; i < t; i++) {
            out.println(ans[i]);
        }
    }
}
