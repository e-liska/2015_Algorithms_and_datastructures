package e26_1_siblings;

import networkFlow.Network;

public class Siblings<V> {
	private Network<V> city;

	public Siblings(Network<V> n) {
		this.city = n;
	}

	public boolean pathExists() {
		city.fordFulkerson();
		if (city.getMaxFlow() >= 2)
			return true;
		return false;
	}
}
