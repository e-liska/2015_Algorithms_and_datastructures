package horrorList;

import java.util.HashMap;
import java.util.Map;

public class HorrorList {
	// private Heap<Integer, Integer> horrorQueue;
	private Map<Integer, Integer> horrorMap;
	private int nrOfMovies;
	private int nrOfHorrorListMovies;
	private int nrOfLinks;

	private static final int MAX = 1000000;

	public HorrorList(int nrOfMovies, int nrOfHorrorLM, int nrOfLinks,
			int[] horrorIndexes) {
		// horrorQueue = new Heap<Integer, Integer>();
		horrorMap = new HashMap<Integer, Integer>();
		this.nrOfMovies = nrOfMovies;
		this.nrOfHorrorListMovies = nrOfHorrorLM;
		this.nrOfLinks = nrOfLinks;

		for (int i = 0; i < nrOfMovies; i++) {
			horrorMap.put(i, MAX);
		}

		for (int i : horrorIndexes) {
			// horrorQueue.insert(i, 0);
			horrorMap.replace(i, 0);
		}
	}

	public int getBestMovie(Link[] links) {
		int checked = 0;
		while (checked != links.length) {
			for (Link link : links) {
				if (horrorMap.get(link.i1) > horrorMap.get(link.i2) + 1) {
					horrorMap.replace(link.i1, horrorMap.get(link.i2) + 1);
				}
				if (horrorMap.get(link.i2) > horrorMap.get(link.i1) + 1) {
					horrorMap.replace(link.i2, horrorMap.get(link.i1) + 1);
				}
			}
		}

		// for (Link link : links) {
		//
		// if (horrorMap.get(link.i1) > horrorMap.get(link.i2) + 1) {
		// horrorMap.replace(link.i1, horrorMap.get(link.i2) + 1);
		// }
		// if (horrorMap.get(link.i2) > horrorMap.get(link.i1) + 1) {
		// horrorMap.replace(link.i2, horrorMap.get(link.i1) + 1);
		// }
		// }
		int highestHorrorIndex = 0;
		int bestMovie = 0;
		for (int i = 0; i < nrOfMovies; i++) {
			int temp = horrorMap.get(i);
			if (temp > highestHorrorIndex) {
				bestMovie = i;
				highestHorrorIndex = temp;
			}
		}
		return bestMovie;
	}
}
