public class ForumPostEasy {

    public String getCurrentTime(String[] exactPostTime, String[] showPostTime) {
        int n = exactPostTime.length;

        time[] ptimes = new time[n];

        for (int i = 0; i < n; i++) {
            ptimes[i] = parse(exactPostTime[i]);
        }

        String ans = "impossible";
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 60; k++) {
                    boolean isp = true;
                    for (int x = 0; x < exactPostTime.length; x++) {
                        time dif = diff(ptimes[x], i, j, k);

                        if (!isValid(dif, showPostTime[x])) {
                            isp = false;
                            break;
                        }
                    }

                    if (isp) {
                        String form = tostring(new time(i, j, k));
                        if ("impossible".equals(ans) || ans.compareTo(form) > 0) {
                            ans = form;
                        }
                    }
                }
            }
        }

        return ans;
    }

    String tostring(time time) {
        String str = "";

        if (time.h >= 10) {
            str += time.h;
        } else {
            str += "0";
            str += time.h;
        }

        str += ":";
        if (time.m >= 10) {
            str += time.m;
        } else {
            str += "0";
            str += time.m;
        }

        str += ":";
        if (time.s >= 10) {
            str += time.s;
        } else {
            str += "0";
            str += time.s;
        }

        return str;
    }

    boolean isValid(time time, String showTime) {
        int h = time.h;
        int m = time.m;
        int s = time.s;
//        if (h < 0) {
//            h += 24;
//        }

        if (h < 0 || m < 0 || s < 0)
            return false;

        String ex;
        if (h > 0) {
            ex = h + " hours ago";
        } else if (m > 0) {
            ex = m + " minutes ago";
        } else {
            ex = "few seconds ago";
        }

        return ex.equals(showTime);
    }

    time diff(time f, int h, int m, int s) {
        //return new time(h - f.h, m - f.m, s - f.s);

        int ns = s - f.s;

        if (ns < 0) {
            ns += 60;
            m -= 1;
        }

        int nm = m - f.m;

        if (nm < 0) {
            nm += 60;
            h -= 1;
        }

        int nh = h - f.h;

        if (nh < 0) {
            nh += 24;
        }

        return new time(nh, nm, ns);
    }


    time parse(String exact) {
        String[] ps = exact.split(":");

        return new time(Integer.parseInt(ps[0]), Integer.parseInt(ps[1]), Integer.parseInt(ps[2]));
    }

    class time {
        int h;
        int m;
        int s;

        public time(int h, int m, int s) {
            this.h = h;
            this.m = m;
            this.s = s;
        }
    }
}
