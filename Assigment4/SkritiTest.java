
import java.util.*;
import java.lang.reflect.*;
import java.io.*;

public class SkritiTest {

    private static final File VHOD = new File("vhod.txt");
    private static final File KLICI = new File("klici.txt");
    private static final File IZHOD = new File("izhod.txt");
    private static final double EPSILON = 1e-6;
    private static final double FAKTOR_NATANCNOSTI = 1000.0;

    // oznaka tipa --> tip
    private static final Map<Character, Class> ZNAK_2_RAZRED = new HashMap<>();

    static {
        ZNAK_2_RAZRED.put('T', Tocka.class);
        ZNAK_2_RAZRED.put('P', Premica.class);
        ZNAK_2_RAZRED.put('S', java.lang.String.class);
        ZNAK_2_RAZRED.put('d', double.class);
    }

    public static void main(String[] args) {
        SkritiTest str = new SkritiTest();
        str.preberiVhod();
        str.preberiIzhod();
        str.izvediKlice();
    }

    /** testne to"cke */
    private Tocka[] tocke;

    /** testne premice */
    private Premica[] premice;

    /** vrstice pri"cakovanega izhoda */
    private List<String> izhod;

    /** datoteka VHOD --> this.tocke, this.premice */
    public void preberiVhod() {
        try (Scanner sc = new Scanner(VHOD)) {
            int stTock = Integer.parseInt(sc.nextLine());
            this.tocke = new Tocka[stTock];
            for (int i = 0;  i < stTock;  i++) {
                this.tocke[i] = dekodirajTocko(sc.nextLine());
            }

            int stPremic = Integer.parseInt(sc.nextLine());
            this.premice = new Premica[stPremic];
            for (int i = 0;  i < stPremic;  i++) {
                this.premice[i] = dekodirajPremico(sc.nextLine());
            }
            sc.close();

        } catch (IOException ex) {
            System.err.println("Napaka pri odpiranju/branju datoteke " + VHOD);
        }
    }

    /** datoteka IZHOD --> this.izhod */
    public void preberiIzhod() {
        this.izhod = new ArrayList<>();
        try (Scanner sc = new Scanner(IZHOD)) {
            while (sc.hasNextLine()) {
                String vrstica = sc.nextLine().trim();
                if (!vrstica.isEmpty() && !vrstica.startsWith("#")) {
                    this.izhod.add(vrstica);
                }
            }
            sc.close();

        } catch (IOException ex) {
            System.err.println("Napaka pri odpiranju/pisanju datoteke " + IZHOD);
        }
    }

    /** Izvr"si vsak klic v datoteki KLICI in dobljeni rezultat primerja s
     * pri"cakovanim.  Vrne "stevilo pravilno izvr"senih klicev. */
    public void izvediKlice() {
        try (Scanner sc = new Scanner(KLICI)) {

            int ix = 0;
            int stPravilnih = 0; 
            while (sc.hasNextLine()) {
                String klic = sc.nextLine().trim();
                if (!klic.startsWith("#") && !klic.isEmpty()) {
                    boolean izid = this.izvediEnKlic(klic, ix);
                    stPravilnih += izid ? 1 : 0;
                    System.out.printf("%-35s --> %s%n", klic, izid ? "OK" : "napaka");
                    ix++;
                }
            }
            System.out.println(stPravilnih);
            sc.close();

        } catch (IOException ex) {
            System.err.println("Napaka pri odpiranju/branju datoteke " + KLICI);
        }
    }

    /** Izvr"si podani klic v datoteki KLICI in dobljeni rezultat primerja s
     * pri"cakovanim.  Vrne true natanko v primeru ujemanja. 
     * @param klic v datoteki KLICI
     * @param ixIzhoda indeks pripadajo"ce vrstice v datoteki IZHOD
     */
    private boolean izvediEnKlic(String klic, int ixIzhoda) {
        try {
            String[] komponente = klic.split("\\s+");

            Class razred = ZNAK_2_RAZRED.get(komponente[0].charAt(0));
            List<Class> tipi = new ArrayList<>();
            String strTipi = komponente[2].substring(1, komponente[2].length()-1);
            for (int i = 0;  i < strTipi.length();  i++) { 
                tipi.add(ZNAK_2_RAZRED.get(strTipi.charAt(i)));
            }

            Method metoda = razred.getMethod(komponente[1], tipi.toArray(new Class[0]));

            Object dhis = this.dekodirajParameter(komponente[3]);
            List<Object> parametri = new ArrayList<>();
            for (int i = 4;  i < komponente.length - 1;  i++) {
                parametri.add(this.dekodirajParameter(komponente[i]));
            }
            Object dobljeniRezultat = metoda.invoke(dhis, parametri.toArray());

            char tip = komponente[komponente.length - 1].charAt(1);
            Object pricakovaniRezultat = dekodirajObjekt(this.izhod.get(ixIzhoda));
            return rezultatSeUjema(tip, dobljeniRezultat, pricakovaniRezultat);

        } catch (Exception ex) {
            return false;
        }
    }

