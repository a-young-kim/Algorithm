import java.util.Scanner;
public class Main {
	
	static int answer = -1;
	static int N, r, c, setN, cnt;

	public static void visited(int startRow, int startCol, int size) {
		
		for(int i = startRow; i > startRow - size * 2; i = i - size) {
			for(int j = startCol ; j > startCol - size * 2 ; j = j - size) {
				if(i == r && j == c) {
					answer = cnt;
					return;
				}
				if(i - size < r && j - size < c) {
					size = size / 2;
					visited(i, j, size);
				}
				if(answer != -1) return;
				cnt -= size * size;
			}
		}
		
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		setN = (int) Math.pow(2, N);
		r = sc.nextInt();
		c = sc.nextInt();
		sc.close();
		int size = (setN / 2);
		cnt = setN * setN - 1;

		visited(setN - 1, setN - 1, size);
		System.out.println(answer);
	}
}