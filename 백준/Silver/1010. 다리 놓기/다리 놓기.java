import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 도시를 동쪽과 서쪽으로 나누는 큰 일직선 모양의 강이 흐른다. 
 * 다리를 짓기로 했다. 
 * 강 주변에 다리를 짓기 적합한 곳은 사이트
 * 강 서쪽에는 N개의 사이트가 있고 동쪽에는 M개의 사이트가 있다 M은 항상 N보다 크거나 같아
 * 
 * 서쪽과 동쪽 사이ㅌ를 다리로 연결하려고 한다. 
 * 한사이트에서는 최대 한개의 다리만 연결될 수 었다. 
 * 다리를 최대한 많이 지으려고 한다  즉 N개의 다리
 * 다리가 서로 꼅쳐질 수 없다고 할 때 다리를 지울 수 있는 경우의 수를 구하여라
 * 
 * 입력
 * 테스트 케이스 T
 * 강 서쪽과 동쪽에 ㅇㅆ는 N , M
 * 
 * 출력
 * 경우의 수
 * 
 * 풀이 
 * M개 중에 N개를 선택하는 수
 */
public class Main {
	
	static int answer, N, M;
	static int[][] dp = new int[31][31];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < 31; i++) {
			Arrays.fill(dp[i],  Integer.MAX_VALUE);
		}
		for(int t = 0; t < T ; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			if(n > m / 2) n = m - n;
			f(m, n);
			
			System.out.println(dp[m][n]);
		}
	}
	
	public static int f(int n, int r) {
		
		if(dp[n][r] != Integer.MAX_VALUE) return dp[n][r];
		if(r > n) return  dp[n][r] = 0;
		if(r == 0) return dp[n][r] = 1;
		if(r == 1) return dp[n][r] = n;
		return dp[n][r] = f(n - 1, r - 1) + f(n - 1, r);
	}
	


}