package gradely;

import java.util.*;

public class CourseV2 {
	private String name; // name of the course
	private ArrayList<Category> cats;// represents a list of categories that make up this course
	private double totalGrade; // total grade of course with all weights applied
	private boolean isAP; // if this course is AP or not and affects weighted GPA calculator
	private String letter;

	// constructor
	public CourseV2(String n, ArrayList<Category> c, boolean a) {
		isAP = a;
		name = n;
		cats = c;
		double preGrade = 0;
		// if there is stuff here
		if (cats.size() > 0) {
			for (Category x : c) {
				preGrade = preGrade + x.getCatGradeWeight(); // more stuff here
			}
			// total grade with weight accounted for
			totalGrade = preGrade; //recent modification : pregrade/cat.size(
		}
		// if categories is empty
		if (cats.size() == 0) {
			totalGrade = 0;
		}
		//
		if (totalGrade >= 60 && totalGrade < 70) {
			letter = "D";
		}
		if (totalGrade >= 70 && totalGrade < 80) {
			letter = "C";
		}
		if (totalGrade >= 80 && totalGrade < 90) {
			letter = "B";
		}
		if (totalGrade >= 90) {
			letter = "A";
		}
		if (totalGrade == 0) {
			letter = "Empty";
		}
	}

	public String getCName() {
		return name;
	}

	// return list of categories
	public ArrayList<Category> getCats() {
		return cats;
	}

	// return AP status of this course
	public boolean getAP() {
		return isAP;
	}

	// return the total grade of course, with wieghted average applied from
	// categories
	public double getTotalGrade() {
		return totalGrade;
	}

	public String getLetter() {
		return letter;
	}
	public String toString()
	{
		return name + " " + totalGrade + " " + letter + " " + isAP;
	}
	

}