public class Test_40 {
	static void int(int n) {
		if(n == 0) {
			System.out.println(n);
			return;
		}
		float sum = 0;
		if(n%2 == 0) {
			for(int i = 2; i<=n; i=i+2) {
				sum = sum + (float)(1.0 / i);
			}
		}else {
			for(int i = 1; i<=n; i=i+2) {
				sum = sum + (float)(1.0 / i);
			}
		}
		System.out.printf("%.4f", sum);
		System.out.println();
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("请输入整数n");
		int n = in.nextInt();
		init(n);
	}
}