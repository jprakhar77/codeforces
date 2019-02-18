import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class SimulateBST {
	
	public int checksum(int n, int[] Sprefix, int a, int m) {

		int p = Sprefix.length;

		long[] s = new long[n];
		long[] d = new long[n];

		TreeMap<Long, Long> tm = new TreeMap<>();

		Map<Long, Long> dm = new HashMap<>();

		long[] tp = new long[5000000];

		tp[0] = 1;

		for (int i = 0; i < p; i++)
		{
			s[i] = Sprefix[i];
		}

		for (int i = 1; i <= 2500000; i++)
		{
			tp[i] = tp[i - 1] * 10;
			tp[i] %= mod;
		}

		long ans = 0;
		for (int i = 0; i < n; i++)
		{
			if (i >= p)
			{
				s[i] = a * s[i - p] + d[i - 1] + 1;
				s[i] %= m;
			}

			long num = s[i];

			if (tm.containsKey(num))
			{
				d[i] = dm.get(num);
			}else {
				Long floor = tm.floorKey(num);

				Long ceil = tm.ceilingKey(num);

				if (floor == null && ceil == null)
				{

				}else if (floor == null)
				{
					tm.merge(ceil, 1l, (x, y) -> x + y);
					d[i] = dm.get(ceil) + 1;
				}else if (ceil == null)
				{
					tm.merge(floor, 2l, (x, y) -> x + y);
					d[i] = dm.get(floor) + 1;
				}else {
					if (tm.get(floor) < 2)
					{
						tm.merge(floor, 2l, (x, y) -> x + y);
						d[i] = dm.get(floor) + 1;
					}else {
						tm.merge(ceil, 1l, (x, y) -> x + y);
						d[i] = dm.get(ceil) + 1;
					}
				}

				tm.put(num, 0l);
				dm.put(num, d[i]);
			}

			ans += (d[i] * tp[5 * i]) % mod;
			ans %= mod;
		}

		return (int)ans;
	}

	long pow(long a, long p, int mod) {
		if (p == 0) {
			return 1;
		}

		long t = pow(a, p / 2, mod);

		if (p % 2 != 0) {
			return (((t * t) % mod) * a) % mod;
		} else {
			return (t * t) % mod;
		}
	}

	int mod = (int)1e9 + 7;
}
