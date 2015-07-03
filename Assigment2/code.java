import java.util.Scanner;

public class code {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// Program izračuna koliko škatel mora skladičnik premkaniti iz vsakega
		// kupa, da pride do svoje škatle
		long p = sc.nextLong();
		long k = sc.nextLong();
		double p2 = Math.pow(p,2);
		double k2 = Math.pow(k,2);
		long d = sc.nextLong();

		long kolikokrat = 0;
		
		
		for (long i = 0; i<d; i++) {
			if (sc.hasNext()) {
				long škatla = sc.nextLong();
				// n je člen vsote aritmetičnega zaporedja pri dani vsoti(škatli)
				double n = (-2*p + k + Math.sqrt(4*p2-4*p*k+k2+8*k*škatla))/(2*k);
				// stKup je člen vsote aritmetičnega zaporedja zaokrožen navzgor
				long stKup = (long)Math.ceil(n);
				// kup je vsota aritmetičnega zaporedja pri kateri je n = ceil(n) = stKup
				long kup = stKup*(2*p+(stKup-1)*k)/2;
				// razlika med kupom in škatlo je število škatel ki jih skladiščnik
				// mora premakniti da pride do željene škatle
				long razlika = kup - škatla;
				kolikokrat += razlika;
			}
		}
		System.out.println(kolikokrat);
	}

}

