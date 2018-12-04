import java.util.*;
class RemoveInvalidParentheses {
	public static void main(String[] args) { 
		String s = "()()";
		solution(s);
	}

	public static List<String> solution(String s) {
		List<String> res = new ArrayList<>();

		// if(s == null) return res;

		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();

		queue.add(s);
		visited.add(s);

		System.out.println(visited);
		System.out.println(queue);

		boolean found = false;

		while(!queue.isEmpty()) {
			s = queue.poll();

			System.out.println(s + "e");

			if(isValid(s)) {
				res.add(s);
				found = true;
			}

			if(found) continue;

			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i) != '(' && s.charAt(i) != ')') continue;

				String t = s.substring(0, i) + s.substring(i + 1);

				if(!visited.contains(t)) {
					queue.add(t);
					visited.add(t);
				}
			}

		}
		return res;

	}

	boolean isValid(String s) {
		int count = 0;
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if(c == '(') count++;
			if(c == ')' && count-- == 0) return false;
		}
		return count == 0;
	}
}