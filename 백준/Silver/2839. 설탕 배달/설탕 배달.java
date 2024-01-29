import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int cnt5 = N / 5;
		int cnt3 = N % 5 / 3;
		int answer = -1;
		
		while(cnt5 >= 0) {			
			if(N == (cnt5 * 5 + cnt3 * 3)) {
				answer = cnt5 + cnt3;
				break;
			}
			
			cnt5 --;
			if(cnt5 != 0) cnt3 = (N - 5 * cnt5) / 3;
			else cnt3 = N / 3;
			
		}
		System.out.println(answer);
		sc.close();
	}
}