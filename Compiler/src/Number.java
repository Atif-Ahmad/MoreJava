package Compiler;

public class Number {
	
	private double dec;
	
	private String name;
	
	
	
	public Number(String na, double d) {
		dec = d;
		name = na;
	}
	
	
	public void setDoub(double d) {
		dec = d;
	}
	
	public double getDec() {
		return dec;
	}
	
	public String getName() {
		return name;
	}

}
