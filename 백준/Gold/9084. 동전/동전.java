import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 1. 화폐단위는 1, 5, 10, 50, 100, 500이 있다. 
 * 2. 동전의 종류가 주어질 때 주어진 금액을 만드는 모든 방법을 세는 프로그램
 * 
 * 입력
 * 1. 테스트 개수 T, 10이하
 * 2. 동전의 가지수 N, 20이하, 
 * 3. N가지 동전의 각 금액이 오름차순 정렬
 * 4. 금액은 정수로서 1원부터 10000원까지 있다. 
 * 5. 주어진 N가지 동전으로 만들어야할 금액 M 만보다 작음
 * 
 * 주의 사항
 * 같은 동전이 여러 번 존재하지 않음
 * 
 * 출력
 * N가지 동전으로 M을 만드는 모든 방법의 수
 * 
 * 풀이
 * 1. T입력 후 T만큼 for 문
 * 2. N가지 동전 입력
 * 3. dp, 각 숫자에 사용할 수 있는 개수를 저장
 */
public class Main {
	
	static int[] array, group;
	static int N, M, answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for(int test_case = 0 ; test_case < T; test_case++) {
			 st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken());
			 
			 array = new int[N];
		
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				 array[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			
			int[][] dp = new int[M + 1][N];
			
			for(int i = 1; i < M + 1; i ++) {
				for(int j = 0; j < N; j++) {
					if(i - array[j] < 0) break;
					if(i == array[j]) {
						dp[i][j] = 1;
						break;
					}
					int idx = i - array[j];
					int n = 0;
					for(int k = j ; k < N; k++) {
						n += dp[idx][k];
					}
					
					dp[i][j] = n;
				}
			}
			System.out.println(Arrays.stream(dp[M]).sum());
		}
	}
	
}