    /** Vrne vrednost parametra klica metode. */
    private Object dekodirajParameter(String parameter) {
        char prviZnak = parameter.charAt(0);
        String ostalo = parameter.substring(1);

        switch (prviZnak) {
            case '_':
                return null;

            case 'd':
                return dekodirajUlomek(ostalo);

            case 't':
                return this.tocke[Integer.parseInt(ostalo)];

            case 'p':
                return this.premice[Integer.parseInt(ostalo)];
        }
        throw new RuntimeException("Napaka pri dekodiranju parametra");
    }

    /** Vrne true natanko v primeru, "ce je dobljeni rezultat ``pribli"zno''
     * enak pri"cakovanemu. 
     * @param tip tip rezultata (d, S, T ali P)
     */
    private boolean rezultatSeUjema(char tip, Object dobljeniRezultat, Object pricakovaniRezultat) {
        switch (tip) {
            case 'd':
                return doubleSeUjema((Double) dobljeniRezultat, (Double) pricakovaniRezultat);

            case 'S':
                return dobljeniRezultat.toString().equals(pricakovaniRezultat.toString());

            case 'T':
                if (dobljeniRezultat == null || pricakovaniRezultat == null) {
                    return dobljeniRezultat == pricakovaniRezultat;
                }
                double xd = ((Tocka) dobljeniRezultat).vrniX();
                double yd = ((Tocka) dobljeniRezultat).vrniY();
                double xp = ((Tocka) pricakovaniRezultat).vrniX();
                double yp = ((Tocka) pricakovaniRezultat).vrniY();
                return doubleSeUjema(xd, xp) && doubleSeUjema(yp, yd);

            case 'P':
                if (dobljeniRezultat == null || pricakovaniRezultat == null) {
                    return dobljeniRezultat == pricakovaniRezultat;
                }
                double kd = ((Premica) dobljeniRezultat).vrniK();
                double nd = ((Premica) dobljeniRezultat).vrniN();
                double kp = ((Premica) pricakovaniRezultat).vrniK();
                double np = ((Premica) pricakovaniRezultat).vrniN();
                return doubleSeUjema(kd, kp) && doubleSeUjema(np, nd);
        }
        throw new RuntimeException("Neznani tip: " + tip);
    }

    private boolean doubleSeUjema(double dobljeniRezultat, double pricakovaniRezultat) {
        if (Math.abs(dobljeniRezultat) < EPSILON) {
            return Math.abs(pricakovaniRezultat) < EPSILON;
        }
        if (Math.abs(pricakovaniRezultat) < EPSILON) {
            return Math.abs(dobljeniRezultat) < EPSILON;
        }
        double faktor = Math.pow(10, (int) -Math.log10(dobljeniRezultat));
        int dobljeno = (int) Math.round(dobljeniRezultat * faktor * FAKTOR_NATANCNOSTI);
        int pricakovano = (int) Math.round(pricakovaniRezultat * faktor * FAKTOR_NATANCNOSTI);
        return (dobljeno == pricakovano);
    }

    private static Object dekodirajObjekt(String niz) {
        if (niz.equals("null")) {
            return null;
        }
        char tip = niz.charAt(0);
        switch (tip) {
            case 'T':
                return dekodirajTocko(niz);
            case 'P':
                return dekodirajPremico(niz);
            case 'd':
                return dekodirajUlomek(niz);
            case 'S':
                return dekodirajNiz(niz);
        }
        throw new RuntimeException("Neznani tip: " + tip);
    }

    // Tx,y --> Tocka(x, y)
    private static Tocka dekodirajTocko(String niz) {
        if (niz.charAt(0) == 'T') {
            niz = niz.substring(1);
        }
        String[] si = niz.split(",");
        return new Tocka(dekodirajUlomek(si[0]), dekodirajUlomek(si[1]));
    }

    // Pk,n --> Premica(k, n)
    private static Premica dekodirajPremico(String niz) {
        if (niz.charAt(0) == 'P') {
            niz = niz.substring(1);
        }
        String[] si = niz.split(",");
        return new Premica(dekodirajUlomek(si[0]), dekodirajUlomek(si[1]));
    }

    // "d-15000/1000000" --> -0.015
    private static double dekodirajUlomek(String niz) {
        if (niz.charAt(0) == 'd') {
            niz = niz.substring(1);
        }
        String[] si = niz.split("/");
        return ((double) Integer.parseInt(si[0])) / ((double) Integer.parseInt(si[1]));
    }

    private static String dekodirajNiz(String niz) {
        if (niz.charAt(0) == 'S') {
            return niz.substring(1);
        }
        return niz;
    }
}
