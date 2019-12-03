package e3_getting_gold;

import java.util.Scanner;

public class GettingGoldApp {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Insert input");
		int width = in.nextInt();
		int height = in.nextInt();
		char[][] map = new char[height][width];
		in.nextLine();
		for (int i = 0; i < height; i++) {
			map[i] = in.nextLine().toCharArray();
		}
		GettingGold game = new GettingGold(map);
		game.playGame();
		System.out.println(game.getCollectedGold());
	}
}
