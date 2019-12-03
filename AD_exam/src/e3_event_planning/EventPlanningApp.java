package e3_event_planning;

import java.util.Scanner;

public class EventPlanningApp {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Insert input: ");

		int nrParticipants = in.nextInt();
		int budget = in.nextInt();
		int nrHotels = in.nextInt();
		int nrWeekends = in.nextInt();
		in.nextLine();

		EventPlanning planning = new EventPlanning(nrParticipants, budget,
				nrHotels);

		for (int i = 0; i < nrHotels; i++) {
			int price = in.nextInt();
			in.nextLine();
			int[] weekendsAvailable = new int[nrWeekends];
			for (int j = 0; j < nrWeekends; j++) {
				weekendsAvailable[j] = in.nextInt();
			}
			planning.addHotel(price, weekendsAvailable);
			in.nextLine();
		}

		int minStay = planning.calcMinimumStay();
		if (minStay < 0) {
			System.out.println("Stay home");
		} else {
			System.out.println(minStay);
		}

	}
}
