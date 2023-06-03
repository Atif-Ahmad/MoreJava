import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhoneLetterCombs {

	public static void main(String[] args) {
		PhoneLetterCombs p = new PhoneLetterCombs();
		System.out.println(p.letterCombiniations("5678"));
	}

	public List<String> letterCombiniations(String digits) {
		ArrayList<String> l = new ArrayList<>();
		HashMap<Character, String[]> h = new HashMap<>();
		char[] digs = digits.toCharArray();
		h.put('2', new String[] { "a", "b", "c" });
		h.put('3', new String[] { "d", "e", "f" });
		h.put('4', new String[] { "g", "h", "i" });
		h.put('5', new String[] { "j", "k", "l" });
		h.put('6', new String[] { "m", "n", "o" });
		h.put('7', new String[] { "p", "q", "r", "s" });
		h.put('8', new String[] { "t", "u", "v" });
		h.put('9', new String[] { "w", "x", "y", "z" });

		for (int i = 0; i < digs.length; i++) {
			if (digs.length == 2) {
				for (int j = i + 1; j < digs.length; j++) {
					for (String x : h.get(digs[i])) {
						for (String y : h.get(digs[j])) {
							l.add(x + y);
						}
					}
				}
			}
			if (digs.length == 1) {
				for (String s : h.get(digs[0])) {
					l.add(s);
				}
			}
			if (digs.length == 3) {
				for (int j = i + 1; j < digs.length; j++) {
					for (int k = j + 1; k < digs.length; k++) {
						for (String x : h.get(digs[i])) {
							for (String y : h.get(digs[j])) {
								for (String z : h.get(digs[k])) {
									l.add(x + y + z);
								}
							}
						}
					}
				}
			}
			if (digs.length == 4) {
				for (int j = i + 1; j < digs.length; j++) {
					for (int k = j + 1; k < digs.length; k++) {
						for (int m = k + 1; m < digs.length; m++) {
							for (String x : h.get(digs[i])) {
								for (String y : h.get(digs[j])) {
									for (String z : h.get(digs[k])) {
										for (String w : h.get(digs[m]))
											l.add(x + y + z + w);
									}
								}
							}
						}
					}
				}
			}
		}

		return l;

	}

}
