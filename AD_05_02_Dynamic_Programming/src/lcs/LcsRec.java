package lcs;

public class LcsRec {
	public static void main(String[] args) {
		System.out.println(LcsRec.lscRec("bdcaba", "abcbdab"));
	}

	public static int lscRec(String a, String b) {
		if (a.length() == 0 || b.length() == 0)
			return 0;
		else if (a.charAt(a.length() - 1) == b.charAt(b.length() - 1))
			return (1 + LcsRec.lscRec(a.substring(0, a.length() - 1),
					b.substring(0, b.length() - 1)));
		else
			return Math.max(LcsRec.lscRec(a, b.substring(0, b.length() - 1)),
					LcsRec.lscRec(a.substring(0, a.length() - 1), b));
	}
}
