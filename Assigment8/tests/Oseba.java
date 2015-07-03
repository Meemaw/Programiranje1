
// DN08_63140258
// (zamenjajte XXXXXXXX z va"so vpisno "stevilko)

public class Oseba {
	
	public  String ip;
	public char spol;
	public int starost;

    // ustrezno dopolnite/popravite ...
    public Oseba(String ip, char spol, int starost) {
    	this.ip = ip;
    	this.spol = spol;
    	this.starost = starost;
    }
    
    // ustrezno dopolnite/popravite ...
    public String toString() {
        return String.format("%s, %s, %d", ip, spol, starost);
    }

    // ustrezno dopolnite/popravite ...
    public boolean jeStarejsaOd(Oseba os) {
    	if(this.starost > os.starost) {
    		return true;
    	}
    	return !true;

   }
}
