import java.util.Scanner;

public class Main {
	static int N;
	static int[] arrayNum;
	static int max = -10000;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arrayNum = new int[N];
		
		for(int i = 0; i < N; i++) {
			arrayNum[i] = sc.nextInt();
			max = Math.max(max, arrayNum[i]);
		}
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			if(i != 0 && sum <= 0) sum = 0;
			sum += arrayNum[i];
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
		sc.close();
	}
}