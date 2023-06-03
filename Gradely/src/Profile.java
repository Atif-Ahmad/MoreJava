package gradely;

import java.util.*;

public class Profile {
	private String profName;
	private ArrayList<CourseV2> courses;
	private ArrayList<Double> grades;
	private double gpa;

	public Profile(String n, ArrayList<CourseV2> i, double g) {
		profName = n;
		courses = i;
		gpa = g;
	}

	public Profile(String n, ArrayList<Double> i) {
		profName = n;
		grades = i;
	}

	public double getGPA() {
		ArrayList<Double> i = grades;
		String[] letters = new String[i.size()];

		int e = 0;
		for (double x : i) {
			String letter = "";
			if (x >= 60 && x < 70) {
				letter = "D";
				letters[e] = letter;
			}
			if (x >= 70 && x < 80) {
				letter = "C";
				letters[e] = letter;
			}
			if (x >= 80 && x < 90) {
				letter = "B";
				letters[e] = letter;
			}
			if (x >= 90) {
				letter = "A";
				letters[e] = letter;
			}
			if (x <= 50) {
				letter = "Empty";
				letters[e] = "Empty";
			}
			e++;
		}
		double preGPA = 0;
		for (String x : letters) {
			if (x.equals("A")) {
				preGPA = preGPA + 4.0;
			}
			if (x.equals("B")) {
				preGPA = preGPA + 3.0;
			}
			if (x.equals("C")) {
				preGPA = preGPA + 2.0;
			}
			if (x.equals("D")) {
				preGPA = preGPA + 1.0;
			}
		}

		double gpa = preGPA / i.size();
		return gpa;
	}

	public String getName() {
		return profName;
	}

	public ArrayList<CourseV2> getCourses() {
		return courses;
	}

	public void addCourse(CourseV2 e) {
		courses.add(e);
	}
}