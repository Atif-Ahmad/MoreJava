package TicTacToe;

public class TicTacToe {
	
	
	public static void main(String[] args) {
		
		Board board = new Board();
		board.printGameBoard();
		
		Player human = new HumanPlayer('X', board);
		Player computer = new CpuPlayer('O', board);
		
		while (true) {
			human.placePiece();
			board.printGameBoard();
			computer.placePiece();
			board.printGameBoard();
			
			if (board.hasGameEnded()) {
				break;
			}
		}
		
		System.out.println("Congratulations" + board.getWinner());
	}
	
}
