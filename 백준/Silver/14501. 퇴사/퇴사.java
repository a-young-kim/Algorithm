import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * N + 1에서 퇴사를 하기 위해  남은 N일 동안 최대한 많은 상담을 한다. 
 * 각각의 상담은 상담을 완료하는데 걸리는 기간 T와 
 * 상담을 했을 때 받을 수 있는 금액 P
 * 모든 상담을 할 수는 없다. 
 * 상담을 적절히 했을 때 백준이 얻ㅇ르 숭 ㅣㅆ는 수익을 구하는 프로그램
 * 
 * 입력
 * 첫째 줄 N 15이하
 * 
 * 출력
 * 최대 이익
 * 
 * 풀이과정
 * 완탐
 * dfs
 */
public class Main {
	
	static int N, answer;
	static int[][] array;
	static boolean[] vistied;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		array = new int[N][2];
		vistied = new boolean[N];

		for(int i = 0;i < N; i++) {
			st = new StringTokenizer(br.readLine());
			array[i][0] = Integer.parseInt(st.nextToken());
			array[i][1] =  Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		System.out.println(answer);
	}
	
	public static void dfs(int day, int price) {
		if(day == N) {
			answer = Math.max(answer, price);
			return;
		}
		
		if(day > N) {
			return;
		}
		
		for(int i = day; i < N; i++) {
			if(vistied[i]) continue;
			vistied[i] = true;
			dfs(i + array[i][0],  price + array[i][1]);
			answer = Math.max(answer, price);
			vistied[i] = false;
		}
	}
}