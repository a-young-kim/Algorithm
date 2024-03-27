import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제
 * 최장 부분 증가 수열
 * 
 * 입력
 * 테스트 케이스 수 T
 * 첫째 줄 수열의 길기 N 1000 이하
 * 
 * 출력
 * #T 최장 증가 수열의 길이
 * 
 * 풀이
 * 이진 검색 DP 사용
 */
public class Solution {
	
	static int[] dp;
	static int[] array;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			array = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N + 1; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}
			
			dp = new int[N + 1];
			dp[1] = array[1];
			
			int answer = 1;
			
			for(int i = 2; i < N + 1; i++) {
				int num = array[i];
				
				if(num > dp[answer]) {
					answer++;
					dp[answer] = num;
					continue;
				}
				int idx = f(num, 1, answer);

				
				dp[idx] = num;
				answer = Math.max(answer, idx);

			}

			System.out.println("#" + test_case + " " + answer);
		}

	}
	
	public static int f(int num, int low, int high) {
		
		int mid = 1;
		while(low <= high) {
			mid = (low + high) / 2;
			
			if(dp[mid] < num) {
				low = mid + 1;
			}
			else if(dp[mid] > num) high = mid - 1;
			else return mid;
		}
		
		if(dp[mid] > num) return mid;
		else return mid + 1;

	}

}