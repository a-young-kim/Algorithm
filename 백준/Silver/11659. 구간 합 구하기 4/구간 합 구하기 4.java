import java.util.Scanner;

/*
 * 문제
 * 수가 N개 주어졌을 때 i번째 수부터 j 번째 수까지의 합을 구하는 프로그램
 * 
 * 입력
 * 첫째 줄: 수의 개수N 합을 구해야하는 횟수M
 * 둘째쭐: N개의 수, 수는 1000보다 작거나 같은 수
 * 셋재줄에서부터 M개의 줄에는 합을 구해야하는 구간 i, j
 * 
 * 출력
 * M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합
 * 
 * 문재 해결
 * 메모라이제이션
 */
public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] numArray = new int[N + 1];
		int[][] indexArray = new int[M][2];
		
		int sum = 0;
		for(int i = 1; i < N + 1; i++) {
			numArray[i] = sum + sc.nextInt();
			sum = numArray[i];
		}
		
		for(int i = 0; i < M; i++) {
			indexArray[i][0] = sc.nextInt();
			indexArray[i][1] = sc.nextInt();
		}
		
		for(int i = 0; i < M; i++) {
			System.out.println(numArray[indexArray[i][1]] - numArray[indexArray[i][0] - 1]);
		}
		
		sc.close();
	}
}