import java.util.ArrayList;

public class PalindromeNumber {
	public static void main(String[] args) {
		PalindromeNumber p = new PalindromeNumber();
		System.out.println(p.palindromeNum(10));
	}
	
	public boolean palindromeNum(int x) {
		char[] a = Integer.toString(x).toCharArray();
		for (int i = 0, j = a.length-1; i < j; i++, j--) {
			if (a[i] != a[j]) {
				return false;
			}
		}
		return true;
	}
	
	
}
