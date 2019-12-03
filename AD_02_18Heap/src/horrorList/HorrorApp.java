package horrorList;

public class HorrorApp {
	public static void main(String[] args) {
		int[] horrors = { 0, 2, 5 };
		HorrorList hl = new HorrorList(6, 4, 5, horrors);
		Link[] links = { new Link(0, 1), new Link(1, 2), new Link(4, 5),
				new Link(3, 5), new Link(0, 2) };

		System.out.println(hl.getBestMovie(links));

		int[] horrors2 = { 5, 2 };
		HorrorList hl2 = new HorrorList(6, 2, 3, horrors2);
		Link[] links2 = { new Link(0, 5), new Link(0, 1), new Link(3, 4) };

		System.out.println(hl2.getBestMovie(links2));
	}
}
