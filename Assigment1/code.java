import java.util.Scanner;

public class code {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		long a = sc.nextLong();
		long b = sc.nextLong();
		
		long v = 0;
		while ( a%30 != 0 && a<=b ) {
			if (( a%2 == 0) || (a%3 == 0) || (a%5 == 0)) {
				v ++;
			}
			a++;
		}
		if(b > a){
			long razlika = (b - a);
			long faktor = razlika / 30;
			v += 22 * faktor;
			a += faktor * 30;
		}
		
		for(long i = a; i <= b; i++){
			if ( (i%2 == 0) ||(i%3 == 0) || (i%5 == 0)) {
				v += 1;
			}
		}
		System.out.println(v);
		
	

		
	}
}


