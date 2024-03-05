import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 2진 수열을 위한 0과 1이 적혀진 낱장의 타일
 * 2. 현재 1하나만으로 이루어진 타일 또는 0타일이 두개 붙인 한쌍의 타일들만 남게 되었다. 
 * 3. N이 주어졌을 때 지원이가 만들 수 있는 모든 가짓 수를 세 보시오
 * 
 * 입력
 * 1. 자연 수 N 백만 이하
 * 
 * 출력
 * 1. N % 15746
 * 
 * 풀이
 * 1. N을 입력으로 받음
 * 2. dp[N] = dp[N - 1] + d[N - 2];
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		if(N == 1) {
			System.out.println(1);
			return;
		}
		int[] dp = new int[N + 1];
		
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i < N + 1; i ++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
		}
		
		System.out.println(dp[N]);
	}

}