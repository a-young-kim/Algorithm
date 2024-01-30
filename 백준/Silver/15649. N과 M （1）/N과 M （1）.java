
import java.util.Scanner;

/*
 * 문제
 * 1부터 N까지 자연수 중 중복 없이 M개를 고른 수열
 * 
 * 입력
 * 자연수 N, M
 * 
 * 출력
 * 한줄에 하나의 문제 조건을 만족하는 수열 출력
 * 중복 수열 출력 금지
 * 각 수열을 공백으로 구분
 * 사전 순으로 증가
 */
public class Main {
	static int N;
	static int M;
	static int[] arr;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.close();
		arr = new int[M];
		visited = new boolean[N + 1];
		f(0);
	}
	
	public static void f(int idx) {
		if(idx == M) {
			for(int i = 0; i < M; i ++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 1; i < N + 1; i ++) {
			if(visited[i]) continue;
			visited[i] = true;
			arr[idx] = i;
			f(idx + 1);
			visited[i] = false;
		}
	}
}
