package MowingFinal;

public class Lawn {

	private int length;
	private int width;
	public String [][] lawn;

	public Lawn(int w, int l) {
		length = l;
		width = w;
		
		lawn = new String[l][w];

		for (int i = 0; i < l; i++) {
			for (int j = 0; j < w; j++) {
				lawn[i][j] = " * ";
			}
		}
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}
	
	public void printLawn() {
		for (int i = 0; i < length; i++) {
			for( int j = 0; j < width; j++) {
				System.out.print(lawn[i][j]);
			}
			System.out.println();
		}
		System.out.println("——————————————————————————————————");
	} 
	
	public void placeTree(int x, int y) {
		lawn[x][y] = " T ";
	}
	
	public String[][] getLawn() {
		return lawn;
	}
}