package e3_event_planning;

public class Hotel implements Comparable<Hotel> {
	private int price;
	private int[] bedsAvailable;

	public Hotel(int price) {
		this.price = price;
	}

	public Hotel(int price, int[] bedsAvailable) {
		this.price = price;
		this.bedsAvailable = bedsAvailable;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int[] getBedsAvailable() {
		return this.bedsAvailable;
	}

	public void setBedsAvailable(int[] bedsAvailable) {
		this.bedsAvailable = bedsAvailable;
	}

	public boolean availableMoreThan(int required) {
		for (int i = 0; i < bedsAvailable.length; i++) {
			if (bedsAvailable[i] > required)
				return true;
		}
		return false;
	}

	@Override
	public int compareTo(Hotel o) {
		Hotel other = o;
		return this.price - other.price;
	}

}
