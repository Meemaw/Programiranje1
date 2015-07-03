// DN04_63140258

public class Tocka {

	private double x;
	private double y;

	public Tocka(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double vrniX() {
		return this.x;
	}

	public double vrniY() {
		return this.y;
	}

	public String toString() {
		return String.format("(%.2f, %.2f)", this.x, this.y);
	}

	public static Tocka izhodisce() {
		return new Tocka(0.0, 0.0);
	}

	public double razdalja(Tocka t) {
		return Math.sqrt(Math.pow((this.x - t.x), 2)
				+ Math.pow(this.y - t.y, 2));
	}

	public double razdaljaOdIzhodisca() {
		return razdalja(izhodisce());
	}
}
