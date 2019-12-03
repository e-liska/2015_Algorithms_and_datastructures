package e1_basic;

public class Team {
	private String name;
	private int points;
	private int nrOfGamesLeft;
	private int[] gamesToPlay;

	public Team(String name, int points, int nrOfGamesLeft, int[] gamesToPlay) {
		this.name = name;
		this.points = points;
		this.nrOfGamesLeft = nrOfGamesLeft;
		this.gamesToPlay = gamesToPlay;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getNrOfGamesLeft() {
		return this.nrOfGamesLeft;
	}

	public void setNrOfGamesLeft(int nrOfGamesLeft) {
		this.nrOfGamesLeft = nrOfGamesLeft;
	}

	public int[] getGamesToPlay() {
		return this.gamesToPlay;
	}

	public void setGamesToPlay(int[] gamesToPlay) {
		this.gamesToPlay = gamesToPlay;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(name + " " + points + " " + nrOfGamesLeft + "\n   ");
		for (int i : gamesToPlay) {
			s.append(i + ", ");
		}
		return s.toString();
	}
}
