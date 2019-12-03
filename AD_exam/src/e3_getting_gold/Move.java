package e3_getting_gold;

public class Move {
	private int oldLine;
	private int oldRow;
	private int newLine;
	private int newRow;

	public Move(int oldLine, int oldRow, int newLine, int newRow) {
		this.oldLine = oldLine;
		this.oldRow = oldRow;
		this.newLine = newLine;
		this.newRow = newRow;
	}

	public int getOldLine() {
		return this.oldLine;
	}

	public void setOldLine(int oldLine) {
		this.oldLine = oldLine;
	}

	public int getOldRow() {
		return this.oldRow;
	}

	public void setOldRow(int oldRow) {
		this.oldRow = oldRow;
	}

	public int getNewLine() {
		return this.newLine;
	}

	public void setNewLine(int newLine) {
		this.newLine = newLine;
	}

	public int getNewRow() {
		return this.newRow;
	}

	public void setNewRow(int newRow) {
		this.newRow = newRow;
	}

}
