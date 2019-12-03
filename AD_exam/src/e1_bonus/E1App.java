package e1_bonus;

import java.util.Scanner;

public class E1App {
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out
		.println("Insert information about standings and remaining games in a league: ");
		String league = in.nextLine();

		// String league =
		// "Viborg 66 8 0 1 6 1-Aarhus 58 4 1 0 0 3-Kolding 56 7 6 0 0 1-Herning 54 5 1 3 1 0-";
		// String selectedTeam = "Aarhus";

		// String league =
		// "Atlanta 83 8 0 1 6 1-Philadelphia 80 3 1 0 0 2-New_York 78 6 6 0 0 0-Montreal 77 3 1 2 0 0-";

		E1 e = new E1(league);
		System.out.println(e.determineEliminated());
	}
}
