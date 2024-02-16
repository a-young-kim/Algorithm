import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 가로 두칸 세로 N 칸 우리
 * 사자는 가로로도 세로로도 붙어 있게 배치할 수 없다.
 * 
 * 사자를 배치하는 경우의 수가 몇가지인지
 * 사자를 한마리도 배치하지 않는 경우도 하나의 경우의 수
 * 
 * 입력
 * 우리의 크기 N
 * 
 * 출력
 * 경우의 수 % 9901
 * 
 * 풀이
 * 사자가 배치될 수 있는 경우 최대 N마리
 * 규칙
 * N =  (N - 1) 2 + N - 2
 * 2부터 적용
 * dp

 */
public class Main {
	
	static int N;
	static long[] dp;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		// 사자를 한마리 배치 부터 N마리 배치할 때 까지
		dp = new long[N + 1];
		dp[0] = 1;
		dp[1] = 3;
		for(int i = 2; i < N + 1; i ++) {
			dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % 9901;
		}
		
		System.out.println(dp[N]);	
	}
	
}