package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
	char[][] Board = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
			{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } };
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	static ArrayList<List<Integer>> win = new ArrayList<List<Integer>>();
	
	private String winner;
	
	public Board() {
		List<Integer> topRow = Arrays.asList(1, 2, 3);
		List<Integer> medRow = Arrays.asList(4, 5, 6);
		List<Integer> bottomRow = Arrays.asList(7, 8, 9);

		List<Integer> leftCol = Arrays.asList(1, 4, 7);
		List<Integer> midCol = Arrays.asList(2, 5, 8);
		List<Integer> rightCol = Arrays.asList(3, 6, 9);

		List<Integer> firstCross = Arrays.asList(1, 5, 9);
		List<Integer> secondCross = Arrays.asList(3, 5, 7);

		win.add(topRow);
		win.add(medRow);
		win.add(bottomRow);
		win.add(leftCol);
		win.add(midCol);
		win.add(rightCol);
		win.add(firstCross);
		win.add(secondCross);
	}
	
	
	public  void printGameBoard() {
		for (char[] row : Board) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public boolean hasGameEnded() {
		if (checkWinner().length() > 0) {
			return true;
		}
		return false;
	}
	
	private String checkWinner() {
		for (List<Integer> l : win) {
			if (playerPositions.containsAll(l)) {
				winner = "Human";
				return "Human Player wins. Congratulations =D";
			} else if (cpuPositions.containsAll(l)) {
				winner = "computer";
				return "You lose. Try again. :(";
			} else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "Tie";
			}
		}
		return "";
	}


	public String getWinner() {
		return null;
	}


	public boolean place(int pos, Player player) {
		
		if (playerPositions.contains(pos) || cpuPositions.contains(pos)) {
			System.out.println("Position Taken, do another one");
			return false;
		}else {
			char symbol = player.getSymbol();
			switch (pos) {
				case 1:
					Board[0][0] = symbol;
					break;
				case 2:
					Board[0][2] = symbol;
					break;
				case 3:
					Board[0][4] = symbol;
					break;
				case 4:
					Board[2][0] = symbol;
					break;
				case 5:
					Board[2][2] = symbol;
					break;
				case 6:
					Board[2][4] = symbol;
					break;
				case 7:
					Board[4][0] = symbol;
					break;
				case 8:
					Board[4][2] = symbol;
					break;
				case 9:
					Board[4][4] = symbol;
					break;
			}
			
			if (player instanceof HumanPlayer) {
				playerPositions.add(pos);
			}
			else {
				cpuPositions.add(pos);
			}
			
			return true;
			
		}
		
	}


	public List<Integer> getPlayerPositions() {

		return playerPositions;
	}


	public List<List<Integer>> getWinningCases() {
		// TODO Auto-generated method stub
		return win;
	}

	
	

}
