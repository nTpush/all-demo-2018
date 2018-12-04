public class Test_44 {
	public static void main(String[] args) {
		int a[] = new int[100000];
		boolean hash[] = new boolean[1000001];

		int count = 0;
		for(int i = 0; i < 100000; i++) {
			hash[i] = true;
			if(i == 0 || i == 1) {
				continue;
			} 

			for(int j = 2; j*j <= i; j++) {
				if(i % j ==0) {
					hash[i] = false;
					break;
				}
			}

			if(hash[i]) {
				a[count++] = i;
			}
		}

		System.out.println("请输入一");
	}
}