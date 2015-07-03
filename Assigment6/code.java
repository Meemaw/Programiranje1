
import java.util.*;

public class code {

	static class Tarca {

		public int x0;
		public int y0;

		public Tarca(int x, int y) {
			this.x0 = x;
			this.y0 = y;
		}
	}

	static class Luc {

		public int x0;
		public int y0;
		public int osvetlitev;
		public int padecOsvetlitve;

		public Luc(int x, int y, int osvetlitev, int padecOsvetlitve) {
			this.x0 = x;
			this.y0 = y;
			this.osvetlitev = osvetlitev;
			this.padecOsvetlitve = padecOsvetlitve;
		}
	}

	private static long osvetlitev(Luc[] arrayLuci, Tarca x) {
		long osvetljenost = 0;
		for (int i = 0; i < arrayLuci.length; i++) {
			long dx = (int) Math.abs(arrayLuci[i].x0 - x.x0);
			long dy = (int) Math.abs(arrayLuci[i].y0 - x.y0);
			long razdalja = dx * dx + dy * dy;
			long padec = razdalja * arrayLuci[i].padecOsvetlitve;
			if (padec < arrayLuci[i].osvetlitev) {
				osvetljenost += arrayLuci[i].osvetlitev - padec;
			}
		}
		return osvetljenost;
	}

	private static long osvetljenost(Luc[] arrayLuci, Tarca[] arrayTarc) {
		long osvetljenost;
		osvetljenost = 0;
		for (int x = 0; x < arrayTarc.length; x++) {
			osvetljenost += osvetlitev(arrayLuci, arrayTarc[x]);
		}
		return osvetljenost;
	}

	private static Luc[] luci(int p, int q, int x0, int y0, int spremembaX,
			int spremembaY, int osvetlitev, int padecOsvetlitve) {
		Luc[] arr = new Luc[p * q];
		int c = 0;
		for (int i = 0; i < p; i++) {
			for (int x = 0; x < q; x++) {
				arr[c++] = new Luc(x0 + spremembaX * i, y0 + spremembaY * x,
						osvetlitev, padecOsvetlitve);
			}
		}
		return arr;

	}

	private static Tarca[] tarce(int p, int q, int x0, int y0, int spremembaX,
			int spremembaY) {
		Tarca[] arrayTarc = new Tarca[p * q];
		int c = 0;
		for (int i = 0; i < p; i++) {
			for (int x = 0; x < q; x++) {
				arrayTarc[c++] = new Tarca(x0 + spremembaX * i, y0 + spremembaY
						* x);
			}
		}
		return arrayTarc;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long osvetljenost = 0;
		int c = 0;


		int stLuci = sc.nextInt();
		int stVrstic = sc.nextInt();
		Luc[] arrayLuci = new Luc[stLuci];
		for (int i = 0; i < stVrstic; i++) {
			int p = sc.nextInt();
			int q = sc.nextInt();
			int x0 = sc.nextInt();
			int y0 = sc.nextInt();
			int spremembaX = sc.nextInt();
			int spremembaY = sc.nextInt();
			int osvetlitev = sc.nextInt();
			int padecOsvetlitve = sc.nextInt();
			Luc[] lucke = luci(p, q, x0, y0, spremembaX, spremembaY,
					osvetlitev, padecOsvetlitve);
			for (int j = 0; j < lucke.length; j++) {
				arrayLuci[c++] = lucke[j];
			}
		}

		int h = 0;
		int stTarc = sc.nextInt();
		Tarca[] arrayTarc = new Tarca[stTarc];
		int stVrsticTarce = sc.nextInt();
		for (int i = 0; i < stVrsticTarce; i++) {
			int p = sc.nextInt();
			int q = sc.nextInt();
			int x0tarce = sc.nextInt();
			int y0tarce = sc.nextInt();
			int spremembaX = sc.nextInt();
			int spremembaY = sc.nextInt();
			Tarca[] tarce = tarce(p, q, x0tarce, y0tarce, spremembaX,
					spremembaY);
			for (int j = 0; j < tarce.length; j++) {
				arrayTarc[h++] = tarce[j];
			}
		}
		osvetljenost = osvetljenost(arrayLuci, arrayTarc);
		System.out.println(osvetljenost);

	}
}