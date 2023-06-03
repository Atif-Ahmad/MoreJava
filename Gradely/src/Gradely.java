package gradely;

import java.io.BufferedReader;
import java.io.File; // Import the File class
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException; // Import the IOException class to handle errors
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gradely {
	public static void main(String[] args) {

		ArrayList<String> res = new ArrayList<String>();
		int x = 0;
		try {
			File myObj = new File("/Users/atif/eclipse-workspace/HW/filename.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		Scanner sc = new Scanner(System.in);

		FileWriter myWriter;
		try {
			myWriter = new FileWriter("filename.txt", true);
			String name = "Sandman";

			System.out.println("Enter Name");
			name = sc.nextLine();

			String decision = "";

			while (!decision.equals("no")) {
				System.out.println("Enter Mode:");
				System.out.println(
						"1. GPA Calculator, 2. Individual Course Calculator, 3. Predict what you need to get on the next test to get A"
								+ "\n + 4. Get Profile");
				int choice = sc.nextInt();
				sc.nextLine();

				if (choice == 1) {
					try {
						myWriter = new FileWriter("filename.txt", true);
//						boolean abcd = new File("filename.txt").exists();
//						if (!abcd) {
						System.out.println("Enter number of courses");
						x = sc.nextInt();
						sc.nextLine();
						System.out.println("Enter the class and the grade in the following format: \n"
								+ "Name of course : Number Grade");

						for (int i = 0; i < x; i++) {
							String str = sc.nextLine();
							res.add(str);
						}

						myWriter.write(name + "\nGrade : 12" + "\nSchool : Mission San Jose High School" + " \n");
						for (String s : res) {
							myWriter.write(s);
							myWriter.write("\n");
						}
						//System.out.println("Successfully wrote to the file.");
						String[] courses = new String[res.size()];
						ArrayList<Double> grades = new ArrayList<Double>();

						for (int i = 0; i < res.size(); i++) {
							String[] s = res.get(i).split(":");
							courses[i] = s[0];
							grades.add(Double.parseDouble(s[1]));
						}

						Profile p = new Profile(name, grades);
						System.out.println(p.getGPA());
						myWriter.write("GPA (unweighted) : " + p.getGPA() + "\n");

						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						myWriter.append("Profile modified at : " + dtf.format(now) + "\n");

						myWriter.close();

						res.removeAll(res);

					} catch (IOException e) {
						System.out.println("An error occurred.");
						e.printStackTrace();
					}

				} else if (choice == 2) {

					System.out.println("Enter the course that you want to calculate");
					String e = sc.nextLine();
					System.out.println("How many categories in " + e + "?");
					int num = sc.nextInt();
					sc.nextLine();
					ArrayList<Category> f = new ArrayList<Category>();
					double grade = 0;
					double sum = 0;
					for (int i = 0; i < num; i++) {
						System.out.println("Enter category followed by a space then the weight.");
						String a = sc.next();
						double b = sc.nextDouble();
						sum += b;
						if (sum <= 1) {
							System.out.println("How many grades would you like to enter for " + a + "?");
							int abc = sc.nextInt();
							sc.nextLine();
							ArrayList<Double> g = new ArrayList<>();
							System.out.println("Enter your grades for this weight");
							for (int j = 0; j < abc; j++) {
								g.add(sc.nextDouble());
							}
							f.add(new Category(a, g, b));
						} else {
							System.out.println("Bad amount, enter a weight. Current sum: " + sum);
							b = sc.nextDouble();
							f.add(new Category(a, b));
						}

					}

					System.out.println(f);

					for (Category cat : f) {
						grade += cat.getCatGradeWeight();
					}

					System.out.println("Add to profile?");
					String s = sc.next();
					if (s.equals("Yes") || s.equals("yes")) {
						FileWriter t = new FileWriter("filename.txt", true);
						t.append(e + ":");
						for (Category cat : f) {
							t.append(cat.toString() + ",");
						}
						t.append("\n" + "Grade : " + grade + " \n");
						System.out.println("Grade : " + grade);
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						t.append("Profile modified at : " + dtf.format(now) + "\n");
						t.close();
					} else {
						System.out.println("Grade : " + grade);
					}

				} else if (choice == 3) {
					System.out.println("This will not go in your profile.");
					System.out.println("Enter the course you want to predict");
					String e = sc.nextLine();
					System.out.println("Enter your current grade");
					double g = sc.nextDouble();
					sc.nextLine();
					System.out.println("Enter the weight for your test");
					double w = sc.nextDouble();
					sc.nextLine();

					if (g < 90) {
						double result = nextTest(new Category("Test", w), g);
						if (result < 100) {
							System.out.println("You need a " + result + "% on the next test.");
						} else if (result > 100) {
							System.out.println("You need a " + result + "% on the next test. Stay positive...");
						}
					} else {
						System.out.println("Who you foolin'?");
					}

				} else if (choice == 4) {
					BufferedReader br = null;
					BufferedReader br2 = null;

					sc = new Scanner(System.in);
					try {
						br = new BufferedReader(new FileReader("/Users/atif/eclipse-workspace/HW/filename.txt"));

						String contentLine = br.readLine();
						while (contentLine != null) {
							System.out.println(contentLine);
							contentLine = br.readLine();
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
				System.out.println("Would you like to continue your session? Yes or No");
				decision = sc.next().toLowerCase();
			}
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		System.out.println("Thank you for using Gradely. We will definitely be seeing you again B)");

	}

	public static double nextTest(Category a, double totalGrade) {
//	  if(cats.size() == 0)
//	  {
//	    return 0;
//	  }

		double x = (90.0 - totalGrade * (1.0 - a.getWeight())) / a.getWeight();
		return x;

	}

}
