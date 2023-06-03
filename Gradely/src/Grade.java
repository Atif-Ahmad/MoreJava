package gradely;

public class Grade {
	private String cat;
	private double grade;
	private double weight;

	public Grade(String c, double g, double w) {
		cat = c;
		grade = g;
		weight = w;
	}

	public String getCat() {
		return cat;
	}

	public double getGrade() {
		return grade;
	}

	public double getWeight() {
		return weight;
	}

	// =============//
	public void setCat(String c) {
		cat = c;
	}

	public void setGrade(double g) {
		grade = g;
	}

	public void setWeight(double w) {
		weight = w;
	}

}