package e2;

public class AppStack {
	public static void main(String[] args) {
		Stack<String> s = new Stack<String>();
		s.insert("Agatha");
		s.insert("Bob");
		s.insert("Cecilie");
		System.out.println(s.remove());
		s.insert("Daniel");
		System.out.println(s.remove());
		System.out.println(s.remove());
	}
}
