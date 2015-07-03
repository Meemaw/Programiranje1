
public class Test1 {

    public static void main(String[] args) {
        Oseba o = new Oseba("Alenka Avbelj", 'Z', 53);
        TestSkupno.preveri(o.toString().equals("Alenka Avbelj, Z, 53"));
    }
}
