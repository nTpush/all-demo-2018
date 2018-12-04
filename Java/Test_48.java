public class Test_48 {
	public static void main(String[] args) {
		int n = 1594;
		int a = n / 1000;
		int b = n / 100 % 10;
		int c = n / 10 % 10;
		int d = n % 10;

		a = (a+5) % 10;
		b = (b+5) % 10;
		c = (c+5) % 10;
		d = (d+5) % 10;

		int temp = 0;

		temp = a;

		a = d;

		d = temp;

		temp = b;
		b = c;
		c = temp;
		int ans = a * 1000 + b * 100 + c * 10 + d;

		System.out.println(ans);

	}
}