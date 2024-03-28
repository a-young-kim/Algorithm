import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 실행시간: 5632
 * 메모리: 108380
 */
/*
 * 문제
 * 1. N은 노드 수
 * 2. CC 다른 모든 사람에게 얼마나 가까운 가
 * 	- 노드 i에서 노드 J까지의 최단 거리
 * 3. 사람을 지나면 1씩 증가
 * 4. 한 사람이 모든 사람이랑 연결되는 최소 값 수들중 최소값 출력
 * 
 * 제약사항
 * 네트워크에서 노드 자신을 연결하는 간선은 없다. 
 * 네트워크는 무향그래프
 * 최대 사용자의 수는 1000 이하
 * 사이클은 있을 수도 있고 없을 수도 있다. 
 * 
 * 입력 
 * 전체 케이스의 수 T
 *  사람 수인 양의 정수 N이 주어진 다음, 사람 네트워크의 인접 행렬이 행 우선 (row-by-row) -> 정수 N -> N * N 행렬을 일차원으로 표현
 *  
 *  출력
 *  CC 값 중 최소 값
 *  
 *  아이디어
 *  플로이드 워샬
 *  1. 각 값을 max 값으로 초기화
 *  2.dp[N][N] 배열에 연결된 값을 표시 ->
 *  3. for i를 N 만큼
 *  	for j를 N만큼
 *  		for k를 N만큼
 *  	이전 값이랑 현재 값이랑 연결된 dp[i][j]의 값을 찾기 위해  dp[i][j]와 dp[i][k] + dp[k][j]까지의 최소값
 *  4. 각 행의 최대값들을 구한 후 그 중에서 최소값을 출력
 *  
 *  풀이
 *  1. 테스트 케이서 T 입력 
 *  	- for test_case를 T + 1만큼
 *  2. int dp[N][N] 배열 생성, N * N값으로 초기화
 *  3. 사람 수의 양의 정수 N 입력 
 *  	- for i N만큼
 *  	- 	for j 만큼 -> 입력 값이 1이면 dp[i][j] = 1로 변환
 *  4. int answer = Integer.max로 초기화
 *  5. for i N만큼 -> int answer = Integer.min(answer, cnt);
 *  	for j N 만큼 -> cnt  += dp[i][j];
 *  		for k N 만큼 -> dp[i][j] = Math.min(dp[i][j] + dp[i][k] + dp[k][j]);
 *  6. answer 출력
 *  			
 *  	
 */
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb=new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st= new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int[][] dp = new int[N][N];

			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(i == j) dp[i][j] = 0;
					else if(num == 1) dp[i][j] = 1;
					else dp[i][j] = 2000;
				}
			}
			
			int answer = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				int cnt = 0;
				for(int j = 0; j < N; j++) {
					for(int k = 0; k < N; k++) {
						dp[i][j] = Math.min(dp[i][j],  dp[i][k] + dp[k][j]);
					}
					cnt += dp[i][j];
				}
				answer = Math.min(answer,  cnt);
			}
			sb.append("#" + test_case + " " + answer + "\n");
			
		}
		System.out.println(sb);
	}

}