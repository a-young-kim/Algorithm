import java.io.*;
import java.util.*;

/*
 * 문제
 * 가장 긴 증가하는 부분 수열을 구하는 프로그램 작성
 * 
 * 입력
 * 수열 크기 1000 이하
 * 각 수열의 값 1000이하
 * 
 * 출력
 * 수열이 가장 증가하는 부분 수열의 길이
 * 
 * 해결 방안
 * dp를 통해 현재 값보다 가장 작은 값을 찾아서 해당 위치의 값 + 1;
 * 
 * 반례
 * 8
1 8 9 9 9 2 3 4

[o] 4
[x] 3
 * 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] array = new int[N + 1];
		
		for(int i = 1; i < N + 1; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N + 1];
		for(int i = 1; i < N + 1; i++) dp[i] = 1;

		int answer = 1;
		for(int i = 2; i < N + 1; i++) {
			boolean check = false;
			for(int j = i - 1; j >= 1; j--) {
				if(array[i] > array[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
					check = true;
				}
			}
			//if(check) dp[i] ++;
			answer = Math.max(answer,  dp[i]);
		}
		
		System.out.println(answer);
	}
}