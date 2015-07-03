
public class Test5 {

    public static void main(String[] args) {
        Oseba o = new Oseba("Eva Erjavec", 'Z', 23);
        TestSkupno.preveri(o.toString().equals("Eva Erjavec, Z, 23"));
    }
}
