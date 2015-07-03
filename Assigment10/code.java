import java.util.*;

public class code {

	static int stStolpcev;
	static int[] tipi;
	static int[] tipiUreditve;
	static int[] urejaj;

	// izpis
	private static void izpisi(String[][] array, int izpisVrstice,
			int izpisStolpca) {
		System.out.println(array[izpisVrstice - 1][izpisStolpca - 1]);
	}
	
	private static boolean primerjaj(String prvi, String drugi, int ukaz, int primerjava) {
		boolean vrednost = false;
		if(primerjava == 0) {
			if(ukaz == 0) {
				if(Integer.parseInt(prvi) == Integer.parseInt(drugi)) {
					vrednost = true;
				}
			}
			else if(ukaz == 1) {
				if(Integer.parseInt(prvi) > Integer.parseInt(drugi)) {
					vrednost = true;
				}
			}
			else {
				if(Integer.parseInt(prvi) < Integer.parseInt(drugi)) {
					vrednost = true;
				}
			}
		}
		else {
			if(prvi.compareTo(drugi) == ukaz) {
				vrednost = true;
			}
		}
		return vrednost;
	}

	private static String[][] stolpci(String[][] array, int zacetek, int konec) {
		// st stolpcev nove tabele
		int stNovihStolpcev = konec - zacetek + 1;
		int stVrstic = array.length;

		String[][] novaTabela = new String[stVrstic][stNovihStolpcev];

		// prepis vrednosti iz stare tabele
		for (int i = 0; i < stVrstic; i++) {
			for (int x = zacetek - 1; x < konec; x++) {
				novaTabela[i][x - zacetek + 1] = array[i][x];
			}
		}
		stStolpcev = stNovihStolpcev;
		return novaTabela;
	}

	private static String[][] filtriraj(String[][] array, int ukaz,
			int stolpec, int vrstica) {

		int stNovihVrstic = stNovihVrstic(array, ukaz, stolpec, vrstica);
		String[][] novaTabela = new String[stNovihVrstic][stStolpcev];
		// Naredi novo tabelo
		int a = 0;
		int tip = tipi[stolpec-1];
		String drugi = array[vrstica-1][stolpec-1];
		for (int i = 0; i < array.length; i++) {
			String prvi = array[i][stolpec-1];
			if(primerjaj(prvi, drugi,ukaz,tip) == true) {
					novaTabela[a] = array[i];
					a++;
			}
		}
		return novaTabela;
	}

	// Ugotovi koliko novih vrstic bo imela nova tabela
	private static int stNovihVrstic(String[][] array, int ukaz, int stolpec,
			int vrstica) {
		int stNovihVrstic = 0;
		int tip = tipi[stolpec-1];
		
		for(int i = 0; i < array.length; i++) {
			String prvi = array[i][stolpec-1];
			String drugi = array[vrstica-1][stolpec-1];
			if(primerjaj(prvi,drugi,ukaz,tip) == true) {
				stNovihVrstic++;
			}
		}
		return stNovihVrstic;
	}
	
	private static String[][] uredi(String[][] array, int stolpec, int tip) {
		
		
		if(stolpec > 0) {
			for(int i = 0; i < array.length-1; i++) {
				for(int x = 0; x < stStolpcev; x++) {
					String prvi = array[i][stolpec-1];
					String drugi = array[i][x];
					if(primerjaj(prvi,drugi, tip, 1) == true) {
						String[] temp = array[i];
						array[i] = array[i+1];
						array[i+1] = temp;
					}
				}
			}		
		}
		else {
			for(int i = 0; i < array.length-1; i++) {
				for(int x = 0; x < stStolpcev; x++) {
					String prvi = array[i][Math.abs(stolpec)-1];
					String drugi = array[i][x];
					if(primerjaj(prvi,drugi, tip, -1) == true) {
						String[] temp = array[i];
						array[i] = array[i+1];
						array[i+1] = temp;
					}
				}
			}
		}
		return array;
	}
	
	private static int[] stolpciTipi(int[] tipi, int zacetek, int konec) {
		int stNovihTipov = konec - zacetek + 1;
		int[] noviTipi = new int[stNovihTipov];
		for(int i = zacetek-1; i < konec; i++) {
			noviTipi[i - zacetek + 1] = tipi[i];
		}
		
		
		return noviTipi;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int stVrstic = sc.nextInt();
		stStolpcev = sc.nextInt();

		tipi = new int[stStolpcev];
		String[][] tabela = new String[stVrstic][stStolpcev];

		for (int i = 0; i < stStolpcev; i++) {
			tipi[i] = sc.nextInt();
		}

		// naredi tabelo
		for (int i = 0; i < stVrstic; i++) {
			for (int x = 0; x < stStolpcev; x++) {
				tabela[i][x] = sc.next();
			}
		}
		//System.out.println(Arrays.deepToString(tabela));

		// prebere ukaze
		int ukazi = sc.nextInt();
		for (int i = 0; i < ukazi; i++) {
			String ukaz = sc.next();
			// System.out.println(ukaz);
			// tabela od stolpca(zacetek) do stolpca(konec)
			if (ukaz.equals("stolpci")) {
				int zacetek = sc.nextInt();
				int konec = sc.nextInt();
				tabela = stolpci(tabela, zacetek, konec);
				tipi = stolpciTipi(tipi, zacetek, konec);
				//System.out.println(Arrays.deepToString(tabela));
			} else if (ukaz.equals("filtriraj")) {
				int stolpec = sc.nextInt();
				int argument = sc.nextInt();
				int vrstica = sc.nextInt();
				//System.out.println(tipi[stolpec - 1]);
				//int a = stNovihVrstic(tabela, argument, stolpec, vrstica);
				//System.out.println(a);
				tabela = filtriraj(tabela, argument, stolpec, vrstica);
				//System.out.println(Arrays.deepToString(tabela));
			}
			else if (ukaz.equals("uredi")) {
				int steviloUreditev = sc.nextInt();
				tipiUreditve = new int[steviloUreditev];
				urejaj = new int[steviloUreditev];
				for(int x = 0; x < steviloUreditev; x++) {
					int stolpecUredi = sc.nextInt();
					int tip = tipi[Math.abs(stolpecUredi) -1];
					tipiUreditve[x] = tip;
					urejaj[x] = stolpecUredi;
					//System.out.println(Arrays.deepToString(tabela));
				}
				for(int x = steviloUreditev-1; x >= 0; x--) {
					tabela = uredi(tabela, urejaj[x], tipiUreditve[x]);
				}
			}
		}

		//System.out.println(Arrays.deepToString(tabela));
		int izpisVrstice = sc.nextInt();
		int izpisStolpca = sc.nextInt();

		izpisi(tabela, izpisVrstice, izpisStolpca);

	}

}
