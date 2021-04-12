package TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.math3.util.Pair;

public class CpuPlayer extends Player{
	
	private Board board;
	
	public CpuPlayer(char s, Board board) {
		symbol = s;
		this.board = board;
	}

	@Override
	public void placePiece() {
		int cpuPos=0;

		List<Pair<Integer, Integer>> lastTwoMoves;

		char symbol = ' ';

		symbol = 'O';
		Boolean isAWin = false;
		List<Integer> playerPositions = board.getPlayerPositions();
		if (playerPositions.size()>=2) {
			
			Combinations c = new Combinations();
			lastTwoMoves = c.combinations(playerPositions);
			

			for (Pair<Integer, Integer> p:lastTwoMoves) {
			
				for (List<Integer> i : board.getWinningCases()) {
					if (i.contains(p.getFirst()) && i.contains(p.getSecond())) {
						isAWin = true;
						
						List<Integer> copy = new ArrayList<>();
						makeCopy(i, copy);
						
						copy.remove(p.getFirst());
						copy.remove(p.getSecond());
						
						cpuPos = copy.get(0);
					

						
					}
				}
			}
		}
		if (isAWin) {
			boolean success = false;
			
			success = board.place(cpuPos, this);
			if (!success) {
				do {
					Random r = new Random();
					cpuPos 	= r.nextInt(9) + 1;
					success = board.place(cpuPos, this);
				}while (!success);
					
				
				
//				while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
//					cpuPos = r.nextInt(9) + 1;
//					
//				}
				
			}
			
		}
		
//		if (cpuPositions.contains(cpuPos) || !isAWin){
//			Random r = new Random();
//			cpuPos 	= r.nextInt(9) + 1;
//			while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
//				cpuPos = r.nextInt(9) + 1;
//				
//			}
//		}
//		
//		cpuPositions.add(cpuPos);
//
//		placePiece(Board, cpuPos, "Computer");
	}		
	
	private  void makeCopy(List<Integer> i, List<Integer> copy) {
		// TODO Auto-generated method stub
		for (Integer x: i) {
			copy.add(x);
		}
	}
	
}
