import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 정수 N이 주어졌을 때 n을 1, 2, 3합으로 나타내는 방법의 수
 * 
 * 입력
 * 1. 테스트 케이스의 개수 T
 * 2. 한줄로 이루어져 있고, 정수 N이 주어진다. N은 11이하
 * 
 * 출력
 * 각 케이스마다 n을 1, 2, 3의 합으로 나타내는 방법의 수
 * 
 * 풀이 
 * DP
 * dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		int[] dp = new int[12];
		dp[1] = 1;
		dp[2] = dp[1] + 1;
		dp[3] = dp[1] + dp[2] + 1;
		for(int i = 4; i < 12; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}
		
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			System.out.println(dp[N]);
		}

	}

}