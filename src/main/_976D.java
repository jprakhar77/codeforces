package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class _976D {
    class edge {
        int u;
        int v;

        public edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();

        int[] d = new int[n];

        TreeSet<Integer> ts = new TreeSet<>();
        TreeSet<Integer> ots = new TreeSet<>();
        TreeSet<Integer> ets = new TreeSet<>();

        int sum = 0;
        for (int i = 0; i < n; i++) {
            d[i] = in.nextInt();
            ts.add(d[i]);
            if (d[i] % 2 == 1) {
                ots.add(d[i]);
            } else {
                ets.add(d[i]);
            }
            sum += d[i];
        }

        int nodes = d[n - 1] + 1;

        List<edge> edges = new ArrayList<>();

        if (sum % 2 == 1) {
            List<Integer> ds = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                ds.add(d[i]);
            }
            for (int i = 0; i < n; i++) {
                if (d[i] % 2 == 1) {
                    ds.add(d[i]);
                    break;
                }
            }

            ds.sort((x, y) -> x - y);

            int anodes = n + 1;

            int[] rv = new int[nodes];

            for (int i = nodes - 1, j = anodes - 1; j >= 0; j--, i--) {
                rv[i] = ds.get(j);
            }

            int dd = nodes - anodes - 1;

            for (int i = nodes - 1, j = anodes - 1; j >= 0; j--, i--) {
                while (rv[i] > 0) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (rv[k] > 0) {
                            edges.add(new edge(k + 1, i + 1));
                            rv[i]--;
                            if (k <= dd)
                                rv[k]++;
                            else
                                rv[k]--;
                        }
                    }
                }
            }

            int nr = 0;
            int nev = -1;
            int nei = -1;
            int ned = (int) 1e9;
            for (int i = 0; i <= dd; i++) {
                if (!ts.contains(rv[i])) {
                    Integer ceil = ts.ceiling(rv[i]);
                    if (ceil % 2 == 0) {
                        Integer nceil = ots.ceiling(ceil);
                        if (nceil != null) {
                            if (nceil - ceil < ned) {
                                ned = nceil - ceil;
                                nev = nceil;
                                nei = i;
                            }
                        }
                    } else {
                        Integer nceil = ets.ceiling(ceil);
                        if (nceil != null) {
                            if (nceil - ceil < ned) {
                                ned = nceil - ceil;
                                nev = nceil;
                                nei = i;
                            }
                        }
                    }
                    rv[i] = ceil - rv[i];
                    nr += rv[i];

                } else {
                    int ceil = rv[i];
                    if (ceil % 2 == 0) {
                        Integer nceil = ots.ceiling(ceil);
                        if (nceil != null) {
                            if (nceil - ceil < ned) {
                                ned = nceil - ceil;
                                nev = nceil;
                                nei = i;
                            }
                        }
                    } else {
                        Integer nceil = ets.ceiling(ceil);
                        if (nceil != null) {
                            if (nceil - ceil < ned) {
                                ned = nceil - ceil;
                                nev = nceil;
                                nei = i;
                            }
                        }
                    }
                    rv[i] = 0;
                }


            }

            if (nr % 2 == 1) {
                rv[nei] = nev;
            }

            List<ele> eles = new ArrayList<>();

            for (int i = 0; i <= dd; i++) {
                eles.add(new ele(i, rv[i]));
            }

            eles.sort((x, y) -> x.num - y.num);

            for (int i = dd; i >= 0; i--) {
                while (eles.get(i).num > 0) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (eles.get(j).num > 0) {
                            edges.add(new edge(eles.get(i).i + 1, eles.get(j).i + 1));
                            eles.get(i).num--;
                            eles.get(j).num--;
                        }
                    }
                }
            }
        } else {
            List<Integer> ds = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                ds.add(d[i]);
            }

            ds.sort((x, y) -> x - y);

            int anodes = n;

            int[] rv = new int[nodes];

            for (int i = nodes - 1, j = anodes - 1; j >= 0; j--, i--) {
                rv[i] = ds.get(j);
            }

            int dd = nodes - anodes - 1;

            for (int i = nodes - 1, j = anodes - 1; j >= 0; j--, i--) {
                while (rv[i] > 0) {
                    for (int k = i - 1; k >= 0; k--) {
                        if (rv[k] > 0) {
                            edges.add(new edge(k + 1, i + 1));
                            rv[i]--;
                            if (k <= dd)
                                rv[k]++;
                            else
                                rv[k]--;
                        }
                    }
                }
            }

            int nr = 0;
            int nev = -1;
            int nei = -1;
            int ned = (int) 1e9;
            for (int i = 0; i <= dd; i++) {
                if (!ts.contains(rv[i])) {
                    Integer ceil = ts.ceiling(rv[i]);
                    if (ceil % 2 == 0) {
                        Integer nceil = ots.ceiling(ceil);
                        if (nceil != null) {
                            if (nceil - ceil < ned) {
                                ned = nceil - ceil;
                                nev = nceil;
                                nei = i;
                            }
                        }
                    } else {
                        Integer nceil = ets.ceiling(ceil);
                        if (nceil != null) {
                            if (nceil - ceil < ned) {
                                ned = nceil - ceil;
                                nev = nceil;
                                nei = i;
                            }
                        }
                    }
                    rv[i] = ceil - rv[i];
                    nr += rv[i];

                } else {
                    int ceil = rv[i];
                    if (ceil % 2 == 0) {
                        Integer nceil = ots.ceiling(ceil);
                        if (nceil != null) {
                            if (nceil - ceil < ned) {
                                ned = nceil - ceil;
                                nev = nceil;
                                nei = i;
                            }
                        }
                    } else {
                        Integer nceil = ets.ceiling(ceil);
                        if (nceil != null) {
                            if (nceil - ceil < ned) {
                                ned = nceil - ceil;
                                nev = nceil;
                                nei = i;
                            }
                        }
                    }
                    rv[i] = 0;
                }


            }

            if (nr % 2 == 1) {
                rv[nei] = nev;
            }

            List<ele> eles = new ArrayList<>();

            for (int i = 0; i <= dd; i++) {
                eles.add(new ele(i, rv[i]));
            }

            eles.sort((x, y) -> x.num - y.num);

            for (int i = dd; i >= 0; i--) {
                while (eles.get(i).num > 0) {
                    for (int j = i - 1; j >= 0; j--) {
                        if (eles.get(j).num > 0) {
                            edges.add(new edge(eles.get(i).i + 1, eles.get(j).i + 1));
                            eles.get(i).num--;
                            eles.get(j).num--;
                        }
                    }
                }
            }
        }


    }

    class ele {
        int i;
        int num;

        public ele(int i, int num) {
            this.i = i;
            this.num = num;
        }
    }
}
