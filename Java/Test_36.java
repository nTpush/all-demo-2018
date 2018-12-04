
import java.util.*;
public class Test_36 {
	public static void main(String[] args) {
		HashMap h1 = new HashMap();
		Random r1 = new Random();

		for(int i=0;i<1000;i++) {
			Integer t = new Integer(r1.nextInt(20));
			if(h1.containsKey(t)) {
				((Ctime)h1.get(t)).count ++;
			}else {
				h1.put(t, new Ctime());
			}
		}

		System.out.println(h1);
	}
}

class Ctime {
	int count = 1;
	public String toString() {
		return Integer.toString(count);
	}
}