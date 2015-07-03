import java.awt.Point;
import java.util.*;

public class code {

	public static short[][] matrikaEnic(short[][] matrika, String[] vrstice,
			int visina, int sirina) {
		for (int i = 0; i < vrstice.length; i++) {
			for (int x = 0; x < sirina; x++) {
				if (vrstice[i].charAt(x) == '1') {
					matrika[i][x] = 1;
				}

			}
		}
		return matrika;
	}
	
	public static void izbrisiMolekulo(int i, int j, short[][] matrika){
		Stack<Point> stack = new Stack<Point>();
		stack.push(new Point(i, j));
		while(!stack.isEmpty()){
			Point current = stack.pop();
			int x = current.x;
			int y = current.y;
			
			if(x - 1 >= 0 && matrika[x-1][y] == 1){
				matrika[x-1][y] = 0;
				stack.push(new Point(x-1, y));
			}
			if(x + 1 < matrika.length && matrika[x+1][y] == 1){
				matrika[x+1][y] = 0;
				stack.push(new Point(x+1, y));
			}
			if(y - 1 >= 0 && matrika[x][y-1] == 1){
				matrika[x][y-1] = 0;
				stack.push(new Point(x, y-1));
			}
			if(y + 1 < matrika[0].length && matrika[x][y+1] == 1){
				matrika[x][y+1] = 0;
				stack.push(new Point(x, y+1));
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int visina = sc.nextInt();
		int sirina = sc.nextInt();
		short[][] matrika = new short[visina][sirina];
		String[] vrstice = new String[visina];
		for (int i = 0; i < visina; i++) {
			vrstice[i] = sc.next();
		}
		int sum = 0;
		matrika = matrikaEnic(matrika, vrstice, visina, sirina);
		for(int i = 0; i < visina; i++){
			for(int y = 0; y < sirina; y++){
				if(matrika[i][y] == 1){
					sum++;
					izbrisiMolekulo(i, y, matrika);
				}
			}
		}

		System.out.println(sum);
	}

}