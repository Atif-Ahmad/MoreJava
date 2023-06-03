package Compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Compiler2 {

	static ArrayList<Number> numbers = new ArrayList<>();
	static ArrayList<Word> words = new ArrayList<>();
	static int compare = 0;

	public static void main(String[] args) throws IOException, Exception {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("/Users/atif/Desktop/PythonStuff/create.txt"));
			String contentLine = br.readLine();
			while (contentLine != null) {	
				if (contentLine.isBlank() || contentLine.isEmpty()) {
					contentLine = br.readLine();
				}

				if (!contentLine.isBlank() || !contentLine.isEmpty() || contentLine != null) {

					if (contentLine.charAt(0) == '#') {
						contentLine = br.readLine();
					}
					if (contentLine != null) {
						if (contentLine.contains("num ") && !contentLine.contains("input()")) {
							contentLine.replaceAll(" ", "");
							String name = contentLine.substring(contentLine.indexOf("num") + 3,
									contentLine.indexOf('='));
							String val = contentLine.substring(contentLine.indexOf('=') + 1, contentLine.length());
							try {
								assignNum(name, val, null);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						if (contentLine.contains("word") && !contentLine.contains("input()")) {
							String name = contentLine
									.substring(contentLine.indexOf("word") + 4, contentLine.indexOf('='))
									.replaceAll(" ", "");
							try {
								String val = contentLine.substring(contentLine.indexOf('"') + 1,
										contentLine.length() - 1);
								Word w = new Word(name, val);
								words.add(w);
							} catch (Exception e) {
								throw new Exception("Need the quotes man");
							}
						}

						if (contentLine.contains("display")) {
							String temp = contentLine.replaceAll(" ", "");
							if (temp.substring(0, 7).equals("display")) {
								if (temp.indexOf("\"") != -1) {
									String val = contentLine.substring(contentLine.indexOf("\"") + 1,
											contentLine.length() - 2);
									System.out.println(val);
								} else {
									if (containsNum(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"))) != null) {
										System.out.println(
												(containsNum(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")))
														.getDec()));
									} else if (containsWord(
											temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"))) != null) {
										System.out.println(
												containsWord(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")))
														.getWord());
									} else {
										try {
											String val = "" + operations(
													temp.substring(temp.indexOf("(") + 1, temp.length() - 1));
											System.out.println(val);
										} catch (Exception e) {
											throw new Exception("Need quotes around the string");
										}
									}
								}
							}
						}

						if (contentLine.contains("input")) {
							try {
								String x = input(contentLine, null);
							} catch (Exception e) {

							}
						}

						if (contentLine.contains("if")) {
							if (contentLine.contains("compare")) {
								String x = contentLine.substring(contentLine.indexOf("(") + 1,
										contentLine.length() - 2);
								try {
									if (strComp(x)) {
										// continue with code
									} else {
										contentLine = contentLine.replaceAll(" ", "");
										while (!contentLine.equals("endif")) {
											contentLine = br.readLine();
										}
									}
								} catch (Exception e) {
									throw new Exception("need to write 'end' at the end of the if statement");
								}
							} else {
								try {
									if (numComp(contentLine, "xyz")) {
										// continue with code
									} else {
										contentLine = contentLine.replaceAll(" ", "");
										while (!contentLine.equals("endif")) {
											contentLine = br.readLine();
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}

						if (contentLine.contains("set") && !contentLine.contains("for")) {
							String var = contentLine.substring(0, contentLine.indexOf(".")).replaceAll(" ", "");
							String val = contentLine.substring(contentLine.indexOf("(") + 1, contentLine.indexOf(")"));
							if (containsNum(var) != null) {
								double x = operations(val);
								containsNum(var).setDoub(x);
							} else if (containsWord(var) != null) {
								String word = val.substring(1, val.length() - 1);
								containsWord(var).setWord(word);
							} else {
								throw new Exception("Unrecognized variable");
							}
						}

						if (contentLine.contains("for")) {
							contentLine.replaceAll(" ", "");
							String statement1 = contentLine.substring(contentLine.indexOf("(") + 1,
									contentLine.indexOf(";"));
							contentLine = contentLine.substring(contentLine.indexOf(";") + 1);
							String statement2 = contentLine.substring(0, contentLine.indexOf(";"));
							contentLine = contentLine.substring(contentLine.indexOf(";") + 1);
							String statement3 = contentLine.substring(0, contentLine.length() - 2);

							ArrayList<String> e = new ArrayList<String>();

							double x = 0;
							boolean y = false;

							if (statement1.contains("set")) {
								String name = statement1.substring(0, statement1.indexOf("."));
								String val = statement1.substring(statement1.indexOf("(") + 1, statement1.indexOf(")"));
								x = operations(val);
								if (containsNum(name) != null)
									containsNum(name).setDoub(x);
							} else {
								throw new Exception("You need to declare a counter number here");
							}

							y = numComp(statement2, "for");

							if (y) {
								while (!contentLine.contains("endfor")) {
									contentLine = br.readLine();
									e.add(contentLine);
								}
								e.add(statement3);
								forExecute(e, statement2);
							} else {
								while (!contentLine.equals("endfor")) {
									contentLine = br.readLine();
								}
							}

						}

						if (contentLine.contains("random")) {
							String name = contentLine.substring(contentLine.indexOf("num") + 3,
									contentLine.indexOf("="));
							contentLine = contentLine.replaceAll(" ", "");
							String num1 = contentLine.substring(contentLine.indexOf("(") + 1, contentLine.indexOf(","));
							String num2 = contentLine.substring(contentLine.indexOf(",") + 1, contentLine.indexOf(")"));

							double x1 = operations(num1);
							double x2 = operations(num2);

							double res = (int) (Math.random() * (x2 - x1)) + x1;
							name = name.replaceAll(" ", "");
							Number n = new Number(name, res);
							numbers.add(n);
						}
						
//						if (contentLine.substring(0, 4).equals("func")) {
//							String temp = contentLine.substring(0,5);
//							String func_type = 
//							
//						}

						contentLine = br.readLine();
					}
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static String input(String contentLine, ArrayList<Number> temp) throws Exception {
		Scanner sc = new Scanner(System.in);

		if (temp == null) {
			try {
				if (contentLine.substring(0, 3).equals("num") || contentLine.contains("num")) {
					double x = sc.nextDouble();
					String name = contentLine.substring(contentLine.indexOf("num") + 3, contentLine.indexOf("=") - 1);
					name = name.replaceAll(" ", "");
					Number n = new Number(name, x);
					numbers.add(n);
					return x + "";
				} else if (contentLine.subSequence(0, 4).equals("word")) {
					String s = sc.nextLine();
					String name = contentLine.substring(5, contentLine.indexOf(("=")) - 1);
					Word w = new Word(name, s);
					words.add(w);
					return s;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(compare);
			}
			return "error";
		}else {
			try {
				if (contentLine.substring(0, 3).equals("num") || contentLine.contains("num")) {
					double x = sc.nextDouble();
					String name = contentLine.substring(contentLine.indexOf("num") + 3, contentLine.indexOf("=") - 1);
					name = name.replaceAll(" ", "");
					Number n = new Number(name, x);
					numbers.add(n);
					temp.add(n);
					return x + "";
				} else if (contentLine.subSequence(0, 4).equals("word")) {
					String s = sc.nextLine();
					String name = contentLine.substring(5, contentLine.indexOf(("=")) - 1);
					Word w = new Word(name, s);
					words.add(w);
					return s;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(compare);
			}
			return "error";
		}
	}

	static void assignNum(String name, String val, ArrayList<Number> temp) throws Exception {
		if (temp == null) {
			name = name.replaceAll(" ", ""); // try replaceAll("\\s", ""); later
			val = val.replaceAll(" ", "");
			try {
				double x = operations(val);

				Number n = new Number(name, x);
				numbers.add(n);
			} catch (Exception e) {

			}
		} else {
			name = name.replaceAll(" ", ""); // try replaceAll("\\s", ""); later
			val = val.replaceAll(" ", "");
			try {
				double x = operations(val);

				Number n = new Number(name, x);
				temp.add(n);
				numbers.add(n);
			} catch (Exception e) {

			}
		}

	}

	private static double operations(String val) throws Exception {
		val = val.replaceAll(" ", "");
		double value = 0.0;
		ArrayList<String> small = new ArrayList<String>();
		ArrayList<Double> variables = new ArrayList<Double>();
		ArrayList<String> addSub = new ArrayList<String>();
		String f = "";
		for (int i = 0; i < val.toCharArray().length; i++) {
			if (val.toCharArray()[i] != '+' && val.toCharArray()[i] != '-') {
				f += val.toCharArray()[i];
			} else {
				small.add(f);
				addSub.add("" + val.toCharArray()[i]);
				f = "";
			}

			if (i == val.toCharArray().length - 1) {
				try {
					small.add(f);

				} catch (Exception e) {
					e.printStackTrace();
					System.exit(i);
				}

			}
		}
		ArrayList<String> temp = new ArrayList<>();
		ArrayList<String> multDivMod = new ArrayList<>();
		try {
			for (int i = 0; i < small.size(); i++) {
				temp.removeAll(temp);
				multDivMod.removeAll(multDivMod);
				char[] ch = small.get(i).toCharArray();
				String t = "";
				for (int j = 0; j < ch.length; j++) {
					if (ch[j] != '*' && ch[j] != '/' && ch[j] != '%') {
						t += ch[j];
					} else {
						temp.add(t);
						multDivMod.add(ch[j] + "");
						t = "";
					}

					if (j == ch.length - 1) {
						if (containsNum(t) != null || isNum(t)) {
							temp.add(t);
						}
					}
				}
				value = 0.0;

				if (containsNum(temp.get(0)) != null) {
					value += containsNum(temp.get(0)).getDec();
				} else {
					value += Double.parseDouble(temp.get(0));

				}
				int ind = 1;

				for (int j = 0; j < multDivMod.size(); j++) {

					if (multDivMod.get(j).equals("*")) {
						if (containsNum(temp.get(ind)) != null) {
							value *= containsNum(temp.get(ind)).getDec();
						} else {
							value *= Double.parseDouble(temp.get(ind));

						}
					}
					if (multDivMod.get(j).equals("/")) {
						if (containsNum(temp.get(j + 1)) != null) {
							value /= containsNum(temp.get(j + 1)).getDec();
						} else {
							value /= Double.parseDouble(temp.get(ind));
						}
					}
					if (multDivMod.get(j).equals("%")) {
						if (containsNum(temp.get(j + 1)) != null) {
							value %= containsNum(temp.get(j + 1)).getDec();
						} else {
							value %= Double.parseDouble(temp.get(ind));

						}
					}
					ind++;
				}

				variables.add(value);
			}

			value = 0.0;
			value += variables.get(0);
			int j = 1;
			for (int i = 0; i < addSub.size(); i++) {
				if (addSub.get(i).equals("+"))
					value += variables.get(j);
				if (addSub.get(i).equals("-"))
					value -= variables.get(j);
				j++;
			}

		} catch (Exception e) {
			throw new Exception("Invalid Character");
		}

		return value;
	}

	public static Number containsNum(String s) {
		for (Number num : numbers) {
			if (num.getName().equals(s)) {
				return num;
			}
		}
		return null;
	}

	public static Word containsWord(String s) {
		for (Word word : words) {
			if (word.getName().equals(s)) {
				return word;
			}
		}
		return null;
	}

	public static boolean isNum(char str) throws Exception {
		int intValue = 0;

		if (str == ' ') {
			return false;
		}

		try {
			intValue = Integer.parseInt(str + "");
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isNum(String string) {
		int intValue;
		string = string.replaceAll(" ", "");

		if (string == null || string.equals("")) {
			System.out.println("String cannot be parsed, it is null or empty.");
			return false;
		}

		try {
			intValue = Integer.parseInt(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean strComp(String str) throws Exception {
		str.replaceAll(" ", "");
		String first = "  ";
		String sec = " ";
		try {
			first = str.substring(0, str.indexOf("."));
			if (containsWord(first) != null) {
				first = containsWord(first).getWord();
			} else {
				if (first.indexOf("\"") != -1) {
					if (first.substring(first.indexOf("\"") + 1).contains("\"")) {
						first = str.substring(2, str.indexOf(".") - 1);
					}
				} else if (containsWord(first) == null || containsWord(sec) == null) {
					throw new Exception("Unrecognized variable");
				} else {
					throw new Exception("Need quotation marks around String.");
				}
			}

			sec = str.substring(str.indexOf("(") + 2, str.length() - 1);
			if (containsWord(sec) != null) {
				sec = containsWord(sec).getWord();
			} else {
				if (str.substring(str.indexOf("(") + 1).indexOf("\"") != -1) {
					if (str.substring(str.indexOf("(") + 2).contains("\"")) {
						sec = str.substring(str.indexOf("compare") + 9, str.length() - 2);
					} else {
						// ERROR
					}
				}
			}

			/*
			 * #Python is kewl #display(x) #num y = 3 + 4 + 5 #num z = y * 3 + 4 #display(z)
			 */
		} catch (Exception e) {
			e.printStackTrace();
			compare = 1;
			System.exit(69);
		}

		if (first.equals(sec)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean numComp(String line, String opt) throws Exception {
		line = line.replaceAll(" ", "");
		char less = '<';
		char greater = '>';
		String equals = "==";
		String notEq = "!=";
		String lessEq = "<=";
		String greaterEq = ">=";
		boolean l = false;
		double v1 = 0;
		double v2 = 0;

		try {

			if (!opt.equals("for")) {

				if (line.indexOf(greaterEq) != -1) {
					String st1 = line.substring(3, line.indexOf(greaterEq)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(greaterEq) + 2, line.length() - 2);
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 >= v2)
						l = true;
					else
						l = false;
				} else if (line.indexOf(lessEq) != -1) {
					String st1 = line.substring(3, line.indexOf(lessEq)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(lessEq) + 2, line.length() - 2).replaceAll(" ", "");
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 <= v2)
						l = true;
					else
						l = false;
				} else if (line.indexOf(equals) != -1) {
					String st1 = line.substring(3, line.indexOf(equals)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(equals) + 2, line.length() - 2).replaceAll(" ", "");
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 == v2 || (int) v1 == (int) v2)
						l = true;
					else
						l = false;
				} else if (line.indexOf(notEq) != -1) {
					String st1 = line.substring(3, line.indexOf(notEq)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(notEq) + 2, line.length() - 2).replaceAll(" ", "");
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 != v2)
						l = true;
					else
						l = false;
				} else if (line.indexOf(less) != -1) {
					String st1 = line.substring(3, line.indexOf(less)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(less) + 1, line.length() - 2).replaceAll(" ", "");
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 < v2)
						l = true;
					else
						l = false;
				} else if (line.indexOf(greater) != -1) {
					String st1 = line.substring(3, line.indexOf(greater)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(greater) + 1, line.length() - 2).replaceAll(" ", "");
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 > v2)
						l = true;
					else
						l = false;
				}
			} else {
				if (line.indexOf(greaterEq) != -1) {
					String st1 = line.substring(0, line.indexOf(greaterEq)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(greaterEq) + 2, line.length());
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 >= v2)
						l = true;
					else
						l = false;
				} else if (line.indexOf(lessEq) != -1) {
					String st1 = line.substring(0, line.indexOf(lessEq)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(lessEq) + 2, line.length()).replaceAll(" ", "");
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 <= v2)
						l = true;
					else
						l = false;
				} else if (line.indexOf(equals) != -1) {
					String st1 = line.substring(0, line.indexOf(equals)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(equals) + 2, line.length()).replaceAll(" ", "");
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 == v2)
						l = true;
					else
						l = false;
				} else if (line.indexOf(notEq) != -1) {
					String st1 = line.substring(0, line.indexOf(notEq)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(notEq) + 2, line.length()).replaceAll(" ", "");
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 != v2)
						l = true;
					else
						l = false;
				} else if (line.indexOf(less) != -1) {
					String st1 = line.substring(0, line.indexOf(less)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(less) + 1, line.length()).replaceAll(" ", "");
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 < v2)
						l = true;
					else
						l = false;
				} else if (line.indexOf(greater) != -1) {
					String st1 = line.substring(0, line.indexOf(greater)).replaceAll(" ", "");
					String st2 = line.substring(line.indexOf(greater) + 1, line.length()).replaceAll(" ", "");
					v1 = operations(st1);
					v2 = operations(st2);
					if (v1 > v2)
						l = true;
					else
						l = false;
				}
			}
		} catch (Exception e) {
			throw new Exception("This program isn't advanced enough to do that type of comparison");
		}

		return l;

	}

	public static void forExecute(ArrayList<String> strings, String st3) throws IOException, Exception {
		ArrayList<Number> temporaryNums = new ArrayList<>();
		while (numComp(st3, "for")) {
			for (int i = 0; i < strings.size(); i++) {
				String str = strings.get(i);

				if (str.isBlank() || str.isEmpty()) {
					if (i + 1 < strings.size()) {
						i++;
						str = strings.get(i);
					}
				}

				if (str != null) {

					if (str.charAt(0) == '#') {
						if (i + 1 < strings.size()) {
							i++;
							str = strings.get(i);
						}
					}
					if (str.contains("num") && !str.contains("input()")) {
						String name = str.substring(4, str.indexOf('='));
						String val = str.substring(str.indexOf('=') + 1, str.length());
						try {
							assignNum(name, val, temporaryNums);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					if (str.contains("word") && !str.contains("input()")) {
						String name = str.substring(5, str.indexOf('=')).replaceAll(" ", "");
						try {
							String val = str.substring(str.indexOf('"') + 1, str.length() - 1);
							Word w = new Word(name, val);
							words.add(w);
						} catch (Exception e) {
							throw new Exception("Need the quotes man");
						}
					}

					if (str.contains("display")) {
						String temp = str.replaceAll(" ", "");
						if (temp.substring(0, 7).equals("display")) {
							if (temp.indexOf("\"") != -1) {
								String val = str.substring(str.indexOf("\"") + 1, str.length() - 2);
								System.out.println(val);
							} else {
								if (containsNum(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"))) != null) {
									System.out.println(
											(containsNum(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")))
													.getDec()));
								} else if (containsWord(
										temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"))) != null) {
									System.out.println(
											containsWord(temp.substring(temp.indexOf("(") + 1, temp.indexOf(")")))
													.getWord());
								} else {
									try {
										String val = ""
												+ operations(temp.substring(temp.indexOf("(") + 1, temp.length() - 1));
										System.out.println(val);
									} catch (Exception e) {
										throw new Exception("Need quotes around the string");
									}
								}
							}
						}
					}

					if (str.contains("input")) {
						try {
							String x = input(str, temporaryNums);
						} catch (Exception e) {

						}
					}

					if (str.contains("if")) {
						if (str.contains("compare")) {
							String x = str.substring(3, str.length() - 2);
							try {
								if (strComp(x)) {
									// continue with code
								} else {
									str = str.replaceAll(" ", "");
									while (!str.equals("endif")) {
										if (i + 1 < strings.size()) {
											i++;
											str = strings.get(i);
										}
									}
								}
							} catch (Exception e) {
								throw new Exception("need to write 'end' at the end of the if statement");
							}
						} else {
							try {
								if (numComp(str, "xdyz")) {
									// continue with code
								} else {
									str = str.replaceAll(" ", "");
									while (!str.contains("endif")) {
										i++;
										str = strings.get(i);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								System.exit(i);
							}
						}
					}

					if (str.contains("set") && !str.contains("for")) {
						String var = str.substring(0, str.indexOf(".")).replaceAll(" ", "");
						String val = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
						if (containsNum(var) != null) {
							double x = operations(val);
							containsNum(var).setDoub(x);
						} else if (containsWord(var) != null) {
							String word = val.substring(1, val.length() - 1);
							containsWord(var).setWord(word);
						} else {
							throw new Exception("Unrecognized variable");
						}
					}

					if (str.contains("for") && !str.contains("end")) {
						str.replaceAll(" ", "");
						String statement1 = str.substring(str.indexOf("for") + 4, str.indexOf(";"));
						str = str.substring(str.indexOf(";") + 1);
						String statement2 = str.substring(0, str.indexOf(";"));
						str = str.substring(str.indexOf(";") + 1);
						String statement3 = str.substring(0, str.length() - 2);

						ArrayList<String> e = new ArrayList<String>();

						double x = 0;
						boolean y = false;

						if (statement1.contains("set")) {
							String name = statement1.substring(0, statement1.indexOf("."));
							String val = statement1.substring(statement1.indexOf("(") + 1, statement1.indexOf(")"));
							x = operations(val);
							if (containsNum(name) != null)
								containsNum(name).setDoub(x);
						} else {
							throw new Exception("You need to declare a counter number here");
						}

						y = numComp(statement2, "for");

						if (y) {
							while (!str.equals("endfor")) {
								i++;
								str = strings.get(i);
								e.add(str);
							}
							e.add(statement3);
							forExecute(e, statement2);
						} else {
							while (!str.equals("endfor")) {
								i++;
								str = strings.get(i);
							}
						}

					}

					if (str.contains("random")) {
						String name = str.substring(str.indexOf("num") + 3, str.indexOf("="));
						str = str.replaceAll(" ", "");
						String num1 = str.substring(str.indexOf("(") + 1, str.indexOf(","));
						String num2 = str.substring(str.indexOf(",") + 1, str.indexOf(")"));

						double x1 = operations(num1);
						double x2 = operations(num2);

						double res = (int) (Math.random() * (x2 - x1)) + x1;
						name = name.replaceAll(" ", "");
						Number n = new Number(name, res);
						numbers.add(n);
					}

				}

			}
			for (int i = 0; i < numbers.size(); i++) {
				for (int j = 0; j < temporaryNums.size(); j++) {
					if (numbers.get(i).getName().equals(temporaryNums.get(j).getName())) {
						numbers.remove(i);
						temporaryNums.remove(j);
					}
				}
			}
		}

	}
}
