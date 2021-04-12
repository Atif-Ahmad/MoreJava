package TicTacToe;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;

/**
 * @author atifahmad
 *
 */
public class Combinations {
	
	
	/**
	 * combinations: returns all possible 2 pair combinations
	 * @param list
	 * @return
	 */
	public List<Pair<Integer,Integer>> combinations(List<Integer> list){
		
		
		System.out.println("hellp");
		
		List<Pair<Integer,Integer>>x = new ArrayList<>();
		
		
		
		for (int i = 0; i < list.size(); i++) {
			for (int j = i+1; j<list.size(); j++) {
				
				Pair<Integer,Integer> p = new Pair<>(list.get(i), list.get(j));
				System.out.print(list.get(i));
				System.out.print(",");
				System.out.print(list.get(j));
				System.out.println("");
				x.add(p);
			}
		}
		
		return x;
		
	}
 
}
