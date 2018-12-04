class Test_34 {
	public static void main(String[] args) {
		int i = 0, a, b;
		a = (i++) + (i++) + (i++);
		b = (++i) + (++i) + (++i);

		System.out.println(a);
		System.out.println(b);
	}
}