package e3_phone_list;

import java.util.Scanner;

public class PhoneListApp {
	public static void main(String[] args) {

		System.out.println("Insert input: ");
		Scanner in = new Scanner(System.in);

		int nrTestCases = in.nextInt();
		String[] answers = new String[nrTestCases];
		in.nextLine();
		for (int i = 0; i < nrTestCases; i++) {
			DigitTree tree = new DigitTree();
			int nrNumbers = in.nextInt();
			in.nextLine();
			boolean isConstistent = true;
			for (int j = 0; j < nrNumbers; j++) {
				char[] nr = in.nextLine().toCharArray();
				if (!tree.add(nr)) {
					isConstistent = false;
					break;
				}
			}
			if (isConstistent) {
				answers[i] = "Yes";
			} else {
				answers[i] = "No";
			}
		}
		for (int i = 0; i < answers.length; i++) {
			System.out.println(answers[i]);
		}
		in.close();
	}
}
