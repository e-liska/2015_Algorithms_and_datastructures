package e3_getting_gold;

public class Position {
	private Type type;
	private boolean visited;
	private boolean nextToTrap;

	Position(Type t) {
		this.type = t;
		visited = false;
		nextToTrap = false;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type t) {
		this.type = t;
	}

	public boolean isVisited() {
		return this.visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isNextToTrap() {
		return this.nextToTrap;
	}

	public void setNextToTrap(boolean nextToTrap) {
		this.nextToTrap = nextToTrap;
	}

}
