package e3WithBFS;

public class E3GraphApp {
	public static void main(String[] args) {
		E3Graph case1 = new E3Graph(10, 1, 10, 2, 1);
		System.out.println(case1.countMoves());

		System.out.println();
		E3Graph case2 = new E3Graph(100, 2, 1, 1, 0);
		System.out.println(case2.countMoves());

		System.out.println();
		E3Graph caseMax = new E3Graph(1000000, 1, 1000000, 2, 1);
		System.out.println(caseMax.countMoves()); // done
	}
}
