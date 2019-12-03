package e3;

public class E3 {

	public static void main(String[] args) {
		Building b = new Building(10, 1, 10, 2, 1);
		System.out.println(b.findPath());

		System.out.println(new Building(1, 2, 1, 1, 0).findPath());

		System.out.println(new Building(1000000, 1, 1000000, 2, 1).findPath()); // 5581
	}
}
