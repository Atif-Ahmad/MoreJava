import java.util.*;

public class Valid_P {
	public static void main(String[] args) {
		String s = "))";
		// ([{}{}]), (([]){})
		// last in first out, stack
		System.out.println(valid(s));
	}



	public static boolean valid(String s) {
		HashMap<Character, Character> h = new HashMap<>();
		h.put('(', ')');
		h.put('{', '}');
		h.put('[', ']');

		Stack<Character> stack = new Stack<Character>();
		char[] p = s.toCharArray();
		for (int i = 0; i < p.length; i++) {
			if (p[i] != ')' && p[i] != '}' && p[i] != ']') {
				stack.add(p[i]);
			} else {
				if (stack.size() != 0) {
					if (h.containsKey(stack.peek())  && p[i] == h.get(stack.peek())) {
						stack.pop();
					} else {
						return false;
					}
				}else {
					stack.add(p[i]);
				}
			}
		}
		if (stack.size() == 0)
			return true;
		else
			return false;

	}
}
