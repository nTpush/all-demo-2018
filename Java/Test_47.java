public class Test_47 {

	// 两个字符串连接
	public static void main(String[] args) {
		String str1 = "asdf";
		String str2 = "qwer";

		char[] s = concat(str1, str2);

		System.out.println(s);
	}

	private static char[] concat(String str1, String str2) {
		char s[] = new char[str1.length() + str2.length()];
		for(int i = 0; i < str1.length(); i ++) {
			s[i] = str1.charAt(i);
		}
		for(int i = str1.length(); i < str2.length() + str1.length(); i ++) {
			s[i] = str2.charAt(i-str1.length());
		}

		return s;
	}
}