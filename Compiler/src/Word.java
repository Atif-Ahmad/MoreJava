package Compiler;

public class Word {
	private String word;
	private String name;
	
	public Word(String n, String w) {
		name = n;
		word = w; 
	}
	
	public void setWord(String s) {
		word = s;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getName() {
		return name;
	}
}
