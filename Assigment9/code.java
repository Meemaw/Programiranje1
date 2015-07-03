import java.util.*;
import java.lang.*;

public class code{

	static class PodatkovniTip {
		private String opis = "";
		private int velikost = 0;
		private int b;

		public PodatkovniTip(int b) {
			this.b = b;
		}

		int roundUp() {
			return (velikost + b - 1) / b * b;
		}

		String izpis(int ukaz) {
			if (ukaz == 1)
				return opis;
			else if (ukaz == 2)
				return "" + velikost;
			else
				return "" + roundUp();
		}
	}

	static class Kalkulator {
		int b;
		Scanner sc;

		public Kalkulator(int b, Scanner sc) {
			this.b = b;
			this.sc = sc;
		}

		private PodatkovniTip rekurzija() {

			String strukt = sc.next();
			PodatkovniTip t = new PodatkovniTip(b);
			if (strukt.equals("prim")) {
				int vrednost = sc.nextInt();
				t.velikost += vrednost;
				t.opis += String.format("prim%d", vrednost);
			}
			if (strukt.equals("arr")) {
				int steviloElementov = sc.nextInt();
				PodatkovniTip rezultat = rekurzija();
				t.velikost += steviloElementov * rezultat.velikost;
				t.opis += String.format("%s[%d]", rezultat.opis,
						steviloElementov);
			}
			if (strukt.equals("pstruct")) {
				String stevilka = sc.next();
				int steviloTipov = Integer.parseInt(stevilka);
				t.opis += "pstruct{";
				for (int i = 0; i < steviloTipov; i++) {
					PodatkovniTip rezultat = rekurzija();
					t.velikost += rezultat.velikost;
					t.opis += String.format("%s, ", rezultat.opis);
				}
				t.opis = t.opis.substring(0, t.opis.length() - 2);
				t.opis += "}";
			}
			if (strukt.equals("ostruct")) {
				int steviloTipov = sc.nextInt();
				t.opis += "ostruct{";
				for (int i = 0; i < steviloTipov; i++) {
					PodatkovniTip rezultat = rekurzija();
					t.velikost += rezultat.roundUp();
					t.opis += String.format("%s, ", rezultat.opis);
				}
				t.opis = t.opis.substring(0, t.opis.length() - 2);
				t.opis += "}";
			}
			return t;
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int velikostPomBesede = sc.nextInt();
		int steviloDeklaracij = sc.nextInt();
		Kalkulator kalk = new Kalkulator(velikostPomBesede, sc);

		List<PodatkovniTip> rezultati = new ArrayList<PodatkovniTip>();
		for (int i = 0; i < steviloDeklaracij; i++) {
			PodatkovniTip rezultat = kalk.rekurzija();
			rezultati.add(rezultat);
		}

		int steviloUkazov = sc.nextInt();
		for (int i = 0; i < steviloUkazov; i++) {
			int ukaz = sc.nextInt();
			int vrsticaIzpisa = sc.nextInt() - 1;
			String izpis = rezultati.get(vrsticaIzpisa).izpis(ukaz);
			System.out.println(izpis);
		}

		sc.close();

	}

}