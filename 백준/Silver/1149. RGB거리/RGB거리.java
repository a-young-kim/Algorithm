import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제
 * RGB거리에는 집이 N개 있다.
 * 거리는 선분으로 나타낼 수 있고 1번 N 번 집이 순서대로 있다.
 * 집은 빨 초 파 중 하나록 색을 칠해야한다. 
 * 각각의 집을 빨 초 파로 칠하는 비용이 주었졌을 때 다음 조건을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자
 * 
 * 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * N번 집의 색은 N - 1집의 색과 같지 않아야 한다. 
 * i 번재 잡의 색은 i - 1번 i + 1번 집의 색과 같지 않아야 한다. 
 * 
 * 입력
 * 첫째 줄에 집의 수 N 천 이하
 * 둘쨰 줄에는 각 빨 초 파랑으로 칠하는 비용
 * 집을 칠하는 비용은 1000보다 작음
 * 
 * 출력
 * 최솟값 출력
 * 
 * 풀이
 * dfs --> 시간 초과
 * dp
 */
public class Main {
	
	static int N, answer = Integer.MAX_VALUE;
	static int[][] array;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		array = new int[N][3];
		for(int i = 0;i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++){
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][3];
		dp[0][0] = array[0][0];
		dp[0][1] = array[0][1];
		dp[0][2] = array[0][2];
		
		
		for(int i = 1; i < N; i++) {
			
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + array[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + array[i][1];
			dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + array[i][2];
			
		}
		System.out.println(Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]));
	}
}