public class Test_45 {
	// 一个整数能被几个9整除
	public static void main(String[] args) {
		int n = 243;
		int count = 0;

		while(n > 0) {
			if(n%9 == 0) {
				count ++;
				n = n / 9;
			}else {
				if( count == 0) {
					System.out.println("该数字不能被9整除");
					break;
				}else {
					System.out.println("该数能被9出整" + count + "");
					break;
				}
			}
		}
	}
}
