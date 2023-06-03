public class ReverseInt {

	public static void main(String[] args) {
		//ReverseInt r = new ReverseInt();
		//System.out.println(r.reverse(-2147483412));
		int[] arr = {2,3,4,5,18,17,6};
		System.out.println(efficientMax(arr));
	}

	public int reverse(int x) {
		boolean isNeg = false;
		int res = 0;
		if (x < 0) {
			isNeg = true;
			x *= -1;
		}
		while (x > 0) {
			int temp = x % 10;
			if (Math.abs(res) > Integer.MAX_VALUE / 10)
				return 0;
			else {
				res *= 10;
				res += temp;
				x = x / 10;
			}
		}
		if (isNeg)
			return res * -1;
		else
			return res;
	}

	public static int max(int[] arr) {
		int area = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] <= arr[j]) {
					if (area <  arr[i] * (Math.abs(i-j)))
						area = arr[i] * (Math.abs(i-j));
				}
				if (arr[j] < arr[i]) {
					if(area < arr[j] * Math.abs(i-j)) {
						area = arr[j] * (Math.abs(i-j));
					}
				}
			}
		}
		return area; 
	}
	
	public static int efficientMax(int[] arr) {
		int area = 0;
		for (int i = 0, j = arr.length-1; j>-1;) {
			if (arr[i] <= arr[j]) {
				if (area <  arr[i] * Math.abs(j-i))
					area = arr[i] * Math.abs(j-i);
			}
			if (arr[j] < arr[i]) {
				if (area <  arr[j] * Math.abs(j-i))
					area =  arr[j] * Math.abs(j-i);
			}
		}
		return area; 
	}
			
}
