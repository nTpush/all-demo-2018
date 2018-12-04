import java.util.Scanner;
public class Test_38 {
	public static void main(String[] args) {
		// Scanner s = new Scanner(System.in);
		// System.out.print("请输入一个整数n");
		int n = 10;

		boolean[] arr = new boolean[n];

		for(int i = 0; i < arr.length; i ++) {
			arr[i] = true;
		}

		int leftCount = n;
		int countNum = 0;
		int index = 0;
		while(leftCount > 1) {
			if(arr[index] == true) {
				countNum ++;
				if(countNum == 3) {
					countNum = 0;
					arr[index] = false;
					leftCount --;
				}
			}
			index ++;
			if(index == n) {
				index = 0;
			}
		}
		for(int i = 0; i < n; i ++) {
			if(arr[i] == true) {
				System.out.println((i + 1));
			}
		}
	}
}