import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 3 * N 크기의 벽을 2 * 1, 1 * 2 크기의 타일로 채우느 ㄴ경우의 수
 * 
 * 입력
 * 첫째 줄에 N
 * 
 * 출력
 * 모든 경우의 수 출력
 * 
 * 풀이 방식
 * dp
 * i 가 홀 수 이면 무조건 0
 */
public class Main {
	
	static int N;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		dp = new int[40];
		
		dp[0] = 1;
		dp[2] = 3;
		dp[4] = 11;
		for(int i = 4; i < N + 1; i = i + 2) {
			dp[i]=dp[i-2]*4-dp[i-4];
		}
		System.out.println(dp[N]);
	}
}