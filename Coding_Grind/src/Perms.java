public class Perms {
	
	int factorial(int n){
	    if (n == 1 || n == 0){
	        return 1;
	    }else{
	        return n * factorial(n-1);
	    }
	}

	void print(int arr[]){
	    for (int i = 0; i < arr.length; i++){
	        System.out.print(arr[i]+" ");
	    }
	    System.out.println();
	}

	void swap(int arr[], int i, int j){
	    int temp = arr[j];
	    arr[j] = arr[i];
	    arr[i] = temp;
	    print(arr);
	}

	void permutations(int arr[], int a, int b){
	    for (int i = a; i < b; i++){
	        swap(arr, i, b);
	        permutations(arr, i, b-1);
	        swap(arr, b, i);
	    }
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		Perms p = new Perms();
		p.permutations(arr, 0, 2);
	}
	
}

