package e3;

public class AppQueue {
	public static void main(String[] args) {
		Queue<String> q = new Queue<String>();
		q.insert("Agatha");
		q.insert("Bob");
		q.insert("Cecilie");
		System.out.println(q.remove());
		q.printQueue();
		q.insert("Daniel");
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
	}
}
