
public class RomanToInt {
	
	public static void main(String[]args) {
		System.out.println(fastRomanToInt("IV"));
	}
	
	public static int romanToInt(String s) {
        String[][] ones = {{"I", "1"}, {"II", "2"}, {"III", "3"}, {"IV", "4"}, {"V", "5"}, {"VI", "6"}, {"VII", "7"}, {"VIII", "8"}, {"IX", "9"}};
        String[][] tens = {{"X", "10"}, {"XX", "20"}, {"XXX", "30"}, {"XL", "40"}, {"L", "50"}, {"LX", "60"}, {"LXX", "70"}, {"LXXX", "80"}, {"XC", "90"}};
        String[][] hunds = {{"C", "100"}, {"CC", "200"}, {"CCC", "300"}, {"CD", "400"}, {"D", "500"}, {"DC", "600"}, {"DCC", "700"}, {"DCCC", "800"}, {"CM", "900"}};
        String[][] t = {{"M", "1000"},{ "MM", "2000"}, {"MMM", "3000"}};
        
        int res = 0;

        for (int i = ones.length-1; i > -1; i--){
            int temp = s.indexOf(ones[i][0]);
            if (temp != -1) {
            	if (i == 4) {
            		int a = s.indexOf(ones[i-1][0]);
            		if (a !=-1)
            			i=i-2;
            	}
            	int r = Integer.parseInt(ones[i][1]);
            	res += r;
                s = s.substring(0, temp);
            }
        }
        
        for (int i = tens.length-1; i > -1; i--){
            int temp = s.indexOf(tens[i][0]);
            if (temp != -1) {
            	if (i == 4) {
            		int a = s.indexOf(tens[i-1][0]);
            		if (a !=-1)
            			i=i-2;
            	}
            	int r = Integer.parseInt(tens[i][1]);
            	res += r;
                s = s.substring(0, temp);
            }
        }
        
        for (int i = hunds.length-1; i > -1; i--){
            int temp = s.indexOf(hunds[i][0]);
            if (temp != -1) {
            	if (i == 4) {
            		int a = s.indexOf(hunds[i-1][0]);
            		if (a !=-1)
            			i=i-2;
            	}
            	int r = Integer.parseInt(hunds[i][1]);
            	res += r;
                s = s.substring(0, temp);
            }
        }
        
        for (int i = t.length-1; i > -1; i--){
            int temp = s.indexOf(t[i][0]);
            if (temp != -1) {
            	int r = Integer.parseInt(t[i][1]);
            	res += r;
                s = s.substring(0, temp);
            }
        }
       
        return res;
    }
	
	public static int fastRomanToInt(String s) {
	    
	    int answer = 0, number = 0, prev = 0;

	    for (int j = s.length() - 1; j >= 0; j--) {
	        switch (s.charAt(j)) {
	            case 'M' -> number = 1000;
	            case 'D' -> number = 500;
	            case 'C' -> number = 100;
	            case 'L' -> number = 50;
	            case 'X' -> number = 10;
	            case 'V' -> number = 5;
	            case 'I' -> number = 1;
	        }
	        if (number < prev) {
	            answer -= number;
	        }
	        else {
	            answer += number;
	        }
	        prev = number;
	    }
	    return answer;
	}
}
