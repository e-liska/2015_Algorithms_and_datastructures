package e3;

public class Building {
	private int floor;
	private int start;
	private int goal;
	private int up;
	private int down;
	private boolean[] floors;

	public Building(int f, int s, int g, int u, int d) {
		floor = f;
		start = s;
		goal = g;
		up = u;
		down = d;
		floors = new boolean[floor + 1];
		for (int i = 0; i < floors.length; i++) {
			floors[i] = true;
		}
	}

	public String findPath() {
		int a = this.moves(0, start);
		if (a < 0)
			return "Use the stairs";
		return "From floor " + start + " to floor " + goal + " : " + a
				+ " moves.";
	}

	private int moves(int m, int f) {
		System.out.println("m: " + m + ", f: " + f);
		if (f < 0)
			return -100;
		if (f > floor)
			return -100;
		if (floors[f] == false)
			return -100;
		floors[f] = false;
		if (f == goal)
			return m;
		int a = this.moves(m + 1, f + up);
		int b = this.moves(m + 1, f - down);
		if (a == -100)
			return b;
		if (b == -100)
			return a;
		return Math.min(a, b);
	}
}
