public class Test_51 {
	// 不使用中间变量，把两个变量的值互换
	public static void main(String[] args) {
		int a = 5;
		int b = 6;
		System.out.println("交换前：a=" + a + ",b=" + b);
		/*利用加法*/
		// a = a + b;
		// b = a - b;
		// a = a - b;
		/*利用乘法*/
		// a = a * b;
		// b = a / b;
		// a = a / b;
		/*利用异或交换*/
		// a = a ^ b;
		// b = a ^ b;
		// a = a ^ b;
		System.out.println("交换后：a=" + a + ",b=" + b);
	}


}