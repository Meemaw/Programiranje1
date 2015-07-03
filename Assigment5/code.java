
import java.util.Scanner;

public class code {
    
    private static final int MAKS_ST_UKAZOV = 10000;
    private static final int MAKS_VREDNOST_CELICE = 255;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] program = sc.nextLine().toCharArray();
        int[] vhod = new int[MAKS_ST_UKAZOV];
        int stVhodnihStevil = 0;
        while (stVhodnihStevil < MAKS_ST_UKAZOV && sc.hasNextInt()) {
            vhod[stVhodnihStevil++] = sc.nextInt();
        }
        izvedi(program, vhod, MAKS_ST_UKAZOV);
        sc.close();
    }
    
    private static void izvedi(char[] program, int[] vhod, int maksStUkazov) {
        
        int pc = 0;
        
        int[] trak = new int[2 * maksStUkazov + 1];
        
        int pozTrak = maksStUkazov;
        
        int pozVhod = 0;
        
        int stUkazov = 0;
        
        while (pc < program.length && stUkazov < maksStUkazov) {
            
            switch (program[pc]) {
                case '+':
                    trak[pozTrak] = plusEna(trak[pozTrak]);
                    pc++;
                    break;
                    
                case '-':
                    trak[pozTrak] = minusEna(trak[pozTrak]);
                    pc++;
                    break;
                    
                case '.':
                    System.out.println(trak[pozTrak]);
                    pc++;
                    break;
                case ',':
                    trak[pozTrak] = vhod[pozVhod++];
                    pc++;
                    break;
                    
                case '>':
                    pozTrak++;
                    pc++;
                    break;
                    
                case '<':
                    pozTrak--;
                    pc++;
                    break;
                    
                case '[':
                    if (trak[pozTrak] == 0) {
                        pc = poisciUjemajociZaklepaj(program, pc);
                    }
                    pc++;
                    break;
                    
                case ']':
                    if (trak[pozTrak] > 0) {
                        pc = poisciUjemajociPredklepaj(program, pc);
                    }
                    pc++;
                    break;
                    
                default:
                    throw new UnsupportedOperationException("Neznani ukaz: " + program[pc]);
            }
            stUkazov++;
        }
    }
    
    private static int plusEna(int st) {
        return (st == MAKS_VREDNOST_CELICE) ? (0) : (st + 1);
    }
    
    private static int minusEna(int st) {
        return (st == 0) ? (MAKS_VREDNOST_CELICE) : (st - 1);
    }
    
    private static int poisciUjemajociZaklepaj(char[] program, int pc) {
        int nivo = 1;
        while (nivo > 0) {
            pc++;
            if (program[pc] == '[') {
                nivo++;
            } else if (program[pc] == ']') {
                nivo--;
            }
        }
        return pc;
    }
    
    private static int poisciUjemajociPredklepaj(char[] program, int pc) {
        int nivo = -1;
        while (nivo < 0) {
            pc--;
            if (program[pc] == '[') {
                nivo++;
            } else if (program[pc] == ']') {
                nivo--;
            }
        }
        return pc;
    }
}
