package gradely;

import java.util.*;

//Newest and FINAL version of Category
public class Category {
	private String catName;
	private ArrayList<Double> gradeList; // array of doubles that represent the grades gotten in this category
	private double weight;
	private double catGrade; // calculated average grade for this category
	private double catGradeWeight; // average grade * weight for application outside of object

	// Custom cons. with a list of grades already initialized
	public Category(String n, ArrayList<Double> g, double w) {
		catName = n;
		gradeList = g;
		weight = w;
		// calculate average grade
		double preGrade = 0;
		for (double x : gradeList) {
			preGrade = preGrade + x;
		}
		catGrade = preGrade / g.size();
		// average grade * weight
		catGradeWeight = catGrade * weight; //with wieght applied
	}

	// Custom cons. empty grades
	public Category(String n, double w) {
		catName = n;
		ArrayList<Double> gradeList = new ArrayList<Double>();
		weight = w;
		// calculate average grade
		double preGrade = 0;
		for (double x : gradeList) {
			preGrade = preGrade + x;
		}
		// we cant have empty grades generate errors
		if (gradeList.size() > 0) {
			catGrade = preGrade / gradeList.size();
			// average grade * weight
			catGradeWeight = catGrade * weight;
		}
		// so that we know if somethin is empty or not
		if (gradeList.size() == 0) {
			catGrade = 0;
			catGradeWeight = 0;
		}

	}

	public String getCatName() {
		return catName;
	}

	// get the list of grades
	public ArrayList<Double> getGradeList() {
		return gradeList;
	}

	// get the weight of this this category
	public double getWeight() {
		return weight;
	}

	// get the overall grade of the vategory
	public double getCatGrade() {
		return catGrade;
	}

	// apply the weight of this category to the overall grade
	public double getCatGradeWeight() {
		return catGradeWeight;
	}

	public void setCatName(String n) {
		catName = n;
	}

	// set a new list of grades
	public void setGradeList(ArrayList<Double> g) {
		gradeList = g;
	}

	// add a double to the double "grade" array
	public void addGradeList(double d) {
		gradeList.add(d);
	}

	public String toString() {
		return catName + " " + (weight * 100) + "% " + catGrade + " " + catGradeWeight;
	}
}