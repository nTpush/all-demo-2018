public class Test_49 {

	// 计算字符串子串出现的次数
	public static void main(String[] args) {
		String main = "abcdefg";
		String child = "abe";

		int count = 0;
		int i = 0, j = 0;

		while(i < main.length() && j < child.length()) {
			if(main.charAt(i) == child.charAt(j)) {
				System.out.println(main.charAt(i) + "11");
				i ++;
				j ++;
			}else {
				i = i - j + 1;
				j = 0;
			}

			if(j == child.length()) {
				count ++;
				System.out.println(i);
				i = i - j + 1;
				j = 0;
			}
		}
		System.out.println("字符" + child + "在字" + main + "中出现的数为" + count);
	}
}