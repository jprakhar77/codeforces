public class UniformingMatrix {

	String possible = "possible";
	String impossible = "impossible";

	public String isPossible(String[] mat) {
		int n = mat.length;

		int m = mat[0].length();

		int[][] ar = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < mat[i].length(); j++) {
				ar[i][j] = mat[i].charAt(j) - '0';
			}
		}

		boolean poss = true;

		int or = 0;
		for (int i = 1; i < n; i++) {
			int s = 0, ns = 0;

			for (int j = 0; j < m; j++) {
				if (ar[i][j] == ar[0][j]) {
					s++;
				} else {
					ns++;
				}
			}

			if (s == 0 || ns == 0) {
				if (ns > 0) {
					or++;
				}
			} else {
				poss = false;
				break;
			}
		}

		if (poss) {
			int z = 0;

			for (int j = 0; j < m; j++) {
				if (ar[0][j] == 0) {
					z++;
				}
			}

			if (z % 2 == or % 2) {
				return possible;
			}
		}

		for (int j = 0; j < m; j++) {
			ar[0][j] = 1 - ar[0][j];
		}

		or = 1;

		poss = true;

		for (int i = 1; i < n; i++) {
			int s = 0, ns = 0;

			for (int j = 0; j < m; j++) {
				if (ar[i][j] == ar[0][j]) {
					s++;
				} else {
					ns++;
				}
			}

			if (s == 0 || ns == 0) {
				if (ns > 0) {
					or++;
				}
			} else {
				poss = false;
				break;
			}
		}

		if (poss) {
			int z = 0;

			for (int j = 0; j < m; j++) {
				if (ar[0][j] == 0) {
					z++;
				}
			}

			if (z % 2 == or % 2) {
				return possible;
			}
		}

		return impossible;
	}
}
