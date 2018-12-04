public class Test_41 {
	static boolean compare(String s1, String s2) {
		for(int i=0; i<s1.length() && i<s2.length(); i++) {
			if(s1.charAt(i) - s2.charAt(i) > 0) {
				return true;
			}
			else if(s1.charAt(i) - s2.charAt(i) < 0) {
				return false;
			}
		}
		if(s1.length() >= s2.length()) {
			return true;
		}else {
			return false;
		}
	}

	public static void main(String[] args) {
		String str1 = "dgshj";
		String str2 = "dgshjjk1";

		System.out.println("两个字符串比较大的是");
		if(compare(str1, str2)) {
			System.out.println(str1);
		}
		else {
			System.out.println(str2);
		}
	}
}