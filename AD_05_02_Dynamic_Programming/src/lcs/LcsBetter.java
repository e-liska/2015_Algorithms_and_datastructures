package lcs;

public class LcsBetter {
	private static Arrow[][] arrows;
	private static int[][] values;

	public static void main(String[] args) {
		// String x = "abcbdab";
		// String y = "bdcaba";
		String x = "10010101";
		String y = "010110110";
		LcsBetter.lcs(x, y);
		System.out.println("LCS: "
				+ values[values.length - 1][values[0].length - 1]);
		LcsBetter.printLcs(x, arrows.length - 1, arrows[0].length - 1);
		LcsBetter.printTables();
	}

	public static void lcs(String x, String y) {
		int m = x.length();
		int n = y.length();
		arrows = new Arrow[m][n];
		values = new int[m + 1][n + 1];

		for (int i = 0; i < m; i++) {
			values[i][0] = 0;
		}
		for (int i = 1; i < n; i++) {
			values[0][i] = 0;
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (x.charAt(i) == y.charAt(j)) {
					arrows[i][j] = Arrow.SLANT;
					values[i + 1][j + 1] = values[i][j] + 1;
				} else if (values[i][j + 1] >= values[i + 1][j]) {
					values[i + 1][j + 1] = values[i][j + 1];
					arrows[i][j] = Arrow.UP;
				} else {
					values[i + 1][j + 1] = values[i + 1][j];
					arrows[i][j] = Arrow.LEFT;
				}
			}
		}
	}

	public static void printLcs(String x, int i, int j) {
		if (!(i == -1 || j == -1)) {
			if (arrows[i][j].equals(Arrow.SLANT)) {
				LcsBetter.printLcs(x, i - 1, j - 1);
				System.out.println(x.charAt(i));
			} else if (arrows[i][j].equals(Arrow.UP)) {
				LcsBetter.printLcs(x, i - 1, j);
			} else {
				LcsBetter.printLcs(x, i, j - 1);
			}
		}

	}

	public static void printTables() {
		System.out.println();
		for (int[] a : values) {
			for (int i : a) {
				System.out.print(i + " | ");
			}
			System.out.println();
		}

		System.out.println();
		for (Arrow[] a : arrows) {
			for (Arrow arrow : a) {
				switch (arrow) {
				case UP:
					System.out.print("^ | ");
					break;
				case LEFT:
					System.out.print("< | ");
					break;
				case SLANT:
					System.out.print("\\ | ");
				}
			}
			System.out.println();
		}
	}
}
