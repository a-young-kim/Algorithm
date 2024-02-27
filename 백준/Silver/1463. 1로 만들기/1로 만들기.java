import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * X가 3으로 나누어떨어지면 3으로 나눈다.
 * X가 2로 나눈어 떨어지면 2로 나눈다
 * 1을 뺀다
 * 
 * 정수 N을 주어졌을 때 위와 같은 연산을 세개를 적절히 상요해서 1을 만들려고 한다. 
 * 
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int[] dp;
		if(N < 3) dp = new int[5];
		else dp = new int[N + 1];
		
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		for(int i = 4; i < N + 1; i ++) {
			
			if(i % 6 == 0) 
				dp[i] = Math.min(dp[i / 3], Math.min(dp[i / 2], dp[i - 1])) + 1;
			else if(i % 3 == 0) dp[i] = Math.min(dp[i / 3], dp[i - 1]) + 1;
			else if(i % 2 == 0) dp[i] = Math.min(dp[i / 2], dp[i - 1]) + 1;
			else dp[i] = dp[i - 1] + 1;
		}
		
		System.out.println(dp[N]);
	}

}