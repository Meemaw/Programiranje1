import java.util.*;

// DN08_63140258
// (zamenjajte XXXXXXXX z va"so vpisno "stevilko)

public class Stanovanje {

	public Oseba[] stanovalci;
	public Stanovanje levi, desni, zgornji, spodnji;

	// ustrezno dopolnite/popravite ...
	public Stanovanje(Oseba[] stanovalci) {
		this.stanovalci = stanovalci;
	}

	// ustrezno dopolnite/popravite ...
	public int steviloStanovalcev() {
		return stanovalci.length;
	}

	// ustrezno dopolnite/popravite ...
	public int steviloStarejsihOd(Oseba os) {
		int starejsih = 0;
		for (int i = 0; i < stanovalci.length; i++) {
			if (stanovalci[i].starost > os.starost) {
				starejsih++;
			}
		}
		return starejsih;
	}

	// ustrezno dopolnite/popravite ...
	public int[] mz() {
		int[] tabela = new int[2];
		for (int i = 0; i < stanovalci.length; i++) {
			// tabela[stanovalci[i].spol == 'M' ? 0 : 1]++;
			if (stanovalci[i].spol == 'M') {
				tabela[0]++;
			}
			if (stanovalci[i].spol == 'Z') {
				tabela[1]++;
			}
		}
		return tabela;
	}

	// ustrezno dopolnite/popravite ...
	public Oseba starosta() {
		Oseba s;
		if (this.stanovalci.length == 0) {
			return null;
		} else {
			s = stanovalci[0];
			for (Oseba st : stanovalci)
				if (st.starost > s.starost) {
					s = st;
				}
		}
		return s;
	}

	// ustrezno dopolnite/popravite ...
	public void nastaviSosede(Stanovanje levi, Stanovanje zgornji,
			Stanovanje desni, Stanovanje spodnji) {
		this.levi = levi;
		this.desni = desni;
		this.zgornji = zgornji;
		this.spodnji = spodnji;
	}

	// ustrezno dopolnite/popravite ...
	public Oseba starostaSosescine() {
		Oseba starosta = this.starosta();

		for (Stanovanje stanovanje : sosednjaStanovanja()) {
			if (stanovanje != null) {
				Oseba a = stanovanje.starosta();
				if (a == null)
					continue;
				if (starosta == null || a.starost > starosta.starost) {
					starosta = a;
				}
			}
		}
		return starosta;

	}

	Stanovanje[] sosednjaStanovanja() {
		return new Stanovanje[] { levi, desni, spodnji, zgornji };
	}


	 List<Oseba> sosedi() {
		List<Oseba> sosedi = new ArrayList<>();
		for (Stanovanje stanovanje : sosednjaStanovanja()) {
			if (stanovanje != null) {
				sosedi.addAll(Arrays.asList(stanovanje.stanovalci));
			}

		}
		return sosedi;
	}

	// ustrezno dopolnite/popravite ...
	public Oseba[] sosedjeSosedov() {
		Set<Oseba> sosedjeSosedov = new HashSet<>();
		for (Stanovanje stanovanje : sosednjaStanovanja()) {
			if (stanovanje == null)
				continue;
			for (Stanovanje ss : stanovanje.sosednjaStanovanja()) {
				if (ss != this && ss != null) {
					sosedjeSosedov.addAll(Arrays.asList(ss.stanovalci));
				}

			}
		}

		return sosedjeSosedov.toArray(new Oseba[0]);
	}
}
