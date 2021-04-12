package TicTacToe;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player{

	
	private Board board;
	
	public HumanPlayer(char s, Board board) {
		symbol = s;
		this.board = board;
	}
	@Override
	public void placePiece() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your placement: 1-9");
		
		boolean success = false;
		
		
		do  {
			int playerPos = scan.nextInt();
			success = board.place(playerPos, this);
		} while (!success);
		
		
		
	}
	
}
