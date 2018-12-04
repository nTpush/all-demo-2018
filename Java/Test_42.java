public class Test_42 {
	public static void main(String[] args){
		int count = 1;
		int flag = 1;
		for(int i = 4; flag == 1; i = i + 4) {
			count = 0;
			int t = i / 4 * 5 + 1;
			for(int j = 0; j < 5; j ++) {
				if(t % 4 == 0) {
					count ++;
					t = t / 4 * 5 + 1;
					if(count == 4) {
						System.out.println("和为" + t);
						flag = 0;
						break;
					}
				}
				else {
					break;
				}
			}
		}
	}
}