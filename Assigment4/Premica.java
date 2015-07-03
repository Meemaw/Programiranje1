// DN04_63140258

public class Premica {

	private double k;
	private double n;

	private static double zacetnaVrednost(double k, Tocka t) {
		return t.vrniY() - (k * t.vrniX());
	}

	public Premica(double k, double n) {
		this.k = k;
		this.n = n;
	}

	public double vrniK() {
		return this.k;
	}

	public double vrniN() {
		return this.n;
	}

	public String toString() {
		return String.format("y = %.2f x + %.2f", this.k, this.n);
	}

	public Tocka tockaPriX(double x) {
		double kordinataY = k * x + n;
		return new Tocka(x, kordinataY);
	}

	public static Premica skoziTocko(double k, Tocka t) {
		return new Premica(k, zacetnaVrednost(k, t));
	}

	public Premica vzporednica(Tocka t) {
		return new Premica(k, zacetnaVrednost(k, t));
	}

	public Premica pravokotnica(Tocka t) {
		double koeficient = (-1 / this.k);
		return new Premica(koeficient, zacetnaVrednost(koeficient, t));
	}

	public Tocka presecisce(Premica p, double epsilon) {
		if (Math.abs(p.k - this.k) < epsilon) {
			return null;
		}
		double kordinataX = (p.n - this.n) / (this.k - p.k);
		return tockaPriX(kordinataX);
	}

	public Tocka projekcija(Tocka t) {
		double koeficient = (-1 / this.k);
		double kordinataX = (zacetnaVrednost(koeficient, t) - this.n)
				/ (this.k - koeficient);
		return tockaPriX(kordinataX);
	}

	public double razdalja(Tocka t) {
		Tocka proj = projekcija(t);
		return Math.sqrt(Math.pow(t.vrniX() - proj.vrniX(), 2)
				+ Math.pow(t.vrniY() - proj.vrniY(), 2));

	}

	public double razdaljaOdIzhodisca() {
		Tocka t = new Tocka(0.0, 0.0);
		return razdalja(t);

	}

	public double razdalja(double n) {
		double kot = Math.atan(this.k);
		double fi = Math.PI / 2 - kot;
		double razlikaN = Math.abs(n - this.n);
		double razdalja = razlikaN * Math.sin(fi);
		return razdalja;
	}
}
