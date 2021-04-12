package TicTacToe;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.junit.Test;

public class CombinationsTest {
	
	@Test
	public void testCombinations() {
		
		Combinations c = new Combinations();
		List<Pair<Integer, Integer>> result = c.combinations(Arrays.asList(1,2,3,4));
		
		
		assertEquals("size of array should be", 6, result.size());
		
		
	}

}
