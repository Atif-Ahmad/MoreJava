package TicTacToe;

public abstract class Player {
	
	protected char symbol;
	public abstract void placePiece();
	
	public char getSymbol() {
		return symbol;
	}
	
}
