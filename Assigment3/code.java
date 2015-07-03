import java.math.*;
import java.util.*;

public class code {
	
	
	private static long Kvadratki(long h, long w, int k) {
		long s = 0;
		// Zanka se izvrsi k krat
		for (int i = 0; i < k; i++) {
			// Preveri zadnji bit h-ja in w-ja
			if ((h & 1) > 0 && (w & 1) > 0) {
				s += w + h -1;
			}
			else if ((h & 1) > 0) {
				s += w;
			}
			else if ((w & 1) > 0) {
				s +=h;
			}
			// binarni zapis premakne za 1 v desno
			h = h >> 1;
			w = w >> 1;	
 		}
		// Pristeje ostanek pri kju, ki smo ga vnesli
		s += w*h;
		return s;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long h = sc.nextLong();
		long w = sc.nextLong();
		int k = sc.nextInt();
		System.out.println(Kvadratki(h,w,k));
	}
}
