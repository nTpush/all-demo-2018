
import java.util.*;
class Test {
	public static void main(String[] args) { 
		String text = "30*1+4*2*10-10+40/20";
		g(text);
	}

	public static String g(String text) {
		List<String> numList = new ArrayList<String>();
		int splitIndex = 0;
		for(int i=0; i<text.length(); i++) {
			char c = text.charAt(i);
			if(c == '+' || c == '-' || c == '*' || c == '/') {
				numList.add(text.substring(splitIndex, i));
				numList.add(c+"");
				splitIndex = i + 1;
			}
		}
		numList.add(text.substring(splitIndex, text.length()));
		System.out.println(numList);

		System.out.println("===== after =====");
		for(int i=0;i<numList.size();i++) {
			System.out.println(i + "->" + numList.get(i));
		}

		List<String> list = new ArrayList<String>();
		Integer temp = null;
		for(int i=1; i<numList.size(); i+=2) {
			if("+".equals(numList.get(i)) || "-".equals(numList.get(i))) {
				if(null != temp) {
					list.add(temp.toString());
					temp = null;
				}else {
					list.add(numList.get(i-1));
				}
				list.add(numList.get(i));
			}else if("*".equals(numList.get(i))) {
				if(null == temp) {
					temp = Integer.parseInt(numList.get(i-1)) * Integer.parseInt(numList.get(i+1));
				}else {
					temp = temp * Integer.parseInt(numList.get(i+1));
				}

				if(i == numList.size() - 2) {
					list.add(temp.toString());
					temp = null;
				}
			}else if("/".equals(numList.get(i))) {
				if(null == temp) {
					temp = Integer.parseInt(numList.get(i-1)) / Integer.parseInt(numList.get(i+1));
				} else {
					temp = temp/Integer.parseInt(numList.get(i+1));
				}

				if(i == numList.size() - 2) {
					list.add(temp.toString());
					temp = null;
				}
			}
		}

		System.out.println("=============");
		for(int i=0; i<list.size(); i++) {
			System.out.println(i+ "->" + list.get(i));
		}

		Integer sum = Integer.parseInt(list.get(0));

		for(int i=1; i<list.size(); i+=2) {
			if("+".equals(list.get(i))) {
				sum+=Integer.parseInt(list.get(i+1));
			}else if("-".equals(list.get(i))) {
				sum-=Integer.parseInt(list.get(i+1));
			}
		}

		System.out.println(sum);
		return "s";
	}
}