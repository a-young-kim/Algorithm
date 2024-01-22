import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int newNumber = sc.nextInt();
		int P = sc.nextInt();
		
		if(N == 0) System.out.println(1);
		else {
			int[] arrayNum = new int[N];
			for(int i = 0; i < N; i++) {
				arrayNum[i] = sc.nextInt();
			}
			
			if(N == P && arrayNum[N - 1] >= newNumber) {
				System.out.println(-1);
			}
			else if(arrayNum[N - 1] > newNumber) {
				System.out.println(N + 1);
			}
			else {
				for(int i = 0; i < N; i++) {
					if(arrayNum[i] <= newNumber) {
						System.out.println(i + 1);
						break;
					}
				}
			}
		}
		sc.close();
	}

}