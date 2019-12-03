package rodCutting;

public class RodCuttingTopDown {
	public static void main(String[] args) {
		int[] p = { 0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
		System.out.println(RodCuttingTopDown.memoizedCutRod(p, 4));
	}

	public static int memoizedCutRod(int[] p, int n) {
		int r[] = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			r[i] = -100;
		}
		// return RodCuttingTopDown.cutRod(p, n, r);
		return RodCuttingTopDown.memoizedCutRodAux(p, n, r);
	}

	public static int memoizedCutRodAux(int[] p, int n, int[] r) {
		if (r[n] >= 0)
			return r[n];
		int q = -100;
		if (n == 0) {
			q = 0;
		} else {
			for (int i = 1; i <= n; i++) {
				q = Math.max(q,
						p[i] + RodCuttingTopDown.memoizedCutRodAux(p, n - i, r));
			}
		}
		r[n] = q;
		return q;
	}

}
