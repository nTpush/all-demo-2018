import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Test_50 {
	public static void main(String[] args) {
		Scanner ss = new Scanner(System.in);

		String[][] a = new String[5][6];

		for(int i = 1; i < 6; i ++) {
			System.out.println("请输入第" + i + "个学生的学号");
			a[i-1][0] = ss.nextLine();
			System.out.println("请输入第" + i + "个学生的姓名");
			a[i-1][1] = ss.nextLine();
			for(int j = 1; j < 4; j++) {
				System.out.println("请输入该学生的第" + j + "成绩5");
				a[i-1][j+1] = ss.nextLine();
			}
			System.out.println("\n");
		}

		String s1;
		try {
			File f = new File("D:\\2018\\z-java\\javaDemo\\a");
			if(f.exists()) {
				System.out.println("文件存在");
			}else {
				System.out.println("文件不存在，正在创建文件");
				f.createNewFile(); //不存在则创建
			}

			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			for(int i = 0; i < 5; i ++) {
				for(int j = 0; j < 6; j ++) {
					s1=a[i][j]+"\r\n";
					output.write(s1);
				}
			}
			output.close();
			System.out.println("数据已写入f盘中");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}