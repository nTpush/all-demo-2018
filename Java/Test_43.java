public class Test_43 {
	public static void main(String[] args) {
		int sum = 4;
		int t = 28;
		sum = sum + t;
		for(int i = 0; i < 6; i ++) {
			t = t * 8;
			sum = sum + t;
		}

		System.out.println("0-7组成的奇数数" + sum);
	}
}