package game;

public class Game {
	private BinaryTree<GameData> gameTree;

	public Game() {
		GameData g = new GameData();
		int pieces = 5;
		g.piecesLeft = pieces;
		g.player = 1;
		gameTree = this.countGame(g);
	}

	private BinaryTree<GameData> countGame(GameData data) {
		int i = data.piecesLeft;
		int player = data.player;

		if (i == 1) {
			GameData newData = new GameData();
			newData.piecesLeft = i;
			newData.player = player;
			newData.winner = player % 2 + 1;
			return new BinaryTree<Game.GameData>(newData);
		}
		if (i == 0) {
			GameData newData = new GameData();
			newData.piecesLeft = i;
			newData.player = player;
			newData.winner = player;
			return new BinaryTree<Game.GameData>(newData);
		} else {
			GameData state1 = new GameData();
			state1.piecesLeft = i - 1;
			state1.player = player % 2 + 1;

			GameData state2 = new GameData();
			state2.piecesLeft = i - 2;
			state2.player = player % 2 + 1;

			BinaryTree<GameData> tree1 = this.countGame(state1);
			BinaryTree<GameData> tree2 = this.countGame(state2);
			int winner1 = tree1.getRoot().winner;
			int winner2 = tree2.getRoot().winner;
			if (winner1 == player || winner2 == player) {
				data.winner = player;
			} else if (winner1 == winner2 && winner1 != 0) {
				data.winner = winner1;
			}
			return new BinaryTree<Game.GameData>(data, tree1, tree2);
		}

	}

	public void printGame() {
		gameTree.printBFS();
	}

	private class GameData {
		public int piecesLeft;
		public int player;
		public int winner;

		@Override
		public String toString() {
			return "Pieces: " + piecesLeft + ", player: " + player + ", won: "
					+ winner;
		}
	}
}
