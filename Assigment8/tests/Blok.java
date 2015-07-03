import java.util.*;
// DN08_63140258
// (zamenjajte XXXXXXXX z va"so vpisno "stevilko)

public class Blok {
	private List<Stanovanje> stanovanja;

	class Par {
		private int x;
		private int y;
		private Stanovanje s;

		public Par(int x, int y, Stanovanje s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}

	public class Tocka {
		private int xMax = 0;
		private int xMin = 0;
		private int yMax = 0;
		private int yMin = 0;

		public Tocka(int xMax, int xMin, int yMax, int yMin) {
			this.xMax = xMax;
			this.xMin = xMin;
			this.yMax = yMax;
			this.yMin = yMin;
		}

	}

	Tocka vrniMeje(Set<Par> vsaStanovanja) {
		int tempxMax = 0;
		int tempxMin = 0;
		int tempyMax = 0;
		int tempyMin = 0;
		for (Par stanovanje : vsaStanovanja) {
			tempxMax = Math.max(stanovanje.x, tempxMax);
			tempxMin = Math.min(stanovanje.x, tempxMin);
			tempyMax = Math.max(stanovanje.y, tempyMax);
			tempyMin = Math.min(stanovanje.y, tempyMin);
		}
		return new Tocka(tempxMax, tempxMin, tempyMax, tempyMin);
	}

	Set<Par> kordinirajStanovanja(Stanovanje e) {
		Stack<Par> nepregledano = new Stack<Par>();
		Set<Par> vsaStanovanja = new HashSet<Par>();
		Set<Stanovanje> obiskana = new HashSet<Stanovanje>();
		Par t = new Par(0, 0, e);

		nepregledano.add(t);
		vsaStanovanja.add(t);
		obiskana.add(e);

		while (!nepregledano.isEmpty()) {
			Par current = nepregledano.pop();
			for (Stanovanje stanovanje : current.s.sosednjaStanovanja()) {
				if (stanovanje != null && !obiskana.contains(stanovanje)) {
					obiskana.add(stanovanje);
					if (stanovanje == current.s.desni) {
						t = new Par(current.x + 1, current.y, stanovanje);
						vsaStanovanja.add(t);
						nepregledano.add(t);
					}
					if (stanovanje == current.s.levi) {
						t = new Par(current.x - 1, current.y, stanovanje);
						vsaStanovanja.add(t);
						nepregledano.add(t);
					}
					if (stanovanje == current.s.zgornji) {
						t = new Par(current.x, current.y - 1, stanovanje);
						vsaStanovanja.add(t);
						nepregledano.add(t);
					}
					if (stanovanje == current.s.spodnji) {
						t = new Par(current.x, current.y + 1, stanovanje);
						vsaStanovanja.add(t);
						nepregledano.add(t);
					}

				}
			}
		}
		return vsaStanovanja;
	}

	// ustrezno dopolnite/popravite ...
	public Blok(Stanovanje stanovanje) {
		Stack<Stanovanje> nepreverjena = new Stack<>();
		List<Stanovanje> vsaStanovanja = new ArrayList<>();

		vsaStanovanja.add(stanovanje);
		nepreverjena.add(stanovanje);

		while (!nepreverjena.isEmpty()) {
			Stanovanje s = nepreverjena.pop();
			for (Stanovanje stanovanje2 : s.sosednjaStanovanja()) {
				if (stanovanje2 != null && !vsaStanovanja.contains(stanovanje2)) {
					vsaStanovanja.add(stanovanje2);
					nepreverjena.add(stanovanje2);
				}
			}

		}

		stanovanja = vsaStanovanja;
	}

	// ustrezno dopolnite/popravite ...
	public Oseba starosta() {
		if (stanovanja.size() == 0) {
			return null;
		} else {
			Oseba starosta = this.stanovanja.get(0).starosta();
			for (Stanovanje stanovanje : stanovanja) {
				Oseba a = stanovanje.starosta();
				if (a == null)
					continue;
				if (starosta == null || a.starost > starosta.starost) {
					starosta = a;
				}
			}
			return starosta;

		}
	}

	// ustrezno dopolnite/popravite ...
	public int[][] razporeditev() {
		Set<Par> kordiniranaStanovanja = kordinirajStanovanja(this.stanovanja
				.get(0));
		Tocka t = vrniMeje(kordiniranaStanovanja);
		int visina = t.yMax - t.yMin + 1;
		int sirina = t.xMax - t.xMin + 1;
		int premakniY = Math.abs(t.yMin);
		int premakniX = Math.abs(t.xMin);
		int[][] matrika = new int[visina][sirina];
		for (int i = 0; i < visina; i++) {
			for (int x = 0; x < sirina; x++) {
				matrika[i][x] = -1;
			}
		}
		for (Par stanovanje : kordiniranaStanovanja) {
			stanovanje.x += premakniX;
			stanovanje.y += premakniY;
			matrika[stanovanje.y][stanovanje.x] = stanovanje.s
					.steviloStanovalcev();
		}

		return matrika;
	}
}
