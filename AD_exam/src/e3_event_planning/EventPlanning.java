package e3_event_planning;

import java.util.PriorityQueue;

public class EventPlanning {
	private PriorityQueue<Hotel> hotels;
	private int nrParticipants;
	private int budget;

	public EventPlanning(int nrParticipants, int budget, int nrHotels) {
		this.hotels = new PriorityQueue<Hotel>();
		this.nrParticipants = nrParticipants;
		this.budget = budget;
	}

	public void addHotel(int price, int[] weekendsAvailable) {
		Hotel h = new Hotel(price, weekendsAvailable);
		hotels.add(h);
	}

	public int calcMinimumStay() {
		while (!hotels.isEmpty()) {
			Hotel currentHotel = hotels.remove();
			if (currentHotel.availableMoreThan(nrParticipants)) {
				int price = currentHotel.getPrice() * nrParticipants;
				if (price <= budget)
					return price;
			}
		}
		return -1;
	}
}
