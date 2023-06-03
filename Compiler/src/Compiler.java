package Compiler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Compiler {

	private static ArrayList<Word> w = new ArrayList<>();
	private static ArrayList<Number> n = new ArrayList<>();

	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedReader br2 = null;
		
		try {
			br = new BufferedReader(new FileReader("/Users/atif/Desktop/PythonStuff/create.txt"));

			String contentLine = br.readLine();
			while (contentLine != null) {
				ArrayList<String> x = new ArrayList<>();
				contentLine = br.readLine();
				String c = "";
				char[] temporary = contentLine.toCharArray();
				for (int i = 0; i < temporary.length; i++) {
					if (temporary[i] != ' ') {
						c += temporary[i];
					} else {
						x.add(c);
						c = "";
					}
				}
			
				if (x.get(0).equals("num")) {
					if (x.size() == 3) {
						Number nu = new Number(x.get(1), Integer.parseInt(x.get(3)));
						n.add(nu);
					}
					if (x.size() > 3) {
						Number nu = new Number(x.get(1), operations(x));
						n.add(nu);
					}
				}
				
				if (x.get(0).equals("word")) {
					if (x.size() == 3) {
						char [] ch = x.get(2).toCharArray();
						if (ch[0] == '"' && ch[ch.length-1] == '"') {
							Word wo = new Word(x.get(1), x.get(2));
							w.add(wo);
						}
					}
				}
				
//				if (x.get(0).equals("if")) {
//					if (If(x)) {
//						
//					}
//				}

				if (containsNum(x.get(0)) == null || containsWord(x.get(0)) == null) {
					System.out.println(x.get(0) + " is not defined");
				}

			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (br2 != null)
					br2.close();
			} catch (IOException ioe) {
				System.out.println("Error in closing the BufferedReader");
			}
		}

	}


	public static double operations(ArrayList<String> s) {
		ArrayList<Double> d = new ArrayList<>();

//		if (containsNum(s.get(0)) != null) {
//			Number temp = containsNum(s.get(0));
//			d.add((double) temp.getNum());
//		}
		int ind = 0;
		for (int i = 0; i < s.size(); i++) {
			if (s.get(i).equals("=")) {
				ind = i;
			}
		}

		String str = "";
		for (int i = ind + 1; i < s.size(); i++) {
			str += s.get(i);
		}

		String f = "";
		ArrayList<Character> c = new ArrayList<>();
		ArrayList<String> t = new ArrayList<>();
		char[] e = str.toCharArray();
		for (int i = 0; i < e.length; i++) {
			if (e[i] != '+' && e[i] != '-') {
				f += e[i];
			} else {
				c.add(e[i]);
				t.add(f);
				f = "";
			}

			if (i == e.length - 1) {
				if (isNum(e[i])) {
					t.add(f);
				} else {
					// error
				}

			}
		}
		int k = 0;
		for (int i = 0; i < t.size(); i++) {
			double res = 0.0;
			char[] temp = t.get(i).toCharArray();
			for (int j = 0; j < temp.length - 1; j++) {
				if (j == 0) {
					if (isNum(temp[j])) {
						res = Character.getNumericValue(temp[j]);
					} else {
						j = temp.length; // can't use break, so this is the next best thing
					}
				}
				if (temp[j] == '*') {
					j++;
					res = res * Character.getNumericValue(temp[j]);
				}
				if (temp[j] == '/') {
					j++;
					res = res / Character.getNumericValue(temp[j]);
				}
				if (temp[j] == '%') {
					j++;
					res = res % Character.getNumericValue(temp[j]);
				}
			}
			if (temp.length == 1) {
				if (isNum(temp[0]))
					d.add(0.0 + Character.getNumericValue(temp[0]));
			} else {
				d.add(res);
			}

		}

		double res = 0;
		res += d.get(0);

		for (int i = 0; i < c.size(); i++) {
			if (c.get(i) == '+')
				res = res - d.get(i + 1);
			if (c.get(i) == '-')
				res = res - d.get(i + 1);
		}

		return res;

	}

	public static void While(ArrayList<String> s) {

	}

	public static Number containsNum(String s) {
		for (Number num : n) {
			if (num.getName().equals(s)) {
				return num;
			}
		}
		return null;
	}

	public static Word containsWord(String s) {
		for (Word word : w) {
			if (word.getName().equals(s)) {
				return word;
			}
		}
		return null;
	}
	
/**	public static boolean If(ArrayList<String> s) {
//		String e = s.get(1);
//		int ind = e.indexOf(")");
//		String f = e.substring(1, ind);
//		String var = "";
//		ArrayList<String> temp = new ArrayList<String>();
//		for (int i = 0; i < f.toCharArray().length; i++) {
//			if (f.toCharArray()[i] != ' ') {
//				var+= f.toCharArray()[i];
//			}else {
//				temp.add(var);
//				var = "";
//			}
//		}
//		
//		
//		
////		if (containsNum(temp.get(0)) != null || containsWord(temp.get(0) != null)) {
////			*/

	public static boolean isNum(char str) {
		int intValue = 0;

		if (str == ' ') {
			return false;
		}

		try {
			intValue = Character.getNumericValue(str);
			return true;
		} catch (NumberFormatException e) {
			System.out.println("Invalid character");
		}
		return false;
	}

}
