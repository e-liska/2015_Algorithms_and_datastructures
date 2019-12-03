package e3_getting_gold;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GettingGold {
	private Position[][] map;
	private int currentLine;
	private int currentRow;
	private int collectedGold;
	private Stack<Move> moves;

	public GettingGold(char[][] board) {
		this.collectedGold = 0;
		this.map = new Position[board.length][board[0].length];
		this.parseMap(board);
		this.moves = new Stack<Move>();
	}

	private void parseMap(char[][] board) {
		List<Integer> trapLine = new ArrayList<Integer>();
		List<Integer> trapRow = new ArrayList<Integer>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				Type currentType = this.getType(board[i][j]);
				this.map[i][j] = new Position(currentType);
				if (currentType.equals(Type.TRAP)) {
					trapLine.add(i);
					trapRow.add(j);
				} else if (currentType.equals(Type.PLAYER_START)) {
					currentLine = i;
					currentRow = j;
				}
			}
		}
		for (int i = 0; i < trapLine.size(); i++) {
			int currentTrapLine = trapLine.get(i);
			int currentTrapRow = trapRow.get(i);
			map[currentTrapLine - 1][currentTrapRow].setNextToTrap(true);
			map[currentTrapLine + 1][currentTrapRow].setNextToTrap(true);
			map[currentTrapLine][currentTrapRow - 1].setNextToTrap(true);
			map[currentTrapLine][currentTrapRow + 1].setNextToTrap(true);
		}
	}

	private Type getType(char current) {
		switch (current) {
		case '#':
			return Type.WALL;
		case 'G':
			return Type.GOLD;
		case 'T':
			return Type.TRAP;
		case 'P':
			return Type.PLAYER_START;
		default:
			return Type.FLOOR;
		}
	}

	public void playGame() {
		this.move(currentLine, currentRow);
		while (!moves.isEmpty()) {
			this.determineMove();
		}
	}

	public void determineMove() {

		if (!map[currentLine][currentRow + 1].getType().equals(Type.WALL)
				&& !map[currentLine][currentRow + 1].isVisited()) {
			this.move(currentLine, currentRow + 1);
		} else if (!map[currentLine][currentRow - 1].getType()
				.equals(Type.WALL)
				&& !map[currentLine][currentRow - 1].isVisited()) {
			this.move(currentLine, currentRow - 1);
		} else if (!map[currentLine + 1][currentRow].getType()
				.equals(Type.WALL)
				&& !map[currentLine + 1][currentRow].isVisited()) {
			this.move(currentLine + 1, currentRow);
		} else if (!map[currentLine - 1][currentRow].getType()
				.equals(Type.WALL)
				&& !map[currentLine - 1][currentRow].isVisited()) {
			this.move(currentLine - 1, currentRow);
		} else {
			this.moveBack();
		}
	}

	public void moveBack() {
		if (!moves.isEmpty()) {
			Move move = moves.pop();
			currentLine = move.getOldLine();
			currentRow = move.getOldRow();
		}
	}

	public void move(int newLine, int newRow) {
		moves.push(new Move(currentLine, currentRow, newLine, newRow));
		currentLine = newLine;
		currentRow = newRow;
		Position currentPos = map[currentLine][currentRow];
		if (currentPos.getType().equals(Type.GOLD)) {
			collectedGold++;
		}
		currentPos.setVisited(true);
		if (currentPos.isNextToTrap()) {
			this.moveBack();
		}
	}

	public void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j].getType());
			}
			System.out.println();
		}
	}

	public int getCollectedGold() {
		return collectedGold;
	}

}
