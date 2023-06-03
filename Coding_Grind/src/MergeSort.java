
public class MergeSort {
	public static void main(String[] args) {

		int[] e = { 4, 3, 2, 6, 9, 10, 1 };
		e = mergeSort(e);
		
		for (int i : e) {
			System.out.print(i + " ");
		}

	}

	public static int[] mergeSort(int[] arr) {
		if (arr.length == 1) {
			return arr;
		}else {
			int middle = (arr.length/2);
			int[] low = new int[middle];
			int[] high;
			if (arr.length % 2 == 0) {
				high = new int[middle];
			}else {
				int temp = middle + 1;
				high = new int[temp];
			}
			
			for (int i = 0; i < middle; i++) {
				low[i] = arr[i];
			}
			for (int j = 0; j < high.length; j++) {
				high[j] = arr[middle + j];
			}
						
			low = mergeSort(low);
			high=mergeSort(high);
			
			return m(low, high);
			
		}
	}
	
	public static int[] m(int[] e, int[] f) {
		int[] res = new int[e.length + f.length]; 
		int i = 0;
		int j = 0;
		int k = 0; 
		while (i < e.length || j < f.length) {
			if (i < e.length && j < f.length) {
				if (e[i] < f[j]) {
					res[k] = e[i];
					i++;
					k++;
				}else {
					res[k] = f[j];
					k++;
					j++;
				}
			}else if (i < e.length) {
				res[k] = e[i];
				k++;
				i++;
			}
			else if (j < f.length) {
				res[k] = f[j];
				k++;
				j++;
			}
		}
		
		return res;
	}

	

}
